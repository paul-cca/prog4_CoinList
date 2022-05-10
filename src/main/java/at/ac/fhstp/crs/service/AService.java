package at.ac.fhstp.crs.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.data.repository.CrudRepository;

import at.ac.fhstp.crs.model.AEntity;

public abstract class AService<T extends AEntity> {

  private CrudRepository<T, Integer> repository;

  public AService(CrudRepository<T, Integer> repository) {
    this.repository = repository;
  }

  public List<T> getAll() {
    return StreamSupport
      .stream(repository.findAll().spliterator(), false)
      .collect(Collectors.toList());
  }

  public Optional<T> getOne(Integer id) {
    return repository.findById(id);
  }

  public T save(T obj) {
    repository.save(obj);
    return obj;
  }

  public T update(T obj) {
      repository.save(obj);
      return obj;
  }

  public void delete(Integer id) {
      repository.deleteById(id);
  }
}