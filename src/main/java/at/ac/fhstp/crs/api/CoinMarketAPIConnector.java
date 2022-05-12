package at.ac.fhstp.crs.api;

import at.ac.fhstp.crs.model.Token;
import at.ac.fhstp.crs.model.TokenBuilder;
import at.ac.fhstp.crs.model.factories.JsonTokenFactory;
import com.harium.dotenv.Env;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class CoinMarketAPIConnector implements IAPIConnector {

  private final String apiKey;
  private final boolean production;
  private static final String sandboxURI =
    "https://sandbox-api.coinmarketcap.com/v1/cryptocurrency/listings/latest";
  private static final String proURI =
    "https://pro-api.coinmarketcap.com/v1/cryptocurrency/listings/latest";
  private static final int maxAmountPerCall = 5000;

  public CoinMarketAPIConnector() {
    String a = Env.get("API_KEY");
    String e = Env.get("ENV");

    apiKey = a != null ? a : "";
    production = e != null ? e.equals("PROD") : false;
  }

  public List<Token> getTokens(int amount) {
    if (amount > maxAmountPerCall) amount = maxAmountPerCall;

    HttpClient client = HttpClient.newHttpClient();
    HttpRequest request;
    HttpRequest.Builder requestBuilder = HttpRequest.newBuilder();
    StringBuilder uriBuilder = new StringBuilder();
    String uri;
    HttpResponse<String> response;

    uriBuilder.append((production) ? proURI : sandboxURI);
    uriBuilder.append(String.format("?%s=%d", "start", 1));
    uriBuilder.append(String.format("&%s=%d", "limit", amount));
    uriBuilder.append(String.format("&%s=%s", "convert", "EUR"));
    uri = uriBuilder.toString();

    requestBuilder.GET();
    requestBuilder.uri(URI.create(uri));
    requestBuilder.timeout(Duration.ofMinutes(1));
    requestBuilder.header("Accept", "application/json");
    requestBuilder.header("X-CMC_PRO_API_KEY", apiKey);
    request = requestBuilder.build();

    try {
      response = client.send(request, HttpResponse.BodyHandlers.ofString());
    } catch (IOException | InterruptedException e) {
      return null;
    }

    ArrayList<Token> tokenArrayList = new ArrayList<Token>();
    JSONObject o = new JSONObject(response.body());

    JsonTokenFactory factory = new JsonTokenFactory();

    if (Arrays.asList(JSONObject.getNames(o)).contains("data")) {
      o
        .getJSONArray("data")
        .forEach(
          item -> {
            tokenArrayList.add(factory.createModel((JSONObject) item));
          }
        );
    }
    return tokenArrayList;
  }
}
