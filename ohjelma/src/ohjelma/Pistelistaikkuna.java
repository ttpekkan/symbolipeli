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

    private JButton sulje;
    private Container pohja;
    private JDialog pistelistaikkuna;
    private Pistelista pistelista;

    /**
     * Käynnistää pistelistaikkunan.
     */
    public void run(JFrame omistus) {
        pistelistaikkuna = new JDialog(omistus, true);
        pistelista = new Pistelista();
        pistelistaikkuna.setLocation(130, 200);
        UIManager.put("Button.select", Color.green);
        KomponenttienMuokkaus.luoDialoginContentPaneKuvasta("pistelista.png", pistelistaikkuna);
        luoKomponentit();
        KomponenttienMuokkaus.neDialoginavausToiminnotJotkaAinaSamat(pistelistaikkuna);
        pistelistaikkuna.addWindowListener(new PistelistaikkunanKuuntelija(this));

    }

    /**
     * Tämä metodi luo halutut komponentit pistelistaikkunaan.
     */
    private void luoKomponentit() {
        pohja = pistelistaikkuna.getContentPane();
        sulje = new JButton("Sulje Pistelista");
        KomponenttienMuokkaus.luoNappula(sulje, 400, 50, 300, 75,
                Color.green.darker(), true, Color.BLUE.darker(), false, true, true, 26,
                Color.green, pohja);
        sulje.addActionListener(new PistelistaikkunanKuuntelija(this));
        pistelistaikkuna.getRootPane().setDefaultButton(sulje);
        pistelista.JLabelesitys(pohja);
    }

    public void sulje() {
        pistelistaikkuna.dispose();
    }
}
