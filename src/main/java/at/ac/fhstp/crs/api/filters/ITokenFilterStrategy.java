package at.ac.fhstp.crs.api.filters;

import java.util.List;

import at.ac.fhstp.crs.model.Token;

public interface ITokenFilterStrategy {
  List<Token> filterTokens(List<Token> tokens);
}
