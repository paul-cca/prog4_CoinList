package at.ac.fhstp.crs.model;

import at.ac.fhstp.crs.api.filters.ETokenChangePeriod;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Quote extends AEntity<Quote> {

  private String symbol;
  private float price;
  
  @OneToMany(fetch = FetchType.EAGER)
  private List<TokenChangeInPeriod> changeInPeriod = new ArrayList<TokenChangeInPeriod>();

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
      .findFirst()
      .filter(p -> p.getPeriod() == period);
    if (c.isPresent()) {
      return c.get().getPrice();
    }
    return 0;
  }

  public List<TokenChangeInPeriod> getChangeInPeriods() {
    return  changeInPeriod;
  }
  public void addPercentChange(ETokenChangePeriod period, float percentChange) {
    this.changeInPeriod.add(new TokenChangeInPeriod(period, percentChange));
  }
  public void update(Quote obj)
  {
    this.symbol = obj.symbol;
    this.price = obj.price;
  }
}
