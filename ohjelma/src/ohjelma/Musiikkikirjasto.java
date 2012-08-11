package ohjelma;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Tämä luokka sisältää taustamusiikit ja muutaman ääniefektin.
 * 
 * Metodeilla luodaan biisit ja ohjataan niiden käyttöä. 
 * @author timo
 */


public class Musiikkikirjasto {
    public ArrayList<AudioClip> lista;
    public AudioClip päävalikkoMusa;
    public AudioClip pelimusa;
    
   /**
    * Luokan konstruktori. Luo ArrayListin, joka sisältää AudioClippeja. 
    */
    
    public Musiikkikirjasto() {
        lista = new ArrayList<AudioClip>();     
    } 
    
    /**
     * Tällä metodilla etsitään taustamusiikkitiedosto ja sitten loopataan se. 
     */

    public void aloitaTaustaMusa() {
        try {
        File currentDir = new File(".");
        URL currentDirURL = currentDir.toURL();
        URL url = new URL(currentDirURL, "src/päävalikkoMusa.wav");
        päävalikkoMusa = Applet.newAudioClip(url);
        päävalikkoMusa.loop();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    /**
     * Etsii ja soittaa (yhden kerran) pelin aloituksessa käytettävän musiikkitiedoston. 
     */
    
    public void aloitusKlippi() {
        try {
        File currentDir = new File(".");
        URL currentDirURL = currentDir.toURL();
        URL url = new URL(currentDirURL, "src/pelinaloitus.wav");
        AudioClip musa = Applet.newAudioClip(url);
        musa.play();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    /**
     * Tämän metodin avulla voi lisätä AudioClippeja ArrayListiin. 
     * 
     * @param tiedostonimi Etsittävä musiikkitiedosto.
     */
    
    public void lisääBiisi(String tiedostonimi) {
        try {
        File currentDir = new File(".");
        URL currentDirURL = currentDir.toURL();
        URL url = new URL(currentDirURL, "src/" + tiedostonimi);
        AudioClip clip = Applet.newAudioClip(url);
        lista.add(clip);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    /**
     * Tämän metodin avulla soitetaan aloitusklippi, jonka jälkeen aloitetaan pelimusiikki (loopattuna).
     * 
     * Thread.sleep ajetaan sen vuoksi, ettei musiikit menisi päällekkäin. Täytyy ehkä löytää parempi
     * tapa estää musiikkien päällekkäisyys. 
     */
    
    public void uusiPeli() {
        lisääBiisit();
        aloitusKlippi();
        try {
            Thread.sleep(7000);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } 
        pelimusa.loop();
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
        } catch (Exception e) {
            System.out.println(e.getMessage());
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
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    /**
     * Metodi lisää halutut biisit ArrayListiin AudioClippeinä ja sitten sekoittaa listan. 
     */    
    
    public void lisääBiisit() {
        lisääBiisi("stageselect.wav");
        lisääBiisi("mylittlepony.wav");
        lisääBiisi("lovegravy.wav");
        lisääBiisi("teaparty.wav");
        lisääBiisi("always.wav");
        lisääBiisi("future.wav");
        lisääBiisi("avaus.wav");
        lisääBiisi("otsikko.wav");
        Collections.shuffle(lista);
        pelimusa = lista.get(0);
    }
    
}
