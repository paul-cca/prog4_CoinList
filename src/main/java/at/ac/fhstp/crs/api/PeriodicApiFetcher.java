package at.ac.fhstp.crs.api;

import at.ac.fhstp.crs.model.Quote;
import at.ac.fhstp.crs.model.Token;
import at.ac.fhstp.crs.model.TokenChangeInPeriod;
import at.ac.fhstp.crs.service.AService;
import com.harium.dotenv.Env;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

@Configuration
@EnableScheduling
public class PeriodicApiFetcher {

  private IAPIConnector apiConnector;
  //private ITokenService detailedTokenService;
  private AService<Token> tokenService;
  private AService<Quote> quoteService;
  private AService<TokenChangeInPeriod> tokenChangeInPeriodService;
  private int tokenAmountToFetch;

  public PeriodicApiFetcher(
    IAPIConnector apiConnector,
    //ITokenService detailedTokenService,
    AService<Quote> quoteService,
    AService<Token> tokenService,
    AService<TokenChangeInPeriod> tokenChangeInPeriodService
  ) {
    this.apiConnector = apiConnector;
    //this.detailedTokenService = detailedTokenService;
    this.quoteService = quoteService;
    this.tokenService = tokenService;
    this.tokenChangeInPeriodService = tokenChangeInPeriodService;

    String amount = Env.get("TOKEN_AMOUNT");
    if (amount != null) {
      tokenAmountToFetch = Integer.parseInt(amount);
    } else {
      tokenAmountToFetch = 10;
    }
  }

  @Scheduled(fixedRate = 1000 * 600)
  public void fetchData() {
    // TODO: adopt relations to allow deletion of quotes before token

    tokenService.deleteAll();

    List<Token> tokens = apiConnector.getTokens(tokenAmountToFetch);
    if (tokens != null) {
      for (Token t : tokens) {
        for (Quote quote : t.getQuotes()) {
          for (TokenChangeInPeriod tcp : quote.getChangeInPeriods()) {
            tokenChangeInPeriodService.save(tcp);
          }
          quoteService.save(quote);
        }
        tokenService.save(t);
      }
    }
  }
}
