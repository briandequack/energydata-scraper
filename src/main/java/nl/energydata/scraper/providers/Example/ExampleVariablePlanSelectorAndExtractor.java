package nl.energydata.scraper.providers.Example;

import java.math.BigDecimal;

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
import nl.energydata.scraper.task.VariablePlanSelectorAndExtractor;
import nl.energydata.scraper.util.DebugUtils;

@Component("vattenFallVariablePlanSelectorAndExtractor")
public class ExampleVariablePlanSelectorAndExtractor implements VariablePlanSelectorAndExtractor{

	@Autowired
    protected EnergyTaxService energyTaxService;

	@Override
	public void selectAndExtractPlan(ObjectHolder objectHolder, ScraperPack scraperPack) {
		
		EnergyUtilityDataContainer dataContainer = new EnergyUtilityDataContainer();
		dataContainer.setDurationCategory(DurationCategory.OPEN_ENDED);
		
		DebugUtils.randomSleep(1,3);
		WebElement selectPlan = scraperPack.getWebDriverService().getElementByxpath("(//button[normalize-space()='Kiezen'])[2]", "selectPlan");
		scraperPack.getWebDriverService().clickElement(selectPlan, "selectPlan");	
		DebugUtils.randomSleep(4,6);
		
		DebugUtils.randomSleep(1,2);
		WebElement openElec = scraperPack.getWebDriverService().getElementByxpath("//span[normalize-space()='Stroom']","openElec");
		scraperPack.getWebDriverService().clickElement(openElec, "openElec");
		
		DebugUtils.randomSleep(1,2);
		WebElement openElecRates = scraperPack.getWebDriverService().getElementByxpath("//span[normalize-space()='Totaal variabele kosten']","openElecRates");
		scraperPack.getWebDriverService().clickElement(openElecRates, "openElecRates");
		
		DebugUtils.randomSleep(1,2);
		WebElement openElecOnPeak = scraperPack.getWebDriverService().getElementByxpath("//span[normalize-space()='Normaaltarief']","openElecOnPeak");
		scraperPack.getWebDriverService().clickElement(openElecOnPeak, "openElecOnPeak");
		
		DebugUtils.randomSleep(1,2);
		WebElement openElecOffPeak = scraperPack.getWebDriverService().getElementByxpath("//span[normalize-space()='Daltarief']","openElecOffPeak");
		scraperPack.getWebDriverService().clickElement(openElecOffPeak, "openElecOffPeak");
		
		DebugUtils.randomSleep(1,2);
		WebElement openElecFixedCosts = scraperPack.getWebDriverService().getElementByxpath("//span[normalize-space()='Totaal vaste kosten']","openElecFixedCosts");
		scraperPack.getWebDriverService().clickElement(openElecFixedCosts, "openElecFixedCosts");
		
		DebugUtils.randomSleep(1,3);
		WebElement elecRates = scraperPack.getWebDriverService().getElementBycssSelector("vfc-collapsible[class*='ElectricityProduct']", "hasSolarButton");
		//scraperPack.getWebDriverService().printWordsArray(elecRates);
		
		// ELEC
		scraperPack.getWebDriverService().matchStringAtIndex(elecRates, 0, "Stroom");
	    scraperPack.getWebDriverService().matchStringAtIndex(elecRates, 8, "Normaaltarief");
		BigDecimal elecOnPeakRateInclAll = scraperPack.getWebDriverService().getBigDecimalAtIndex(elecRates, 22);
		scraperPack.getWebDriverService().matchStringAtIndex(elecRates, 38, "Daltarief");
		BigDecimal elecOffPeakRateInclAll = scraperPack.getWebDriverService().getBigDecimalAtIndex(elecRates, 52);
		scraperPack.getWebDriverService().matchStringAtIndex(elecRates, 74, "leveringskosten");
		BigDecimal elecFixedCostsInclAll = scraperPack.getWebDriverService().getBigDecimalAtIndex(elecRates, 83);
		
		BigDecimal elecOnPeakRate = energyTaxService.elecRateRemoveAllTax(elecOnPeakRateInclAll);	
		BigDecimal elecOffPeakRate = energyTaxService.elecRateRemoveAllTax(elecOffPeakRateInclAll);
		BigDecimal elecFixedCosts = energyTaxService.fixedCostsRemoveTax(elecFixedCostsInclAll);
		System.out.println("elecOnPeakRate" + elecOnPeakRate);
		System.out.println("elecOffPeakRate" + elecOffPeakRate);
		System.out.println("elecFixedCosts" + elecFixedCosts);
		
		DebugUtils.randomSleep(1,3);
		WebElement openGas = scraperPack.getWebDriverService().getElementByxpath("//span[normalize-space()='Gas']","openGas");
		scraperPack.getWebDriverService().clickElement(openGas, "openGas");
	    
		DebugUtils.randomSleep(1,2);
		WebElement openGasRates = scraperPack.getWebDriverService().getElementByxpath("//span[normalize-space()='Totaal variabele kosten']",1,"openGasRates");
		scraperPack.getWebDriverService().clickElement(openGasRates, "openGasRates");
		
		DebugUtils.randomSleep(1,2);
		WebElement openGasFixedCosts = scraperPack.getWebDriverService().getElementByxpath("//span[normalize-space()='Totaal vaste kosten']",1,"openGasFixedCosts");
		scraperPack.getWebDriverService().clickElement(openGasFixedCosts, "openGasFixedCosts");

		DebugUtils.randomSleep(1,3);
		WebElement gasRates = scraperPack.getWebDriverService().getElementBycssSelector("vfc-collapsible[class*='GasProduct']", "hasSolarButton");
		//scraperPack.getWebDriverService().printWordsArray(gasRates);
        
		// GAS
		scraperPack.getWebDriverService().matchStringAtIndex(gasRates, 0, "Gas");
		scraperPack.getWebDriverService().matchStringAtIndex(gasRates, 5, "kosten");
		BigDecimal gasRateInclAll = scraperPack.getWebDriverService().getBigDecimalAtIndex(gasRates, 7);
		scraperPack.getWebDriverService().matchStringAtIndex(gasRates, 41, "leveringskosten");
		BigDecimal gasFixedCostsInclAll = scraperPack.getWebDriverService().getBigDecimalAtIndex(gasRates, 50);
		
		BigDecimal gasRate = energyTaxService.gasRateRemoveAllTax(gasRateInclAll);	
		BigDecimal gasFixedCosts = energyTaxService.fixedCostsRemoveTax(gasFixedCostsInclAll);
		System.out.println("gasRate" + gasRate);
		System.out.println("gasFixedCosts" + gasFixedCosts);
		
		dataContainer.setElectricityOnPeakRatePerKwh(elecOnPeakRate);
		dataContainer.setElectricityOffPeakRatePerKwh(elecOffPeakRate);
		dataContainer.setElectricityFixedCosts(elecFixedCosts);
		dataContainer.setProducedElectricityRatePerKwh(elecOnPeakRate);
		dataContainer.setGasRatePerM3(gasRate);
		dataContainer.setGasFixedCosts(gasFixedCosts);
	
		objectHolder.addContainer(dataContainer);
		
		DebugUtils.randomSleep(5,10);
	}
	 
	@Override
	public void execute(ObjectHolder objectHolder, ScraperPack scraperPack) {
		selectAndExtractPlan(objectHolder, scraperPack);
		
	}


}
