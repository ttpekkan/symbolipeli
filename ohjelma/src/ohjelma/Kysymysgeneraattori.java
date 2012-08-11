
package ohjelma;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 *Tämä luokka generoi pelissä käytettävät kysymykset.
 * 
 * @author timo
 */

public class Kysymysgeneraattori {
    
    public ArrayList<Alkuaine> helpotKysymykset;
    public ArrayList<Alkuaine> keskivaikeatKysymykset;
    public ArrayList<Alkuaine> vaikeatKysymykset;
    
    /**
     * Luokan konstruktori, joka luo kolme ArrayListiä. 
     */
    
    public Kysymysgeneraattori() {
        helpotKysymykset = new ArrayList<Alkuaine>();
        keskivaikeatKysymykset = new ArrayList<Alkuaine>();
        vaikeatKysymykset = new ArrayList<Alkuaine>();
    }
    
    /**
     * Metodi lisää helpot kysymykset helpotKysymykset ArrayListiin ja sekoittaa listan.
     */
    
    public void lataaHelpotKysymykset() {      
        try {
            Scanner lukija = new Scanner(new File("src/ohjelma/helpotkysymykset.txt"));
            while (lukija.hasNextLine()) {
                helpotKysymykset.add(new Alkuaine(lukija.nextLine(), lukija.nextLine(), lukija.nextLine()));
            }
        } catch (Exception e) {
        }
        Collections.shuffle(helpotKysymykset);
    }
    
    /**
     * Metodi lisää keskivaikeat kysymykset keskivaikeatKysymykset ArrayListiin ja sekoittaa listan.
     */
    
    public void lataaKeskivaikeatKysymykset() {     
        try {
            Scanner lukija = new Scanner(new File("src/ohjelma/keskivaikeatkysymykset.txt"));
            while (lukija.hasNextLine()) {
                keskivaikeatKysymykset.add(new Alkuaine(lukija.nextLine(), lukija.nextLine(), lukija.nextLine()));
            }
        } catch (Exception e) {
        }
        Collections.shuffle(keskivaikeatKysymykset);
    }
    
    /**
     * Metodi lisää vaikeat kysymykset vaikeatKysymykset ArrayListiin ja sekoittaa listan.
     */
    
    public void lataaVaikeatKysymykset() {    
        try {
            Scanner lukija = new Scanner(new File("src/ohjelma/vaikeatkysymykset.txt"));
            while (lukija.hasNextLine()) {
                vaikeatKysymykset.add(new Alkuaine(lukija.nextLine(), lukija.nextLine(), lukija.nextLine()));
            }
        } catch (Exception e) {
        }
        Collections.shuffle(vaikeatKysymykset);
    }
    
    /**
     * Tämä metodi luo kaikki kysymykset kerralla, käyttäen edellisiä metodjeja. 
     */
    
    public void luoKaikkiKysymykset() {
        lataaHelpotKysymykset();
        lataaKeskivaikeatKysymykset();
        lataaVaikeatKysymykset();
    }
}
