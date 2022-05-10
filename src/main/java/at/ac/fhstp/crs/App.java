package at.ac.fhstp.crs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = { "at.ac.fhstp.crs" })
public class App {

  public static void main(String[] args) throws Exception {
    SpringApplication.run(App.class, args);
    /*
        if(Objects.equals(Env.get("API_KEY"), "CHANGE_ME") || Env.get("API_KEY") == null || Objects.equals(Env.get("API_KEY"), "")){
            System.out.println("API_KEY invalid.");
        }

        IAPIConnector connector = CoinMarketAPIConnector.getInstance(Env.get("API_KEY"), false);
        IMenu menu = new ConsoleMenu();
        menu.init(connector);
        menu.showBasicOuput();
*/
  }
}
