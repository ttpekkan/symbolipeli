package ohjelma;

import java.awt.Color;
import java.awt.Container;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.UIManager;

/**
 * Tämä luokan avulla avataan pistelistaikkuna.
 *
 * @author Timo Pekkanen
 */
public class Pistelistaikkuna {

    private JButton suljePistelistaikkuna;
    private Container pohja;
    private JDialog pistelistaikkuna;
    private PistelistaRajapinta pistelista;

    /**
     * Tämä metodi käynnistää pistelistaikkunan.
     *
     * @param omistus JFrame, jolle pistelistaikkuna "kuuluu".
     */
    public void run(JFrame omistus) {
        pistelistaikkuna = new JDialog(omistus, false);
        pistelista = new Pistelista();
        pistelistaikkuna.setLocation(130, 200);
        UIManager.put("Button.select", Color.green);
        MuokkaaKomponenttia.luoDialoginContentPaneKuvasta("pistelista.png", pistelistaikkuna);
        luoPistelistaikkunanKomponentit();
        MuokkaaKomponenttia.suoritaNeDialoginavausToiminnotJotkaAinaSamat(pistelistaikkuna);
        pistelistaikkuna.addWindowListener(new PistelistaikkunanKuuntelija(this));

    }

    /**
     * Tämä metodi luo halutut komponentit pistelistaikkunaan.
     */
    private void luoPistelistaikkunanKomponentit() {
        pohja = pistelistaikkuna.getContentPane();
        suljePistelistaikkuna = new JButton("Sulje Pistelista");
        MuokkaaKomponenttia.muokkaaJButtonia(suljePistelistaikkuna, 400, 50, 300, 75,
                Color.green.darker(), true, Color.BLUE.darker(), false, true, true, 26,
                Color.green, pohja);
        suljePistelistaikkuna.addActionListener(new PistelistaikkunanKuuntelija(this));
        pistelistaikkuna.getRootPane().setDefaultButton(suljePistelistaikkuna);
        pistelista.rakennaPistelistanJLabelesitys(pohja);
    }

    /**
     * Toiminnot jotka halutaan tehdä, kun pistelistaikkuna suljetaan.
     */
    public void suljePistelistaikkuna() {
        MuokkaaKomponenttia.asetaLaskurinArvo(0);
        pistelistaikkuna.dispose();
    }
}