package at.ac.fhstp.crs.api;

import java.util.List;

public class IAPIConnector {
   List<Token> getPopularTokens();
   List<Token> getTopMoversToken(int amount, TIMESPAN timespan);
}

public enum TIMESPAN {
   1_HOUR,
   24_HOURS,
   7_DAYS
}
