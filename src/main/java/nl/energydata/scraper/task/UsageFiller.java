package nl.energydata.scraper.task;

import nl.energydata.library.objectholder.ObjectHolder;
import nl.energydata.scraper.IOperation;
import nl.energydata.scraper.ScraperPack;

public interface UsageFiller extends IOperation{
	void fillUsage(ObjectHolder objectHolder, ScraperPack scraperPack);
}