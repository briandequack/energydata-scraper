package nl.energydata.scraper.providers.Example;

import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Component;

import nl.energydata.library.datacontainer.EnergyUtilityDataContainer;
import nl.energydata.library.objectholder.ObjectHolder;
import nl.energydata.library.webdriver.WebDriverService;
import nl.energydata.scraper.ScraperPack;
import nl.energydata.scraper.task.CookieAcceptor;
import nl.energydata.scraper.util.DebugUtils;

@Component("vattenfallCookieAcceptor")
public class ExampleCookieAcceptor implements CookieAcceptor {
    
    public ExampleCookieAcceptor() {}

	@Override
	public void acceptCookies(ObjectHolder objectHolder, ScraperPack scraperPack) {
		
		DebugUtils.randomSleep(4,7);
		WebElement cookieButton = scraperPack.getWebDriverService().getElementByxpath("//a[normalize-space()='Ja, ik accepteer cookies']", "cookieButton");
		scraperPack.getWebDriverService().clickElement(cookieButton, "cookieButton");	
		

	}

	@Override
	public void execute(ObjectHolder objectHolder, ScraperPack scraperPack) {
		acceptCookies(objectHolder, scraperPack);
		
	}
}