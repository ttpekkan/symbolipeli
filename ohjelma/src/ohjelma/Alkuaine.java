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
     * @param vihje Peli_ikkuna-luokan antama vihje, jos ensimmäinen vastaus on
     * väärä.
     */
    public Alkuaine(String nimi, String symboli, String vihje) {
        this.nimi = nimi;
        this.symboli = symboli;
        this.vihje = vihje;
    }

    /**
     * Metodi palauttaa nimi-attribuutin.
     *
     * @return Alkuaineen nimi.
     */
    public String aineenNimi() {
        String palautus = nimi;
        return palautus;
    }

    /**
     * Metodi palauttaa symboli-attribuutin.
     *
     * @return Alkuaineen symboli.
     */
    public String aineenSymboli() {
        String palautus = symboli;
        return palautus;
    }

    /**
     * Metodi palauttaa vihje-attribuutin.
     *
     * @return Alkuaineen vihje.
     */
    public String aineenVihje() {
        String palautus = vihje;
        return palautus;
    }
}
