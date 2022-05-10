package at.ac.fhstp.crs.service;

import org.springframework.data.repository.CrudRepository;

import at.ac.fhstp.crs.model.Quote;

public class QuoteService extends AService<Quote> {

    public QuoteService(CrudRepository<Quote, Integer> repository) {
        super(repository);
    }
    
}
