package at.ac.fhstp.crs.api;

import java.util.List;

import at.ac.fhstp.crs.model.Token;

public interface IAPIConnector {
   List<Token> getTokens(int amount);
}