
package ohjelma;

public class Tulos implements Comparable<Tulos> {
    public int pisteet;
    public String nimi;
    
    public Tulos(String nimi, int pisteet) {
        this.pisteet = pisteet;
        this.nimi = nimi;
    }
    
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
