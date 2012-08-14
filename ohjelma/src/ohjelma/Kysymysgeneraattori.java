package ohjelma;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * Tämä luokka generoi pelissä käytettävät kysymykset.
 *
 * @author timo
 */
public class Kysymysgeneraattori {

    private ArrayList<Alkuaine> helpotKysymykset;
    private ArrayList<Alkuaine> keskivaikeatKysymykset;
    private ArrayList<Alkuaine> vaikeatKysymykset;

    /**
     * Luokan konstruktori, joka luo kolme valmista ArrayListiä.
     */
    public Kysymysgeneraattori() {
        helpotKysymykset = new ArrayList<Alkuaine>();
        keskivaikeatKysymykset = new ArrayList<Alkuaine>();
        vaikeatKysymykset = new ArrayList<Alkuaine>();
        luoKaikkiKysymykset();
    }

    public ArrayList<Alkuaine> palautaHelpotKysymykset() {
        return helpotKysymykset;
    }

    public ArrayList<Alkuaine> palautaKeskivaikeatKysymykset() {
        return keskivaikeatKysymykset;
    }

    public ArrayList<Alkuaine> palautaVaikeatKysymykset() {
        return vaikeatKysymykset;
    }

    /**
     * Metodi lisää helpot kysymykset helpotKysymykset ArrayListiin ja sekoittaa
     * listan.
     */
    private void lataaKysymyssarja(String tiedosto, ArrayList<Alkuaine> sarja) {
        try {
            Scanner lukija = new Scanner(new File("src/" + tiedosto));
            while (lukija.hasNextLine()) {
                sarja.add(new Alkuaine(lukija.nextLine(), lukija.nextLine(), lukija.nextLine()));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void lataaHelpotKysymykset() {
        lataaKysymyssarja("helpotkysymykset.txt", helpotKysymykset);
        Collections.shuffle(helpotKysymykset);
    }

    /**
     * Metodi lisää keskivaikeat kysymykset keskivaikeatKysymykset ArrayListiin
     * ja sekoittaa listan.
     */
    private void lataaKeskivaikeatKysymykset() {
        lataaKysymyssarja("keskivaikeatkysymykset.txt", keskivaikeatKysymykset);
        Collections.shuffle(keskivaikeatKysymykset);
    }

    /**
     * Metodi lisää vaikeat kysymykset vaikeatKysymykset ArrayListiin ja
     * sekoittaa listan.
     */
    private void lataaVaikeatKysymykset() {
        lataaKysymyssarja("vaikeatkysymykset.txt", vaikeatKysymykset);
        Collections.shuffle(vaikeatKysymykset);
    }

    /**
     * Tämä metodi luo kaikki kysymykset kerralla, käyttäen edellisiä metodjeja.
     */
    private void luoKaikkiKysymykset() {
        lataaHelpotKysymykset();
        lataaKeskivaikeatKysymykset();
        lataaVaikeatKysymykset();
    }
}
