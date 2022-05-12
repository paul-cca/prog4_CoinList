package at.ac.fhstp.crs.service;

import at.ac.fhstp.crs.api.filters.ITokenFilterStrategy;
import at.ac.fhstp.crs.model.Quote;
import at.ac.fhstp.crs.model.Token;
import at.ac.fhstp.crs.model.TokenChangeInPeriod;
import at.ac.fhstp.crs.repository.TokenRepository;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

@Component
public class TokenService extends AService<Token> implements ITokenService {

  private TokenRepository tokenRepository;
  private AService<Quote> quoteService;
  private AService<TokenChangeInPeriod> tokenChangeInPeriodService;

  public TokenService(
    TokenRepository repository,
    AService<Quote> quoteService,
    AService<TokenChangeInPeriod> tokenChangeInPeriodService
  ) {
    super(repository);
    this.tokenRepository = repository;
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

  @Override
  public void updateTokenBySymbol(Token token) {
    Optional<Token> org = tokenRepository.findOneBySymbol(token.getSymbol());
    if (org.isPresent()) {
      org.get().update(token);
      repository.save(org.get());
    }
  }

  @Override
  public Optional<Token> findBySymbol(String symbol) {
    return tokenRepository.findOneBySymbol(symbol);
  }
}
