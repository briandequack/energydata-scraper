package nl.energydata.scraper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;



@SpringBootApplication(scanBasePackages = {"nl.energydata.scraper", "nl.energydata.library"})
@EntityScan(basePackages = {"nl.energydata.scraper", "nl.energydata.library"})
@EnableJpaRepositories(basePackages = {"nl.energydata.library"})
public class ScraperApplication {

	public static void main(String[] args) {
		SpringApplication.run(ScraperApplication.class, args);
	}

}
