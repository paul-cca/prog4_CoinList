package at.ac.fhstp.crs.service;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import at.ac.fhstp.crs.model.Token;

@Component
public class TokenService extends AService<Token> {

  public TokenService(CrudRepository<Token, Integer> repository) {
    super(repository);
  }
}
