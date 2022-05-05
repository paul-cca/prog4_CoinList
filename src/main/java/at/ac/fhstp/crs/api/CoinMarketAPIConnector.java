package at.ac.fhstp.crs.api;

import at.ac.fhstp.crs.Quote;
import at.ac.fhstp.crs.QuoteBuilder;
import at.ac.fhstp.crs.Token;
import at.ac.fhstp.crs.TokenBuilder;
import java.util.ArrayList;
import java.util.Arrays;
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

    String[] quoteNames = JSONObject.getNames(object.getJSONObject("qoute"));

    Arrays
      .stream(quoteNames)
      .forEach(
        q -> {
          JSONObject o = object.getJSONObject(q);
          QuoteBuilder qb = new QuoteBuilder(q);
          qb
            .setPrice(o.getFloat("price"))
            .addPercentageChange(
              TIMESPAN.HOUR_1,
              o.getFloat("percent_change_1h")
            )
            .addPercentageChange(
              TIMESPAN.HOURS_24,
              o.getFloat("percent_change_24h")
            )
            .addPercentageChange(
              TIMESPAN.DAYS_7,
              o.getFloat("percent_change_7d")
            );

          builder.addQuote(qb.toQuote());
        }
      );

    return builder.toToken();
  }
}
