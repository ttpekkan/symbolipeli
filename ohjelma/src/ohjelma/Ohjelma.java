
package ohjelma;

/**
 * Alkuainepeli!
 * @author timo
 */

public class Ohjelma {
    /**
     * Aloittaa pelin.
     * @param args 
     */
    public static void main(String[] args) {
        Kysymysgeneraattori uusi = new Kysymysgeneraattori();
        uusi.lataaHelpotKysymykset();
        uusi.lataaKeskivaikeatKysymykset();
        uusi.lataaVaikeatKysymykset();
        System.out.println(uusi.helpotKysymykset.get(0).nimi);
        System.out.println(uusi.keskivaikeatKysymykset.get(0).nimi);
        System.out.println(uusi.vaikeatKysymykset.get(0).nimi);
    
        }
    }

