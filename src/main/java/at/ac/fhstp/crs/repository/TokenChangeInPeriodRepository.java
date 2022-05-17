package at.ac.fhstp.crs.repository;

import at.ac.fhstp.crs.model.TokenChangeInPeriod;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TokenChangeInPeriodRepository
  implements PanacheRepository<TokenChangeInPeriod> {}
