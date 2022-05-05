package at.ac.fhstp.crs.menu;

import at.ac.fhstp.crs.Token;
import com.harium.dotenv.Env;

import java.util.List;

public class ConsoleMenu implements IMenu{

    @Override
    public void displayInitMenu() {


    }

    @Override
    public void displayPopularTokens() {
        List<Token> tokenList = null;

        String apiKey = Env.get("API_KEY");

        assert tokenList != null;

        System.out.println("NAME    |   SHORTCUT    |   COINQUOTE   |   USD-QUOTE");

        for (Token token:tokenList) {
            System.out.println(token);
        }

    }

    @Override
    public void displayTopMoverTokens() {
        List<Token> tokenList;
    }
}
