package ohjelma;

/**
 * Luokka luo alkuaineolion.
 *
 * @author timo
 */
public class Alkuaine {

    private String nimi;
    private String symboli;
    private String vihje;

    /**
     * Luokan konstruktori.
     *
     * @param nimi Alkuaineen nimi.
     * @param symboli Alkuaineen kemiallinen symboli.
     * @param vihje Ohjelman antama vihje, jos ensimmäinen vastaus on väärä.
     */
    public Alkuaine(String nimi, String symboli, String vihje) {
        this.nimi = nimi;
        this.symboli = symboli;
        this.vihje = vihje;
    }

    public String palautaNimi() {
        return nimi;
    }

    public String palautaSymboli() {
        return symboli;
    }

    public String palautaVihje() {
        return vihje;
    }
}
