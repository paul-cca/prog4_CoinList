package at.ac.fhstp.crs.api;

import at.ac.fhstp.crs.Token;
import at.ac.fhstp.crs.TokenBuilder;
import at.ac.fhstp.crs.Token.Quote;

import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class CoinMarketAPIConnector implements IAPIConnector {

  @Override
  public List<Token> getPopularTokens() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<Token> getTopMoversToken(int amount, TIMESPAN timespan) {
    // TODO Auto-generated method stub
    return null;
  }

  private Token convertToToken(JSONObject object) {
    TokenBuilder builder = new TokenBuilder(
      object.getString("symbol"),
      object.getString("name")
    );

    Quote coinQuote = new Quote();

    return builder.toToken();
  }
}
