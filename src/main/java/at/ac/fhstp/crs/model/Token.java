package at.ac.fhstp.crs.model;

import lombok.AllArgsConstructor;
import lombok.Builder;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.*;

@Builder
@Entity
@AllArgsConstructor
public class Token extends AEntity<Token> implements Comparable<Token> {

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

  @Override
  public int compareTo(Token o) {
    int compareValue;

    if ((compareValue = Integer.compare(this.getId(), o.getId())) != 0)
      return compareValue;

    if (this.name != null ^ o.name != null)
      return (this.name == null) ? -1 : 1;
    else if ((compareValue = this.name.compareTo(o.name)) != 0)
      return compareValue;

    if (this.symbol != null ^ o.symbol != null)
      return (this.symbol == null) ? -1 : 1;
    if ((compareValue = this.symbol.compareTo(o.symbol)) != 0)
      return compareValue;

    if ((compareValue = this.slug.compareTo(o.slug)) != 0)
      return compareValue;

    return 0;
  }
}
