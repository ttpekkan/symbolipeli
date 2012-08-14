
package ohjelma;

import java.io.FileNotFoundException;
import javax.swing.*;

/**
 * Alkuainepeli!
 * @author timo
 */

public class Ohjelma {
    public static Päävalikko pääohjelma;
    /**
     * Aloittaa pelin.
     * @param args 
     */
    public static void main(String[] args)  {
           pääohjelma = new Päävalikko();
           SwingUtilities.invokeLater(pääohjelma);
        }
}

