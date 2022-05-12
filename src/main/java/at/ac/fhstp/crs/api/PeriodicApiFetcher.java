package at.ac.fhstp.crs.api;

import at.ac.fhstp.crs.model.Quote;
import at.ac.fhstp.crs.model.Token;
import at.ac.fhstp.crs.model.TokenChangeInPeriod;
import at.ac.fhstp.crs.service.AService;
//import at.ac.fhstp.crs.service.ITokenService;
import com.harium.dotenv.Env;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

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
