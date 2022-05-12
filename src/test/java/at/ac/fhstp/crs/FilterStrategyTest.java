package at.ac.fhstp.crs;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.when;
import static org.hamcrest.Matchers.arrayContaining;
import static org.hamcrest.MatcherAssert.assertThat;
import java.util.ArrayList;
import java.util.List;

import at.ac.fhstp.crs.api.IAPIConnector;
import at.ac.fhstp.crs.api.filters.ETokenChangePeriod;
import at.ac.fhstp.crs.api.filters.ITokenFilterStrategy;
import at.ac.fhstp.crs.api.filters.SortByChangeInPeriod;
import at.ac.fhstp.crs.api.filters.SortByValue;
import at.ac.fhstp.crs.model.QuoteBuilder;
import at.ac.fhstp.crs.model.Token;
import at.ac.fhstp.crs.model.TokenBuilder;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class FilterStrategyTest {
        @Mock
        IAPIConnector apiConnector;

        List<Token> tokens;
        Token t1, t2, t3;

        @BeforeEach
        void createData() {
                tokens = new ArrayList<Token>();

                // token 1
                QuoteBuilder quoteBuilder = new QuoteBuilder("EUR");
                quoteBuilder.setPrice(12.4f)
                                .addPercentageChange(ETokenChangePeriod.HOURS_24, 3.56f);
                TokenBuilder tokenBuilder = new TokenBuilder("BTC", "Bitcoin");
                tokenBuilder.addQuote(quoteBuilder.toQuote());

                t1 = tokenBuilder.toToken();
                tokens.add(t1);

                // token 2
                quoteBuilder = new QuoteBuilder("EUR");
                quoteBuilder.setPrice(2.1f)
                                .addPercentageChange(ETokenChangePeriod.HOURS_24, 16.001f);
                tokenBuilder = new TokenBuilder("ETH", "Ethereum");
                tokenBuilder.addQuote(quoteBuilder.toQuote());

                t2 = tokenBuilder.toToken();
                tokens.add(t2);

                // token 3
                quoteBuilder = new QuoteBuilder("EUR");
                quoteBuilder.setPrice(8.9f)
                                .addPercentageChange(ETokenChangePeriod.HOURS_24, 15.67f);
                tokenBuilder = new TokenBuilder("DOG", "Dogecoin");
                tokenBuilder.addQuote(quoteBuilder.toQuote());

                t3 = tokenBuilder.toToken();
                tokens.add(t3);
        }

        @Test
        void testSortByValueList() {

                // mocking
                when(apiConnector.getTokens(3)).thenReturn(tokens);

                // sorting by price
                ITokenFilterStrategy filterStrategy = new SortByValue(true);
                List<Token> filtered = filterStrategy.filterTokens(apiConnector.getTokens(3));
                assertThat(
                                filtered.toArray(),
                                arrayContaining(t2, t3, t1));

                // sorting by price
                filterStrategy = new SortByValue(false);
                filtered = filterStrategy.filterTokens(apiConnector.getTokens(3));
                assertThat(filtered.toArray(),arrayContaining(t1, t3, t2));
        }

        @Test
        void testSortByChangeInPeriodList() {
                // mocking
                when(apiConnector.getTokens(3)).thenReturn(tokens);

                // sorting by price
                ITokenFilterStrategy filterStrategy = new SortByChangeInPeriod(ETokenChangePeriod.HOURS_24, false);
                List<Token> filtered = filterStrategy.filterTokens(apiConnector.getTokens(3));
                assertThat(filtered.toArray(),arrayContaining(t2, t3, t1));

                // sorting by price
                filterStrategy = new SortByChangeInPeriod(ETokenChangePeriod.HOURS_24, true);
                filtered = filterStrategy.filterTokens(apiConnector.getTokens(3));
                assertThat(filtered.toArray(),arrayContaining(t1, t3, t2));
        }
}
