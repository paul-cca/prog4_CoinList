package at.ac.fhstp.crs;

import at.ac.fhstp.crs.api.CoinMarketAPIConnector;
import at.ac.fhstp.crs.api.IAPIConnector;
import at.ac.fhstp.crs.api.filters.ITokenFilterStrategy;
import at.ac.fhstp.crs.api.filters.SortByValue;
import at.ac.fhstp.crs.dto.Token;
import at.ac.fhstp.crs.menu.ConsoleMenu;
import at.ac.fhstp.crs.menu.IMenu;
import com.harium.dotenv.Env;

import java.util.List;

public class App 
{
    public static void main( String[] args )
    {
        IAPIConnector connector =CoinMarketAPIConnector.getInstance(Env.get("API_KEY"));

        ITokenFilterStrategy strategy = new SortByValue(true);
        List<Token> tokenSortedByValue = strategy.filterTokens(connector.getTokens(100));

        IMenu menu = new ConsoleMenu();
        menu.displayPopularTokens();
    }
}
