package nl.energydata.scraper;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import nl.energydata.scraper.providers.Example.ExampleConfiguration;

@Configuration
public class ScraperBeanConfiguration {
	
	private final ExampleConfiguration exampleConfiguration;
	
	

    public ScraperBeanConfiguration(
    	ExampleConfiguration exampleConfiguration
    	) {
    	this.exampleConfiguration = exampleConfiguration;
    }
 
     
    @Bean
    public ITask task1() {  
        MultiThreadedTaskWrapper scrapingService = new MultiThreadedTaskWrapper();
        scrapingService.setScraperConfiguration(exampleConfiguration);
        return scrapingService;
    }
   

}

