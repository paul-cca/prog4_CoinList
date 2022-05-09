package at.ac.fhstp.crs;

import at.ac.fhstp.crs.api.CoinMarketAPIConnector;
import at.ac.fhstp.crs.api.IAPIConnector;
import at.ac.fhstp.crs.api.filters.ETokenChangePeriod;
import at.ac.fhstp.crs.api.filters.ITokenFilterStrategy;
import at.ac.fhstp.crs.api.filters.SortByChangeInPeriod;
import at.ac.fhstp.crs.api.filters.SortByValue;
import at.ac.fhstp.crs.menu.ConsoleMenu;
import at.ac.fhstp.crs.menu.IMenu;
import com.harium.dotenv.Env;

import java.util.Objects;

public class App 
{
    public static void main( String[] args )
    {
        if(Objects.equals(Env.get("API_KEY"), "CHANGE_ME") || Env.get("API_KEY") == null || Objects.equals(Env.get("API_KEY"), "")){
            System.out.println("API_KEY invalid.");
        }

        IAPIConnector connector = CoinMarketAPIConnector.getInstance(Env.get("API_KEY"), false);

        ITokenFilterStrategy mostPopularStrategy = new SortByValue(true);
        ITokenFilterStrategy topMoverStrategy = new SortByChangeInPeriod(ETokenChangePeriod.HOURS_24,true);
        IMenu menu = new ConsoleMenu();
        menu.displayTokens(connector,mostPopularStrategy);
        menu.displayTokens(connector,topMoverStrategy);

    }
}
