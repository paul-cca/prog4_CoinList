package at.ac.fhstp.crs.model.factories;

import at.ac.fhstp.crs.model.Token;
import at.ac.fhstp.crs.model.TokenBuilder;
import java.util.Arrays;
import org.json.JSONObject;

public class JsonTokenFactory implements IJSONModelFactory<Token> {

  @Override
  public Token createModel(JSONObject object) {
    TokenBuilder builder = new TokenBuilder(
      object.getString("symbol"),
      object.getString("name")
    );

    builder.setSlug(object.getString("slug"));

    String[] quoteNames = JSONObject.getNames(object.getJSONObject("quote"));

    Arrays
      .stream(quoteNames)
      .forEach(
        q -> {
          JSONObject o = object.getJSONObject("quote").getJSONObject(q);
          JsonQuoteFactory factory = new JsonQuoteFactory(q);
          builder.addQuote(factory.createModel(o));
        }
      );

    return builder.toToken();
  }
}
