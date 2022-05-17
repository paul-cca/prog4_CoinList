package at.ac.fhstp.crs.repository;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;

import at.ac.fhstp.crs.model.Token;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class TokenRepository implements PanacheRepository<Token> {
    public Optional<Token> findOneBySymbol(String symbol) {
        return Optional.empty();
    }
}
