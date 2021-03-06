package at.ac.fhstp.crs.controller;

import at.ac.fhstp.crs.api.filters.ETokenChangePeriod;
import at.ac.fhstp.crs.api.filters.ITokenFilterStrategy;
import at.ac.fhstp.crs.api.filters.SortByChangeInPeriod;
import at.ac.fhstp.crs.api.filters.SortByValue;
import at.ac.fhstp.crs.model.Token;
import at.ac.fhstp.crs.service.AService;
import at.ac.fhstp.crs.service.ITokenService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/token")
public class TokenController extends AController<Token> {

    private ITokenService tokenService;

    public TokenController(AService<Token> service, ITokenService tokenService) {
        super(service);
        this.tokenService = tokenService;
    }

    @PreAuthorize("hasRole('data_creator')")
    @GetMapping(value = "sortedByValue")
    public @ResponseBody List<Token> getAllSortedByValue(
            @RequestParam(required = false, defaultValue = "true") boolean ascending
    ) {
    /*
    Collection<SimpleGrantedAuthority> authorities = (Collection<SimpleGrantedAuthority>) SecurityContextHolder
      .getContext()
      .getAuthentication()
      .getAuthorities();
    System.out.println("here");

    System.out.println(SecurityContextHolder.getContext().getAuthentication().getName());
    for (SimpleGrantedAuthority a : authorities) {
      System.out.println(a.getAuthority());
    }

     */

        ITokenFilterStrategy strategy = new SortByValue(ascending);
        return tokenService.getAllFilteredBy(strategy);
    }

    @GetMapping(value = "sortedByChange")
    public @ResponseBody List<Token> getAllSortedByChange(
            @RequestParam(required = false, defaultValue = "true") boolean ascending
    ) {
        ITokenFilterStrategy strategy = new SortByChangeInPeriod(
                ETokenChangePeriod.HOURS_24,
                ascending
        );
        return tokenService.getAllFilteredBy(strategy);
    }
}
