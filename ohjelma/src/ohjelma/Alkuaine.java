
package ohjelma;

/**
 * Luokka luo alkuaineolion.
 * @author timo
 */
public class Alkuaine {
    public String nimi;
    public String symboli;
    public String vihje;
    
    /**
     * Luokan konstruktori.
     * @param nimi Alkuaineen nimi.
     * @param symboli Alkuaineen kemiallinen symboli.
     * @param vihje Ohjelman antama vihje, jos ensimmäinen vastaus on väärä. 
     */
    
    public Alkuaine(String nimi, String symboli, String vihje) {
        this.nimi = nimi;
        this.symboli = symboli;
        this.vihje = vihje;
    }
    
}
