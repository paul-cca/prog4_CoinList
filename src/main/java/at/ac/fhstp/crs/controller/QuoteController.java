package at.ac.fhstp.crs.controller;

import at.ac.fhstp.crs.model.Quote;
import at.ac.fhstp.crs.service.AService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/quote")
public class QuoteController extends AController<Quote> {

    public QuoteController(AService<Quote> service) {
        super(service);
    }
}
