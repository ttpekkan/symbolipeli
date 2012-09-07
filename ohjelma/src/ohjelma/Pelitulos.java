package ohjelma;

/**
 *  Luokka luo pelitulos-olion, joka voidaan lisätä pistelistaan.
 *
 *  Luokka mahdollistaa myös pelituloksien vertailun.
 *
 *  @author Timo Pekkanen
 */
public class Pelitulos implements Comparable<Pelitulos> {

    private String nimi;
    private int pisteet;

    /**
     * Luokan konstruktori, joka luo Tulos-olion.
     *
     * @param nimi Annettu nimimerkki.
     * @param pisteet Annetut pisteet.
     */
    public Pelitulos(String nimi, int pisteet) {
        this.pisteet = pisteet;
        this.nimi = nimi;
    }

    /**
     * Palauttaa pelituloksen nimen.
     *
     * @return Palautettu nimi.
     */
    public String palautaNimi() {
        String palautus = nimi;
        return palautus;
    }

    /**
     * Palauttaa pelituloksen pisteet.
     *
     * @return Palautettu pistemäärä.
     */
    public int palautaPisteet() {
        int palautus = pisteet;
        return palautus;
    }

    /**
     * Tämän metodin avulla pystytään vertaamaan, ja siten järjestämään,
     * tulos-olioita.
     *
     * @param toinen Verrattava tulos-olio.
     * @return Vertaukseen käytetty luku.
     */
    @Override
    public int compareTo(Pelitulos toinen) {
        if (this.pisteet == toinen.pisteet) {
            if (this.nimi.compareTo(toinen.nimi) < 0) {
                return -1;
            } else if (this.nimi.compareTo(toinen.nimi) == 0) {
                return 0;
            } else {
                return 1;
            }
        } else if (this.pisteet < toinen.pisteet) {
            return 1;
        } else {
            return -1;
        }
    }
}
