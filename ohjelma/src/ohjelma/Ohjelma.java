package ohjelma;

import javax.swing.SwingUtilities;

/**
 * Alkuainepeli!
 *
 * @author timo
 */
public class Ohjelma {

    /**
     * Aloittaa pelin.
     *
     * @param args
     */
    public static void main(String[] args) {
        Päävalikko pääohjelma = new Päävalikko();
        SwingUtilities.invokeLater(pääohjelma);
    }
}
