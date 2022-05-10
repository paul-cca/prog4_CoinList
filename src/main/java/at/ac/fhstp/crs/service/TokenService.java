package at.ac.fhstp.crs.service;

import at.ac.fhstp.crs.api.filters.ITokenFilterStrategy;
import at.ac.fhstp.crs.model.Quote;
import at.ac.fhstp.crs.model.Token;
import at.ac.fhstp.crs.model.TokenChangeInPeriod;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public class TokenService extends AService<Token> implements ITokenService {

  private AService<Quote> quoteService;
  private AService<TokenChangeInPeriod> tokenChangeInPeriodService;

  public TokenService(
    CrudRepository<Token, Integer> repository,
    AService<Quote> quoteService,
    AService<TokenChangeInPeriod> tokenChangeInPeriodService
  ) {
    super(repository);
    this.quoteService = quoteService;
    this.tokenChangeInPeriodService = tokenChangeInPeriodService;
  }

  @Override
  public Token save(Token token) {
    for (Quote quote : token.getQuotes()) {
      for (TokenChangeInPeriod tcp : quote.getChangeInPeriods()) {
        tokenChangeInPeriodService.save(tcp);
      }
      quoteService.save(quote);
    }
    repository.save(token);
    return token;
  }

  @Override
  public List<Token> getAllFilteredBy(ITokenFilterStrategy strategy) {
    return strategy.filterTokens(getAll());
  }
}
