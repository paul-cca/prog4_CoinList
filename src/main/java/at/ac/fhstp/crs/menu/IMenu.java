package at.ac.fhstp.crs.menu;

import at.ac.fhstp.crs.api.IAPIConnector;
import at.ac.fhstp.crs.dto.Token;

import java.util.List;

public interface IMenu {
    void displayInitMenu();
    void displayPopularTokens(IAPIConnector connector);

    void displayTopMoverTokens();
}
