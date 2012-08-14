package ohjelma;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

/**
 * Tämä luokka valvoo pistelistaikkunassa olevaa nappulaa.
 * 
 * @author Timo Pekkanen
 */

public class PistelistanKuuntelija implements ActionListener {

    private Pistelista ohjelma;
    
    /**
     * Luokan konstruktori.
     * @param ohjelma Pistelistaikkuna.
     */

    public PistelistanKuuntelija(Pistelista ohjelma) {
        this.ohjelma = ohjelma;
    }
    
    /**
     * Metodi määrää mitä tehdään, kun nappia painetaan.
     * @param e Napinpainallus.
     */

    public void actionPerformed(ActionEvent e) {
        JButton nappula = ((JButton) e.getSource());
        if (nappula.getText().equals("Sulje Pistelista")) {
            ohjelma.vähennäIkkunoidenMäärää();
            ohjelma.palautaPisteListaIkkuna().dispose();
        }
    }
}