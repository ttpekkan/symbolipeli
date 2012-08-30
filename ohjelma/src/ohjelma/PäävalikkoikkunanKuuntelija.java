package ohjelma;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

/**
 * Tämä luokka valvoo, mitä nappeja päävalikkoikkunassa painetaan.
 *
 * @author Timo Pekkanen
 */
public class PäävalikkoikkunanKuuntelija implements ActionListener {
    private Päävalikkoikkuna päävalikkoikkuna;

    /**
     * Luokan konstruktori.
     *
     * @param ohjelma Päävalikkoikkuna.
     */
    public PäävalikkoikkunanKuuntelija(Päävalikkoikkuna päävalikkoikkuna) {
        this.päävalikkoikkuna = päävalikkoikkuna;
    }

    /**
     * Tämä metodi kertoo mitä tehdä, kun jotain nappia painetaan.
     *
     * @param e Napinpainallus.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton nappula = ((JButton) e.getSource());
        if (nappula.getText().equals("Aloita Peli")) {
            Musiikkikirjasto.pysäytäPäävalikkolaulu();
            päävalikkoikkuna.peli_ikkuna();
        } else if (nappula.getText().equals("Lopeta")) {
            System.exit(0);
        } else {
           päävalikkoikkuna.pistelista();
        }
    }
}
