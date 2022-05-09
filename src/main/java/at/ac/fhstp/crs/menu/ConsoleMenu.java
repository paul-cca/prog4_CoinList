package at.ac.fhstp.crs.menu;

import at.ac.fhstp.crs.api.IAPIConnector;
import at.ac.fhstp.crs.api.filters.ETokenChangePeriod;
import at.ac.fhstp.crs.api.filters.ITokenFilterStrategy;
import at.ac.fhstp.crs.dto.Token;
import java.util.List;

public class ConsoleMenu implements IMenu{

    private static final String seperator = " | ";

    @Override
    public void displayInitMenu() {


    }

    @Override
    public void displayTokens(IAPIConnector connector, ITokenFilterStrategy strategy) {
        //assert tokenList != null;
<<<<<<< HEAD
        List<Token> tokenSortedByValue = strategy.filterTokens(connector.getTokens(100, false));
=======
        List<Token> tokenSortedByValue = strategy.filterTokens(connector.getTokens(100));
>>>>>>> b73b557 (add running tests)

        if(tokenSortedByValue.isEmpty()){
            System.out.println("Got no data!");
        }

        System.out.format("%-21s | %-8s | %12s | %-20s%n", "NAME", "SHORTCUT", "EUR-QUOTE", "24H-CHANGE");
        StringBuilder stringBuilder = new StringBuilder();

       for (Token token:tokenSortedByValue) {
            if(token.getQuote("EUR").isPresent()){
                stringBuilder.append(String.format("%-21s %s %-8s %s %12s %s %-20s %n",
                                                   token.getName(),
                                                   seperator,
                                                   token.getSymbol(),
                                                   seperator,
                                                   token.getQuote("EUR").get().getPrice(),
                                                   seperator,
                                                   token.getQuote("EUR").get().getPercentChange(ETokenChangePeriod.HOURS_24)));
            }
       }
        System.out.println(stringBuilder);
    }
}
