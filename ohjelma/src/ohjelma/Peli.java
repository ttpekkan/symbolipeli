package ohjelma;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Tämä luokka sisältää pelin toimintalogiikan.
 *
 * Julkisten metodien avulla pelissä tapahtuvat muutokset kommunikoidaan
 * käyttäliittymälle. Käyttöliittymästä voidaan päivittää Pelitilanetta.
 *
 * @author Timo Pekkanen
 */
public class Peli implements HaePelinArvoja {

    private Alkuaine kysyttyAine;  
    private ArrayList<String> henkilöt;
    private ArrayList<String> onnittelut;
    private HaePistelista pistelista;
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
    public Peli() {
        lisääHenkilöt();
        lisääOnnittelut();
        alkuaineet = new Kysymysgeneraattori();
        pistelista = new Pistelista();
        kysymysnumero = 1;
        moneskoHelppoKysytään = 0;
        moneskoKeskivaikeaKysytään = 0;
        moneskoVaikeaKysytään = 0;
        kysyKysymys(alkuaineet.palautaHelppoKysymys(moneskoHelppoKysytään));
        vihje = "";
        vaikeusaste = "helppo";
        yrityskerta = 0;
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
    private void kysyKysymys(Alkuaine aine) {
        kysyttyAine = aine;
        symboli = kysyttyAine.aineenSymboli();
        oikeaVastaus = kysyttyAine.aineenNimi();
    }

    /**
     * Tämän metodin avulla päivitetään tilannetta ja kysytään uusi kysymys.
     *
     * @param kerroin Kerroin määrää, paljon edellisestä kysymyksetä annettaan
     * pisteitä.
     */
    @Override
    public void päivitäTilanne(int kerroin) {
        vihje = (palautaSatunnainenNimi() + ": " + palautaSatunnainenOnnittelu());
        yrityskerta = 0;
        kysymysnumero = kysymysnumero + 1;
        if (moneskoHelppoKysytään < 11 && moneskoKeskivaikeaKysytään == 0 && moneskoVaikeaKysytään == 0) {
            kysyHelppo(kerroin);
            if (moneskoHelppoKysytään == 11) {
                vaikeusaste = "keskivaikea";
                vihje = (palautaSatunnainenNimi() + ": Edellinen Oikein! Siirrytään keskivaikeisiin.");
                kysyKysymys(alkuaineet.palautaKeskivaikeaKysymys(moneskoKeskivaikeaKysytään));
                return;
            }
        }
        if (moneskoHelppoKysytään == 11 && moneskoKeskivaikeaKysytään < 46 && moneskoVaikeaKysytään == 0) {
            kysyKeskivaikea(kerroin);
            if (moneskoKeskivaikeaKysytään == 46) {
                vaikeusaste = ("vaikea");
                vihje = (palautaSatunnainenNimi() + ": Siirrytään vaikeisiin.");
                kysyKysymys(alkuaineet.palautaVaikeaKysymys(moneskoVaikeaKysytään));
                return;
            }
        }
        if (moneskoHelppoKysytään == 11 && moneskoKeskivaikeaKysytään == 46 && moneskoVaikeaKysytään < 54) {
            kysyVaikea(kerroin);
            if (moneskoVaikeaKysytään == 54) {
                kysymysnumero = kysymysnumero - 1;
                lopetus();
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
            kysyKysymys(alkuaineet.palautaHelppoKysymys(moneskoHelppoKysytään));
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
            kysyKysymys(alkuaineet.palautaKeskivaikeaKysymys(moneskoKeskivaikeaKysytään));
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
            kysyKysymys(alkuaineet.palautaVaikeaKysymys(moneskoVaikeaKysytään));
        }
    }

    /**
     * Tämän luokan avulla muutetaan niitä kenttiä, joiden tulee muuttua, kun
     * peli loppuu.
     */
    private void lopetus() {
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
    public String annaVihje() {
        yrityskerta = yrityskerta + 1;
        return (palautaSatunnainenNimi() + ": " + kysyttyAine.aineenVihje());
    }

    /**
     * Palauttaa onnittelun.
     *
     * @return Palautettu onnittelu.
     */
    @Override
    public String palautaOnnittelu() {
        String palautus = vihje;
        return palautus;
    }

    /**
     * Palauttaa oikean vastauksen.
     *
     * @return Oikea vastaus.
     */
    @Override
    public String palautaOikeaVastaus() {
        String palautus = oikeaVastaus;
        return palautus;
    }

    /**
     * Palauttaa alkuaineen symbolin.
     *
     * @return Palautetty symboli.
     */
    @Override
    public String palautaSymboli() {
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
    public int palautaPisteet() {
        int palautus = pelaajanPisteet;
        return palautus;
    }

    /**
     * Palauttaa luvun, joka kertoo, kuinka monta yritystä on käytetty
     * kysymykseen.
     *
     * @return Yrityskerta lukuna.
     */
    @Override
    public int palautaYrityskerta() {
        int palautus = yrityskerta;
        return palautus;
    }

    /**
     * Kertoo, pääseekö tulos pistelistaan.
     *
     * @return True tai false.
     */
    @Override
    public boolean pääseeListalle() {
        boolean palautus = pistelista.pääseeListalle(pelaajanPisteet);
        return palautus;
    }

    /**
     * Metodin avulla lisätään nimi pistelistaan.
     *
     * @param nimi Lisättävä nimimerkki.
     * @param tiedosto Tiedosto, johon tulos lisätään.
     */
    @Override
    public void lisääNimi(String nimi, String tiedosto) {
        pistelista.lisääTulos(nimi, pelaajanPisteet);
        pistelista.tallennaPistelista(tiedosto);
    }

    /**
     * Palauttaa vaikeusasteen.
     *
     * @return Vaikeusaste merkkijonona.
     */
    @Override
    public String palautaVaikeusaste() {
        String palautus = vaikeusaste;
        return palautus;
    }

    /**
     * Metodi kertoo, kuinka monta vaikeaa kysymystä on ratkaistu.
     *
     * 54 on vaikeiden kysymysten määrä.
     *
     * @return Ratkaistus vaikeat kysymykset kokonaislukuna.
     */
    @Override
    public int onko54() {
        int palautus = moneskoVaikeaKysytään;
        return palautus;
    }
}
