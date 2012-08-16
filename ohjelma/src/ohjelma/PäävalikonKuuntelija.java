package ohjelma;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.SwingUtilities;

/**
 * Tämä luokka valvoo, mitä nappeja päävalikkoikkunassa painetaan.
 * 
 * @author Timo Pekkanen
 */

public class PäävalikonKuuntelija implements ActionListener {

    private Päävalikko ohjelma;
    
    /**
     * Luokan konstruktori.
     * 
     * @param ohjelma Päävalikkoikkuna. 
     */

    public PäävalikonKuuntelija(Päävalikko ohjelma) {
        this.ohjelma = ohjelma;
    }
    
    /**
     * Tämä metodi kertoo mitä tehdä, kun jotain nappia painetaan.
     * @param e Napinpainallus.
     */

    public void actionPerformed(ActionEvent e) {
        JButton nappula = ((JButton) e.getSource());
        if (nappula.getText().equals("Aloita Peli")) {
            ohjelma.palautaMusiikkikirjasto().pysäytäPäävalikkolaulu();
            PeliIkkuna uusi = new PeliIkkuna();
            SwingUtilities.invokeLater(uusi);
        } else if (nappula.getText().equals("Lopeta")) {
            System.exit(0);
        } else {
            Pistelista uusi = new Pistelista();
            SwingUtilities.invokeLater(uusi);
        }
    }
}
