package at.ac.fhstp.crs.repository;
import org.springframework.data.repository.CrudRepository;

import at.ac.fhstp.crs.model.Quote;

public interface QuoteRepository extends CrudRepository<Quote,Integer> {

    
}
