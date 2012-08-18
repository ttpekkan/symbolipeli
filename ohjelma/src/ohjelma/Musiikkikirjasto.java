package ohjelma;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Tämän luokan avulla hoidetaan äänentoisto.
 *
 * @author Timo Pekkanen
 */
public class Musiikkikirjasto {
    
    private static AudioClip aloituslaulu;
    private static AudioClip häviölaulu;
    private static AudioClip pelilaulu;    
    private static AudioClip päävalikkolaulu;    
    private static AudioClip voittolaulu;    
    private static ArrayList<KuvaJaLaulu> kuvaJaLaulu_Lista;

    /**
     * Luokan konstruktori. Luo halutut audioklipit ja yhden LauluJaKuva
     * ArrayListin.
     */    
    public Musiikkikirjasto() {
        aloituslaulu = palautaHaluttuLaulu("pelinaloitus.wav");
        häviölaulu = palautaHaluttuLaulu("lose.wav");
        päävalikkolaulu = palautaHaluttuLaulu("päävalikko.wav");
        voittolaulu = palautaHaluttuLaulu("win.wav");        
        kuvaJaLaulu_Lista = new ArrayList<KuvaJaLaulu>();
        lisääKaikkiLaulutJaKuvat();
    }

    /**
     * Laitetaan pelilaulu jatkuvaan toistoon.
     */
    public void jatkuvaToistoPelilaulu() {
        pelilaulu.loop();
    }

    /**
     * Pysäytetään pelilaulu.
     */    
    public void pysäytäPelilaulu() {
        pelilaulu.stop();
    }

    /**
     * Laitetaan päävalikko laulu jatkuvaan toistoon.
     */
    public void jatkuvaToistoPäävalikkolaulu() {
        päävalikkolaulu.loop();
    }

    /**
     * Pysäytetään päävalikkolaulu.
     */
    public void pysäytäPäävalikkolaulu() {
        päävalikkolaulu.stop();
    }

    /**
     * Soita voittolaulu.
     */
    public void soitaVoittolaulu() {
        voittolaulu.play();
    }

    /**
     * Soita häviölaulu.
     */
    public void soitaHäviölaulu() {
        häviölaulu.play();
    }

    /**
     * Soita aloituslaulu.
     */
    public void soitaAloituslaulu() {
        aloituslaulu.play();
    }

    /**
     * Pysäyttää aloituslaulun.
     */    
    public void pysäytäAloituslaulu() {
        aloituslaulu.stop();
    }

    /**
     * Palauttaa kuvan nimen kuvaJaLaulu_Listasta.
     */
    public String palautaKuvannimi() {
        return kuvaJaLaulu_Lista.get(0).palautaKuva();
    }

    /**
     * Luo ja palauttaa audioklipin, joka ladataan halutusta tiedostosta.
     *
     * @param tiedosto Haluttu äänitiedosto.
     * @return Palautettu audioklippi.
     */
    private AudioClip palautaHaluttuLaulu(String tiedosto) {
        AudioClip musa = null;
        try {
            File currentDir = new File(".");
            URL currentDirURL = currentDir.toURL();
            URL url = new URL(currentDirURL, "src/" + tiedosto);
            musa = Applet.newAudioClip(url);
        } catch (Exception e) {
            System.out.println(e);
        }
        return musa;
    }

    /**
     * Metodi luo LauluJaKuva-olion ja lisää sen ArrayListiin.
     *
     * @param tiedostonimi1 Haluttu audioklippi.
     * @param tiedostonimi2 Halutun kuvatiedoston nimi merkkijonona.
     */
    private void lisääKuvaJaLaulu(String tiedostonimi1, String tiedostonimi2) {
        try {
            File currentDir = new File(".");
            URL currentDirURL = currentDir.toURL();
            URL url1 = new URL(currentDirURL, "src/" + tiedostonimi2);
            AudioClip clip = Applet.newAudioClip(url1);
            // String nimi = "/home/timo/symbolipeli/ohjelma/src/" + tiedostonimi2;
            String nimi = tiedostonimi1;
            kuvaJaLaulu_Lista.add(new KuvaJaLaulu(nimi, clip));
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Luo kahdeksan LauluJaKuva oliota ja lisää ne ArrayListiin.
     *
     * Peli myös sekoittaa ArrayListin, samalla arpoen pelin taustakuvan ja
     * pelimusiikin.
     */
    private void lisääKaikkiLaulutJaKuvat() {
        lisääKuvaJaLaulu("stageselect.png", "stageselect.wav");
        lisääKuvaJaLaulu("mylittlepony.png", "mylittlepony.wav");
        lisääKuvaJaLaulu("lovegravy.png", "lovegravy.wav");
        lisääKuvaJaLaulu("teaparty.png", "teaparty.wav");
        lisääKuvaJaLaulu("always.png", "always.wav");
        lisääKuvaJaLaulu("future.png", "future.wav");
        lisääKuvaJaLaulu("avaus.png", "avaus.wav");
        lisääKuvaJaLaulu("otsikko.png", "otsikko.wav");
        Collections.shuffle(kuvaJaLaulu_Lista);
        pelilaulu = kuvaJaLaulu_Lista.get(0).palautaLaulu();
    }
}
