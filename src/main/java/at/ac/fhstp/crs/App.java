package at.ac.fhstp.crs;

import at.ac.fhstp.crs.api.CoinMarketAPIConnector;
import at.ac.fhstp.crs.api.IAPIConnector;
import at.ac.fhstp.crs.menu.ConsoleMenu;
import at.ac.fhstp.crs.menu.IMenu;
import com.harium.dotenv.Env;

import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        if(Env.get("API_KEY") == "CHANGE_ME" || Env.get("API_KEY") == null){
            System.out.println("API_KEY invalid.");
        }

        IAPIConnector connector =CoinMarketAPIConnector.getInstance(Env.get("API_KEY"));
        List<Token> popToken = connector.getPopularTokens(20);

        IMenu menu = new ConsoleMenu();
        menu.displayPopularTokens(popToken);
    }
}
