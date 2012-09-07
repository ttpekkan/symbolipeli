package ohjelma;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Tämä on kirjastoluokka, jonka avulla hoidetaan pelin äänentoisto. 
 * 
 * @author Timo Pekkanen
 */
public class Musiikkikirjasto {

    private static AudioClip aloituslaulu = palautaHaluttuLaulu("pelinaloitus.wav");
    private static AudioClip häviölaulu = palautaHaluttuLaulu("lose.wav");
    private static AudioClip valikkolaulu = palautaHaluttuLaulu("valikko.wav");
    private static AudioClip voittolaulu = palautaHaluttuLaulu("win.wav");
    private static ArrayList<KuvaJaLaulu> kuvaJaLaulu_Lista = new ArrayList<KuvaJaLaulu>();

    /**
     * Luo ja palauttaa audioklipin, joka ladataan halutusta tiedostosta.
     *
     * @param tiedosto Haluttu äänitiedosto.
     * @return Palautettu audioklippi.
     */
    private static AudioClip palautaHaluttuLaulu(String tiedosto) {
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
    private static void lisääKuvaJaLaulu(String tiedostonimi1, String tiedostonimi2) {
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
     */
    private static void lisääKaikkiLaulutJaKuvat() {
        lisääKuvaJaLaulu("stageselect.png", "stageselect.wav");
        lisääKuvaJaLaulu("mylittlepony.png", "mylittlepony.wav");
        lisääKuvaJaLaulu("lovegravy.png", "lovegravy.wav");
        lisääKuvaJaLaulu("teaparty.png", "teaparty.wav");
        lisääKuvaJaLaulu("always.png", "always.wav");
        lisääKuvaJaLaulu("future.png", "future.wav");
        lisääKuvaJaLaulu("avaus.png", "avaus.wav");
        lisääKuvaJaLaulu("otsikko.png", "otsikko.wav");
    }

    /**
     * Metodi asettaa pelilaulun jatkuvaan toistoon.
     */
    public static void jatkuvaToistoPelilaulu() {
        AudioClip pelilaulu = kuvaJaLaulu_Lista.get(0).palautaLaulu();
        pelilaulu.loop();
    }

    /**
     * Metodi pysäyttää pelilaulun.
     */
    public static void pysäytäPelilaulu() {
        AudioClip pelilaulu = kuvaJaLaulu_Lista.get(0).palautaLaulu();
        pelilaulu.stop();
    }

    /**
     * Metodi asettaa valikkolualun jatkuvaan toistoon.
     */
    public static void jatkuvaToistoValikkolaulu() {
        valikkolaulu.loop();
    }

    /**
     * Metodi pysäyttää valikkolaulun.
     */
    public static void pysäytäValikkolaulu() {
        valikkolaulu.stop();
    }

    /**
     * Metodi soittaa (yhden kerran) voittolaulun.
     */
    public static void soitaVoittolaulu() {
        voittolaulu.play();
    }

    /**
     * Metodi soittaa (yhden kerran) häviölaulun.
     */
    public static void soitaHäviölaulu() {
        häviölaulu.play();
    }

    /**
     * Metodi soittaa (yhden kerran) pelinkäynnistyslaulun.
     */
    public static void soitaAloituslaulu() {
        aloituslaulu.play();
    }

    /**
     * Metodi pysäyttää pelinkäynnistyslaulun.
     */
    public static void pysäytäAloituslaulu() {
        aloituslaulu.stop();
    }

    /**
     * Metodi palauttaa pelilauluun liittyvän kuvan nimen.
     */
    public static String palautaKuvannimi() {
        lisääKaikkiLaulutJaKuvat();
        Collections.shuffle(kuvaJaLaulu_Lista);
        return kuvaJaLaulu_Lista.get(0).palautaKuvanNimi();
    }
}
