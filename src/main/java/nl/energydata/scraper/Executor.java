package nl.energydata.scraper;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import jakarta.annotation.PreDestroy;


@Service
public class Executor {
	
    private final ExecutorService executorService;

    public Executor(@Value("${THREAD_COUNT}") int threadCount) {
        this.executorService = Executors.newFixedThreadPool(threadCount);
    }

    public <T> Future<T> submitTask(Callable<T> task) {
        return executorService.submit(task);
    }
    
    @PreDestroy
    public void tearDown() {
        executorService.shutdown();
    }
}
