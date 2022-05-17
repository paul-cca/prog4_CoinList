package at.ac.fhstp.crs.model;

import at.ac.fhstp.crs.api.filters.ETokenChangePeriod;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@NoArgsConstructor
@Table(name = "tokenchangeinperiod")
public class TokenChangeInPeriod extends AEntity {

  @Column
  private ETokenChangePeriod period;
  @Column
  private float price;

  public TokenChangeInPeriod(ETokenChangePeriod period, float price) {
    super();
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

  public void update(AEntity obj) {
    TokenChangeInPeriod t = (TokenChangeInPeriod) obj;
    this.period = t.getPeriod();
    this.price = t.getPrice();
  }
}
