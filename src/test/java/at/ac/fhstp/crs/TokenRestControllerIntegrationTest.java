package at.ac.fhstp.crs;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import at.ac.fhstp.crs.controller.TokenController;
import at.ac.fhstp.crs.model.Token;
import at.ac.fhstp.crs.service.AService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(TokenController.class)
public class TokenRestControllerIntegrationTest {
 
  @MockBean
  AService<Token> tokenService;
 
  @Autowired
  MockMvc mockMvc;
 
  @Test
  public void testfindAll() throws Exception {
    Token t = new Token("BTC", "Bitcoin");
    List<Token> tokens = Arrays.asList(t);
 
    Mockito.when(tokenService.getAll()).thenReturn(tokens);
 
    mockMvc.perform(get("/employee"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", Matchers.hasSize(1)))
        .andExpect(jsonPath("$[0].symbol", Matchers.is("BTC")));
  }
 
}