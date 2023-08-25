package nl.energydata.scraper;
 
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;

import org.openqa.selenium.Cookie;

import nl.energydata.library.objectholder.ObjectHolder;

public class MultiThreadedTaskWrapper implements ITask{
    private ScraperConfiguration scraperConfiguration;
    private List<IOperation> operations;

    public MultiThreadedTaskWrapper( ) {}

     
    public void setScraperConfiguration(ScraperConfiguration scraperConfiguration) {
        this.scraperConfiguration = scraperConfiguration;
        this.operations = scraperConfiguration.getOperations();
    }
  
    public Callable<ObjectHolder> getTask(ObjectHolder objectHolder, ScraperPack scraperPack) {
        return () -> {
        	
        	scraperPack.getLogService().setLocalObjectHolder(objectHolder);
        	objectHolder.setDataProvider(scraperConfiguration.getDataProvider());
        	
        	scraperPack.getWebDriverQueueService().decrementTasksPending(objectHolder.getWebDriverQueue());
        	scraperPack.getWebDriverService().setLocalWebDriver(objectHolder.getWebDriver());
        	
        	scraperPack.getLogService().executeSafeWithLog("Get URL", () -> {
        		scraperPack.getWebDriverService().getLocalWebDriver().get(scraperConfiguration.getURL());
        		return null;
        	});
        	
        	try {        		
	        	for (IOperation operation : operations) { 
	        		scraperPack.getLogService().executeSafeWithLog(String.format("Execute operation", scraperConfiguration.getURL()), () -> { 
	        			operation.execute(objectHolder, scraperPack); 
	        		});
	        	}
        	}finally {           
        		scraperPack.getWebDriverService().clearLocal();
        		scraperPack.getWebDriverQueueService().returnObject(objectHolder.getWebDriverQueue(), objectHolder.getWebDriver());
        		scraperPack.getLogService().clearLocal();
        	}
            return objectHolder;
        };
    }
}
