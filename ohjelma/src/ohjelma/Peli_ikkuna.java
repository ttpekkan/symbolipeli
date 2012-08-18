package ohjelma;

import java.awt.Color;
import java.awt.Container;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.*;

/**
 * Tämä luokka kontrolloi tapahtumia, kun peli on aloitettu.
 *
 * @author Timo Pekkanen
 */
public class Peli_ikkuna implements Runnable {

    private Alkuaine kysyttyAine;
    private ArrayList<String> henkilöt;
    private ArrayList<String> onnittelut;
    private JButton ok_nappula;
    private IlmoitaHäviö_Ikkuna hävisit;
    private IlmoitaVoitto_Ikkuna voitit;
    private int aika;
    private int kysymysnumero;
    private int moneskoHelppoKysytään;
    private int moneskoKeskivaikeaKysytään;
    private int moneskoVaikeaKysytään;
    private int pelaajanPisteet;
    private int yrityskerta;
    private JFrame lopetusikkuna;
    private JFrame peli_ikkuna;
    private JLabel ajannäyttö;
    private JLabel moneskoKysymysMenossa;
    private JLabel pelinAntamaAloitusOhje;
    private JLabel pistetilanne;
    private JLabel symboli;
    private JLabel tekstikentänSelitys;
    private JLabel vaikeusaste;
    private JLabel vihje;
    private JTextField vastauskenttä;
    private KomponenttienLataus komponentit;
    private Kysymysgeneraattori alkuaineet;
    private Musiikkikirjasto musa;
    private Pistelista pistelista;
    private Timer ajastin;
    private static int laskuri = 0;

    /**
     * Käynnistää pelin Pääikkunan ja asettaa sen alkuominaisuudet.
     */
    @Override
    public void run() {
        if (laskuri == 0) {
            peli_ikkuna = new JFrame();
            komponentit = new KomponenttienLataus(peli_ikkuna);
            pelaajanPisteet = 0;
            laskuri = laskuri + 1;
            kysymysnumero = 0;
            aika = 15;
            peli_ikkuna.setTitle("Symbolipeli");
            peli_ikkuna.setLocation(130, 180);
            UIManager.put("Button.select", Color.green);
            musa = new Musiikkikirjasto();
            musa.soitaAloituslaulu();
            komponentit.luoContentPaneKuvasta(musa.palautaKuvannimi());
            luoKomponentit();
            komponentit.neIkkunanavausToiminnotJotkaAinaSamat();
            peli_ikkuna.addWindowListener(new Peli_ikkunanKuuntelija(this));
        }
    }

    /**
     * Metodi luo halutut komponentit Pelin pääikkunaan.
     */
    private void luoKomponentit() {
        Container pohja = peli_ikkuna.getContentPane();
        pistetilanne = new JLabel("Pisteet: " + pelaajanPisteet);
        komponentit.luoTeksti(pistetilanne, 805, 360, 140, 30, false, Color.GRAY, Color.white, 20, pohja);
        vaikeusaste = new JLabel("Vaikeusaste: helppo");
        komponentit.luoTeksti(vaikeusaste, 805, 326, 290, 30, false, Color.GRAY, Color.white, 20, pohja);
        moneskoKysymysMenossa = new JLabel("Kysymys: " + kysymysnumero);
        komponentit.luoTeksti(moneskoKysymysMenossa, 805, 290, 160, 30, false, Color.GRAY, Color.white, 20, pohja);
        ajastin = new Timer(1000, new AjastimenKuuntelija(this));
        pelinAntamaAloitusOhje = new JLabel("Oikaan yläkulmaan annetaan alkuaineen symboli. Kirjoita sitä vastaava alkuaine tekstikenttään.");
        komponentit.luoTeksti(pelinAntamaAloitusOhje, 40, 430, 1000, 30, true, Color.black, Color.white.brighter(), 18, pohja);
        ajannäyttö = new JLabel("Aika: " + aika);
        komponentit.luoTeksti(ajannäyttö, 840, 530, 200, 50, false, Color.GRAY, Color.white, 40, pohja);
        symboli = new JLabel("------------->Hg");
        komponentit.luoTeksti(symboli, 20, 20, 1075, 165, false, Color.red, Color.white, 130, pohja);
        tekstikentänSelitys = new JLabel("Ole hyvä ja anna vastauksesi pienellä.");
        komponentit.luoTeksti(tekstikentänSelitys, 50, 622, 300, 40, false, Color.red, Color.white, 14, pohja);
        vihje = new JLabel("Arrhenius, Lewis, Debye ja Pauling tarpeen mukaan jakavat neuvoja tähän kohtaan.");
        komponentit.luoTeksti(vihje, 17, 585, 1070, 30, false, Color.red, Color.white, 14, pohja);
        vastauskenttä = new JTextField();
        komponentit.luoTekstikenttä(vastauskenttä, 375, 615, 300, 40, 24, "Kirjoita 'pelaa' tähän.", pohja);
        ok_nappula = new JButton("ok");
        komponentit.luoNappula(ok_nappula, 876, 671, 190, 66, Color.white, false, Color.green, false, true,
                false, 0, Color.white, pohja);
        ok_nappula.addActionListener(new Peli_ikkunanKuuntelija(this));
        JButton nappula = new JButton("Sulje Peli");
        komponentit.luoNappula(nappula, 911, 780, 125, 47, Color.white, false,
                Color.green.darker(), false, true, false, 0, Color.green.darker(), pohja);
        nappula.addActionListener(new Peli_ikkunanKuuntelija(this));
        peli_ikkuna.getRootPane().setDefaultButton(ok_nappula);
    }

    /**
     * Suorittaa alustavat toimenpiteet, kun peli aloitetaan.
     */
    public void aloitaPeli() {
        musa.pysäytäAloituslaulu();
        kysymysnumero = kysymysnumero + 1;
        vastauskenttä.setText("");
        pelinAntamaAloitusOhje.setVisible(false);
        tekstikentänSelitys.setVisible(false);
        moneskoKysymysMenossa.setText("Kysymys: " + kysymysnumero);
        moneskoHelppoKysytään = 0;
        moneskoKeskivaikeaKysytään = 0;
        moneskoVaikeaKysytään = 0;
        yrityskerta = 0;
        lisääHenkilöt();
        lisääOnnittelut();
        alkuaineet = new Kysymysgeneraattori();
        kysyKysymys(alkuaineet.palautaHelppoKysymys(moneskoHelppoKysytään));
        musa.jatkuvaToistoPelilaulu();
        ajastin.start();
    }
    
    /**
     * Lisää henkilöt-arraylistiin henkilöitä.
     */
    private void lisääHenkilöt() {
        henkilöt = new ArrayList<String>();
        henkilöt.add("Pauling");
        henkilöt.add("Arrhenius");
        henkilöt.add("Lewis");
        henkilöt.add("Debye");
    }
    /**
     * Metodi palauttaa satunnaisen henkiön.
     * 
     * @return Henkilö. 
     */
    private String palautaSatunnainenNimi() {
        Collections.shuffle(henkilöt);
        return henkilöt.get(0);
    }
    
    /**
     * Metodi lisää onnittelut arraylistiin.
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
     * Palauttaa satunnaisen onnittelun.
     * 
     * @return Onnittelu. 
     */
    private String palautaSatunnainenOnnittelu() {
        Collections.shuffle(onnittelut);
        return onnittelut.get(0);
    }

    /**
     * Tämän metodin avulla esitetään kysymys alkuaineesta.
     *
     * @param aine Alkuaine-olio, josta kysymys esitetään.
     */
    private void kysyKysymys(Alkuaine aine) {
        kysyttyAine = aine;
        symboli.setText("                  " + kysyttyAine.aineenSymboli());
    }

    /**
     * Tämä metodi päivittää tilanteen, jos on vastannut oikein, sekä laukaisee
     * voitto-metodin, jos olosuhteet oikeat.
     *
     * @param kerroin Kerroin vaikuttaa siihen, paljonko pisteitä annetaan.
     * Kertoin määrätään sen mukaan, onko vihjettä annettu vai ei.
     */
    public void päivitäTilanne(int kerroin) {
        aika = 15;
        vihje.setText(palautaSatunnainenNimi() + ": " + palautaSatunnainenOnnittelu());
        vastauskenttä.setText("");
        yrityskerta = 0;
        kysymysnumero = kysymysnumero + 1;
        moneskoKysymysMenossa.setText("Kysymys: " + kysymysnumero);
        if (moneskoHelppoKysytään < 11 && moneskoKeskivaikeaKysytään == 0 && moneskoVaikeaKysytään == 0) {
            kysyHelppo(kerroin);
            if (moneskoHelppoKysytään == 11) {
                vaikeusaste.setText("Vaikeusaste: keskivaikea");
                vihje.setText(palautaSatunnainenNimi() + ": Edellinen Oikein! Siirrytään keskivaikeisiin.");
                kysyKysymys(alkuaineet.palautaKeskivaikeaKysymys(moneskoKeskivaikeaKysytään));
                return;
            }
        }
        if (moneskoHelppoKysytään == 11 && moneskoKeskivaikeaKysytään < 46 && moneskoVaikeaKysytään == 0) {
            kysyKeskivaikea(kerroin);
            if (moneskoKeskivaikeaKysytään == 46) {
                vaikeusaste.setText("Vaikeusaste: vaikea");
                vihje.setText(palautaSatunnainenNimi() + ": Siirrytään vaikeisiin.");
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
     * Tämän metodin avulla kysytään helpot kysymykset ja päivitetään
     * tilannetta.
     *
     * @param kertoluku Kertoluku vaikuttaa siihen, paljonko pisteitä annetaan.
     * Kertoimen määrää päivitäTilanne-metodin kerroin.
     */
    private void kysyHelppo(int kertoluku) {
        moneskoHelppoKysytään = moneskoHelppoKysytään + 1;
        pelaajanPisteet = pelaajanPisteet + (1 * kertoluku);
        pistetilanne.setText("Pisteet: " + pelaajanPisteet);
        if (moneskoHelppoKysytään < 11) {
            kysyKysymys(alkuaineet.palautaHelppoKysymys(moneskoHelppoKysytään));
        }
    }

    /**
     * Tämän metodin avulla kysytään keskivaikeat kysymykset ja päivitetään
     * tilannetta.
     *
     * @param kertoluku Kertoluku vaikuttaa siihen, paljonko pisteitä annetaan.
     * Kertoimen määrää päivitäTilanne-metodin kerroin.
     */
    private void kysyKeskivaikea(int kertoluku) {
        moneskoKeskivaikeaKysytään = moneskoKeskivaikeaKysytään + 1;
        pelaajanPisteet = pelaajanPisteet + (2 * kertoluku);
        pistetilanne.setText("Pisteet: " + pelaajanPisteet);
        if (moneskoKeskivaikeaKysytään < 46) {
            kysyKysymys(alkuaineet.palautaKeskivaikeaKysymys(moneskoKeskivaikeaKysytään));
        }
    }

    /**
     * Tämän metodin avulla kysytään vaikeat kysymykset ja päivitetään
     * tilannetta.
     *
     * @param kertoluku Kertoluku vaikuttaa siihen, paljonko pisteitä annetaan.
     * Kertoimen määrää päivitäTilanne-metodin kerroin.
     */
    private void kysyVaikea(int kertoluku) {
        moneskoVaikeaKysytään = moneskoVaikeaKysytään + 1;
        pelaajanPisteet = pelaajanPisteet + (3 * kertoluku);
        pistetilanne.setText("Pisteet: " + pelaajanPisteet);
        if (moneskoVaikeaKysytään < 54) {
            kysyKysymys(alkuaineet.palautaVaikeaKysymys(moneskoVaikeaKysytään));
        }
    }

    /**
     * Tämä metodin avulla annetaan pelaajalle vihje, jos hän on vastannut
     * väärin.
     */
    public void annaVihje() {
        aika = 15;
        yrityskerta = yrityskerta + 1;
        vihje.setText(palautaSatunnainenNimi() + ": " + kysyttyAine.aineenVihje());
        vastauskenttä.setText("");
    }

    /**
     * Tämä metodi vähentää aikaa, laukaisee vihjeen tai laukaisee häviön.
     */
    public void vähennäAikaa() {
        if (aika > 0) {
            aika = aika - 1;
            ajannäyttö.setText("Aika: " + aika);
        } else {
            if (yrityskerta == 0) {
                annaVihje();
            } else {
                ajastin.stop();
                lopetus();
            }
        }
    }
    
    /**
     * Tämä metodi laukaisee lopetusikkunan. 
     */
    public void lopetus() {
        pistelista = new Pistelista();
        ajastin.stop();
        musa.pysäytäPelilaulu();
        vastauskenttä.setEnabled(false);
        ok_nappula.setEnabled(false);
        lopetusikkuna = new JFrame();
        lopetusikkuna.addWindowListener(new Peli_ikkunanKuuntelija(this));
        if (moneskoVaikeaKysytään == 54) {
            musa.soitaVoittolaulu();
            vihje.setText(palautaSatunnainenNimi() + ": Mestarillinen suoritus!");
            voitit = new IlmoitaVoitto_Ikkuna(lopetusikkuna, pelaajanPisteet, pistelista.pääseeListalle(pelaajanPisteet));
            voitit.run();
            voitit.lisääActionListeneriin(this);
        } else {
            musa.soitaHäviölaulu();
            vihje.setText(palautaSatunnainenNimi() + ": Voi voi minkä menit tekemään! No, harjoitus tekee mestarin.");
            hävisit = new IlmoitaHäviö_Ikkuna(lopetusikkuna, pelaajanPisteet, pistelista.pääseeListalle(pelaajanPisteet), kysyttyAine.aineenNimi());
            hävisit.run();
            hävisit.lisääActionListeneriin(this);
        }
    }
    
    /**
     * Tämän metodin avulla lisätään tulos pistelistaan.
     */
    public void lisääNimi() {
        if (voitit != null) {
            if (voitit.palautaNimikentänNimi().length() > 0) {
                pistelista.lisääTulos(voitit.palautaNimikentänNimi(), pelaajanPisteet);
            }
        }
        if (hävisit != null) {
            if (hävisit.palautaNimikentänNimi().length() > 0) {
                pistelista.lisääTulos(hävisit.palautaNimikentänNimi(), pelaajanPisteet);
            }
        }
        pistelista.tallennaPistelista("src/top10.txt");
    }
    
    /**
     * Toiminnot, jotka tehdään, kun suljetaan peli_ikkuna.
     */
    public void sulje() {
        musa.pysäytäAloituslaulu();
        musa.pysäytäPelilaulu();
        musa.jatkuvaToistoPäävalikkolaulu();
        if (peli_ikkuna != null) {
            laskuri = laskuri - 1;
            peli_ikkuna.dispose();
        }
        if (lopetusikkuna != null) {
            lopetusikkuna.dispose();
        }
    }
    
    /**
     * Metodi palauttaa kysymysnumeron. 
     * 
     * @return kysymysnumero. 
     */
    public int palautaKysymysnumero() {
        int palautus = kysymysnumero;
        return palautus;
    }
    
    /**
     * Metodi palauttaa pelaajan antaman vastauksen.
     * 
     * @return Pelaajan vastaus. 
     */
    public String palautaVastaus() {
        String palautus = vastauskenttä.getText();
        return palautus;
    }
    
    /**
     * Metodi palauttaa oikean vastauksen. 
     * @return 
     */
    public String palautaOikeaVastaus() {
        Alkuaine palautus = kysyttyAine;
        return palautus.aineenNimi();
    }
    
    /**
     * Palauttaa pelaajan yrityksien määrän nykyistä kysymystä kohden.
     * 
     * @return Yrityksien määrä. 
     */
    public int yrityksienMäärä() {
        int palautus = yrityskerta;
        return palautus;
    }
}