package ohjelma;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * Tämä luokka generoi pelissä käytettävät kysymykset.
 *
 * @author Timo Pekkanen
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

    /**
     * Metodi palauttaa helpon alkuaineen.
     *
     * @param hakuluku Alkuaineen sijainti ArrayListissä.
     * @return Haluttu alkuaine.
     */
    public Alkuaine palautaHelppoKysymys(int hakuluku) {
        return helpotKysymykset.get(hakuluku);
    }

    /**
     * Metodi palauttaa keskivaikean alkuaineen.
     *
     * @param hakuluku Alkuaineen sijainti ArrayListissä.
     * @return Haluttu alkuaine.
     */
    public Alkuaine palautaKeskivaikeaKysymys(int hakuluku) {
        return keskivaikeatKysymykset.get(hakuluku);
    }

    /**
     * Metodi palauttaa vaikean alkuaineen.
     *
     * @param hakuluku Alkuaineen sijainti ArrayListissä.
     * @return Haluttu alkuaine.
     */
    public Alkuaine palautaVaikeaKysymys(int hakuluku) {
        return vaikeatKysymykset.get(hakuluku);
    }

    /**
     * Lataa kysymysarjan ArrayListiin.
     *
     * @param tiedosto Tiedosto, josta alkuaineet ladataan.
     * @param sarja ArrayList, johon alkuaineet lisätään.
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

    /**
     * Lisää helpot alkuaineet ArrayListiin ja sekoittaa sen.
     */
    private void lataaHelpotKysymykset() {
        lataaKysymyssarja("helpotkysymykset.txt", helpotKysymykset);
        Collections.shuffle(helpotKysymykset);
    }

    /**
     * Lisää keskivaikeat alkuaineet ArrayListiin ja sekoittaa sen.
     */
    private void lataaKeskivaikeatKysymykset() {
        lataaKysymyssarja("keskivaikeatkysymykset.txt", keskivaikeatKysymykset);
        Collections.shuffle(keskivaikeatKysymykset);
    }

    /**
     * Lisää vaikeat alkuaineet ArrayListiin ja sekoittaa sen.
     */
    private void lataaVaikeatKysymykset() {
        lataaKysymyssarja("vaikeatkysymykset.txt", vaikeatKysymykset);
        Collections.shuffle(vaikeatKysymykset);
    }

    /**
     * Lataa kaikki alkuaineet, edellisiä metodeja hyväksikäyttäen.
     */
    private void luoKaikkiKysymykset() {
        lataaHelpotKysymykset();
        lataaKeskivaikeatKysymykset();
        lataaVaikeatKysymykset();
    }
}
