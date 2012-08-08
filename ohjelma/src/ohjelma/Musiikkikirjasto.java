package ohjelma;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Musiikkikirjasto {
    public ArrayList<AudioClip> lista;
    public AudioClip päävalikkoMusa;
    public AudioClip pelimusa;
    
    public Musiikkikirjasto() {
        lista = new ArrayList<AudioClip>();     
    } 

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
    
    public void uusiPeli() {
        lisääBiisit();
    /*    aloitusKlippi();
        try {
            Thread.sleep(7000);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } */
        pelimusa.loop();
    }
    
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
