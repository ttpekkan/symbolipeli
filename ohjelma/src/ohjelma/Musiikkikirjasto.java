package ohjelma;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Tämä luokka sisältää taustamusiikit ja muutaman ääniefektin.
 *
 * Metodeilla luodaan biisit ja ohjataan niiden käyttöä.
 *
 * @author timo
 */
public class Musiikkikirjasto {

    private static AudioClip päävalikkolaulu;
    private static AudioClip pelilaulu;
    private static ArrayList<LauluJaKuva> lauluJaKuvaLista;
    private static AudioClip voittolaulu;
    private static AudioClip häviölaulu;
    private static AudioClip aloituslaulu;

    /**
     * Luokan konstruktori. Luo ArrayListin, joka sisältää AudioClippeja.
     *
     * yksiBiisi arrayListiä käytetään testauksen apuna.
     */
    public Musiikkikirjasto() {
        lauluJaKuvaLista = new ArrayList<LauluJaKuva>();
        lisääKaikkiLaulutJaKuvat();
        voittolaulu = palautaHaluttuLaulu("win.wav");
        häviölaulu = palautaHaluttuLaulu("lose.wav");
        aloituslaulu = palautaHaluttuLaulu("pelinaloitus.wav");
        päävalikkolaulu = palautaHaluttuLaulu("päävalikko.wav");
    }

    public AudioClip palautaPelilaulu() {
        return pelilaulu;
    }

    public AudioClip palautaPäävalikkolaulu() {
        return päävalikkolaulu;
    }

    public AudioClip palautaVoittolaulu() {
        return voittolaulu;
    }

    public AudioClip palautaHäviölaulu() {
        return häviölaulu;
    }

    public AudioClip palautaAloituslaulu() {
        return aloituslaulu;
    }

    public ArrayList<LauluJaKuva> PalautaLauluJaKuvaLista() {
        return lauluJaKuvaLista;
    }

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

    private void lisääLaulu(String tiedostonimi1, String tiedostonimi2) {
        try {
            File currentDir = new File(".");
            URL currentDirURL = currentDir.toURL();
            URL url1 = new URL(currentDirURL, "src/" + tiedostonimi1);
            AudioClip clip = Applet.newAudioClip(url1);
            String nimi = "/home/timo/symbolipeli/ohjelma/src/" + tiedostonimi2;
            lauluJaKuvaLista.add(new LauluJaKuva(clip, nimi));
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void lisääKaikkiLaulutJaKuvat() {
        lisääLaulu("stageselect.wav", "stageselect.jpg");
        lisääLaulu("mylittlepony.wav", "mylittlepony.jpg");
        lisääLaulu("lovegravy.wav", "lovegravy.JPG");
        lisääLaulu("teaparty.wav", "teaparty.jpeg");
        lisääLaulu("always.wav", "always.png");
        lisääLaulu("future.wav", "future.png");
        lisääLaulu("avaus.wav", "avaus.jpg");
        lisääLaulu("otsikko.wav", "otsikko.jpg");
        Collections.shuffle(lauluJaKuvaLista);
        pelilaulu = lauluJaKuvaLista.get(0).palautaLaulu();
    }
}
