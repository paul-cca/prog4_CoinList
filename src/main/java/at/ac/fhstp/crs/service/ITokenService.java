package at.ac.fhstp.crs.service;

import java.util.List;

import at.ac.fhstp.crs.api.filters.ITokenFilterStrategy;
import at.ac.fhstp.crs.model.Token;

public interface ITokenService {
   List<Token> getAllFilteredBy(ITokenFilterStrategy strategy); 
}
