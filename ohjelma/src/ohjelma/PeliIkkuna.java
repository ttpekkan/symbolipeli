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
 *
 * @author timo
 */
public class PeliIkkuna implements Runnable {

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
    private JLabel kysymysnro;
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
            musa.palautaAloituslaulu().play();

            luoKuva(musa.PalautaLauluJaKuvaLista().get(0).palautaKuva(), pelinPääikkuna);
            luoKomponentit();
            lataaPistelista();

            neIkkunanavausToiminnotJotkaAinaSama(pelinPääikkuna);
            luoKysymysikkuna();
        }
    }

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

    private void luoTekstikenttä(JTextField kenttä, int xSijainti, int ySijainti,
            int xKoko, int yKoko, int fonttikoko, String kentänTeksti,
            Container pohja) {
        kenttä.setLocation(xSijainti, ySijainti);
        kenttä.setSize(xKoko, yKoko);
        kenttä.setFont(new Font("Serif", Font.BOLD, fonttikoko));
        kenttä.setText(kentänTeksti);
        pohja.add(kenttä);
    }

    private void neIkkunanavausToiminnotJotkaAinaSama(JFrame ikkuna) {
        ikkuna.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        ikkuna.setResizable(false);
        ikkuna.pack();
        ikkuna.setVisible(true);
    }

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

        kysymysnro = new JLabel("Kysymys: " + kysymysnumero);
        luoTeksti(kysymysnro, 30, 660, 160, 20, true, Color.GRAY, Color.white, 20, pohja);

        ajastin = new Timer(1000, new AjastimenKuuntelija(this));

        ajannäyttö = new JLabel("Aika: " + aika);
        luoTeksti(ajannäyttö, 850, 630, 150, 50, true, Color.GRAY, Color.white, 30, pohja);
    }

    private void luoKuva(String tiedosto, JFrame ikkuna) {
        try {
            JLabel label = new JLabel(new ImageIcon(ImageIO.read(new File(tiedosto))));
            ikkuna.setContentPane(label);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void aloitaPeli() {
        kysymysnro.setText("Kysymys: " + kysymysnumero);
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
        musa.palautaPelilaulu().loop();
        ajastin.start();
    }

    private void kysyKysymys(Alkuaine aine) {
        väärät = 0;
        kysyttyAine = aine;
        symboli.setText("             " + kysyttyAine.palautaSymboli());
        if (helpot == 0) {
            vastauskenttä.setText("Kirjoita symbolia vastaavan alkuaineen nimi tähän (pienillä).");
        }
    }

    public void päivitäTilanne(int kerroin) {
        aika = 15;
        vihje.setText("Edellinen Oikein!");
        vastauskenttä.setText("");
        väärät = 0;
        kysymysnumero = kysymysnumero + 1;
        kysymysnro.setText("Kysymys: " + kysymysnumero);
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

    private void kysyHelppo(int kertoluku) {
        helpot = helpot + 1;
        kokonaispisteet = kokonaispisteet + (1 * kertoluku);
        pistetilanne.setText("Pisteet: " + kokonaispisteet);
        if (helpot < 11) {
            kysyKysymys(alkuaineet.palautaHelpotKysymykset().get(helpot));
        }
    }

    private void kysyKeskivaikea(int kertoluku) {
        keskivaikeat = keskivaikeat + 1;
        kokonaispisteet = kokonaispisteet + (2 * kertoluku);
        pistetilanne.setText("Pisteet: " + kokonaispisteet);
        if (keskivaikeat < 46) {
            kysyKysymys(alkuaineet.palautaKeskivaikeatKysymykset().get(keskivaikeat));
        }
    }

    private void kysyVaikea(int kertoluku) {
        vaikeat = vaikeat + 1;
        kokonaispisteet = kokonaispisteet + (3 * kertoluku);
        pistetilanne.setText("Pisteet: " + kokonaispisteet);
        if (vaikeat < 54) {
            kysyKysymys(alkuaineet.palautaVaikeatKysymykset().get(vaikeat));
        }
    }

    public void annaVihje() {
        aika = 15;
        väärät = väärät + 1;
        vihje.setText(kysyttyAine.palautaVihje());
        vastauskenttä.setText("");
    }

    private void luoKysymysikkuna() {
        kysymysikkuna = new JFrame();
        luoKuva("/home/timo/symbolipeli/ohjelma/src/kysymys.jpeg", kysymysikkuna);
        kysymysikkuna.setLocation(100, 10);
        luoKysymystenKomponentit();

        neIkkunanavausToiminnotJotkaAinaSama(kysymysikkuna);
    }

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

    private void voitit() {
        ajastin.stop();
        kysymysikkuna.dispose();
        musa.palautaPelilaulu().stop();
        musa.palautaVoittolaulu().play();
        voittoikkuna = new JFrame();
        voittoikkuna.setLocation(200, 300);
        luoKuva("/home/timo/symbolipeli/ohjelma/src/voitto.jpeg", voittoikkuna);
        voittoKomponentit();

        neIkkunanavausToiminnotJotkaAinaSama(voittoikkuna);
    }

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

    public void hävisit() {
        ajastin.stop();
        kysymysikkuna.dispose();
        musa.palautaPelilaulu().stop();
        musa.palautaHäviölaulu().play();
        häviöikkuna = new JFrame();
        häviöikkuna.setLocation(200, 300);
        luoKuva("/home/timo/symbolipeli/ohjelma/src/häviö.jpeg", häviöikkuna);
        hävisitKomponentit();

        neIkkunanavausToiminnotJotkaAinaSama(häviöikkuna);
    }

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

    public void kysyNimimerkki() {
        if (kysynimiIkkunalaskuri == 0) {
            kysynimiIkkuna = new JFrame();
            kysynimiIkkunalaskuri = kysynimiIkkunalaskuri + 1;
            luoKuva("/home/timo/symbolipeli/ohjelma/src/kysymys.jpeg", kysynimiIkkuna);
            kysynimiIkkuna.setLocation(300, 300);
            luoKysyNimimerkkiKomponentit();

            neIkkunanavausToiminnotJotkaAinaSama(kysynimiIkkuna);
        }
    }

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

    public boolean pääseeListalle(int pisteet) {
        if (pisteet > pistelista.get(pistelista.size() - 1).palautaPisteet()) {
            return true;
        } else {
            return false;
        }
    }

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

    public void lisääTulos(String nimi, int pisteet) {
        pistelista.remove(pistelista.size() - 1);
        pistelista.add(new Tulos(nimi, pisteet));
        Collections.sort(pistelista);
    }

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

    public Alkuaine palautaKysyttyAine() {
        return kysyttyAine;
    }

    public void AsetaKysymynumero() {
        kysymysnumero = 1;
    }

    public int palautaKysymysnumero() {
        return kysymysnumero;
    }

    public int palautaVäärät() {
        return väärät;
    }

    public int palautaKokonaispisteet() {
        return kokonaispisteet;
    }

    public JFrame palautaPelinPääikkuna() {
        return pelinPääikkuna;
    }

    public JFrame palautaVoittoikkuna() {
        return voittoikkuna;
    }

    public JFrame palautaHäviöikkuna() {
        return häviöikkuna;
    }

    public JFrame palautaKysymysikkuna() {
        return kysymysikkuna;
    }

    public JFrame palautaKysynimiIkkuna() {
        return kysynimiIkkuna;
    }

    public void vähennäPääikkunalaskuri() {
        pääikkunalaskuri = pääikkunalaskuri - 1;
    }

    public void vähennäKysynimiIkkunalaskuri() {
        kysynimiIkkunalaskuri = kysynimiIkkunalaskuri - 1;
    }

    public Musiikkikirjasto palautaMusiikkikirjasto() {
        return musa;
    }

    public Timer palautaAjastin() {
        return ajastin;
    }

    public JTextField palautaVastauskenttä() {
        return vastauskenttä;
    }

    public String palautaPistelistanimi() {
        pistelistanimi = "";
        if (nimikenttä.getText() != null) {
            pistelistanimi = nimikenttä.getText();
            return pistelistanimi;
        }
        return pistelistanimi;
    }
}
