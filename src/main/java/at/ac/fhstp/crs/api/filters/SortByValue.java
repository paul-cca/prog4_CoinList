package at.ac.fhstp.crs.api.filters;

import java.util.List;

import at.ac.fhstp.crs.dto.Token;

public class SortByValue implements ITokenFilterStrategy {

    private boolean ascending;


    public SortByValue(boolean ascending) {
        this.ascending = ascending;
    }

    @Override
    public List<Token> filterTokens(List<Token> tokens) {
        return null;
    }
    
    public boolean isAscending() {
        return ascending;
    }

    public void setAscending(boolean ascending) {
        this.ascending = ascending;
    }
    
}
