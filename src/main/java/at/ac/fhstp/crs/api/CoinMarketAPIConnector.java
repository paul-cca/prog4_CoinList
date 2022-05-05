package at.ac.fhstp.crs.api;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.json.JSONArray;
import org.json.JSONObject;
import at.ac.fhstp.crs.Token;

public class CoinMarketAPIConnector implements IAPIConnector
{
  private final static String apiKey;
  private final static String sandboxURI = "https://sandbox-api.coinmarketcap.com/v1/cryptocurrency/listings/latest";
  private final static String proURI = "https://pro-api.coinmarketcap.com/v1/cryptocurrency/listings/latest";
  private final static int maxAmountPerCall = 5000;
  private JSONObject json;
  private int amount;

  private static CoinMarketAPIConnector instance;

  private CoinMarketAPIConnector()
  {
    ;
  }

  public static CoinMarketAPIConnector getInstance()
  {
    if (instance == null)
    {
      instance = new CoinMarketAPIConnector();
    }

    return instance;
  }

  private void getTokens(int amount)
  {
    if (amount > maxAmountPerCall)
      amount = maxAmountPerCall;

    HttpClient client = HttpClient.newHttpClient();
    HttpRequest request;
    HttpRequest.Builder requestBuilder = HttpRequest.newBuilder();
    StringBuilder uriBuilder = new StringBuilder();
    String uri;
    CompletableFuture<HttpResponse<String>> responseFuture;
    HttpResponse<String> response = null;

    uriBuilder.append(sandboxURI);
    uriBuilder.append(String.format("?%s=%i", "start", 1));
    uriBuilder.append(String.format("&%s=%i", "limit", amount));
    uriBuilder.append(String.format("&%s=%s", "convert", "EUR"));
    uri = uriBuilder.toString();

    requestBuilder.GET();
    requestBuilder.uri(URI.create(uri));
    requestBuilder.timeout(Duration.ofMinutes(1));
    requestBuilder.header("Accept", "application/json");
    requestBuilder.header("X-CMC_PRO_API_KEY", apiKey);

    request = requestBuilder.build();
    responseFuture = client.sendAsync(request, HttpResponse.BodyHandlers.ofString());
    responseFuture.thenApply(HttpResponse::body);
    responseFuture.thenAccept(System.out::println);
    response = responseFuture.join();

    json = new JSONObject(response.body());
    this.amount = amount;
  }

  @Override
  public List<Token> getPopularTokens(int amount)
  {
    ArrayList<Token> tokenArrayList = new ArrayList<Token>();

    if (json == null || this.amount < ((amount > maxAmountPerCall) ? maxAmountPerCall : amount))
      getTokens(amount);

    json.getJSONArray("data").forEach(item -> { tokenArrayList.add(convertToToken((JSONObject) item)); });
    return tokenArrayList;
  }

  @Override
  public List<Token> getTopMoversToken(int amount, TIMESPAN timespan)
  {
    ArrayList<Token> tokenArrayList = new ArrayList<Token>();

    if (json == null || this.amount < ((amount > maxAmountPerCall) ? maxAmountPerCall : amount))
      getTokens(amount);

    json.getJSONArray("data").forEach(item -> { tokenArrayList.add(convertToToken((JSONObject) item)); });
    return tokenArrayList;
  }

  private Token convertToToken(JSONObject object)
  {
    Token t = new Token();
    return t;
  }

}