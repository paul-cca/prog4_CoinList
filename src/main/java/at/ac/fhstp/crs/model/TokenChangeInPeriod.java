package at.ac.fhstp.crs.model;

import at.ac.fhstp.crs.api.filters.ETokenChangePeriod;
import javax.persistence.Entity;

@Entity
public class TokenChangeInPeriod extends AEntity {

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
}
