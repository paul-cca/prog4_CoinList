package at.ac.fhstp.crs.controller;

import at.ac.fhstp.crs.model.TokenChangeInPeriod;
import at.ac.fhstp.crs.service.AService;
import at.ac.fhstp.crs.service.TokenChangeInPeriodService;
import java.util.List;
import java.util.Optional;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.jboss.resteasy.reactive.server.core.request.AcceptHeaders;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("TokenChangeInPeriondChangeInPeriond")
public class TokenChangeInPeriodController
  extends AController<TokenChangeInPeriod> {

  @Inject
  @Override
  public void setService(AService<TokenChangeInPeriod> service) {
    this.service = service;
  }
}
