package at.ac.fhstp.crs.model;

import at.ac.fhstp.crs.api.filters.ETokenChangePeriod;
import lombok.Builder;

import javax.persistence.Entity;
@Builder
@Entity
public class TokenChangeInPeriod extends AEntity<TokenChangeInPeriod> {

  private ETokenChangePeriod period;
  private float price;

  public TokenChangeInPeriod(ETokenChangePeriod period, float price) {
    this.period = period;
    this.price = price;
  }

  public ETokenChangePeriod getPeriod() {
    return period;
  }

  public void setPeriod(ETokenChangePeriod period) {
    this.period = period;
  }

  public float getPrice() {
    return price;
  }

  public void setPrice(float price) {
    this.price = price;
  }

  public void update(TokenChangeInPeriod obj)
  {
    this.period = obj.getPeriod();
    this.price = obj.getPrice();
  }
}
