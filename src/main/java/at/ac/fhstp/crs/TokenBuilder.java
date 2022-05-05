package at.ac.fhstp.crs;

import at.ac.fhstp.crs.Token.Quote;

public class TokenBuilder {
   private Token token;
   
   public TokenBuilder(String symbol, String name) {
       this.token = new Token(symbol, name);
   }

   public TokenBuilder setCoinQuote(Quote quote) {
        this.token.setCoinQuote(quote);
        return this;
   } 
   public TokenBuilder setDollarQuote(Quote quote) {
        this.token.setDollarQuote(quote);
        return this;
   } 

   public Token toToken() {
       return this.token;
   }
}
