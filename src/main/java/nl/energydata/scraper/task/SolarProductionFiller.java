package nl.energydata.scraper.task;

import nl.energydata.library.objectholder.ObjectHolder;
import nl.energydata.scraper.IOperation;
import nl.energydata.scraper.ScraperPack;

public interface SolarProductionFiller extends IOperation{
	void fillProduction(ObjectHolder objectHolder, ScraperPack scraperPack);
}