
package ohjelma;

public interface HaeArvoja {
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
