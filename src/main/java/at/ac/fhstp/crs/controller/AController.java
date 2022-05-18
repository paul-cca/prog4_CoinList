package at.ac.fhstp.crs.controller;

import at.ac.fhstp.crs.model.AEntity;
import at.ac.fhstp.crs.service.AService;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public abstract class AController<T extends AEntity<T>> {

  protected AService<T> service;

  public AController(AService<T> service) {
    this.service = service;
  }

  @GetMapping
  public @ResponseBody List<T> getAll() {
    return service.getAll();
  }

  @GetMapping(value = "/{id}")
  public @ResponseBody Optional<T> getOne(@PathVariable Integer id) {
    return service.getOne(id);
  }

  @PostMapping
    public @ResponseBody T save(@RequestBody T obj) {
    return service.save(obj);
  }

  @PutMapping(value = "/{id}")
  public @ResponseBody T update(@PathVariable Integer id, @RequestBody T obj) {
    return service.update(obj);
  }

  @PreAuthorize("hasRole('data_creator')")
  @DeleteMapping(value = "/{id}")
  public void delete(@PathVariable Integer id) {
    service.delete(id);
  }
}
