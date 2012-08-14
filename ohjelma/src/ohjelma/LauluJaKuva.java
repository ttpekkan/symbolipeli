package ohjelma;

import java.applet.AudioClip;

public class LauluJaKuva {

    private AudioClip laulu;
    private String kuva;

    public LauluJaKuva(AudioClip laulu, String kuvannimi) {
        this.laulu = laulu;
        this.kuva = kuvannimi;
    }

    public AudioClip palautaLaulu() {
        return laulu;
    }

    public String palautaKuva() {
        return kuva;
    }
}
