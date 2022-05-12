package at.ac.fhstp.crs.api.filters;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import at.ac.fhstp.crs.model.Token;

public class SortByChangeInPeriod implements ITokenFilterStrategy {

  private ETokenChangePeriod period;
  private boolean ascending;

  public SortByChangeInPeriod(ETokenChangePeriod period, boolean ascending) {
    this.period = period;
    this.ascending = ascending;
  }

  @Override
  public List<Token> filterTokens(List<Token> tokens) {
    // copy list in order to not modify the original one
    List<Token> res = new ArrayList<Token>(tokens);
    res.sort(
      new Comparator<Token>() {
        @Override
        public int compare(Token t1, Token t2) {
          if (t1.getQuote("EUR").isEmpty()) {
            return -1;
          } else if (t2.getQuote("EUR").isEmpty()) {
            return 1;
          }
          double t1Value = t1.getQuote("EUR").get().getPercentChange(period);
          double t2Value = t2.getQuote("EUR").get().getPercentChange(period);

          // short instead of multiple ifs
          return t1Value > t2Value ? 1 : t1Value < t2Value ? -1 : 0;
        }
      }
    );
    if(!ascending) {
      Collections.reverse(res);
    }

    return res;
  }

  public boolean isAscending() {
    return ascending;
  }

  public void setAscending(boolean ascending) {
    this.ascending = ascending;
  }

  public ETokenChangePeriod getPeriod() {
    return period;
  }

  public void setPeriod(ETokenChangePeriod period) {
    this.period = period;
  }
}
