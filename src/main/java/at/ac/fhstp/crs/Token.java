package at.ac.fhstp.crs;

public class Token {

  private String name, symbol;
  public String getName() {
    return name;
  }


  public void setName(String name) {
    this.name = name;
  }


  public String getSymbol() {
    return symbol;
  }


  public void setSymbol(String symbol) {
    this.symbol = symbol;
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
    public float getPrice() {
      return price;
    }
    public void setPrice(float price) {
      this.price = price;
    }
    public double getPercentChange24h() {
      return percentChange24h;
    }
    public void setPercentChange24h(double percentChange24h) {
      this.percentChange24h = percentChange24h;
    }
    private double percentChange24h;
  }
}
