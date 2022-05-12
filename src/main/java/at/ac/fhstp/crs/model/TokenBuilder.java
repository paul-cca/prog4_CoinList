package at.ac.fhstp.crs.model;

import java.util.Arrays;

import org.json.JSONObject;

import at.ac.fhstp.crs.api.filters.ETokenChangePeriod;

public class TokenBuilder {

  private Token token;

  public TokenBuilder(String symbol, String name) {
    this.token = new Token(symbol, name);
  }
  
  public TokenBuilder(JSONObject object) {
    TokenBuilder builder = new TokenBuilder(
      object.getString("symbol"),
      object.getString("name")
    );

    builder.setSlug(object.getString("slug"));

    String[] quoteNames = JSONObject.getNames(object.getJSONObject("quote"));

    Arrays
      .stream(quoteNames)
      .forEach(
        q -> {
          JSONObject o = object.getJSONObject("quote").getJSONObject(q);
          QuoteBuilder qb = new QuoteBuilder(q);
          qb
            .setPrice(o.getFloat("price"))
            .addPercentageChange(
              ETokenChangePeriod.HOUR_1,
              o.getFloat("percent_change_1h")
            )
            .addPercentageChange(
              ETokenChangePeriod.HOURS_24,
              o.getFloat("percent_change_24h")
            )
            .addPercentageChange(
              ETokenChangePeriod.DAYS_7,
              o.getFloat("percent_change_7d")
            );

          builder.addQuote(qb.toQuote());
        }
      );

     this.token = builder.toToken();
  }

  public TokenBuilder setSlug(String slug) {
    token.setSlug(slug);
    return this;
  }

  public TokenBuilder addQuote(Quote q) {
    token.addQuote(q);
    return this;
  }

  public Token toToken() {
    return this.token;
  }
}
