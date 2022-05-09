package at.ac.fhstp.crs.api;

import java.util.List;

import at.ac.fhstp.crs.dto.Token;

public interface IAPIConnector {
   List<Token> getTokens(int amount);
}