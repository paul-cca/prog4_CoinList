package at.ac.fhstp.crs.dto;

import at.ac.fhstp.crs.api.filters.ETokenChangePeriod;

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
    ETokenChangePeriod ETokenChangePeriod,
    float percentChange
  ) {
    quote.addPercentChange(ETokenChangePeriod, percentChange);
    return this;
  }

  public Quote toQuote() {
    return quote;
  }
}
