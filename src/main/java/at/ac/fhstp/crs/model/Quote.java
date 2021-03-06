package at.ac.fhstp.crs.model;

import at.ac.fhstp.crs.api.filters.ETokenChangePeriod;
import lombok.AllArgsConstructor;
import lombok.Builder;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Builder
@Entity
public class Quote extends AEntity<Quote> implements Comparable<Quote> {

 // @ManyToOne
  //private Token token;
  private String symbol;
  private float price;

  @OneToMany(fetch = FetchType.EAGER)
  private List<TokenChangeInPeriod> changeInPeriod = new ArrayList<TokenChangeInPeriod>();

  public Quote(){
    super();
  }

  public Quote(String symbol) {
    this.symbol = symbol;
    changeInPeriod = new ArrayList<TokenChangeInPeriod>();
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

  public double getPercentChange(ETokenChangePeriod period) {
    Optional<TokenChangeInPeriod> c = changeInPeriod
      .stream()
      .filter(p -> p.getPeriod() == period)
      .findFirst();
    if (c.isPresent()) {
      return c.get().getPrice();
    }
    return 0;
  }

  public List<TokenChangeInPeriod> getChangeInPeriods() {
    return changeInPeriod;
  }

  public void addPercentChange(ETokenChangePeriod period, float percentChange) {
    this.changeInPeriod.add(new TokenChangeInPeriod(period, percentChange));
  }

  public void update(Quote obj) {
    this.symbol = obj.symbol;
    this.price = obj.price;
  }

    @Override
    public int compareTo(Quote o)
    {
        int compareValue;

        if ((compareValue = Integer.compare(this.getId(), o.getId())) != 0)
            return compareValue;

        if (this.symbol != null ^ o.symbol != null)
            return (this.symbol == null) ? -1 : 1;
        else if ((compareValue = this.symbol.compareTo(o.symbol)) != 0)
            return compareValue;

        if ((compareValue = Float.compare(this.price, o.price)) != 0)
            return compareValue;

        return 0;
    }
}
