package at.ac.fhstp.crs.api.filters;

import java.util.List;

import at.ac.fhstp.crs.dto.Token;

public class SortByChangeInPeriod implements ITokenFilterStrategy {

    private ETokenChangePeriod period;

    public ETokenChangePeriod getPeriod() {
        return period;
    }

    @Override
    public List<Token> filterTokens(List<Token> tokens) {
        return null;
    } 

    public void setPeriod(ETokenChangePeriod period) {
        this.period = period;
    }

    public SortByChangeInPeriod(ETokenChangePeriod period) {
        this.period = period;
    } 
}
