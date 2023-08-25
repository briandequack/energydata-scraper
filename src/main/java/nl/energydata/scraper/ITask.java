package nl.energydata.scraper;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;

import nl.energydata.library.objectholder.ObjectHolder;
import nl.energydata.library.session.Session;
import nl.energydata.library.session.SessionGroup;
import nl.energydata.library.webdriver.WebDriverQueue;
import nl.energydata.library.webdriver.WebDriverQueueService;
import nl.energydata.library.webdriver.WebDriverService;

public interface ITask {
		
    Callable<ObjectHolder> getTask(ObjectHolder objectHolder, ScraperPack scraperPack);
}