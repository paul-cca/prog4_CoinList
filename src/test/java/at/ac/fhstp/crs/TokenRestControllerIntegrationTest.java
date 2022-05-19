package at.ac.fhstp.crs;

import at.ac.fhstp.crs.api.security.KeycloakConfiguration;
import at.ac.fhstp.crs.model.Token;
import at.ac.fhstp.crs.model.factories.JsonTokenFactory;
import at.ac.fhstp.crs.service.TokenService;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@ContextConfiguration
@SpringBootTest
@EnableWebSecurity
public class TokenRestControllerIntegrationTest {

    @MockBean
    TokenService tokenService;
    @Autowired
    WebApplicationContext webApplicationContext;
    @Autowired
    KeycloakConfiguration webSecurityConfig;


    MockMvc mockMvc;

    private static JsonTokenFactory jtf = new JsonTokenFactory();
    private static Token tokenBTC, tokenETH, tokenSHT;
    private static ArrayList<Token> tokenList;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        tokenList.clear();
        tokenList.add(tokenBTC);
    }

    @BeforeAll
    public static void setupToken() {
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
        tokenBTC.setId(5);
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
        tokenETH.setId(10);

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
        tokenSHT.setId(15);
    }

    @Test
    public void testFindAll() throws Exception {
        Mockito.when(tokenService.getAll()).thenReturn(tokenList);

        mockMvc
                .perform(get("/token"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(1)))
                .andExpect(jsonPath("$[0].symbol", Matchers.is("BTC")));
    }

    @WithMockUser(roles = {"data_creator"})
    @Test
    public void testDeleteOne() throws Exception {
        Mockito.when(tokenService.getAll()).thenReturn(tokenList);
        Mockito.when(tokenService.delete(Mockito.anyInt()))
                .thenAnswer(I -> tokenList.remove(tokenBTC));

        mockMvc
                .perform(get("/token"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(1)))
                .andExpect(jsonPath("$[0].symbol", Matchers.is("BTC")));

        mockMvc
                .perform(delete("/token/" + tokenBTC.getId()))
                .andExpect(status().isOk());

        mockMvc
                .perform(get("/token"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(0)));
    }

    @WithMockUser(roles = {"data_creator"})
    @Test
    public void testAddOne() throws Exception {
        String requestJSONString =
                "{\n" +
                        "    \"id\": 10,\n" +
                        "    \"name\": \"Ethereum\",\n" +
                        "    \"symbol\": \"ETH\",\n" +
                        "    \"slug\": \"eth\",\n" +
                        "    \"quotes\": [\n" +
                        "        {\n" +
                        "            \"id\": 9,\n" +
                        "            \"symbol\": \"EUR\",\n" +
                        "            \"price\": 3182.164315729725,\n" +
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

        Mockito.when(tokenService.getAll()).thenReturn(tokenList);
        Mockito.when(tokenService.save(Mockito.any(Token.class)))
                .thenAnswer(I -> (tokenList.add(tokenETH)) ? tokenETH : null);

        mockMvc
                .perform(get("/token"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(1)))
                .andExpect(jsonPath("$[0].symbol", Matchers.is("BTC")));

        MockHttpServletRequestBuilder builder =
                MockMvcRequestBuilders.post("/token")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(requestJSONString);

        mockMvc.perform(builder)
                .andExpect(status().isOk());

        mockMvc
                .perform(get("/token"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(2)))
                .andExpect(jsonPath("$[0].symbol", Matchers.is("BTC")))
                .andExpect(jsonPath("$[1].symbol", Matchers.is("ETH")));
    }

    @WithMockUser(roles = {"data_creator"})
    @Test
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
        Mockito.when(tokenService.getAll()).thenReturn(tokenList);
        Mockito.when(tokenService.update(Mockito.any(Token.class)))
                .thenAnswer(I -> tokenList.set(1, tokenSHT));

        mockMvc
                .perform(get("/token"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(2)))
                .andExpect(jsonPath("$[0].symbol", Matchers.is("BTC")))
                .andExpect(jsonPath("$[1].symbol", Matchers.is("ETH")));

        MockHttpServletRequestBuilder builder =
                MockMvcRequestBuilders.put("/token/" + tokenSHT.getId())
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(requestJSONString);

        mockMvc.perform(builder)
                .andExpect(status().isOk());

        mockMvc
                .perform(get("/token"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(2)))
                .andExpect(jsonPath("$[0].symbol", Matchers.is("BTC")))
                .andExpect(jsonPath("$[1].symbol", Matchers.is("SHT")));
    }

    @WithMockUser(roles = {"data_creator"})
    @Test
    public void getTokensSortedByValue() throws Exception {
        Mockito.when(tokenService.getAll()).thenReturn(tokenList);

        mockMvc
                .perform(get("/token/sortedByValue"))
                .andExpect(status().isOk());
    }

    @WithMockUser
    @Test
    public void getTokensSortedByValueWithoutAuthentication() throws Exception {
        Mockito.when(tokenService.getAll()).thenReturn(tokenList);

        // AccessDeniedException thrown = Assertions.assertThrows(AccessDeniedException.class, () -> {
        mockMvc
                .perform(get("/token/sortedByValue"))
                .andExpect(status().isUnauthorized());
        // });
    }
}
