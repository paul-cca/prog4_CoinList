package at.ac.fhstp.crs.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@Entity
@AllArgsConstructor
public class Token extends AEntity<Token> {

  private String name, symbol;
  private String slug;

  public Token() {
    super();
  }

  @OneToMany(fetch = FetchType.EAGER)//, mappedBy = "token")
  private Set<Quote> quotes;
  public Token(String symbol, String name) {
    this.symbol = symbol;
    this.name = name;
    quotes = new HashSet<Quote>();
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

  public List<Quote> getQuotes() {
    return new ArrayList<>(quotes);
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

  public void update(Token obj) {
    this.name = obj.getName();
    this.symbol = obj.getSymbol();
    this.slug = obj.getSlug();
  }
}
