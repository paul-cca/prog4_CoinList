package at.ac.fhstp.crs.menu;

import at.ac.fhstp.crs.api.filters.ETokenChangePeriod;
import com.harium.dotenv.Env;

import at.ac.fhstp.crs.dto.Token;

import java.util.List;

public class ConsoleMenu implements IMenu{

    @Override
    public void displayInitMenu() {


    }

    @Override
    public void displayPopularTokens(List<Token> tokenList) {

        assert tokenList != null;

        System.out.println("NAME    |   SHORTCUT   |   EUR-QUOTE   |   24H-CHANGE");

        StringBuilder stringBuilder = new StringBuilder();
        String seperator = " | ";

       for (Token token:tokenList) {
            if(token.getQuote("EUR").isPresent()){
                stringBuilder.append(token.getName());
                stringBuilder.append(seperator);
                stringBuilder.append(token.getSlug());
                stringBuilder.append(token.getQuote("EUR").get());
                stringBuilder.append(token.getQuote("EUR").get().getPercentChange(ETokenChangePeriod.HOURS_24));
                stringBuilder.append('\n');
            }
       }
        System.out.println(stringBuilder);

    }

    @Override
    public void displayTopMoverTokens() {
        List<Token> tokenList;
    }
}
