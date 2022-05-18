package at.ac.fhstp.crs.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import at.ac.fhstp.crs.model.Quote;
import at.ac.fhstp.crs.service.AService;
import lombok.NoArgsConstructor;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("quote")
@NoArgsConstructor
public class QuoteController extends AController<Quote> {

    @Override
    public void setService(AService<Quote> service) {
        this.service = service;
    }

}
