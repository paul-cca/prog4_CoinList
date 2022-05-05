package at.ac.fhstp.crs;

import at.ac.fhstp.crs.api.IAPIConnector.TIMESPAN;

public class QuoteBuilder {

  private Quote quote;

  public QuoteBuilder(String symbol) {
    quote = new Quote(symbol);
  }

  public QuoteBuilder setPrice(float price) {
    quote.setPrice(price);
    return this;
  }

  public QuoteBuilder addPercentageChange(
    TIMESPAN timespan,
    float percentChange
  ) {
    quote.addPercentChange(timespan, percentChange);
    return this;
  }

  public Quote toQuote() {
    return quote;
  }
}
