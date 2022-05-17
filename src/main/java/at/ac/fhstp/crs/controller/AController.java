package at.ac.fhstp.crs.controller;

import at.ac.fhstp.crs.model.AEntity;
import at.ac.fhstp.crs.service.AService;
import java.util.List;
import java.util.Optional;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Produces(MediaType.APPLICATION_JSON) // <---- Add this
@Consumes(MediaType.APPLICATION_JSON)
public abstract class AController<T extends AEntity> {

  protected AService<T> service;

  public abstract void setService(AService<T> service);


  @Path("/")
  @GET
  public List<T> getAll() {
    return service.getAll();
  }

  @Path("/{id}")
  @GET
  public Optional<T> getOne(@PathParam("id") Long id) {
    return service.getOne(id);
  }

  @Transactional
  @Path("/")
  @POST
  public T save(T obj) {
    return service.save(obj);
  }

  @Transactional
  @Path("/{id}")
  @PUT
  public T update(@PathParam("id") Long id, T obj) {
    T current = getOne(id).get();
   // current.update(obj);
    return service.save(current);
  }

  //@PreAuthorize("hasRole('data_creator')")
  @Transactional
  @Path("/{id}")
  @DELETE
  public void delete(@PathParam("id") Long id) {
    service.delete(id);
  }
}
