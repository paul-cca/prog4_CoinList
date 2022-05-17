package at.ac.fhstp.crs;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import at.ac.fhstp.crs.controller.TokenController;
import at.ac.fhstp.crs.model.Token;
import at.ac.fhstp.crs.repository.TokenRepository;
import at.ac.fhstp.crs.service.AService;
import java.util.Arrays;
import java.util.List;

import org.aspectj.lang.annotation.Before;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TokenRestControllerIntegrationTest {

  @Mock
  AService<Token> tokenService;

  @Autowired
  WebApplicationContext webApplicationContext;
  MockMvc mockMvc;

  @Test
  public void testfindAll() throws Exception {
    Token t = new Token("BTC", "Bitcoin");
    List<Token> tokens = Arrays.asList(t);

    Mockito.when(tokenService.getAll()).thenReturn(tokens);

    mockMvc
      .perform(get("/token"))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$", Matchers.hasSize(1)))
      .andExpect(jsonPath("$[0].symbol", Matchers.is("BTC")));
  }

  @BeforeEach
  public void setUp() {
    mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
  }
}
