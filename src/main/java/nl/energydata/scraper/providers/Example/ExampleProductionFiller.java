package nl.energydata.scraper.providers.Example;

import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import nl.energydata.library.objectholder.ObjectHolder;
import nl.energydata.scraper.ScraperPack;
import nl.energydata.scraper.task.SolarProductionFiller;
import nl.energydata.scraper.util.DebugUtils;

@Component("vattenfallSolarProductionFiller")
public class ExampleProductionFiller implements SolarProductionFiller{
	
    @Value("${ELECTRICTY_PRODUCTION_IN_KWH}")
    protected String ELECTRICTY_PRODUCTION_IN_KWH;
    
	@Override
	public void fillProduction(ObjectHolder objectHolder, ScraperPack scraperPack) {

		DebugUtils.randomSleep(1,3);
		WebElement hasSolarButton = scraperPack.getWebDriverService().getElementBycssSelector("sf-card-input[data-test='solar-usage-yes']", "hasSolarButton");
		scraperPack.getWebDriverService().clickElement(hasSolarButton, "hasSolarButton");

		DebugUtils.randomSleep(1,3);
		WebElement solarInput = scraperPack.getWebDriverService().getElementBycssSelector("input[id='redelivery']", "solarInput");
		scraperPack.getWebDriverService().fillElement(solarInput, ELECTRICTY_PRODUCTION_IN_KWH, "solarInput");

		DebugUtils.randomSleep(1,3);
		WebElement submitButton = scraperPack.getWebDriverService().getElementBycssSelector("button[data-test='solar-usage-submit']", "submitButton");
		scraperPack.getWebDriverService().clickElement(submitButton, "submitButton");
	
	}
	@Override
	public void execute(ObjectHolder objectHolder, ScraperPack scraperPack) {
		fillProduction(objectHolder, scraperPack);	
	}

}
