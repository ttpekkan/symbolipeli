package ohjelma;

import java.applet.AudioClip;

/**
 * Tämä luokka luo olion, jonka attribuutit on kuvan nimi merkkijonona ja
 * audioklippi.
 *
 * @author Timo Pekkanen
 */
public class KuvaJaLaulu {

    private String kuva;
    private AudioClip laulu;

    /**
     * Luokan konstruktori asettaa attribuuttien arvot.
     *
     * @param kuvannimi Syötetty merkkijono.
     * @param laulu Syötetty audioklippi.
     */
    public KuvaJaLaulu(String kuvannimi, AudioClip laulu) {
        this.kuva = kuvannimi;
        this.laulu = laulu;
    }

    /**
     * Metodilla palauttaa luokan Audioklipin.
     *
     * @return Audioklippi, joka palautetaan.
     */
    public AudioClip palautaLaulu() {
        AudioClip palautus = laulu;
        return palautus;
    }

    /**
     * Metodilla palauttaa kuvan nimen.
     *
     * @return Merkkijono, joka palautetaan.
     */
    public String palautaKuva() {
        String palautus = kuva;
        return palautus;
    }
}
