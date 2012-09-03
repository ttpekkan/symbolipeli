package ohjelma;

/**
 * Rajapinta, jonka kautta peli_ikkuna kommunikoi pelin kanssa.
 *
 * Hyödyllinen, jos ohjelmaan halutaan lisätä erilaisia pelimuotoja tai jos
 * halutaan muuttaa jo olemassaolevaa peliä.
 *
 * @author Timo Pekkanen
 */
public interface HaePelinArvoja {

    public void päivitäTilanne(int kerroin);

    public String annaVihje();

    public String palautaOikeaVastaus();

    public String palautaSymboli();

    public int palautaKysymysnumero();

    public int palautaPisteet();

    public int palautaYrityskerta();

    public boolean pääseeListalle();

    public void lisääNimi(String nimi, String tiedosto);

    public String palautaOnnittelu();

    public String palautaVaikeusaste();

    public int onko54();
}
