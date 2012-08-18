package ohjelma;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Tämän luokan avulla suoritetaan jäljellä olevan ajan laskeminen.
 *
 * @author Timo Pekkanen
 */
public class AjastimenKuuntelija implements ActionListener {

    private Peli_ikkuna peli_ikkuna;

    /**
     * Luokan konstruktori.
     *
     * @param ohjelma Ohjelma on Peli_ikkuna-luokka.
     */
    public AjastimenKuuntelija(Peli_ikkuna peli_ikkuna) {
        this.peli_ikkuna = peli_ikkuna;
    }

    /**
     * Tämän metodin avulla luokka käyttää peli_ikkuna luokassa olevaa metodia.
     *
     * @param e Sekunnin välein tapahtuva tapahtuma (kun ajastin päällä).
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        peli_ikkuna.vähennäAikaa();
    }
}
