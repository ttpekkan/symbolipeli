package ohjelma;

import javax.swing.SwingUtilities;

/**
 * Alkuainepelin pääluokka.
 * 
 * @author Timo Pekkanen
 */
public class Ohjelma {

    /**
     * Luo uuden Päävalikko-olion.
     *
     * @param args
     */
    public static void main(String[] args) {
        Päävalikko pääohjelma = new Päävalikko();
        SwingUtilities.invokeLater(pääohjelma);
    }
}
