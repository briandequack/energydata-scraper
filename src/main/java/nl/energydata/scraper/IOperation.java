package nl.energydata.scraper;

import nl.energydata.library.objectholder.ObjectHolder;

public interface IOperation {

	void execute(ObjectHolder objectHolder, ScraperPack scraperPack);
}