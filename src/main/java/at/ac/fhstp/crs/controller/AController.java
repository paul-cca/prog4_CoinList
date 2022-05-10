package at.ac.fhstp.crs.controller;

import at.ac.fhstp.crs.model.AEntity;
import at.ac.fhstp.crs.service.AService;
import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public abstract class AController<T extends AEntity> {

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
    return service.save(obj);
  }

  @DeleteMapping(value = "/{id}")
  public void delete(@PathVariable Integer id) {
    service.delete(id);
  }
}
