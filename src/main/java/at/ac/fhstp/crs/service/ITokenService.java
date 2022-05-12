package at.ac.fhstp.crs.service;

import java.util.List;
import java.util.Optional;

import at.ac.fhstp.crs.api.filters.ITokenFilterStrategy;
import at.ac.fhstp.crs.model.Token;

public interface ITokenService {
   List<Token> getAllFilteredBy(ITokenFilterStrategy strategy);
   void updateTokenBySymbol(Token token); 
   Optional<Token> findBySymbol(String symbol);
}
