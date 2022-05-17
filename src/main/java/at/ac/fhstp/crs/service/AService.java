package at.ac.fhstp.crs.service;

import at.ac.fhstp.crs.model.AEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import java.util.List;
import java.util.Optional;

import javax.inject.Inject;


public abstract class AService<T extends AEntity> {

  @Inject
  protected PanacheRepository<T> repository;

 // @Inject
 // public AService(PanacheRepository<T> repository) {
 //   this.repository = repository;
 // }

  public abstract void setRepository(PanacheRepository<T> repository);

  public List<T> getAll() {
    return repository.findAll().list();
  }

  public Optional<T> getOne(Long id) {
    return repository.findByIdOptional(id);
  }

  public T save(T obj) {
    repository.persist(obj);
    return obj;
  }

  public T update(T obj) {
    obj.update(obj);
    repository.persist(obj);
    return obj;
  }

  public void delete(Long id) {
    repository.deleteById(id);
  }

  public void deleteAll() {
    repository.deleteAll();
  }
}
