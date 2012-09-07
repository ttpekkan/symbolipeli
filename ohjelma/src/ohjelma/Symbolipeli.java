package ohjelma;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Tämä luokka sisältää pelin toimintalogiikan.
 *
 * Julkisten metodien avulla pelissä tapahtuvat muutokset kommunikoidaan
 * käyttäliittymälle. Käyttöliittymästä voidaan päivittää Pelitilanetta.
 *
 *  @author Timo Pekkanen
 */
public class Symbolipeli implements Pelirajapinta {

    private Alkuaine kysyttyAine;
    private ArrayList<String> henkilöt;
    private ArrayList<String> onnittelut;
    private PistelistaRajapinta pistelista;
    private int kysymysnumero;
    private int moneskoHelppoKysytään;
    private int moneskoKeskivaikeaKysytään;
    private int moneskoVaikeaKysytään;
    private int pelaajanPisteet;
    private int yrityskerta;
    private Kysymysgeneraattori alkuaineet;
    private String oikeaVastaus;
    private String symboli;
    private String vaikeusaste;
    private String vihje;

    /**
     * Luokan konstruktori.
     *
     * Konstruktori luo henkilöt, onnittelut, kysymykset, pistelistan ja asettaa
     * lukujen alkuarvot.
     */
    public Symbolipeli() {
        lisääHenkilöt();
        lisääOnnittelut();
        alkuaineet = new Kysymysgeneraattori();
        pistelista = new Pistelista();
        kysymysnumero = 0;
        moneskoHelppoKysytään = 0;
        moneskoKeskivaikeaKysytään = 0;
        moneskoVaikeaKysytään = 0;
        asetaKysymys(alkuaineet.palautaHelppoKysymys(moneskoHelppoKysytään));
        vihje = "";
        vaikeusaste = "helppo";
        yrityskerta = 0;
    }

    private void pelinAloitus() {
        kysymysnumero = 1;
    }

    /**
     * Lisää henkilöt arrayListiin.
     */
    private void lisääHenkilöt() {
        henkilöt = new ArrayList<String>();
        henkilöt.add("Pauling");
        henkilöt.add("Arrhenius");
        henkilöt.add("Lewis");
        henkilöt.add("Debye");
    }

    /**
     * Lisää onnittelut arrayListiin.
     */
    private void lisääOnnittelut() {
        onnittelut = new ArrayList<String>();
        onnittelut.add("Mainiota!");
        onnittelut.add("Hienoa!");
        onnittelut.add("Mahtavaa!");
        onnittelut.add("Täysin oikein!");
        onnittelut.add("Vaikuttavaa!");
    }

    /**
     * Palauttaa satunnaisen henkilön nimen.
     *
     * @return Nimi merkkijonona.
     */
    private String palautaSatunnainenNimi() {
        Collections.shuffle(henkilöt);
        return henkilöt.get(0);
    }

    /**
     * Palauttaa satunnaissen onnittelun.
     *
     * @return Onnittelu merkkijonona.
     */
    private String palautaSatunnainenOnnittelu() {
        Collections.shuffle(onnittelut);
        return onnittelut.get(0);
    }

    /**
     * Tämä metodin avulla päivitetään kysyttävään alkuaineeseen liittyvät
     * kentät.
     *
     * @param aine Alkuaineolio, jonka avulla muutetaan kolmen kentän arvoa.
     */
    private void asetaKysymys(Alkuaine aine) {
        kysyttyAine = aine;
        symboli = kysyttyAine.aineenSymboli();
        oikeaVastaus = kysyttyAine.aineenNimi();
    }

    /**
     * Tämän metodin avulla päivitetään tilannetta ja kysytään uusi kysymys.
     *
     * @param kerroin Kerroin määrää, paljon edellisestä kysymyksetä annetaan
     * pisteitä.
     */
    private void tilanteenPäivitys(int kerroin) {
        vihje = (palautaSatunnainenNimi() + ": " + palautaSatunnainenOnnittelu());
        yrityskerta = 0;
        kysymysnumero = kysymysnumero + 1;
        if (moneskoHelppoKysytään < 11 && moneskoKeskivaikeaKysytään == 0 && moneskoVaikeaKysytään == 0) {
            kysyHelppo(kerroin);
            if (moneskoHelppoKysytään == 11) {
                vaikeusaste = "keskivaikea";
                vihje = (palautaSatunnainenNimi() + ": Edellinen Oikein! Siirrytään keskivaikeisiin.");
                asetaKysymys(alkuaineet.palautaKeskivaikeaKysymys(moneskoKeskivaikeaKysytään));
                return;
            }
        }
        if (moneskoHelppoKysytään == 11 && moneskoKeskivaikeaKysytään < 46 && moneskoVaikeaKysytään == 0) {
            kysyKeskivaikea(kerroin);
            if (moneskoKeskivaikeaKysytään == 46) {
                vaikeusaste = ("vaikea");
                vihje = (palautaSatunnainenNimi() + ": Siirrytään vaikeisiin.");
                asetaKysymys(alkuaineet.palautaVaikeaKysymys(moneskoVaikeaKysytään));
                return;
            }
        }
        if (moneskoHelppoKysytään == 11 && moneskoKeskivaikeaKysytään == 46 && moneskoVaikeaKysytään < 54) {
            kysyVaikea(kerroin);
            if (moneskoVaikeaKysytään == 54) {
                kysymysnumero = kysymysnumero - 1;
                pelinLopetus();
            }
        }
    }

    /**
     * Apumetodi, joka kysyy vaikeudeltaan helpon kysymyksen.
     *
     * @param kertoluku Kertoluku määrää, paljon edellisestä kysymyksestä
     * annettaan pisteitä.
     */
    private void kysyHelppo(int kertoluku) {
        moneskoHelppoKysytään = moneskoHelppoKysytään + 1;
        pelaajanPisteet = pelaajanPisteet + (1 * kertoluku);
        if (moneskoHelppoKysytään < 11) {
            asetaKysymys(alkuaineet.palautaHelppoKysymys(moneskoHelppoKysytään));
        }
    }

    /**
     * Apumetodi, joka kysyy vaikeudeltaan keskivaikean kysymyksen.
     *
     * @param kertoluku Kertoluku määrää, paljon edellisestä kysymyksestä
     * annettaan pisteitä.
     */
    private void kysyKeskivaikea(int kertoluku) {
        moneskoKeskivaikeaKysytään = moneskoKeskivaikeaKysytään + 1;
        pelaajanPisteet = pelaajanPisteet + (2 * kertoluku);
        if (moneskoKeskivaikeaKysytään < 46) {
            asetaKysymys(alkuaineet.palautaKeskivaikeaKysymys(moneskoKeskivaikeaKysytään));
        }
    }

    /**
     * Apumetodi, joka kysyy vaikeudeltaan vaikean kysymyksen.
     *
     * @param kertoluku Kertoluku määrää, paljon edellisestä kysymyksestä
     * annettaan pisteitä.
     */
    private void kysyVaikea(int kertoluku) {
        moneskoVaikeaKysytään = moneskoVaikeaKysytään + 1;
        pelaajanPisteet = pelaajanPisteet + (3 * kertoluku);
        if (moneskoVaikeaKysytään < 54) {
            asetaKysymys(alkuaineet.palautaVaikeaKysymys(moneskoVaikeaKysytään));
        }
    }

    /**
     * Tämän luokan avulla muutetaan niitä kenttiä, joiden tulee muuttua, kun
     * peli loppuu.
     */
    private void pelinLopetus() {
        if (moneskoVaikeaKysytään == 54) {
            vihje = (palautaSatunnainenNimi() + ": Mestarillinen suoritus!");
        } else {
            vihje = (palautaSatunnainenNimi() + ": Voi voi minkä menit tekemään! No, harjoitus tekee mestarin.");
        }
    }

    /**
     * Palauttaa kysyttävän aineen vihjeen.
     *
     * @return Palautettu vihje.
     */
    @Override
    public String palautaAlkuaineenVihje() {
        return (palautaSatunnainenNimi() + ": " + kysyttyAine.aineenVihje());
    }

    /**
     * Palauttaa onnittelun.
     *
     * @return Palautettu onnittelu.
     */
    @Override
    public String palautaOnnitteluOikeastaVastauksesta() {
        String palautus = vihje;
        return palautus;
    }

    /**
     * Palauttaa kysyttävän aineen oikean vastauksen.
     *
     * @return Oikea vastaus.
     */
    @Override
    public String palautaAlkuaineenOikeaVastaus() {
        String palautus = oikeaVastaus;
        return palautus;
    }

    /**
     * Palauttaa kysyttävän aineen alkuaineen symbolin.
     *
     * @return Palautetty symboli.
     */
    @Override
    public String palautaAlkuaineenSymboli() {
        String palautus = symboli;
        return palautus;
    }

    /**
     * Palauttaa kysymysnumeoron.
     *
     * @return Palautettu kysymysnumero.
     */
    @Override
    public int palautaKysymysnumero() {
        int palautus = kysymysnumero;
        return palautus;
    }

    /**
     * Palauttaa pelaajan keräämät pisteet.
     *
     * @return Palautetut pisteet.
     */
    @Override
    public int palautaPelaajanPisteet() {
        int palautus = pelaajanPisteet;
        return palautus;
    }

    /**
     * Kertoo, pääseekö tulos pistelistaan.
     *
     * @return True tai false.
     */
    @Override
    public boolean pääseeköPelitulosListalle() {
        boolean palautus = pistelista.pääseeköPelitulosListalle(pelaajanPisteet);
        return palautus;
    }

    /**
     * Metodin avulla lisätään nimi pistelistaan.
     *
     * @param nimi Lisättävä nimimerkki.
     * @param tiedosto Tiedosto, johon tulos lisätään.
     */
    @Override
    public void lisääPelitulosPistelistaan(String nimi, String tiedosto) {
        pistelista.lisääPelitulosPistelistaan(nimi, pelaajanPisteet);
        pistelista.tallennaPistelista(tiedosto);
    }

    /**
     * Palauttaa pelin vaikeusasteen.
     *
     * @return Vaikeusaste merkkijonona.
     */
    @Override
    public String palautaPelinVaikeusaste() {
        String palautus = vaikeusaste;
        return palautus;
    }

    /**
     * Metodi kertoo, onko peli ratkaistu.
     *
     * @return True tai false.
     */
    @Override
    public boolean onkoVastattuOikeinKaikkiinKysymyksiin() {
        if (moneskoVaikeaKysytään == 54) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Tämän metodin avulla peliin voidaan syöttää vastaus.
     *
     * @param vastaus Vastaus merkkijonona.
     * @return Palautus kertoo, mitä symbolipeli tekee, kun on saanut
     * vastauksen.
     */
    @Override
    public String syötäPeliinVastaus(String vastaus) {
        if (kysymysnumero == 0) {
            pelinAloitus();
            return "aloita";
        } else if (vastaus.equals(oikeaVastaus) && yrityskerta == 0) {
            tilanteenPäivitys(2);
            return "päivitä2";
        } else if (vastaus.equals(oikeaVastaus) && yrityskerta == 1) {
            tilanteenPäivitys(1);
            return "päivitä1";
        } else if (!vastaus.equals(oikeaVastaus) && yrityskerta == 0) {
            yrityskerta = yrityskerta + 1;
            return "vihje";
        } else {
            pelinLopetus();
            return "lopeta";
        }
    }
}
