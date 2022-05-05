package at.ac.fhstp.crs;

public class TokenBuilder {

  private Token token;

  public TokenBuilder(String symbol, String name) {
    this.token = new Token(symbol, name);
  }

  public TokenBuilder setSlug(String slug) {
    token.setSlug(slug);
    return this;
  }

  public TokenBuilder addQuote(Quote q) {
    token.addQuote(q);
    return this;
  }

  public Token toToken() {
    return this.token;
  }
}
