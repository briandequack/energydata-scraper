package nl.energydata.scraper.task;

import nl.energydata.library.objectholder.ObjectHolder;
import nl.energydata.scraper.IOperation;
import nl.energydata.scraper.ScraperPack;

public interface VariablePlanSelectorAndExtractor extends IOperation{
	
	void selectAndExtractPlan(ObjectHolder objectHolder, ScraperPack scraperPack);
}