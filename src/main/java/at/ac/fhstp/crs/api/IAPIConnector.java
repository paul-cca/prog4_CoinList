package at.ac.fhstp.crs.api;

import java.util.List;

import at.ac.fhstp.crs.Token;

public interface IAPIConnector {
   List<Token> getPopularTokens(int amount);
   List<Token> getTopMoversToken(int amount, TIMESPAN timespan);
   
  public enum TIMESPAN {
     HOUR_1,
     HOURS_24,
     DAYS_7
   } 
}