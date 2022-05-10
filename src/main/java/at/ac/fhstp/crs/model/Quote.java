package at.ac.fhstp.crs.model;

import at.ac.fhstp.crs.api.filters.ETokenChangePeriod;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

@Entity
public class Quote extends AEntity {

  private final String symbol;
  private float price;
  
  @OneToMany(fetch = FetchType.EAGER)
  private List<TokenChangeInPeriod> changeInPeriod;

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

  public void addPercentChange(ETokenChangePeriod period, float percentChange) {
    this.changeInPeriod.add(new TokenChangeInPeriod(period, percentChange));
  }
}
