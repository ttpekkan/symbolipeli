package ohjelma;

import java.awt.Color;
import java.awt.Container;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.UIManager;

/**
 * Tämä luokan avulla avataan pistelistaikkuna.
 *
 * @author Timo Pekkanen
 */
public class Pistelistaikkuna implements Runnable {
    private Container pohja;
    private JFrame pistelistaikkuna;
    private KomponenttienLataus komponentit;
    private Pistelista pistelista;
    private static int laskuri = 0;
 
    /**
     * Käynnistää pistelistaikkunan.
     */
    @Override
    public void run() {
        if (laskuri == 0) {
            pistelistaikkuna = new JFrame("Symbolipeli");
            laskuri = laskuri + 1;
            pistelista = new Pistelista();
            komponentit = new KomponenttienLataus(pistelistaikkuna);
            pistelistaikkuna.setLocation(130, 200);
            UIManager.put("Button.select", Color.green);
            komponentit.luoContentPaneKuvasta("pistelista.png");
            luoKomponentit();
            komponentit.neIkkunanavausToiminnotJotkaAinaSamat();
            pistelistaikkuna.addWindowListener(new PistelistaikkunanKuuntelija(this));
        }
    }

    /**
     * Tämä metodi luo halutut komponentit pistelistaikkunaan.
     */
    private void luoKomponentit() {
        pohja = pistelistaikkuna.getContentPane();
        JButton nappula = new JButton("Sulje Pistelista");
        komponentit.luoNappula(nappula, 400, 50, 300, 75,
                Color.green.darker(), true, Color.BLUE.darker(), false, true, true, 26,
                Color.green, pohja);
        nappula.addActionListener(new PistelistaikkunanKuuntelija(this));
        pistelistaikkuna.getRootPane().setDefaultButton(nappula);
        pistelista.JLabelesitys(pohja);
    }

    public void sulje() {
        laskuri = laskuri - 1;
        pistelistaikkuna.dispose();
    }
}
