package at.ac.fhstp.crs.controller;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import at.ac.fhstp.crs.model.TokenChangeInPeriod;
import at.ac.fhstp.crs.service.AService;

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
