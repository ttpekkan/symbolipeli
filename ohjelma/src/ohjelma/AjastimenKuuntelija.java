package ohjelma;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Tämä luokka seuraa peli_ikkunanassa olevaa ajastinta. Tämän luokan avulla
 * voidaan käyttää peli_ikkunassa olevaa vähennäAikaa-metodia.
 *
 * @author Timo Pekkanen
 */
public class AjastimenKuuntelija implements ActionListener {

    private Peli_ikkuna peli_ikkuna;

    /**
     * Luokan konstruktori, joka kertoo, mitä oliota halutaan kuunnella.
     *
     * @param peli_ikkuna Parametri on peli_ikkuna-olio, jota tämä luokka
     * kuuntelee.
     */
    public AjastimenKuuntelija(Peli_ikkuna peli_ikkuna) {
        this.peli_ikkuna = peli_ikkuna;
    }

    /**
     * Metodi suorittaa peli_ikkunassa olevaa vähennäAikaa-metodia sekunnin
     * välein kun ajastin on päällä.
     *
     * @param e Parametri on ajastimen tapahtuma.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        peli_ikkuna.vähennäAikaa();
    }
}
