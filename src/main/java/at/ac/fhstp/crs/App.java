package at.ac.fhstp.crs;

import at.ac.fhstp.crs.menu.ConsoleMenu;
import at.ac.fhstp.crs.menu.IMenu;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        IMenu menu = new ConsoleMenu();
        menu.displayPopularTokens();
    }
}
