package ohjelma;

/**
 * Luokka luo alkuaineolion.
 *
 * @author Timo Pekkanen
 */
public class Alkuaine {
    private String nimi;
    private String symboli;
    private String vihje;

    /**
     * Luokan konstruktori, joka asettaa alkuaineen attribuutit.
     *
     * @param nimi Alkuaineen nimi.
     * @param symboli Alkuaineen kemiallinen symboli.
     * @param vihje PeliIkkuna-luokan antama vihje, jos ensimmäinen vastaus on väärä.
     */
    public Alkuaine(String nimi, String symboli, String vihje) {
        this.nimi = nimi;
        this.symboli = symboli;
        this.vihje = vihje;
    }
    
    /**
     * Metodi palauttaa nimi-attribuutin. 
     * @return Alkuaineen nimi (merkkijono).
     */

    public String palautaNimi() {
        return nimi;
    }
    
    /**
     * Metodi palauttaa symboli-attribuutin. 
     * @return Alkuaineen symboli (merkkijono).
     */

    public String palautaSymboli() {
        return symboli;
    }
    
    /**
     * Metodi palauttaa vihje-attribuutin.  
     * @return Alkuaineen vihje (String).
     */

    public String palautaVihje() {
        return vihje;
    }
}
