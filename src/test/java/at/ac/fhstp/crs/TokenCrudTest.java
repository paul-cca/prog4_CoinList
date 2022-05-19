package at.ac.fhstp.crs;

import at.ac.fhstp.crs.api.IAPIConnector;
import at.ac.fhstp.crs.api.filters.ETokenChangePeriod;
import at.ac.fhstp.crs.model.Quote;
import at.ac.fhstp.crs.model.QuoteBuilder;
import at.ac.fhstp.crs.model.Token;
import at.ac.fhstp.crs.model.TokenBuilder;
import at.ac.fhstp.crs.service.AService;
import at.ac.fhstp.crs.service.ITokenService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TokenCrudTest {
    IAPIConnector apiConnector;
    @Autowired
    ITokenService specificTokenService;
    @Autowired
    AService<Token> tokenService;
    Token t1, t2;
    Quote q1, q2;

    @Test
    @BeforeEach
    public void createAndGetToken() {
        QuoteBuilder quoteBuilder = new QuoteBuilder("EUR");
        quoteBuilder.setPrice(12.4f).addPercentageChange(ETokenChangePeriod.HOURS_24, 3.56f);
        q1 = quoteBuilder.toQuote();

        TokenBuilder tokenBuilder = new TokenBuilder("BTC", "Bitcoin");
        tokenBuilder.setSlug("btc");
        tokenBuilder.addQuote(q1);


        t1 = tokenBuilder.toToken();
        tokenService.save(t1);
        t2 = tokenService.getOne(t1.getId()).get();
        q2 = t2.getQuote("EUR").get();

        Assertions.assertEquals(0, t1.compareTo(tokenService.getOne(t2.getId()).get()));
        Assertions.assertEquals(0, q1.compareTo(q2));
    }

    @Test
    public void updateToken(){
        t1.setSlug("betece");
        tokenService.update(t1);
        t2 = tokenService.getOne(t1.getId()).get();
        q2 = t2.getQuote("EUR").get();

        Assertions.assertEquals(0, t1.compareTo(tokenService.getOne(t2.getId()).get()));
        Assertions.assertEquals(0, q1.compareTo(tokenService.getOne(t2.getId()).get().getQuote("EUR").get()));
    }

    @Test
    public void deleteToken(){
        tokenService.delete(t1.getId());
        assert(specificTokenService.findBySymbol(t1.getSymbol()).isEmpty());
    }

}
