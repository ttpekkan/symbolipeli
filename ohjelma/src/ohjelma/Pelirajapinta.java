package ohjelma;

/**
 * Rajapinta, jonka kautta peli-ikkuna ja symbolipeli kommunikoivat.
 *
 * Voi olla hyödyllinen, jos halutaan pelata samankaltaisia pelejä samalla
 * käyttöliittymällä.
 *
 * @author Timo Pekkanen
 */
public interface Pelirajapinta {

    public String syötäPeliinVastaus(String vastaus);

    public String palautaAlkuaineenVihje();

    public String palautaAlkuaineenOikeaVastaus();

    public String palautaAlkuaineenSymboli();

    public String palautaOnnitteluOikeastaVastauksesta();

    public String palautaPelinVaikeusaste();

    public int palautaKysymysnumero();

    public int palautaPelaajanPisteet();

    public boolean pääseeköPelitulosListalle();

    public boolean onkoVastattuOikeinKaikkiinKysymyksiin();

    public void lisääPelitulosPistelistaan(String nimi, String tiedosto);
}
