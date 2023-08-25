package nl.energydata.scraper.task;

import nl.energydata.library.objectholder.ObjectHolder;
import nl.energydata.scraper.IOperation;
import nl.energydata.scraper.ScraperPack;

public interface CookieAcceptor extends IOperation{
    void acceptCookies(ObjectHolder objectHolder, ScraperPack scraperPack);
    
}
