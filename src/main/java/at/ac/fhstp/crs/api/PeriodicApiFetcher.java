package at.ac.fhstp.crs.api;

import at.ac.fhstp.crs.model.Quote;
import at.ac.fhstp.crs.model.Token;
import at.ac.fhstp.crs.model.TokenChangeInPeriod;
import at.ac.fhstp.crs.service.AService;
import com.harium.dotenv.Env;
import io.quarkus.scheduler.Scheduled;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class PeriodicApiFetcher {

    private IAPIConnector apiConnector;

    private AService<Token> tokenService;
    private AService<Quote> quoteService;
    private AService<TokenChangeInPeriod> tokenChangeInPeriodService;
    private int tokenAmountToFetch;

    public PeriodicApiFetcher(
            IAPIConnector apiConnector,
            AService<Token> tokenService,
            AService<Quote> quoteService,
            AService<TokenChangeInPeriod> tokenChangeInPeriodService
    ) {
        this.apiConnector = apiConnector;
        this.tokenService = tokenService;
        this.quoteService = quoteService;
        this.tokenChangeInPeriodService = tokenChangeInPeriodService;

        String amount = Env.get("TOKEN_AMOUNT");
        if (amount != null) {
            tokenAmountToFetch = Integer.parseInt(amount);
        } else {
            tokenAmountToFetch = 10;
        }
    }

    @Transactional
    @Scheduled(every = "600s")
    public void fetchData() {
        // TODO: adopt relations to allow deletion of quotes before token

        // tokenService.deleteAll();
        List<Token> tokens = apiConnector.getTokens(tokenAmountToFetch);
        if (tokens != null) {
            for (Token t : apiConnector.getTokens(tokenAmountToFetch)) {
      /*
      // if token is present delete all quotes as these will have changed surely
      Optional<Token> token = detailedTokenService.findBySymbol(t.getSymbol());
      if (token.isPresent()) {
        for (Quote quote : token.get().getQuotes()) {
          for (TokenChangeInPeriod tcp : quote.getChangeInPeriods()) {
            //tokenChangeInPeriodService.delete(tcp.getId());
          }
          quoteService.delete(quote.getId());
        }
        detailedTokenService.updateTokenBySymbol(t);
      }*/
                // token is not present it will be created
                //else {
                for (Quote quote : t.getQuotes()) {
                    for (TokenChangeInPeriod tcp : quote.getChangeInPeriods()) {
                        tokenChangeInPeriodService.save(tcp);
                    }
                    quoteService.save(quote);
                }
                tokenService.save(t);
                //}
            }
        }
    }
}
