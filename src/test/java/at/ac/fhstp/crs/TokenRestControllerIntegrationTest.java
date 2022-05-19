package at.ac.fhstp.crs;

import at.ac.fhstp.crs.api.PeriodicApiFetcher;
import at.ac.fhstp.crs.model.Token;
import at.ac.fhstp.crs.model.factories.JsonTokenFactory;
import at.ac.fhstp.crs.service.TokenService;
import io.quarkus.test.junit.QuarkusMock;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.TestSecurity;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.containsString;

@QuarkusTest
public class TokenRestControllerIntegrationTest {

    @Inject
    TokenService tokenService;

    @Inject
    PeriodicApiFetcher periodicApiFetcher;

    private static JsonTokenFactory jtf = new JsonTokenFactory();
    private static Token tokenBTC, tokenETH, tokenSHT;
    private static ArrayList<Token> tokenList;

    @BeforeEach
    public void setup() {
        tokenList.clear();
        tokenList.add(tokenBTC);
    }

    @BeforeAll
    public static void init() {
        PeriodicApiFetcher mock = Mockito.mock(PeriodicApiFetcher.class);
        QuarkusMock.installMockForType(mock, PeriodicApiFetcher.class);

        JSONObject tokenJSON = new JSONObject(
                "{\n" +
                        "    \"id\": 5,\n" +
                        "    \"name\": \"Bitcoin\",\n" +
                        "    \"symbol\": \"BTC\",\n" +
                        "    \"slug\": \"btc\",\n" +
                        "    \"quote\": {\n" +
                        "        \"EUR\": {\n" +
                        "            \"price\": 31821.643157297254,\n" +
                        "            \"percent_change_1h\": -0.53278065,\n" +
                        "            \"percent_change_24h\": -3.84403893,\n" +
                        "            \"percent_change_7d\": -15.13486758,\n" +
                        "        }\n" +
                        "    }\n" +
                        "}");

        tokenBTC = jtf.createModel(tokenJSON);
        tokenBTC.setId(5l);
        tokenList = new ArrayList<Token>();

        tokenJSON = new JSONObject(
                "{\n" +
                        "    \"id\": 10,\n" +
                        "    \"name\": \"Ethereum\",\n" +
                        "    \"symbol\": \"ETH\",\n" +
                        "    \"slug\": \"eth\",\n" +
                        "    \"quote\": {\n" +
                        "        \"EUR\": {\n" +
                        "            \"price\": 3182.164315729725,\n" +
                        "            \"percent_change_1h\": -0.53278065,\n" +
                        "            \"percent_change_24h\": -3.84403893,\n" +
                        "            \"percent_change_7d\": -15.13486758,\n" +
                        "        }\n" +
                        "    }\n" +
                        "}");

        tokenETH = jtf.createModel(tokenJSON);
        tokenETH.setId(10l);

        tokenJSON = new JSONObject(
                "{\n" +
                        "    \"id\": 15,\n" +
                        "    \"name\": \"Shitcoin\",\n" +
                        "    \"symbol\": \"SHT\",\n" +
                        "    \"slug\": \"sht\",\n" +
                        "    \"quote\": {\n" +
                        "        \"EUR\": {\n" +
                        "            \"price\": 0.318216431572972,\n" +
                        "            \"percent_change_1h\": -0.53278065,\n" +
                        "            \"percent_change_24h\": -3.84403893,\n" +
                        "            \"percent_change_7d\": -15.13486758,\n" +
                        "        }\n" +
                        "    }\n" +
                        "}");

        tokenSHT = jtf.createModel(tokenJSON);
        tokenSHT.setId(15l);
    }

    @Test
    public void testFindAll() throws Exception {
        TokenService mock = Mockito.mock(TokenService.class);
        Mockito.when(mock.getAll()).thenReturn(tokenList);
        QuarkusMock.installMockForType(mock, TokenService.class);

        RestAssured.given().request()
                .when().get("/token").then()
                .statusCode(200).body(containsString("BTC"));
    }

    @Test
    @TestSecurity(authorizationEnabled = false)
    public void testDeleteOne() throws Exception {
        TokenService mock = Mockito.mock(TokenService.class);
        Mockito.when(mock.getAll()).thenReturn(tokenList);
        Mockito.when(mock.delete(Mockito.anyLong())).thenAnswer(I -> tokenList.remove(tokenBTC));
        QuarkusMock.installMockForType(mock, TokenService.class);

        RestAssured.given().request()
                .when().delete("/token/5").then()
                .statusCode(204);

        RestAssured.given().request()
                .when().get("/token").then()
                .statusCode(200).body(containsString("[]"));
    }

    //@WithMockUser
    @Test
    @TestSecurity(authorizationEnabled = false)
    public void testAddOne() throws Exception {
        String requestJSONString =
                "{\n" +
                        "    \"name\": \"Ethereum\",\n" +
                        "    \"symbol\": \"ETH\",\n" +
                        "    \"slug\": \"eth\",\n" +
                        "    \"quotes\": [\n" +
                        "        {\n" +
                        "            \"symbol\": \"EUR\",\n" +
                        "            \"price\": 3182.164315729725,\n" +
                        "            \"changeInPeriods\": [\n" +
                        "                {\n" +
                        "                    \"period\": \"HOUR_1\",\n" +
                        "                    \"price\": -0.53278065\n" +
                        "                },\n" +
                        "                {\n" +
                        "                    \"period\": \"HOURS_24\",\n" +
                        "                    \"price\": -3.84403893\n" +
                        "                },\n" +
                        "                {\n" +
                        "                    \"period\": \"DAYS_7\",\n" +
                        "                    \"price\": -15.13486758\n" +
                        "                }\n" +
                        "            ]\n" +
                        "        }\n" +
                        "    ]\n" +
                        "}";

        RestAssured.given().accept(ContentType.JSON).request()
                .contentType(ContentType.JSON)
                .body(requestJSONString)
                .when().post("/token").then()
                .statusCode(200);
    }

    @Test
    @TestSecurity(authorizationEnabled = false)
    public void testUpdateOne() throws Exception {
        String requestJSONString =
                "{\n" +
                        "    \"id\": 10,\n" +
                        "    \"name\": \"Shitcoin\",\n" +
                        "    \"symbol\": \"SHT\",\n" +
                        "    \"slug\": \"sht\",\n" +
                        "    \"quotes\": [\n" +
                        "        {\n" +
                        "            \"id\": 9,\n" +
                        "            \"symbol\": \"EUR\",\n" +
                        "            \"price\": 0.318216431572972,\n" +
                        "            \"changeInPeriods\": [\n" +
                        "                {\n" +
                        "                    \"id\": 6,\n" +
                        "                    \"period\": \"HOUR_1\",\n" +
                        "                    \"price\": -0.53278065\n" +
                        "                },\n" +
                        "                {\n" +
                        "                    \"id\": 7,\n" +
                        "                    \"period\": \"HOURS_24\",\n" +
                        "                    \"price\": -3.84403893\n" +
                        "                },\n" +
                        "                {\n" +
                        "                    \"id\": 8,\n" +
                        "                    \"period\": \"DAYS_7\",\n" +
                        "                    \"price\": -15.13486758\n" +
                        "                }\n" +
                        "            ]\n" +
                        "        }\n" +
                        "    ]\n" +
                        "}";

        tokenList.add(tokenETH);
        TokenService mock = Mockito.mock(TokenService.class);
        Mockito.when(mock.getAll()).thenReturn(tokenList);
        Mockito.when(mock.getOne(Mockito.anyLong())).thenReturn(Optional.of(tokenETH));
        Mockito.when(mock.update(Mockito.any(Token.class)))
                .thenAnswer(I -> tokenList.set(1, tokenSHT));
        QuarkusMock.installMockForType(mock, TokenService.class);

        RestAssured.given().accept(ContentType.JSON).request()
                .contentType(ContentType.JSON)
                .body(requestJSONString)
                .when().put("/token/10").then()
                .statusCode(204);

        RestAssured.given().request()
                .when().get("/token").then()
                .statusCode(200).body(containsString("SHT"));
    }

    @TestSecurity(authorizationEnabled = false)
    @Test
    public void getTokensSortedByValue() throws Exception {
        TokenService mock = Mockito.mock(TokenService.class);
        Mockito.when(mock.getAllFilteredBy(Mockito.any())).thenReturn(tokenList);
        QuarkusMock.installMockForType(mock, TokenService.class);

        RestAssured.given().request()
                .when().get("/token/sortedByValue").then()
                .statusCode(200).body(containsString("BTC"));
    }
}
