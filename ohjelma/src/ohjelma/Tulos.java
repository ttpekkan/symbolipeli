package ohjelma;

/**
 * Luokka luo tulosolion. Luokan avulla pystyy myös järjestämään tuloksia.
 *
 * @author timo
 */
public class Tulos implements Comparable<Tulos> {

    private int pisteet;
    private String nimi;

    /**
     * Luokan konstruktori, joka luo Tulos-olion.
     *
     * @param nimi Annettu nimimerkki.
     * @param pisteet Annetut pisteet.
     */
    public Tulos(String nimi, int pisteet) {
        this.pisteet = pisteet;
        this.nimi = nimi;
    }

    public String palautaNimi() {
        return nimi;
    }

    public int palautaPisteet() {
        return pisteet;
    }

    /**
     * Tämän metodin avulla pystytään vertaamaan, ja siten järjestämään,
     * tulos-olioita.
     *
     * @param toinen Verrattava tulos-olio.
     * @return Vertauksessa käytetty luku.
     */
    public int compareTo(Tulos toinen) {
        if (this.pisteet == toinen.pisteet) {
            return this.nimi.compareTo(toinen.nimi);
        } else if (this.pisteet < toinen.pisteet) {
            return 1;
        } else if (this.pisteet == toinen.pisteet && this.nimi.equals(toinen.nimi)) {
            return 0;
        } else {
            return -1;
        }
    }
}
