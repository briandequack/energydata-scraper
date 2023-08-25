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
import nl.energydata.scraper.ScraperPack;
import nl.energydata.scraper.task.Fixed3YPlanSelectorAndExtractor;
import nl.energydata.scraper.util.DebugUtils;

@Component("example3YPlanSelectorAndExtractor")
public class Example3YPlanSelectorAndExtractor implements Fixed3YPlanSelectorAndExtractor{
	
	@Autowired
    protected EnergyTaxService energyTaxService;

	@Override
	public void execute(ObjectHolder objectHolder, ScraperPack scraperPack) {
		selectAndExtractPlan(objectHolder, scraperPack);
		
	}

	@Override
	public void selectAndExtractPlan(ObjectHolder objectHolder, ScraperPack scraperPack) {
		
		/*
		EnergyUtilityDataContainer dataContainer = new EnergyUtilityDataContainer();
		dataContainer.setDurationCategory(DurationCategory.THREE_YEARS);
		
		dataContainer.setElectricityOnPeakRatePerKwh(elecOnPeakRate);
		dataContainer.setElectricityOffPeakRatePerKwh(elecOffPeakRate);
		dataContainer.setElectricityFixedCosts(elecFixedCosts);
		dataContainer.setProducedElectricityRatePerKwh(elecOnPeakRate);
		dataContainer.setGasRatePerM3(gasRate);
		dataContainer.setGasFixedCosts(gasFixedCosts);
	
		objectHolder.addContainer(dataContainer);
        */
	
	}

}
