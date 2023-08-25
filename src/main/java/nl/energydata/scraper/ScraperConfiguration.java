package nl.energydata.scraper;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.Setter;
import nl.energydata.library.dataprovider.DataProvider;

@Getter
@Setter
@Service
public abstract class ScraperConfiguration {

	protected DataProvider dataProvider;
	
	protected String URL;
	
	protected String DATA_PROVIDER_NAME;

	protected List<IOperation> operations;
	
	@PostConstruct
	protected void init() {
		initDataProvider();
	}
  
    protected abstract void initDataProvider();
   

}
