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
     * 
     */

    public String palautaKuvannimi() {
        return lauluJaKuvaLista.get(0).palautaKuva();
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
         // String nimi = "/home/timo/symbolipeli/ohjelma/src/" + tiedostonimi2;
            String nimi = tiedostonimi2;
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
        lisääLaulu("stageselect.wav", "stageselect.png");
        lisääLaulu("mylittlepony.wav", "mylittlepony.png");
        lisääLaulu("lovegravy.wav", "lovegravy.png");
        lisääLaulu("teaparty.wav", "teaparty.png");
        lisääLaulu("always.wav", "always.png");
        lisääLaulu("future.wav", "future.png");
        lisääLaulu("avaus.wav", "avaus.png");
        lisääLaulu("otsikko.wav", "otsikko.png");
        Collections.shuffle(lauluJaKuvaLista);
        pelilaulu = lauluJaKuvaLista.get(0).palautaLaulu();
    }
}
