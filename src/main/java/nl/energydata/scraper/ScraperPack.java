package nl.energydata.scraper;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.Getter;
import lombok.Setter;
import nl.energydata.library.datacontainer.EnergyUtilityDataContainerService;
import nl.energydata.library.log.ILogService;
import nl.energydata.library.log.LogService;
import nl.energydata.library.objectholder.ObjectHolderService;
import nl.energydata.library.session.Session;
import nl.energydata.library.session.SessionGroup;
import nl.energydata.library.session.SessionGroupRepository;
import nl.energydata.library.session.SessionGroupService;
import nl.energydata.library.session.SessionService;
import nl.energydata.library.webdriver.WebDriverQueue;
import nl.energydata.library.webdriver.WebDriverQueueService;
import nl.energydata.library.webdriver.WebDriverService;

@Getter
@Setter
@Service
public class ScraperPack {
	
	
	
    @Autowired
    private Executor executor;
	
    @Autowired
    private WebDriverService webDriverService;

	@Autowired
	private WebDriverQueueService webDriverQueueService;
	
    @Autowired
    private ILogService logService;
    
    @Autowired
    private SessionGroupService sessionGroupService;
    
    @Autowired
    private SessionService sessionService;
    
    @Autowired 
    private ObjectHolderService objectHolderService;
    
    @Autowired
    private EnergyUtilityDataContainerService energyUtilityDataContainerService;
    
    @Autowired
    private SessionGroupRepository sessionGroupRepository;

}
