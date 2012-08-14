package ohjelma;

import java.applet.AudioClip;

/**
 * Tämä luokka luo olion, jonka attribuutit on kuvan nimi merkkijonona ja audioklippi.
 * @author Timo Pekkanen
 */

public class LauluJaKuva {

    private AudioClip laulu;
    private String kuva;
    
    /**
     * Luokan konstruktori asettaa attribuuttien arvot.
     * @param laulu Syötetty audioklippi.
     * @param kuvannimi Syötetty merkkijono.
     */

    public LauluJaKuva(AudioClip laulu, String kuvannimi) {
        this.laulu = laulu;
        this.kuva = kuvannimi;
    }
    
    /**
     * Metodilla päästään käsiksi olion audioklippiin. 
     * @return Audioklippi, joka palautetaan.
     */

    public AudioClip palautaLaulu() {
        return laulu;
    }
    
    /**
     * Metodilla päästään käsiksi olion merkkijonoon.
     * @return Merkkijono, joka palautetaan.
     */

    public String palautaKuva() {
        return kuva;
    }
}
