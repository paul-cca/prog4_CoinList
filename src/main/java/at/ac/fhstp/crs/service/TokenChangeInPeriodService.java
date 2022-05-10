package at.ac.fhstp.crs.service;

import at.ac.fhstp.crs.model.TokenChangeInPeriod;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public class TokenChangeInPeriodService extends AService<TokenChangeInPeriod> {
    public TokenChangeInPeriodService(CrudRepository<TokenChangeInPeriod, Integer> repository) {
        super(repository);
    }
}
