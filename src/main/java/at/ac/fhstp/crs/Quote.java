package at.ac.fhstp.crs;

import at.ac.fhstp.crs.api.IAPIConnector.TIMESPAN;
import java.util.Dictionary;

public class Quote {

  private final String symbol;
  private float price;
  private Dictionary<TIMESPAN, Float> percentChange;

  public Quote(String symbol) {
    this.symbol = symbol;
  }

  public String getSymbol() {
    return symbol;
  }

  public float getPrice() {
    return price;
  }

  public void setPrice(float price) {
    this.price = price;
  }

  public double getPercentChange(TIMESPAN timespan) {
    return percentChange.get(timespan);
  }

  public void addPercentChange(TIMESPAN timespan, float percentChange) {
    this.percentChange.put(timespan, percentChange);
  }
}
