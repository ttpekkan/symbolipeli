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
 * Äänentoisto oli luontevinta hoitaa staattisten metodien ja kenttien kautta.
 * Koska pelilaulut liittyvät aine tiettyyn kuvaan, niin kuvatiedoston
 * lataaminen tehdään myös tämän luokan kautta.
 *
 * @author Timo Pekkanen
 */
public class Musiikkikirjasto {

    private static AudioClip aloituslaulu = palautaHaluttuLaulu("pelinaloitus.wav");
    private static AudioClip häviölaulu = palautaHaluttuLaulu("lose.wav");
    private static AudioClip päävalikkolaulu = palautaHaluttuLaulu("päävalikko.wav");
    private static AudioClip voittolaulu = palautaHaluttuLaulu("win.wav");
    private static ArrayList<KuvaJaLaulu> kuvaJaLaulu_Lista = new ArrayList<KuvaJaLaulu>();

    /**
     * Laitetaan pelilaulu jatkuvaan toistoon.
     */
    public static void jatkuvaToistoPelilaulu() {
        AudioClip pelilaulu = kuvaJaLaulu_Lista.get(0).palautaLaulu();
        pelilaulu.loop();
    }

    /**
     * Pysäytetään pelilaulu.
     */
    public static void pysäytäPelilaulu() {
        AudioClip pelilaulu = kuvaJaLaulu_Lista.get(0).palautaLaulu();
        pelilaulu.stop();
    }

    /**
     * Laitetaan päävalikko laulu jatkuvaan toistoon.
     */
    public static void jatkuvaToistoPäävalikkolaulu() {
        päävalikkolaulu.loop();
    }

    /**
     * Pysäytetään päävalikkolaulu.
     */
    public static void pysäytäPäävalikkolaulu() {
        päävalikkolaulu.stop();
    }

    /**
     * Soita voittolaulu.
     */
    public static void soitaVoittolaulu() {
        voittolaulu.play();
    }

    /**
     * Soita häviölaulu.
     */
    public static void soitaHäviölaulu() {
        häviölaulu.play();
    }

    /**
     * Soita aloituslaulu.
     */
    public static void soitaAloituslaulu() {
        aloituslaulu.play();
    }

    /**
     * Pysäyttää aloituslaulun.
     */
    public static void pysäytäAloituslaulu() {
        aloituslaulu.stop();
    }

    /**
     * Palauttaa kuvan nimen kuvaJaLaulu_Listasta.
     */
    public static String palautaKuvannimi() {
        lisääKaikkiLaulutJaKuvat();
        Collections.shuffle(kuvaJaLaulu_Lista);
        return kuvaJaLaulu_Lista.get(0).palautaKuva();
    }

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
     *
     * Peli myös sekoittaa ArrayListin, samalla arpoen pelin taustakuvan ja
     * pelimusiikin.
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
}
