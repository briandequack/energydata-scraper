package nl.energydata.scraper.providers.Example;

import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import nl.energydata.library.datacontainer.DurationCategory;
import nl.energydata.library.datacontainer.EnergyUtilityDataContainer;
import nl.energydata.library.datacontainer.RateType;
import nl.energydata.library.datacontainer.UtilityType;
import nl.energydata.library.energytax.EnergyTaxService;
import nl.energydata.library.objectholder.ObjectHolder;
import nl.energydata.library.webdriver.WebDriverService;
import nl.energydata.scraper.ScraperPack;
import nl.energydata.scraper.task.Fixed1YPlanSelectorAndExtractor;
import nl.energydata.scraper.util.DebugUtils;

import java.math.BigDecimal;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;

@Component("example1YPlanSelectorAndExtractor")
public class Example1YPlanSelectorAndExtractor implements Fixed1YPlanSelectorAndExtractor{
	
	@Autowired
    protected EnergyTaxService energyTaxService;

    public Example1YPlanSelectorAndExtractor() {}
    
	@Override
	public void selectAndExtractPlan(ObjectHolder objectHolder, ScraperPack scraperPack) {
		
		/*		 
		EnergyUtilityDataContainer dataContainer = new EnergyUtilityDataContainer();
		dataContainer.setDurationCategory(DurationCategory.ONE_YEAR);
		
		dataContainer.setElectricityOnPeakRatePerKwh(elecOnPeakRate);
		dataContainer.setElectricityOffPeakRatePerKwh(elecOffPeakRate);
		dataContainer.setElectricityFixedCosts(elecFixedCosts);
		dataContainer.setProducedElectricityRatePerKwh(elecOnPeakRate);
		dataContainer.setGasRatePerM3(gasRate);
		dataContainer.setGasFixedCosts(gasFixedCosts);
	
		objectHolder.addContainer(dataContainer);
		*/
        
	}

	
	@Override
	public void execute(ObjectHolder objectHolder, ScraperPack scraperPack) {
		selectAndExtractPlan(objectHolder, scraperPack);
		
	}
	
	 public static void printWebElement(WebElement webElement) {
	    
	}





}
