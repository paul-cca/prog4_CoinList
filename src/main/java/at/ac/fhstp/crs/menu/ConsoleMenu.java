package at.ac.fhstp.crs.menu;

import com.harium.dotenv.Env;

import at.ac.fhstp.crs.dto.Token;

import java.util.List;

public class ConsoleMenu implements IMenu{

    @Override
    public void displayInitMenu() {


    }

    @Override
    public void displayPopularTokens() {
        //List<Token> tokenList =

        //assert tokenList != null;

        System.out.println("NAME    |   SHORTCUT    |   COINQUOTE   |   USD-QUOTE");

       // for (Token token:tokenList) {
       //     System.out.println(token);
       // }

    }

    @Override
    public void displayTopMoverTokens() {
        List<Token> tokenList;
    }
}
