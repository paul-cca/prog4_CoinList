package at.ac.fhstp.crs.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Token extends AEntity<Token> {

  private String name, symbol;
  private String slug;

  @OneToMany(fetch = FetchType.EAGER)
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
    return name + ' ' + symbol;
  }

  public void update(Token obj)
  {
    this.name = obj.getName();
    this.symbol = obj.getSymbol();
    this.slug = obj.getSlug();
  }
}
