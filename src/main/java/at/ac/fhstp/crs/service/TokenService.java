package at.ac.fhstp.crs.service;

import at.ac.fhstp.crs.api.filters.ITokenFilterStrategy;
import at.ac.fhstp.crs.model.Quote;
import at.ac.fhstp.crs.model.Token;
import at.ac.fhstp.crs.model.TokenChangeInPeriod;
import at.ac.fhstp.crs.repository.TokenRepository;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@ApplicationScoped
public class TokenService extends AService<Token> implements ITokenService {

  private TokenRepository tokenRepository;
  private QuoteService quoteService;
  private TokenChangeInPeriodService tokenChangeInPeriodService;

  public TokenService(
    TokenRepository tokenRepository,
    QuoteService quoteService,
    TokenChangeInPeriodService tokenChangeInPeriodService
  ) {
    this.tokenRepository = tokenRepository;
    this.quoteService = quoteService;
    this.tokenChangeInPeriodService = tokenChangeInPeriodService;
  }

  // @Override
  public Token save(Token token) {
    for (Quote quote : token.getQuotes()) {
      for (TokenChangeInPeriod tcp : quote.getChangeInPeriods()) {
        tokenChangeInPeriodService.save(tcp);
      }
      quoteService.save(quote);
    }
    repository.persist(token);
    return token;
  }

  public List<Token> getAllFilteredBy(ITokenFilterStrategy strategy) {
    return strategy.filterTokens(tokenRepository.findAll().list());
  }

  public void updateTokenBySymbol(Token token) {
    Optional<Token> org = tokenRepository.findOneBySymbol(token.getSymbol());
    if (org.isPresent()) {
      org.get().update(token);
      repository.persist(org.get());
    }
  }

  public Optional<Token> findBySymbol(String symbol) {
    return tokenRepository.findOneBySymbol(symbol);
  }

  @Inject
  //@Override
  public void setRepository(TokenRepository repository) {
    this.repository = repository;
  }

  @Override
  public void setRepository(PanacheRepository<Token> repository) {
    this.repository = repository;
  }
}
