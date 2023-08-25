package nl.energydata.scraper.providers.Example;

import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import nl.energydata.library.objectholder.ObjectHolder;
import nl.energydata.library.webdriver.WebDriverService;
import nl.energydata.scraper.ScraperPack;
import nl.energydata.scraper.task.UsageFiller;
import nl.energydata.scraper.util.DebugUtils;

@Component("vattenfallUsageFiller")
public class ExampleUsageFiller implements UsageFiller {

    @Value("${ELECTRICTY_USAGE_ON_PEAK_IN_KWH}")
    protected String ELECTRICTY_USAGE_ON_PEAK_IN_KWH;
    
    @Value("${ELECTRICTY_USAGE_OFF_PEAK_IN_KWH}")
    protected String ELECTRICTY_USAGE_OFF_PEAK_IN_KWH;
    
    @Value("${GAS_USAGE_IN_M3}")
    protected String GAS_USAGE_IN_M3;
    
    public ExampleUsageFiller() {}

	@Override
	public void fillUsage(ObjectHolder objectHolder, ScraperPack scraperPack) {
	
		DebugUtils.randomSleep(1,3);
		WebElement elecAndGasButton = scraperPack.getWebDriverService().getElementByxpath("(//div[normalize-space()='Kiezen'])[2]", "cookieButton");
		scraperPack.getWebDriverService().clickElement(elecAndGasButton, "elecAndGasButton");	
		
		DebugUtils.randomSleep(1,3);		
		WebElement onPeakInput = scraperPack.getWebDriverService().getElementBycssSelector("input[id='singleUsage']", "onPeakInput");
		scraperPack.getWebDriverService().fillElement(onPeakInput, ELECTRICTY_USAGE_ON_PEAK_IN_KWH, "onPeakInput");
		
		DebugUtils.randomSleep(1,3);
		WebElement offPeakInput = scraperPack.getWebDriverService().getElementBycssSelector("input[id='doubleUsage']", "offPeakInput");
		scraperPack.getWebDriverService().fillElement(offPeakInput, ELECTRICTY_USAGE_OFF_PEAK_IN_KWH, "offPeakInput");
		
		DebugUtils.randomSleep(1,3);
		WebElement gasInput = scraperPack.getWebDriverService().getElementBycssSelector("input[id='gasUsage']", "gasInput");
		scraperPack.getWebDriverService().fillElement(gasInput, GAS_USAGE_IN_M3, "gasInput");
		
		DebugUtils.randomSleep(1,3);
		WebElement dropDownButton = scraperPack.getWebDriverService().getElementByxpath("//span[normalize-space()='Enkeltarief (meest gekozen)']","dropDownButton");
		scraperPack.getWebDriverService().clickElement(dropDownButton, "dropDownButton");	
		
		DebugUtils.randomSleep(1,3);
		WebElement doubleRateButton = scraperPack.getWebDriverService().getElementByxpath("//label[normalize-space()='Dubbeltarief']","doubleRateButton");
		scraperPack.getWebDriverService().clickElement(doubleRateButton, "doubleRateButton");	

		DebugUtils.randomSleep(1,3);
		WebElement submitButton = scraperPack.getWebDriverService().getElementBycssSelector("button[data-test='select-usage-submit']", "submitButton");
		scraperPack.getWebDriverService().clickElement(submitButton, "submitButton");
		
	}
	
	@Override
	public void execute(ObjectHolder objectHolder, ScraperPack scraperPack) {
		fillUsage(objectHolder, scraperPack);	
	}
}
