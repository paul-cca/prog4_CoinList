package at.ac.fhstp.crs;

import at.ac.fhstp.crs.api.IAPIConnector.TIMESPAN;
import java.util.Dictionary;

public class Token {

  private final String name, symbol;

  public Token(String symbol, String name) {
    this.symbol = symbol;
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public String getSymbol() {
    return symbol;
  }

  public Quote getCoinQuote() {
    return coinQuote;
  }

  public void setCoinQuote(Quote coinQuote) {
    this.coinQuote = coinQuote;
  }

  public Quote getDollarQuote() {
    return dollarQuote;
  }

  public void setDollarQuote(Quote dollarQuote) {
    this.dollarQuote = dollarQuote;
  }

  private Quote coinQuote;
  private Quote dollarQuote;

  public class Quote {

    private float price;
    private Dictionary<TIMESPAN, Double> percentChange;

    public float getPrice() {
      return price;
    }

    public void setPrice(float price) {
      this.price = price;
    }

    public double getPercentChange(TIMESPAN timespan) {
      return percentChange.get(timespan);
    }

    public void setPercentChange(TIMESPAN timespan, double percentChange) {
      this.percentChange.put(timespan, percentChange);
    }
  }
}
