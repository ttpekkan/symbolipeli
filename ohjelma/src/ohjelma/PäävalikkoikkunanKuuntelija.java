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
        if (nappula.getText().equals("Aloita Peli") && MuokkaaKomponenttia.palautaLaskurinArvo() == 0) {
            Musiikkikirjasto.pysäytäPäävalikkolaulu();
            päävalikkoikkuna.peli_ikkuna();
        } else if (nappula.getText().equals("Lopeta") && MuokkaaKomponenttia.palautaLaskurinArvo() == 0) {
            System.exit(0);
        } else {
            if (MuokkaaKomponenttia.palautaLaskurinArvo() == 0) {
                päävalikkoikkuna.pistelista();
            }
        }
    }
}
