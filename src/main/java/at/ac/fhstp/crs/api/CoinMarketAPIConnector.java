package at.ac.fhstp.crs.api;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import org.json.JSONObject;
import at.ac.fhstp.crs.dto.Token;
import at.ac.fhstp.crs.dto.TokenBuilder;

public class CoinMarketAPIConnector implements IAPIConnector {

  private final String apiKey;
  private static final String sandboxURI =
    "https://sandbox-api.coinmarketcap.com/v1/cryptocurrency/listings/latest";
  private static final String proURI =
    "https://pro-api.coinmarketcap.com/v1/cryptocurrency/listings/latest";
  private static final int maxAmountPerCall = 5000;

  private static CoinMarketAPIConnector instance;

  private CoinMarketAPIConnector(String apiKey) {
    this.apiKey = apiKey;
  }

  public static CoinMarketAPIConnector getInstance(String apiKey) {
    if (instance == null) {
      instance = new CoinMarketAPIConnector(apiKey);
    }

    return instance;
  }

  public List<Token> getTokens(int amount) {
    if (amount > maxAmountPerCall) amount = maxAmountPerCall;

    HttpClient client = HttpClient.newHttpClient();
    HttpRequest request;
    HttpRequest.Builder requestBuilder = HttpRequest.newBuilder();
    StringBuilder uriBuilder = new StringBuilder();
    String uri;
    CompletableFuture<HttpResponse<String>> responseFuture;
    HttpResponse<String> response = null;

    uriBuilder.append(sandboxURI);
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
    responseFuture =
      client.sendAsync(request, HttpResponse.BodyHandlers.ofString());
    responseFuture.thenApply(HttpResponse::body);
    responseFuture.thenAccept(System.out::println);
    response = responseFuture.join();

    JSONObject o = new JSONObject(response.body());
    System.out.println(o.toString());

    ArrayList<Token> tokenArrayList = new ArrayList<Token>();
    o
      .getJSONArray("data")
      .forEach(
        item -> {
          tokenArrayList.add(new TokenBuilder((JSONObject) item).toToken());
        }
      );

    return tokenArrayList;
  }

}
