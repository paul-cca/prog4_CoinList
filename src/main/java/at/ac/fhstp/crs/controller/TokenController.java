package at.ac.fhstp.crs.controller;

import at.ac.fhstp.crs.api.filters.ETokenChangePeriod;
import at.ac.fhstp.crs.api.filters.ITokenFilterStrategy;
import at.ac.fhstp.crs.api.filters.SortByChangeInPeriod;
import at.ac.fhstp.crs.api.filters.SortByValue;
import at.ac.fhstp.crs.model.Token;
import at.ac.fhstp.crs.service.AService;
import at.ac.fhstp.crs.service.ITokenService;
import at.ac.fhstp.crs.service.TokenService;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import io.quarkus.security.identity.SecurityIdentity;
import java.util.List;
import java.util.Optional;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("token")
public class TokenController extends AController<Token> {

  @Inject
  ITokenService tokenService;

  @Inject
  SecurityIdentity identity;

  @Inject
  @Override
  public void setService(AService<Token> service) {
    this.service = service;
  }

  @RolesAllowed("data_creator")
  @Path("/sortedByValue")
  @GET
  public @ResponseBody List<Token> getAllSortedByValue(
    @DefaultValue("false") @QueryParam("ascending") boolean ascending
  ) {
    for (String role : identity.getRoles()) {
      System.out.println(role); 
    }
    
    ITokenFilterStrategy strategy = new SortByValue(ascending);
    return tokenService.getAllFilteredBy(strategy);
  }

  @Path("sortedByChange")
  @GET
  public @ResponseBody List<Token> getAllSortedByChange(
    @DefaultValue("false") @QueryParam("ascending") boolean ascending
  ) {
    ITokenFilterStrategy strategy = new SortByChangeInPeriod(
      ETokenChangePeriod.HOURS_24,
      ascending
    );
    return tokenService.getAllFilteredBy(strategy);
  }
}
