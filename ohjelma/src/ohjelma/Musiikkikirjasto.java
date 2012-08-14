package ohjelma;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Tämän luokan avulla hoidetaan äänentoisto.
 * @author Timo Pekkanen
 */

public class Musiikkikirjasto {
    
    /**
     * Musiikkikirjaston attribuutit.
     * päävalikkolaulu: Päävalikon taustamusiiki.
     * pelilaulu: Pelin taustamusiikki.
     * lauluJaKuvaLista: ArrayList, josta arvotaan pelin taustamusiikki ja taustakuva.
     * voittolaulu: Laulu, joka soitetaan, kun pelaaja voitaa.
     * häviölaulu: Laulu, joka soitetaan, kun pelaaja häviää.
     * aloituslaulu: Laulu, joka soitetaan, kun pelaaja aloittaa pelin.
     */
    
    public void Attribuutit() {     
    }
    private static AudioClip päävalikkolaulu;
    private static AudioClip pelilaulu;
    private static ArrayList<LauluJaKuva> lauluJaKuvaLista;
    private static AudioClip voittolaulu;
    private static AudioClip häviölaulu;
    private static AudioClip aloituslaulu;

    /**
     * Luokan konstruktori. Luo halutut audioklipit ja yhden LauluJaKuva ArrayListin.
     */
    
    public Musiikkikirjasto() {
        lauluJaKuvaLista = new ArrayList<LauluJaKuva>();
        lisääKaikkiLaulutJaKuvat();
        voittolaulu = palautaHaluttuLaulu("win.wav");
        häviölaulu = palautaHaluttuLaulu("lose.wav");
        aloituslaulu = palautaHaluttuLaulu("pelinaloitus.wav");
        päävalikkolaulu = palautaHaluttuLaulu("päävalikko.wav");
    }
    
    /**
     * Tällä päästään käsiksi pelilauluun. 
     * @return Pelilaulu-audioklippi.
     */

    public AudioClip palautaPelilaulu() {
        return pelilaulu;
    }
    
    /**
     * Tällä päästään käsiksi päävalikkolauluun. 
     * @return päävalikko-audioklippi.
     */

    public AudioClip palautaPäävalikkolaulu() {
        return päävalikkolaulu;
    }
    
    /**
     * Tällä päästään käsiksi voitto-ääniefektiin. 
     * @return voitto-audioklippi.
     */

    public AudioClip palautaVoittolaulu() {
        return voittolaulu;
    }
    
    /**
     * Tällä päästään käsiksi häviö-ääniefektiin. 
     * @return häviö-audioklippi.
     */

    public AudioClip palautaHäviölaulu() {
        return häviölaulu;
    }
    
    /**
     * Tällä päästään käsiksi pelinaloitus-ääniefektiin. 
     * @return Pelinaloitus-audioklippi.
     */

    public AudioClip palautaAloituslaulu() {
        return aloituslaulu;
    }
    
    /**
     * Metodi palauttaa LauluJaKuva ArrayListin.
     * @return LauluJaKuva ArrayList.
     */

    public ArrayList<LauluJaKuva> PalautaLauluJaKuvaLista() {
        return lauluJaKuvaLista;
    }
    
    /**
     * Luo ja palauttaa audioklipin, joka ladataan halutusta tiedostosta.
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
     * @param tiedostonimi1 Haluttu audioklippi.
     * @param tiedostonimi2 Halutun kuvatiedoston nimi merkkijonona.
     */

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
    
    /**
     * Luo kahdeksan LauluJaKuva oliota ja lisää ne ArrayListiin.
     * 
     * Peli myös sekoittaa ArrayListin, samalla arpoen pelin taustakuvan ja pelimusiikin. 
     */

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
