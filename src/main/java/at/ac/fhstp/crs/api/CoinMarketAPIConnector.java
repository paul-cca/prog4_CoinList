package at.ac.fhstp.crs.api;

import java.util.List;

import org.json.JSONObject;

import at.ac.fhstp.crs.Token;

public class CoinMarketAPIConnector implements IAPIConnector {

    @Override
    public List<Token> getPopularTokens() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Token> getTopMoversToken(int amount, TIMESPAN timespan) {
        // TODO Auto-generated method stub
        return null;
    }


    private Token convertToToken(JSONObject object) {
       Token t = new Token(); 
    }
    
}

