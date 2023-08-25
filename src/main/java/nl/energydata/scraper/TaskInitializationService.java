package nl.energydata.scraper;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


import jakarta.annotation.PostConstruct;
import nl.energydata.library.log.LogStatus;
import nl.energydata.library.objectholder.ObjectHolder;
import nl.energydata.library.session.SessionGroup;
import nl.energydata.library.webdriver.WebDriverQueue;

@Service
public class TaskInitializationService{
	
    @Value("${THREAD_COUNT}")
    private int threadCount;

	@Autowired
	private List<ITask> tasks;

    @Autowired
    ScraperPack scraperPack;
    
    public TaskInitializationService() {}

    @PostConstruct
    public void prepareAndStartTasks() { 	
    	
    	List<Future<ObjectHolder>> futures = new ArrayList<>();
    	SessionGroup sessionGroup = scraperPack.getSessionGroupService().create();	
    	scraperPack.getSessionGroupRepository().save(sessionGroup);
    	WebDriverQueue webDriverQueue = scraperPack.getWebDriverQueueService().create(Math.min(tasks.size(), threadCount));   
    	
       	webDriverQueue.getTasksPending().addAndGet(tasks.size()); 
        
        for (ITask task : tasks) {
        	ObjectHolder objectHolder = scraperPack.getObjectHolderService().create();
        	scraperPack.getLogService().setLocalObjectHolder(objectHolder);
        	
        	scraperPack.getObjectHolderService().setSessionGroup(objectHolder, sessionGroup);
        	scraperPack.getObjectHolderService().setSession(objectHolder, scraperPack.getSessionService().create());
        	scraperPack.getObjectHolderService().setWebDriverQueue(objectHolder, webDriverQueue);
        	
        	WebDriver webDriver = scraperPack.getWebDriverQueueService().pollFrom(webDriverQueue); 	
        	
            if (webDriver == null) {
            	webDriver = scraperPack.getWebDriverQueueService().takeFrom(webDriverQueue);
            } 
            
            scraperPack.getObjectHolderService().setWebDriver(objectHolder, webDriver);

            scraperPack.getLogService().clearLocal(); 
            Future<ObjectHolder> future = scraperPack.getExecutor().submitTask(task.getTask(objectHolder, scraperPack));
            futures.add(future);
            
        }    
        
        
        List<LogStatus> failedSessions = new ArrayList<>();
        List<LogStatus> successfullSessions = new ArrayList<>();
        for (Future<ObjectHolder> future : futures) {
            try {
            	ObjectHolder objectHolder = future.get();  
            	LogStatus sessionState = scraperPack.getObjectHolderService().process(objectHolder);
            	if(sessionState != LogStatus.FAILURE) {
            		successfullSessions.add(sessionState);
            	} else {
            		failedSessions.add(sessionState);
            	}  	
            } catch (Exception e) {}
        }
                
        if (successfullSessions.isEmpty() && !failedSessions.isEmpty()) {
            sessionGroup.setStatus(LogStatus.ALL_FAILURE);
        } else if (!successfullSessions.isEmpty() && failedSessions.isEmpty()) {
            sessionGroup.setStatus(LogStatus.ALL_SUCCESS);
        } else {
            sessionGroup.setStatus(LogStatus.PARTIAL_SUCCESS);
        } 
        sessionGroup.setFailedSessions(failedSessions.size());
        sessionGroup.setSuccessfullSessions(successfullSessions.size());
		scraperPack.getSessionGroupRepository().save(sessionGroup);
		
    }

}
