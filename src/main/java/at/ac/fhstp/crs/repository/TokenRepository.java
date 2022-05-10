package at.ac.fhstp.crs.repository;
import org.springframework.data.repository.CrudRepository;

import at.ac.fhstp.crs.model.Token;

public interface TokenRepository extends CrudRepository<Token,Integer> {

    
}
