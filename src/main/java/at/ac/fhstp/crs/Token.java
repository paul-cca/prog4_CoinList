package at.ac.fhstp.crs;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Token {

  private final String name, symbol;
  private String slug;

  private List<Quote> quotes;

  public Token(String symbol, String name) {
    this.symbol = symbol;
    this.name = name;
    quotes = new ArrayList<Quote>();
  }

  public String getName() {
    return name;
  }

  public String getSymbol() {
    return symbol;
  }

  public Quote getCoinQuote() {
    return quotes
      .stream()
      .filter(q -> q.getSymbol().equals(this.symbol))
      .findFirst()
      .get();
  }

  public Quote getDollarQuote() {
    return quotes
      .stream()
      .filter(q -> q.getSymbol().equals("USD"))
      .findFirst()
      .get();
  }

  public Optional<Quote> getQuote(String quote) {
    return quotes.stream().filter(q -> q.getSymbol().equals(quote)).findFirst();
  }

  public void addQuote(Quote q) {
    quotes.add(q);
  }


  public void setSlug(String slug) {
    this.slug = slug;
  }

  public String getSlug() {
    return slug;
  }

  @Override
  public String toString() {
    return name + ' ' + symbol + ' ' + getCoinQuote() + getDollarQuote();
  }
}
