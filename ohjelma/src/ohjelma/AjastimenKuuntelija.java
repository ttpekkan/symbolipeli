package ohjelma;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Tämän luokan avulla suoritetaan jäljellä olevan ajan laskeminen.
 * @author Timo Pekkanen
 */

public class AjastimenKuuntelija implements ActionListener {
    private PeliIkkuna ohjelma;
    
    /**
     * Luokan konstruktori. 
     * @param ohjelma Ohjelma on PeliIkkuna-luokka.  
     */

    public AjastimenKuuntelija(PeliIkkuna ohjelma) {
        this.ohjelma = ohjelma;
    }
    
    /**
     * Tämän metodin avulla luokka käyttää PeliIkkuna-luokassa olevaa metodia. 
     * @param e Sekunnin välein tapahtuva tapahtuma (kun ajastin päällä).
     */

    public void actionPerformed(ActionEvent e) {
        ohjelma.vähennäAikaa();
    }
}
