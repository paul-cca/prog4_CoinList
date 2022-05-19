package at.ac.fhstp.crs.service;

import at.ac.fhstp.crs.model.AEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;


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
    repository.getEntityManager().merge(obj);
    return obj;
  }

  public boolean delete(Long id) {
    long count = repository.count();
    repository.deleteById(id);
    return repository.count() < count;
  }

  public void deleteAll() {
    repository.deleteAll();
  }
}
