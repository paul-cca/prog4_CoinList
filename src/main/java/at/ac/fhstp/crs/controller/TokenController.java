package at.ac.fhstp.crs.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import at.ac.fhstp.crs.model.Token;
import at.ac.fhstp.crs.service.AService;

@RestController
@RequestMapping(path = "/token")
public class TokenController extends AController<Token> {

    public TokenController(AService<Token> service) {
        super(service);
    }
    
}
