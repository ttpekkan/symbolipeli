package ohjelma;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 * Tämä luokka sisältää taustamusiikit ja muutaman ääniefektin.
 *
 * Metodeilla luodaan biisit ja ohjataan niiden käyttöä.
 *
 * @author timo
 */
public class Musiikkikirjasto {

    public ArrayList<AudioClip> lista;
    public static AudioClip päävalikkoMusa;
    public AudioClip pelimusa;
    public static ArrayList<AudioClip> yksiBiisi;
    public ArrayList<BiisiJaKuva> lista2;

    /**
     * Luokan konstruktori. Luo ArrayListin, joka sisältää AudioClippeja.
     *
     * yksiBiisi arrayListiä käytetään testauksen apuna.
     */
    public Musiikkikirjasto() {
        lista = new ArrayList<AudioClip>();
        lista2 = new ArrayList<BiisiJaKuva>();
        yksiBiisi = new ArrayList<AudioClip>();
    }

    /**
     * Tällä metodilla etsitään taustamusiikkitiedosto ja sitten loopataan se.
     */
    public static void aloitaTaustaMusa() {
        try {
            File currentDir = new File(".");
            URL currentDirURL = currentDir.toURL();
            URL url = new URL(currentDirURL, "src/päävalikkoMusa.wav");
            päävalikkoMusa = Applet.newAudioClip(url);
            päävalikkoMusa.loop();
            yksiBiisi.clear();
            yksiBiisi.add(päävalikkoMusa);
        } catch (Exception e) {
        }
    }

    /**
     * Etsii ja soittaa (yhden kerran) pelin aloituksessa käytettävän
     * musiikkitiedoston.
     */
    public void aloitusKlippi() {
        try {
            File currentDir = new File(".");
            URL currentDirURL = currentDir.toURL();
            URL url = new URL(currentDirURL, "src/pelinaloitus.wav");
            AudioClip musa = Applet.newAudioClip(url);
            musa.play();           
            yksiBiisi.clear();
            yksiBiisi.add(musa);
        } catch (Exception e) {
        }
    }

    /**
     * Tämän metodin avulla voi lisätä AudioClippeja ArrayListiin.
     *
     * @param tiedostonimi Etsittävä musiikkitiedosto.
     */
    /*
     * public void lisääBiisi(String tiedostonimi) { try { File currentDir = new
     * File("."); URL currentDirURL = currentDir.toURL(); URL url = new
     * URL(currentDirURL, "src/" + tiedostonimi); AudioClip clip =
     * Applet.newAudioClip(url); lista.add(clip); } catch (Exception e) { } }
     */
    /**
     * Tämän metodin avulla soitetaan aloitusklippi, jonka jälkeen aloitetaan
     * pelimusiikki (loopattuna).
     *
     * Thread.sleep ajetaan sen vuoksi, ettei musiikit menisi päällekkäin.
     * Täytyy ehkä löytää parempi tapa estää musiikkien päällekkäisyys.
     */
    public void uusiPeli() {
        lisääBiisit2();
    }

    /**
     * Metodi soittaa voitto-ääniefektin.
     */
    public void voitto() {
        try {
            File currentDir = new File(".");
            URL currentDirURL = currentDir.toURL();
            URL url = new URL(currentDirURL, "src/win.wav");
            AudioClip voitto = Applet.newAudioClip(url);
            voitto.play();
            try {
                Thread.sleep(6000);
            } catch (Exception e) {
            }
            yksiBiisi.clear();
            yksiBiisi.add(voitto);
        } catch (Exception e) {
        }
    }

    /**
     * Metodi soittaa häviö-ääniefektin.
     */
    public void häviö() {
        try {
            File currentDir = new File(".");
            URL currentDirURL = currentDir.toURL();
            URL url = new URL(currentDirURL, "src/lose.wav");
            AudioClip häviö = Applet.newAudioClip(url);
            häviö.play();
            try {
                Thread.sleep(4000);
            } catch (Exception eA) {
            }
            yksiBiisi.clear();
            yksiBiisi.add(häviö);
        } catch (Exception e) {
        }
    }

    /**
     * Metodi lisää halutut biisit ArrayListiin AudioClippeinä ja sitten
     * sekoittaa listan.
     */
    /*
     * public void lisääBiisit() { lisääBiisi("stageselect.wav");
     * lisääBiisi("mylittlepony.wav"); lisääBiisi("lovegravy.wav");
     * lisääBiisi("teaparty.wav"); lisääBiisi("always.wav");
     * lisääBiisi("future.wav"); lisääBiisi("avaus.wav");
     * lisääBiisi("otsikko.wav"); Collections.shuffle(lista); pelimusa =
     * lista.get(0); }
     */
    public void lisääBiisi2(String tiedostonimi1, String tiedostonimi2) {
        try {
            File currentDir = new File(".");
            URL currentDirURL = currentDir.toURL();
            URL url1 = new URL(currentDirURL, "src/" + tiedostonimi1);
            AudioClip clip = Applet.newAudioClip(url1);
            String nimi = "/home/timo/symbolipeli/ohjelma/src/" + tiedostonimi2;
            lista2.add(new BiisiJaKuva(clip, nimi));
        } catch (Exception e) {
        }
    }

    public void lisääBiisit2() {
        lisääBiisi2("stageselect.wav", "stageselect.jpg");
        lisääBiisi2("mylittlepony.wav", "mylittlepony.jpg");
        lisääBiisi2("lovegravy.wav", "lovegravy.JPG");
        lisääBiisi2("teaparty.wav", "teaparty.jpeg");
        lisääBiisi2("always.wav", "always.png");
        lisääBiisi2("future.wav", "future.png");
        lisääBiisi2("avaus.wav", "avaus.jpg");
        lisääBiisi2("otsikko.wav", "otsikko.jpg");
        Collections.shuffle(lista2);
        pelimusa = lista2.get(0).laulu;
    }
}
