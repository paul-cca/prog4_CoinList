package at.ac.fhstp.crs.dto;

import java.util.Dictionary;
import java.util.Hashtable;

import at.ac.fhstp.crs.api.filters.ETokenChangePeriod;

public class Quote {

  private final String symbol;
  private float price;
  private Dictionary<ETokenChangePeriod, Float> percentChange;

  public Quote(String symbol) {
    this.symbol = symbol;
    percentChange = new Hashtable<ETokenChangePeriod, Float>();
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

  public double getPercentChange(ETokenChangePeriod ETokenChangePeriod) {
    return percentChange.get(ETokenChangePeriod);
  }

  public void addPercentChange(ETokenChangePeriod ETokenChangePeriod, float percentChange) {
    this.percentChange.put(ETokenChangePeriod, percentChange);
  }
}
