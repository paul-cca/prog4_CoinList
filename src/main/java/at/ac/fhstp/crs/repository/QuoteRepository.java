package at.ac.fhstp.crs.repository;

import javax.enterprise.context.ApplicationScoped;

import at.ac.fhstp.crs.model.Quote;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class QuoteRepository implements PanacheRepository<Quote> {

    
}
