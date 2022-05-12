package at.ac.fhstp.crs.model.factories;

import at.ac.fhstp.crs.api.filters.ETokenChangePeriod;
import at.ac.fhstp.crs.model.AEntity;
import at.ac.fhstp.crs.model.Quote;
import at.ac.fhstp.crs.model.QuoteBuilder;
import java.util.Arrays;
import org.json.JSONObject;

public class JsonQuoteFactory implements IJSONModelFactory<Quote> {

  private String quoteName;

  public JsonQuoteFactory(String quoteName) {
    this.quoteName = quoteName;
  }

  @Override
  public Quote createModel(JSONObject obj) {
    QuoteBuilder qb = new QuoteBuilder(quoteName);
    qb
      .setPrice(obj.getFloat("price"))
      .addPercentageChange(
        ETokenChangePeriod.HOUR_1,
        obj.getFloat("percent_change_1h")
      )
      .addPercentageChange(
        ETokenChangePeriod.HOURS_24,
        obj.getFloat("percent_change_24h")
      )
      .addPercentageChange(
        ETokenChangePeriod.DAYS_7,
        obj.getFloat("percent_change_7d")
      );

    return qb.toQuote();
  }
}
