package at.ac.fhstp.crs.menu;

import at.ac.fhstp.crs.api.IAPIConnector;

public interface IMenu {
    void init(IAPIConnector apiConnector);
    void showBasicOuput() throws Exception;
}
