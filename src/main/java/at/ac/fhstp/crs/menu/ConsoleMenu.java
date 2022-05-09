package at.ac.fhstp.crs.menu;

import at.ac.fhstp.crs.api.IAPIConnector;
import at.ac.fhstp.crs.api.filters.ETokenChangePeriod;
import at.ac.fhstp.crs.api.filters.ITokenFilterStrategy;
import at.ac.fhstp.crs.api.filters.SortByChangeInPeriod;
import at.ac.fhstp.crs.api.filters.SortByValue;
import at.ac.fhstp.crs.dto.Token;
import java.util.List;

public class ConsoleMenu implements IMenu {

  private static final String seperator = " | ";
  private IAPIConnector apiConnector;

  @Override
  public void init(IAPIConnector apiConnector) {
    this.apiConnector = apiConnector;
  }

  @Override
  public void showBasicOuput() throws Exception {
    if (apiConnector == null) {
      throw new Exception("APIConnector has not been initialized yet!");
    }
    
    // order by percentage change in 24hr
    System.out.println("===== Tokens sorted by change in 24hr =====");
    ITokenFilterStrategy strategy = new SortByChangeInPeriod(ETokenChangePeriod.HOURS_24, true);
    printTokenList(strategy.filterTokens(apiConnector.getTokens(100)));
    
    // order by value
    System.out.println("\n =====Tokens sorted by value =====");
    strategy = new SortByValue(true);
    printTokenList(strategy.filterTokens(apiConnector.getTokens(100)));

  }

  private void printTokenList(List<Token> tokens) {
    if (tokens.isEmpty()) {
      System.out.println("Got no data!");
    }

    System.out.format(
      "%-21s %s %-8s %s %12s %s %-20s %n",
      "NAME",
      seperator,
      "SHORTCUT",
      seperator,
      "EUR-QUOTE",
      seperator,
      "24H-CHANGE"
    );

    StringBuilder stringBuilder = new StringBuilder();

    for (Token token : tokens) {
      if (token.getQuote("EUR").isPresent()) {
        stringBuilder.append(
          String.format(
            "%-21s %s %-8s %s %12s %s %-20s %n",
            token.getName(),
            seperator,
            token.getSymbol(),
            seperator,
            token.getQuote("EUR").get().getPrice(),
            seperator,
            token
              .getQuote("EUR")
              .get()
              .getPercentChange(ETokenChangePeriod.HOURS_24)
          )
        );
      }
    }
    System.out.println(stringBuilder);
  }
}
