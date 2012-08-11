
package ohjelma;

import java.io.FileNotFoundException;
import javax.swing.*;

/**
 * Alkuainepeli!
 * @author timo
 */

public class Ohjelma {
    /**
     * Aloittaa pelin.
     * @param args 
     */
    public static void main(String[] args)  {
           Graafinen uusi = new Graafinen();
           SwingUtilities.invokeLater(uusi);
        }
}

