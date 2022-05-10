package at.ac.fhstp.crs.api;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class PeriodicApiFetcher {

    private IAPIConnector apiConnector;
    public PeriodicApiFetcher(IAPIConnector apiConnector) {
        this.apiConnector = apiConnector;
    }

    @Scheduled(fixedRate = 1000 * 60)
    public void fetchData() {
        System.out.println("Fetching API");
        // deleting all data
    }
}
