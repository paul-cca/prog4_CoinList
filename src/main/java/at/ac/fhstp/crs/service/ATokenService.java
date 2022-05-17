package at.ac.fhstp.crs.service;

import java.util.List;
import java.util.Optional;

import at.ac.fhstp.crs.api.filters.ITokenFilterStrategy;
import at.ac.fhstp.crs.model.Token;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

public abstract class ATokenService extends AService<Token> {
   public abstract List<Token> getAllFilteredBy(ITokenFilterStrategy strategy);
   public abstract void updateTokenBySymbol(Token token); 
   public abstract Optional<Token> findBySymbol(String symbol);
}
