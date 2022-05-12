package at.ac.fhstp.crs;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import at.ac.fhstp.crs.model.Token;


@SpringBootTest
public class TokenCrudTest {
    @BeforeAll
    public void createToken() {
        Token t = new Token();
    }     
}
