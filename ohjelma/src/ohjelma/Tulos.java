
package ohjelma;

/**
 * Luokka luo tulosolion. Luokan avulla pystyy myös järjestämään tuloksia. 
 * @author timo
 */

public class Tulos implements Comparable<Tulos> {
    public int pisteet;
    public String nimi;
    
    /**
     * Luokan konstruktori, joka luo Tulos-olion. 
     * @param nimi Annettu nimimerkki.
     * @param pisteet Annetut pisteet. 
     */
    
    public Tulos(String nimi, int pisteet) {
        this.pisteet = pisteet;
        this.nimi = nimi;
    }
    
    /**
     * Tämän metodin avulla pystytään vertaamaan, ja siten järjestämään, tulos-olioita.
     * @param toinen Verrattava tulos-olio. 
     * @return 
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
