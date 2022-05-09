package at.ac.fhstp.crs.menu;

import at.ac.fhstp.crs.api.IAPIConnector;
import at.ac.fhstp.crs.api.filters.ITokenFilterStrategy;
import at.ac.fhstp.crs.dto.Token;

import java.util.List;

public interface IMenu {
    void displayInitMenu();
    void displayTokens(IAPIConnector connector, ITokenFilterStrategy strategy);
}
