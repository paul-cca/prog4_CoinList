package at.ac.fhstp.crs.service;

import java.util.List;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import at.ac.fhstp.crs.model.TokenChangeInPeriod;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class TokenChangeInPeriodService extends AService<TokenChangeInPeriod> {

  public List<TokenChangeInPeriod> getAll() {
    return repository.findAll().list();
  }

  public Optional<TokenChangeInPeriod> getOne(Long id) {
    return repository.findByIdOptional(id);
  }

  public TokenChangeInPeriod save(TokenChangeInPeriod obj) {
    repository.persist(obj);
    return obj;
  }

  public TokenChangeInPeriod update(TokenChangeInPeriod obj) {
    repository.persist(obj);
    return obj;
  }

  public void delete(Long id) {
    repository.deleteById(id);
  }

  public void deleteAll() {
    repository.deleteAll();
  }

  @Inject
  @Override
  public void setRepository(PanacheRepository<TokenChangeInPeriod> repository) {
    this.repository = repository;
  }
}
