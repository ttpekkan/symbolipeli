package ohjelma;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Point;
import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;

/**
 * Tämä luokka kontrolloi tapahtumia, kun peli on aloitettu.
 * 
 * @author Timo Pekkanen
 */

public class PeliIkkuna implements Runnable {
    
    /**
     * Attribuuttien dokumentointi.
     * kysyttyAine: Alkuaine, jota ohjelma kysesellä hetkellä kysyy.
     * pistelista: Pistelista sisältää aikasempia tuloksia.
     * aika: Tämän luvun avulla päivitetään jäljellä olevaa aikaa.
     * helpot: Tämän luvun avulla kysytään helpot kysymykset.
     * keskivaikeat: Tämän luvun avulla kysytään keskivaikeat kysymykset.
     * kokonaispisteet: Kertoo, paljonko pisteitä on yhteensä kasassa.
     * kysymysnumero: Tämän avulla päivitetään monesko kysymys on menossa.
     * vaikeat: Tämän luvun avulla kysytään vaikeat kysymykset.
     * häviöIkkuna. Häviöikkuna JFrame.
     * kysymysIkkuna: Kysymysikkuna JFrame.
     * pelinPääikkuna: Pelin pääikkuna JFrame.
     * voittoikkuna: voittoikkuna JFrame.
     * ajanäyttö: JLabel, joka kertoo jäljellä olevan ajan.
     * moneskoKysymysMenossa: JLabel, joka kertoo, monesko kysymys on menossa.
     * pistetilanne: Näyttää, paljonko pisteistä on kasassa.
     * symboli: Näyttää kysyttävän aineen kemiallisen symbolin.
     * vaikeusaste: Näyttää, millä vaikeusasteella ollaan.
     * vihje: Mahdollisesti näyttää, mikä kysytyn aineen vihje on.
     * vastauskenttä: Kenttä, johon vastaukset kirjoitetaan.
     * Kysymysgeneraattori: Tämän avulla luodaan kysymykset.
     * musa: Tämän avulla huolehditaan äänentoistosta.
     * pistelistanimi: nimi, joka mahdollisesti tallennetaan pistelistaan.
     * ajastin: Tämän avulla lasketaan aikaa.
     * pääIkkunalaskuri: Tämän avulla pidetään huolta siitä, että vain yksi pääikkuna on auki kerralla. 
     * kysymysIkkunalaskuri. Tämän avulla pidetään huolta siitä, että vain yksi kysymysIkkuna on auki kerralla.
     */
    
    public void Attribuutit() {     
    }
    private Alkuaine kysyttyAine;
    private ArrayList<Tulos> pistelista;
    private int aika;
    private int helpot;
    private int keskivaikeat;
    private int kokonaispisteet;
    private int kysymysnumero;
    private int vaikeat;
    private int väärät;
    private JFrame häviöikkuna;
    private JFrame kysymysikkuna;
    private JFrame kysynimiIkkuna;
    private JFrame pelinPääikkuna;
    private JFrame voittoikkuna;
    private JLabel ajannäyttö;
    private JLabel moneskoKysymysMenossa;
    private JLabel pistetilanne;
    private JLabel symboli;
    private JLabel vaikeusaste;
    private JLabel vihje;
    private JTextField nimikenttä;
    private JTextField vastauskenttä;
    private Kysymysgeneraattori alkuaineet;
    private Musiikkikirjasto musa;
    private String pistelistanimi;
    private Timer ajastin;
    private static int pääikkunalaskuri = 0;
    private static int kysynimiIkkunalaskuri = 0;
    
    /**
     * Käynnistää pelin Pääikkunan ja asettaa sen alkuominaisuudet.
     */

    public void run() {
        if (pääikkunalaskuri == 0) {
            pelinPääikkuna = new JFrame();
            kokonaispisteet = 0;
            pääikkunalaskuri = pääikkunalaskuri + 1;
            kysymysnumero = 0;
            aika = 15;
            pelinPääikkuna.setTitle("Symbolipeli");
            Point piste = new Point(130, 180);
            pelinPääikkuna.setLocation(piste);
            UIManager.put("Button.select", Color.green);
            musa = new Musiikkikirjasto();
            musa.soitaAloituslaulu();

            luoKuva(musa.palautaKuvannimi(), pelinPääikkuna);
            luoKomponentit();
            lataaPistelista();

            neIkkunanavausToiminnotJotkaAinaSama(pelinPääikkuna);
            luoKysymysikkuna();
        }
    }
    
    /**
     * Tämän metodin avulla asetetaan halutulle nappulalle kaikki tarvittavat ominaisuudet.
     * 
     * @param nappula JButton, jota muokataan.
     * @param xSijainti Asettaa nappulan sijainnin x-tasossa.
     * @param ySijainti Asettaa nappulan sijainnin y-tasossa.
     * @param xKoko Asettaa nappulan leveyden.
     * @param yKoko Asettaa nappulan korkeuden.
     * @param väriTausta Asettaa nappulan taustavärin.
     * @param tausta Asettaa, näkyykö nappulan tausta vai ei.
     * @param väriTeksti Asettaa nappulan tekstin värin.
     * @param kohdistusMaalaus Asettaa, reagoiko nappula kun sitä kohdistetaan vai ei.
     * @param täytäAlue Asettaa, reagoiko nappula kun sitä painetaan vai ei.
     * @param näytäRajat Asettaa, näytetäänkä nappulan rajat vai ei.
     * @param fonttikoko Asettaa nappulassa olevan fontin koon.
     * @param rajanvärit Asettaa, minkä värinen nappulan raja on.
     * @param pohja Määrää, mihin pohjaan nappula lisätään.
     */

    private void luoNappula(JButton nappula, int xSijainti, int ySijainti, int xKoko,
            int yKoko, Color väriTausta, boolean tausta, Color väriTeksti,
            boolean kohdistusMaalaus, boolean täytäAlue, boolean näytäRajat,
            int fonttikoko, Color rajanvärit, Container pohja) {
        nappula.setLocation(xSijainti, ySijainti);
        nappula.setSize(xKoko, yKoko);
        nappula.setBackground(väriTausta);
        nappula.setOpaque(tausta);
        nappula.setForeground(väriTeksti);
        nappula.setFocusPainted(kohdistusMaalaus);
        nappula.setContentAreaFilled(täytäAlue);
        nappula.setBorderPainted(näytäRajat);
        nappula.setFont(new Font("Serif", Font.BOLD, fonttikoko));
        nappula.setBorder(new LineBorder(rajanvärit));
        pohja.add(nappula);
    }
    
    /**
     * Tämän metodin avulla asetetaan halutulle tekstille kaikki tarvittavat ominaisuudet.
     * 
     * @param teksti JLabel, jota muokataan.
     * @param xSijainti Asettaa tekstin sijainnin x-tasossa.
     * @param ySijainti Asettaa tekstin sijainnin y-tasossa.
     * @param xKoko Asettaa tekstilaatikon leveyden.
     * @param yKoko Asettaa tekstilaatikon korkeuden.
     * @param tausta Asettaa, näkyykö tekstilaatikon tausta vai ei.
     * @param väriTausta Asettaa tekstilaatikon taustavärin.
     * @param väriKirjoitus Asettaa tekstin värin.
     * @param fonttikoko Asettaa tekstin fontin koon.
     * @param pohja  Määrää, mihin pohjaan nappula lisätään.
     */

    private void luoTeksti(JLabel teksti, int xSijainti, int ySijainti, int xKoko,
            int yKoko, boolean tausta, Color väriTausta, Color väriKirjoitus,
            int fonttikoko, Container pohja) {
        teksti.setLocation(xSijainti, ySijainti);
        teksti.setSize(xKoko, yKoko);
        teksti.setOpaque(tausta);
        teksti.setBackground(väriTausta);
        teksti.setForeground(väriKirjoitus);
        teksti.setFont(new Font("Serif", Font.BOLD, fonttikoko));
        pohja.add(teksti);
    }
    
    /**
     * Tämän metodin avulla asetetaan halutulle tekstikentälle kaikki tarvittavat ominaisuudet.
     * 
     * @param kenttä Kenttä, jota muokataan.
     * @param xSijainti Asettaa kentän sijainnin x-tasossa.
     * @param ySijainti Asettaa kentän sijainnin y-tasossa.
     * @param xKoko Asettaa kentän leveyden.
     * @param yKoko Asettaa kentän korkeuden.
     * @param fonttikoko Asettaa tekstikentän fontin koon.
     * @param kentänTeksti Asettaa tekstikenttään halutun tekstin.
     * @param pohja Määrää, mihin pohjaan tekstikenttä lisätään.
     */

    private void luoTekstikenttä(JTextField kenttä, int xSijainti, int ySijainti,
            int xKoko, int yKoko, int fonttikoko, String kentänTeksti,
            Container pohja) {
        kenttä.setLocation(xSijainti, ySijainti);
        kenttä.setSize(xKoko, yKoko);
        kenttä.setFont(new Font("Serif", Font.BOLD, fonttikoko));
        kenttä.setText(kentänTeksti);
        pohja.add(kenttä);
    }
    
    /**
     * Metodi suorittaa ne toiminnot, jotka ovat identtisiä jokaisessa ikkunnassa.
     * 
     * @param ikkuna Ikkuna määrää, mihin ikkunaan toiminnot tehdään. 
     */

    private void neIkkunanavausToiminnotJotkaAinaSama(JFrame ikkuna) {
        ikkuna.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        ikkuna.setResizable(false);
        ikkuna.pack();
        ikkuna.setVisible(true);
    }
    
    /**
     * Metodi luo halutut komponentit Pelin pääikkunaan.
     */

    private void luoKomponentit() {

        Container pohja = pelinPääikkuna.getContentPane();

        JButton nappula = new JButton("Sulje Peli");
        luoNappula(nappula, 700, 30, 300, 75, Color.white, false,
                Color.green.darker(), false, true, true, 34, Color.green.darker(), pohja);
        nappula.addActionListener(new PeliIkkunanKuuntelija(this));

        pistetilanne = new JLabel("Pisteet: " + kokonaispisteet);
        luoTeksti(pistetilanne, 30, 600, 140, 20, true, Color.GRAY, Color.white, 20, pohja);

        vaikeusaste = new JLabel("Vaikeusaste: helppo");
        luoTeksti(vaikeusaste, 30, 630, 290, 20, true, Color.GRAY, Color.white, 20, pohja);

        moneskoKysymysMenossa = new JLabel("Kysymys: " + kysymysnumero);
        luoTeksti(moneskoKysymysMenossa, 30, 660, 160, 20, true, Color.GRAY, Color.white, 20, pohja);

        ajastin = new Timer(1000, new AjastimenKuuntelija(this));

        ajannäyttö = new JLabel("Aika: " + aika);
        luoTeksti(ajannäyttö, 850, 630, 150, 50, true, Color.GRAY, Color.white, 30, pohja);
    }
    
    /**
     * Metodi luo taustakuvan haluttuun ikkunaan.
     * 
     * @param tiedosto Kuvatiedosto, jonka pohjalta taustakuva muodostetaan.
     * @param ikkuna Ikkuna, johon taustakuva lisätään.
     */

    private void luoKuva(String tiedosto, JFrame ikkuna) {
        try {
         // JLabel label = new JLabel(new ImageIcon(ImageIO.read(new File(tiedosto))));
            JLabel label = new JLabel(new ImageIcon(ImageIO.read(new File("src/" + tiedosto))));
            ikkuna.setContentPane(label);
        } catch (Exception e) {
            System.out.println("HEI HEI");
            System.out.println(e);
            System.out.println("HEI HEI");
        }
    }
    
    /**
     * Suorittaa alustavat toimenpiteet, kun peli aloitetaan.
     */

    public void aloitaPeli() {
        moneskoKysymysMenossa.setText("Kysymys: " + kysymysnumero);
        helpot = 0;
        keskivaikeat = 0;
        vaikeat = 0;
        väärät = 0;
        alkuaineet = new Kysymysgeneraattori();
        kysyKysymys(alkuaineet.palautaHelpotKysymykset().get(helpot));
        try {
            Thread.sleep(4000);
        } catch (Exception e) {
        }
        musa.jatkuvaToistoPelilaulu();
        ajastin.start();
    }
    
    /**
     * Tämän metodin avulla esitetään kysymys alkuaineesta.
     * 
     * @param aine Alkuaine-olio, josta kysymys esitetään.
     */

    private void kysyKysymys(Alkuaine aine) {
        väärät = 0;
        kysyttyAine = aine;
        symboli.setText("             " + kysyttyAine.palautaSymboli());
        if (helpot == 0) {
            vastauskenttä.setText("Kirjoita symbolia vastaavan alkuaineen nimi tähän (pienillä).");
        }
    }
    
    /**
     * Tämä metodi päivittää tilanteen, jos on vastannut oikein, sekä laukaisee voitto-metodin, jos olosuhteet oikeat.
     * 
     * @param kerroin Kerroin vaikuttaa siihen, paljonko pisteitä annetaan. Kertoimen määrää sen mukaan, onko vihjettä annettu vai ei.
     */

    public void päivitäTilanne(int kerroin) {
        aika = 15;
        vihje.setText("Edellinen Oikein!");
        vastauskenttä.setText("");
        väärät = 0;
        kysymysnumero = kysymysnumero + 1;
        moneskoKysymysMenossa.setText("Kysymys: " + kysymysnumero);
        if (helpot < 11 && keskivaikeat == 0 && vaikeat == 0) {
            kysyHelppo(kerroin);
            if (helpot == 11) {
                vaikeusaste.setText("Vaikeusaste: keskivaikea");
                vihje.setText("Edellinen Oikein! Siirrytään keskivaikeisiin.");
                kysyKysymys(alkuaineet.palautaKeskivaikeatKysymykset().get(keskivaikeat));
                return;
            }
        }
        if (helpot == 11 && keskivaikeat < 46 && vaikeat == 0) {
            kysyKeskivaikea(kerroin);
            if (keskivaikeat == 46) {
                vaikeusaste.setText("Vaikeusaste: vaikea");
                vihje.setText("Edellinen Oikein! Siirrytään vaikeisiin.");
                kysyKysymys(alkuaineet.palautaVaikeatKysymykset().get(vaikeat));
                return;
            }
        }
        if (helpot == 11 && keskivaikeat == 46 && vaikeat < 54) {
            kysyVaikea(kerroin);
            if (vaikeat == 54) {
                voitit();
            }
        }
    }
    
    /**
     * Tämän metodin avulla kysytään helpot kysymykset ja päivitetään tilannetta. 
     * 
     * @param kertoluku Kertoluku vaikuttaa siihen, paljonko pisteitä annetaan. Kertoimen määrää päivitäTilanne-metodin kerroin.
     */

    private void kysyHelppo(int kertoluku) {
        helpot = helpot + 1;
        kokonaispisteet = kokonaispisteet + (1 * kertoluku);
        pistetilanne.setText("Pisteet: " + kokonaispisteet);
        if (helpot < 11) {
            kysyKysymys(alkuaineet.palautaHelpotKysymykset().get(helpot));
        }
    }
    
    /**
     * Tämän metodin avulla kysytään keskivaikeat kysymykset ja päivitetään tilannetta.
     * 
     * @param kertoluku Kertoluku vaikuttaa siihen, paljonko pisteitä annetaan. Kertoimen määrää päivitäTilanne-metodin kerroin.
     */

    private void kysyKeskivaikea(int kertoluku) {
        keskivaikeat = keskivaikeat + 1;
        kokonaispisteet = kokonaispisteet + (2 * kertoluku);
        pistetilanne.setText("Pisteet: " + kokonaispisteet);
        if (keskivaikeat < 46) {
            kysyKysymys(alkuaineet.palautaKeskivaikeatKysymykset().get(keskivaikeat));
        }
    }
    
    /**
     * Tämän metodin avulla kysytään vaikeat kysymykset ja päivitetään tilannetta.
     * 
     * @param kertoluku Kertoluku vaikuttaa siihen, paljonko pisteitä annetaan. Kertoimen määrää päivitäTilanne-metodin kerroin.
     */

    private void kysyVaikea(int kertoluku) {
        vaikeat = vaikeat + 1;
        kokonaispisteet = kokonaispisteet + (3 * kertoluku);
        pistetilanne.setText("Pisteet: " + kokonaispisteet);
        if (vaikeat < 54) {
            kysyKysymys(alkuaineet.palautaVaikeatKysymykset().get(vaikeat));
        }
    }
    
    /**
     * Tämä metodin avulla annetaan pelaajalle vihje, jos hän on vastannut väärin. 
     */

    public void annaVihje() {
        aika = 15;
        väärät = väärät + 1;
        vihje.setText(kysyttyAine.palautaVihje());
        vastauskenttä.setText("");
    }
    
    /**
     * Tämä metodi luo kysymysikkunan.
     */

    private void luoKysymysikkuna() {
        kysymysikkuna = new JFrame();
     // luoKuva("/home/timo/symbolipeli/ohjelma/src/kysymys.jpeg", kysymysikkuna);
        luoKuva("kysymys.png", kysymysikkuna);
        kysymysikkuna.setLocation(100, 10);
        luoKysymystenKomponentit();

        neIkkunanavausToiminnotJotkaAinaSama(kysymysikkuna);
    }
    
    /**
     * Tämä metodi luo tarvittavat kysymysikkunan komponentit.
     */

    private void luoKysymystenKomponentit() {
        Container pohja = kysymysikkuna.getContentPane();

        symboli = new JLabel("Symboli tulee tähän.");
        luoTeksti(symboli, 330, 10, 400, 50, false, Color.red, Color.white, 34, pohja);

        vihje = new JLabel("Vihje tulee tähän. Vastausaikaa 15 sekuntia.");
        luoTeksti(vihje, 10, 75, 1070, 30, false, Color.red, Color.white, 14, pohja);

        JLabel tekstiKentänSelitys = new JLabel("Ole hyvä ja kirjoita vastauksesi allaolevaan tekstikenttään.");
        luoTeksti(tekstiKentänSelitys, 10, 120, 800, 20, false, Color.red, Color.white, 14, pohja);

        vastauskenttä = new JTextField();
        luoTekstikenttä(vastauskenttä, 10, 140, 500, 20, 14, "Kirjoita 'pelaa' "
                + "tähän, jos haluat pelata, muuten lopetetaan.", pohja);

        JButton ok = new JButton("ok");
        luoNappula(ok, 10, 170, 50, 30, Color.white, false, Color.white, false, true,
                true, 16, Color.white, pohja);
        ok.addActionListener(new PeliIkkunanKuuntelija(this));
        kysymysikkuna.getRootPane().setDefaultButton(ok);
    }
    
    /**
     * Metodi luo voittoikkunan, jos pelaaja voittaa pelin. 
     */

    private void voitit() {
        ajastin.stop();
        kysymysikkuna.dispose();
        musa.pysäytäPelilaulu();
        musa.soitaVoittolaulu();
        voittoikkuna = new JFrame();
        voittoikkuna.setLocation(200, 300);
     // luoKuva("/home/timo/symbolipeli/ohjelma/src/voitto.jpeg", voittoikkuna);
        luoKuva("voitto.png", voittoikkuna);
        voittoKomponentit();

        neIkkunanavausToiminnotJotkaAinaSama(voittoikkuna);
    }
    
    /**
     * Metodi luo tarvittavat komponentit voittoikkunaan.
     */

    private void voittoKomponentit() {
        Container pohja = voittoikkuna.getContentPane();

        JLabel pinnat = new JLabel("Pisteesi: " + kokonaispisteet);
        luoTeksti(pinnat, 140, 250, 300, 40, false, Color.red, Color.gray, 20, pohja);

        JLabel gameover = new JLabel("Voitit Pelin!");
        luoTeksti(gameover, 110, 0, 300, 35, false, Color.red, Color.gray, 30, pohja);

        JButton lopeta = new JButton("Lopeta Peli");
        luoNappula(lopeta, 120, 300, 150, 30, Color.gray, false, Color.gray, false,
                true, true, 16, Color.gray, pohja);
        lopeta.addActionListener(new PeliIkkunanKuuntelija(this));
    }
    
    /**
     * Metodi luo häviöikkunan, jos pelaaja häviää pelin. 
     */

    public void hävisit() {
        ajastin.stop();
        kysymysikkuna.dispose();
        musa.pysäytäPelilaulu();
        musa.soitaHäviölaulu();
        häviöikkuna = new JFrame();
        häviöikkuna.setLocation(200, 300);
     // luoKuva("/home/timo/symbolipeli/ohjelma/src/häviö.jpeg", häviöikkuna);
        luoKuva("häviö.png", häviöikkuna);
        hävisitKomponentit();

        neIkkunanavausToiminnotJotkaAinaSama(häviöikkuna);
    }
    
    /**
     * Metodi luo tarvittavat komponentit häviöikkunaan. 
     */

    private void hävisitKomponentit() {
        Container pohja = häviöikkuna.getContentPane();

        JLabel pinnat = new JLabel("Pisteesi: " + kokonaispisteet);
        luoTeksti(pinnat, 180, 260, 300, 40, false, Color.red, Color.white, 20, pohja);

        JLabel gameover = new JLabel("Game Over!");
        luoTeksti(gameover, 150, 140, 300, 40, false, Color.red, Color.white, 30, pohja);

        JLabel oikea = new JLabel("Oikea vastaus olisi ollut: " + kysyttyAine.palautaNimi());
        luoTeksti(oikea, 90, 170, 400, 30, false, Color.red, Color.white, 16, pohja);

        JButton lopeta = new JButton("Lopeta Peli");
        luoNappula(lopeta, 175, 340, 150, 30, Color.white, false, Color.white, false,
                true, true, 16, Color.white, pohja);
        lopeta.addActionListener(new PeliIkkunanKuuntelija(this));
    }
    
    /**
     * Metodi luo kysynimimerkki-ikkunan, jonka avullaa tiedustellaan pelaajan nimimerkkiä. 
     */

    public void kysyNimimerkki() {
        if (kysynimiIkkunalaskuri == 0) {
            kysynimiIkkuna = new JFrame();
            kysynimiIkkunalaskuri = kysynimiIkkunalaskuri + 1;
         // luoKuva("/home/timo/symbolipeli/ohjelma/src/kysymys.jpeg", kysynimiIkkuna);
            luoKuva("kysymys.png", kysynimiIkkuna);
            kysynimiIkkuna.setLocation(300, 300);
            luoKysyNimimerkkiKomponentit();

            neIkkunanavausToiminnotJotkaAinaSama(kysynimiIkkuna);
        }
    }
    
    /**
     * Metodi luo tarvittavat komponentit kysynimimerkki-ikkunaan.
     */

    private void luoKysyNimimerkkiKomponentit() {
        Container pohja = kysynimiIkkuna.getContentPane();

        JLabel tähänVastaus = new JLabel("Ole hyvä ja kirjoita nimesi allaolevaan tekstikenttään.");
        luoTeksti(tähänVastaus, 10, 80, 800, 20, false, Color.red, Color.white, 14, pohja);

        nimikenttä = new JTextField();
        luoTekstikenttä(nimikenttä, 10, 100, 200, 20, 14, "", pohja);

        JButton ok = new JButton("Tallenna Nimimerkki");
        luoNappula(ok, 10, 130, 230, 30, Color.white, false, Color.white, false,
                true, true, 16, Color.white, pohja);
        ok.addActionListener(new PeliIkkunanKuuntelija(this));

    }
    
    /**
     * Metodi laskee, pääseekö tulos pistelistalle. 
     * 
     * @param pisteet Pelaajan pisteet.
     * @return Metodi palauttaa true tai false, pisteistä ja pistelistasta riippuen.
     */

    public boolean pääseeListalle(int pisteet) {
        if (pisteet > pistelista.get(pistelista.size() - 1).palautaPisteet()) {
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * Tämä metodi lataa pistelista-ArrayListiin tekstitiedostossa olevat tiedot. 
     */

    private void lataaPistelista() {
        pistelista = new ArrayList<Tulos>();
        try {
            Scanner lukija = new Scanner(new File("src/top10.txt"));
            while (lukija.hasNextLine()) {
                pistelista.add(new Tulos(lukija.nextLine(), Integer.parseInt(lukija.nextLine())));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        Collections.sort(pistelista);
    }
    
    /**
     * Metodi lisää tuloksen pistelistaan.
     * 
     * @param nimi Pistelistaan lisätty nimimerkki.
     * @param pisteet Pistelistaan lisätyt pisteet.
     */

    public void lisääTulos(String nimi, int pisteet) {
        pistelista.remove(pistelista.size() - 1);
        pistelista.add(new Tulos(nimi, pisteet));
        Collections.sort(pistelista);
    }
    
    /**
     * Tämä metodi tallentaa pistelista-ArrayListin tiedot tekstitiedostoon. 
     */

    public void tallennaPistelista() {
        try {
            PrintWriter kirjoittaja = new PrintWriter(new File("src/top10.txt"));
            for (int i = 0; i < pistelista.size(); i = i + 1) {
                kirjoittaja.println(pistelista.get(i).palautaNimi());
                kirjoittaja.println(pistelista.get(i).palautaPisteet());
            }
            kirjoittaja.close();
        } catch (Exception e) {
            System.out.println("Virhe!");
        }
    }
    
    /**
     * Tämä metodi vähentää aikaa, laukaisee vihjeen tai laukaisee häviön. 
     */

    public void vähennäAikaa() {
        if (aika > 0) {
            aika = aika - 1;
            ajannäyttö.setText("Aika: " + aika);
        } else {
            if (väärät == 0) {
                annaVihje();
            } else {
                ajastin.stop();
                hävisit();
            }
        }
    }
    
    /**
     * Palauttaa sen alkuaine-olion, jotta tällä hetkellä kysytään.
     * @return Kysytty alkuaine-olio.
     */

    public Alkuaine palautaKysyttyAine() {
        return kysyttyAine;
    }
    
    /**
     * Asettaa kysymysnumeron arvoksi yhden.
     */

    public void AsetaKysymynumero() {
        kysymysnumero = 1;
    }
    
    /**
     * Metodi palauttaa kysymysnumeron.
     * @return Palautettu kysymysnumero. 
     */

    public int palautaKysymysnumero() {
        return kysymysnumero;
    }
    
    /**
     * Metodi kertoo, mikä väärät-attribuutin arvo on.
     * @return väärät-attribuutin arvo.
     */

    public int palautaVäärät() {
        return väärät;
    }
    
    /**
     * Metodi palauttaa kokonaispisteet.
     * @return Kokonaispisteet-attribuutin arvo.
     */

    public int palautaKokonaispisteet() {
        return kokonaispisteet;
    }
    
    /**
     * Palauttaa pelin pääikkunan.
     * @return Pelin pääikkuna.
     */

    public JFrame palautaPelinPääikkuna() {
        return pelinPääikkuna;
    }
    
    /**
     * Palauttaa voittoikkunan.
     * @return Voittoikkuna.
     */

    public JFrame palautaVoittoikkuna() {
        return voittoikkuna;
    }
    
    /**
     * Palauttaa häviöikkunan.
     * @return Häviöikkuna.
     */

    public JFrame palautaHäviöikkuna() {
        return häviöikkuna;
    }
    
    /**
     * Palauttaa kysymysikkunan.
     * @return Kysymysikkuna.
     */

    public JFrame palautaKysymysikkuna() {
        return kysymysikkuna;
    }
    
    /**
     * Palauttaa kysynimi-ikkunan.
     * @return Kysynimi-ikkuna.
     */

    public JFrame palautaKysynimiIkkuna() {
        return kysynimiIkkuna;
    }
    
    /**
     * Metodi vähentää yhden pääikkunalaskurista. 
     */

    public void vähennäPääikkunalaskuri() {
        pääikkunalaskuri = pääikkunalaskuri - 1;
    }
    
    /**
     * Metodi vähentää yhden kysynimi-ikkunalaskurista.
     */

    public void vähennäKysynimiIkkunalaskuri() {
        kysynimiIkkunalaskuri = kysynimiIkkunalaskuri - 1;
    }
    
    /**
     * Metodi palauttaa Musiikkikirjaston.
     * @return Musiikkikirjasto.
     */

    public Musiikkikirjasto palautaMusiikkikirjasto() {
        return musa;
    }
    
    /**
     * Metodi palauttaa ajastimen.
     * @return Ajastin.
     */

    public Timer palautaAjastin() {
        return ajastin;
    }
    
    /**
     * Metodi palauttaa vastauskentän.
     * @return Vastauskenttä.
     */

    public JTextField palautaVastauskenttä() {
        return vastauskenttä;
    }
    
    /**
     * Metodi antaa arvon pistelistanimi-attribuutille, jos nimikentällä on arvo, ja palauttaa sen.
     * @return Pistelistaan annettu nimi;
     */

    public String palautaPistelistanimi() {
        pistelistanimi = "";
        if (nimikenttä.getText() != null) {
            pistelistanimi = nimikenttä.getText();
            return pistelistanimi;
        }
        return pistelistanimi;
    }
}
