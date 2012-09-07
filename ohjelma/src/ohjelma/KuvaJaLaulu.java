package ohjelma;

import java.applet.AudioClip;

/**
 * Tämä luokka luo olion, jonka kenttiä ovat yksi kuvanNimi (String) ja yksi
 * AudioClip.
 *
 * Pelilaulu liittyy ainaa yhteen tiettyyn kuvaan ja tämä oli helppo tapa
 * liittää yksi laulu tiettyyn kuvaan.
 *
 * @author Timo Pekkanen
 */
public class KuvaJaLaulu {

    private String kuvanNimi;
    private AudioClip laulu;

    /**
     *  Luokan konstruktori, joka asettaa kenttien arvot.
     *
     *  @param kuvannimi Kuvan nimi.
     *  @param laulu AudioClip.
     */
    public KuvaJaLaulu(String kuvannimi, AudioClip laulu) {
        this.kuvanNimi = kuvannimi;
        this.laulu = laulu;
    }

    /**
     * Palauttaa olion AudioClipin.
     *
     * @return palautettu AudioClip.
     */
    public AudioClip palautaLaulu() {
        AudioClip palautus = laulu;
        return palautus;
    }

    /**
     * Palauttaa kuvan nimen.
     *
     * @return Palauttettu kuvan nimi.
     */
    public String palautaKuvanNimi() {
        String palautus = kuvanNimi;
        return palautus;
    }
}
