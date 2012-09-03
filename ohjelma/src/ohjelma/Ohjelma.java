package ohjelma;

import javax.swing.JFrame;

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
        JFrame ikkuna = new JFrame();       
        Päävalikkoikkuna pääohjelma = new Päävalikkoikkuna();
        pääohjelma.run(ikkuna);
    }
    
}
