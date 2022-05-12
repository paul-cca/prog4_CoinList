package at.ac.fhstp.crs.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import at.ac.fhstp.crs.model.TokenChangeInPeriod;
import at.ac.fhstp.crs.service.AService;

@RestController
@RequestMapping(path = "/tokenChangeInPeriond")
public class TokenChangeInPeriodController extends AController<TokenChangeInPeriod>{

    public TokenChangeInPeriodController(AService<TokenChangeInPeriod> service) {
        super(service);
    }
    
}
