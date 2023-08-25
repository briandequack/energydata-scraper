package nl.energydata.scraper.providers.Example;

import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import nl.energydata.library.objectholder.ObjectHolder;
import nl.energydata.scraper.ScraperPack;
import nl.energydata.scraper.task.AddressFiller;
import nl.energydata.scraper.util.DebugUtils;

@Component("exampleAddressFiller")
public class ExampleAddressFiller implements AddressFiller {
    
    @Value("${POSTAL_CODE}")
    protected String POSTAL_CODE;
    
    @Value("${HOUSE_NUMBER}")
    protected String HOUSE_NUMBER;

    
    public ExampleAddressFiller() {
    }

	@Override
	public void fillAddress(ObjectHolder objectHolder, ScraperPack scraperPack) {
	
		
	}

	@Override
	public void execute(ObjectHolder objectHolder, ScraperPack scraperPack) {
		fillAddress(objectHolder, scraperPack);
		
	}
}
