package ohjelma;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * Tämä luokka luo pelissä käytetyt kysymykset tekstitiedostoista.
 *
 * Kysymykset on jaettu kolmeen vaikeusasteen mukaan ja kukin kysymyssarja
 * tallennetaan omaan ArrayListiin.
 *
 * @author Timo Pekkanen
 */
public final class Kysymysgeneraattori {

    private ArrayList<Alkuaine> helpotKysymykset;
    private ArrayList<Alkuaine> keskivaikeatKysymykset;
    private ArrayList<Alkuaine> vaikeatKysymykset;

    /**
     *    Luokan konstruktori, joka luo kolme valmista ArrayListiä.
     */
    public Kysymysgeneraattori() {
        helpotKysymykset = new ArrayList<Alkuaine>();
        keskivaikeatKysymykset = new ArrayList<Alkuaine>();
        vaikeatKysymykset = new ArrayList<Alkuaine>();
        luoKaikkiKysymykset();
    }

    /**
     * Metodi, joka lataa tekstitieodoston kysymykset haluttuun ArrayListiin.
     *
     * @param tiedosto Tieodosto, josta kysymykset ladataan.
     * @param sarja ArrayList, johon kysymykset ladataan.
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
     *    Lataa helpot kysymykset ArrayListiin ja sekoittaa listan.
     */
    private void lataaHelpotKysymykset() {
        lataaKysymyssarja("helpotkysymykset.txt", helpotKysymykset);
        Collections.shuffle(helpotKysymykset);
    }

    /**
     * Lataa keskivaikeat kysymykset ArrayListiin ja sekoittaa listan.
     */
    private void lataaKeskivaikeatKysymykset() {
        lataaKysymyssarja("keskivaikeatkysymykset.txt", keskivaikeatKysymykset);
        Collections.shuffle(keskivaikeatKysymykset);
    }

    /**
     * Lataa vaikeat kysymykset ArrayListiin ja sekoittaa listan.
     */
    private void lataaVaikeatKysymykset() {
        lataaKysymyssarja("vaikeatkysymykset.txt", vaikeatKysymykset);
        Collections.shuffle(vaikeatKysymykset);
    }

    /**
     *    Lataa kaikki kolme kysymyssarjaa kerralla.
     */
    private void luoKaikkiKysymykset() {
        lataaHelpotKysymykset();
        lataaKeskivaikeatKysymykset();
        lataaVaikeatKysymykset();
    }

    /**
     * Metodi palauttaa helpon kysymyksen halutusta kohdasta.
     *
     * @param hakuluku Kohta, josta kysymys haetaan.
     * @return Palauttaa halutun alkuaine-kysymyksen.
     */
    public Alkuaine palautaHelppoKysymys(int hakuluku) {
        Alkuaine palautus = helpotKysymykset.get(hakuluku);
        return palautus;
    }

    /**
     * Metodi palauttaa keskivaikean kysymyksen halutusta kohdasta.
     *
     * @param hakuluku Kohta, josta kysymys haetaan.
     * @return Palauttaa halutun alkuaine-kysymyksen.
     */
    public Alkuaine palautaKeskivaikeaKysymys(int hakuluku) {
        Alkuaine palautus = keskivaikeatKysymykset.get(hakuluku);
        return palautus;
    }

    /**
     * Metodi palauttaa vaikean kysymyksen halutusta kohdasta.
     *
     * @param hakuluku Kohta, josta kysymys haetaan.
     * @return Palauttaa halutun alkuaine-kysymyksen.
     */
    public Alkuaine palautaVaikeaKysymys(int hakuluku) {
        Alkuaine palautus = vaikeatKysymykset.get(hakuluku);
        return palautus;
    }
}
