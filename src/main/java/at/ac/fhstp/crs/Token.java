package at.ac.fhstp.crs;

public class Token {

  private String name, symbol;
  private Quote coinQuote;
  private Quote dollarQuote;


  public class Quote {
    private float price;
    private double percentChange24h;
  }
}
