package at.ac.fhstp.crs.api;

import at.ac.fhstp.crs.model.Quote;
import at.ac.fhstp.crs.model.Token;
import at.ac.fhstp.crs.model.TokenChangeInPeriod;
import at.ac.fhstp.crs.service.AService;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class PeriodicApiFetcher {

    private IAPIConnector apiConnector;
    private AService<Token> tokenService;
    private AService<Quote> quoteService;
    private AService<TokenChangeInPeriod> tokenChangeInPeriodService;


    public PeriodicApiFetcher(IAPIConnector apiConnector, AService<Token> tokenService, AService<Quote> quoteService, AService<TokenChangeInPeriod> tokenChangeInPeriodService) {
        this.apiConnector = apiConnector;
        this.tokenService = tokenService;
        this.quoteService = quoteService;
        this.tokenChangeInPeriodService = tokenChangeInPeriodService;
    }

    @Scheduled(fixedRate = 1000 * 60)
    public void fetchData() {
        tokenService.deleteAll();

        for (Token token:apiConnector.getTokens(100)) {
            System.out.println("Saving: " + token.toString());
            for(Quote quote: token.getQuotes()) {
                for (TokenChangeInPeriod tcp: quote.getChangeInPeriods()) {
                    tokenChangeInPeriodService.save(tcp);
                }
                quoteService.save(quote);
            }
            tokenService.save(token);
        }
    }
}
