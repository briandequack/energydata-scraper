package nl.energydata.scraper.providers.Example;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;
import nl.energydata.library.dataprovider.DataProvider;
import nl.energydata.library.dataprovider.EnergyDataProviderService;
import nl.energydata.scraper.IOperation;
import nl.energydata.scraper.ScraperConfiguration;
import nl.energydata.scraper.task.AddressFiller;
import nl.energydata.scraper.task.CookieAcceptor;
import nl.energydata.scraper.task.Fixed1YPlanSelectorAndExtractor;
import nl.energydata.scraper.task.Fixed3YPlanSelectorAndExtractor;
import nl.energydata.scraper.task.SolarProductionFiller;
import nl.energydata.scraper.task.UsageFiller;
import nl.energydata.scraper.task.VariablePlanSelectorAndExtractor;

@Getter
@Setter
@Component
public class ExampleConfiguration extends ScraperConfiguration{
	
	private final String DATA_PROVIDER_NAME = "Example";
    private final String URL = "https://www.example.com";
	private final List<IOperation> operations;
	private final EnergyDataProviderService energyDataProviderService;
	private DataProvider dataProvider;
	   
    @Autowired
    public ExampleConfiguration(
    		EnergyDataProviderService energyDataProviderService,
	    	@Qualifier("exampleCookieAcceptor") CookieAcceptor step1,
	    	@Qualifier("exampleAddressFiller") AddressFiller step2,
	    	@Qualifier("exampleUsageFiller") UsageFiller step3,
	    	@Qualifier("exampleSolarProductionFiller") SolarProductionFiller step4,
	    	@Qualifier("exampleFixed1YPlanSelectorAndExtractor") Fixed1YPlanSelectorAndExtractor step5,
	    	@Qualifier("exampleFixed3YPlanSelectorAndExtractor") Fixed3YPlanSelectorAndExtractor step6,
	    	@Qualifier("exampleVariablePlanSelectorAndExtractor") VariablePlanSelectorAndExtractor step7
    	) {
    	this.operations = List.of(step1,step2, step3, step4, step5, step6, step7);
		this.energyDataProviderService = energyDataProviderService;   
    }

	@Override
	protected void initDataProvider() {
    	this.dataProvider =  energyDataProviderService.createEnergyDataProvider(DATA_PROVIDER_NAME);	

	}
    
}