package at.ac.fhstp.crs.service;

import at.ac.fhstp.crs.model.Quote;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class QuoteService extends AService<Quote> {

  /*public List<Quote> getAll() {
    return repository.findAll().list();
  }

  public Optional<Quote> getOne(Long id) {
    return repository.findByIdOptional(id);
  }

  public Quote save(Quote obj) {
    repository.persist(obj);
    return obj;
  }

  public Quote update(Quote obj) {
    repository.persist(obj);
    return obj;
  }

  public void delete(Long id) {
    repository.deleteById(id);
  }

  public void deleteAll() {
    repository.deleteAll();
  }*/

  @Inject
  @Override
  public void setRepository(PanacheRepository<Quote> repository) {
    this.repository = repository;
  }
}
