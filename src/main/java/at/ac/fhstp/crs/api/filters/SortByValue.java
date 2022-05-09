package at.ac.fhstp.crs.api.filters;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import at.ac.fhstp.crs.dto.Token;

public class SortByValue implements ITokenFilterStrategy {

    private boolean ascending;

    public SortByValue(boolean ascending) {
        this.ascending = ascending;
    }

    @Override
    public List<Token> filterTokens(List<Token> tokens) {
        // copy list in order to not modify the original one
        List<Token> res = new ArrayList<Token>(tokens);
        res.sort(new Comparator<Token>() {
            @Override
            public int compare(Token t1, Token t2) {
                if(t1.getQuote("EUR").isEmpty()) {
                    return - 1;
                } else if (t2.getQuote("EUR").isEmpty()) 
                {
                    return 1;
                }
                float t1Value = t1.getQuote("EUR").get().getPrice();
                float t2Value = t2.getQuote("EUR").get().getPrice();

                // short instead of multiple ifs
                return t1Value > t2Value ? 1 : t1Value < t2Value ? -1 : 0;
            }
        });

        return res;
    }
    
    public boolean isAscending() {
        return ascending;
    }

    public void setAscending(boolean ascending) {
        this.ascending = ascending;
    }
    
}
