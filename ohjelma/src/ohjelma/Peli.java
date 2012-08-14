/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ohjelma;

import java.awt.*;
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
public class Peli implements Runnable {

    public Alkuaine kysyttyAine;
    public int väärät;
    public Kysymysgeneraattori alkuaineet;
    public JFrame ikkuna3;
    public Container pohja;
    public ArrayList<Tulos> pistelista;
    public Musiikkikirjasto musa;
    public static int laskuri = 0;
    public static int laskuri2 = 0;
    public JLabel pistetilanne;
    public JLabel vaikeusaste;
    public JLabel kysymysnro;
    public JLabel ajanNäyttö;
    public int kokonaispisteet;
    public int kysymysnumero;
    public Timer ajastin;
    public int aika;
    public int helpot;
    public int keskivaikeat;
    public int vaikeat;
    public String vastaus;
    public JTextField vastauskenttä;
    public JTextField nimikenttä;
    public JFrame nimimerkki;
    public String pistelistanimi;
    public JFrame kysymys;
    public JFrame häviö;
    public JFrame voitto;
    public JLabel symboli;
    public JLabel vihje;

    public void run() {
        ikkuna3 = new JFrame();
        if (laskuri == 0) {
            kokonaispisteet = 0;
            laskuri = laskuri + 1;
            kysymysnumero = 0;
            aika = 15;
            ikkuna3.setTitle("Symbolipeli");
            Point piste = new Point(130, 180);
            ikkuna3.setLocation(piste);
            UIManager.put("Button.select", Color.green);
            musa = new Musiikkikirjasto();
            musa.uusiPeli();

            luoKuva();
            luoKomponentit();
            lataaPistelista();

            ikkuna3.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
            ikkuna3.setResizable(false);
            ikkuna3.pack();
            ikkuna3.setVisible(true);
            luoKysymysikkuna();
            musa.aloitusKlippi();          
        } else {
            ikkuna3.dispose();
        }

    }

    public void luoKomponentit() {
        pohja = ikkuna3.getContentPane();

        JButton nappula = new JButton("Sulje Peli");
        nappula.setLocation(700, 30);
        nappula.setSize(300, 75);
        nappula.setBackground(Color.white);
        nappula.setOpaque(false);
        nappula.setForeground(Color.green.darker());
        nappula.setFocusPainted(false);
        nappula.setContentAreaFilled(true);
        nappula.setBorderPainted(true);
        nappula.setFont(new Font("Serif", Font.BOLD, 34));
        nappula.setBorder(new LineBorder(Color.green.darker()));
        nappula.addActionListener(new NappulanKuuntelija3(this));
        pohja.add(nappula);

        pistetilanne = new JLabel("Pisteet: " + kokonaispisteet);
        pistetilanne.setLocation(30, 600);
        pistetilanne.setSize(140, 20);
        pistetilanne.setOpaque(true);
        pistetilanne.setBackground(Color.gray);
        pistetilanne.setForeground(Color.white);
        pistetilanne.setFont(new Font("Serif", Font.BOLD, 20));
        pohja.add(pistetilanne);

        vaikeusaste = new JLabel("Vaikeusaste: helppo");
        vaikeusaste.setLocation(30, 630);
        vaikeusaste.setSize(290, 20);
        vaikeusaste.setOpaque(true);
        vaikeusaste.setBackground(Color.gray);
        vaikeusaste.setForeground(Color.white);
        vaikeusaste.setFont(new Font("Serif", Font.BOLD, 20));
        pohja.add(vaikeusaste);

        kysymysnro = new JLabel("Kysymys: " + kysymysnumero);
        kysymysnro.setLocation(30, 660);
        kysymysnro.setSize(160, 20);
        kysymysnro.setOpaque(true);
        kysymysnro.setBackground(Color.gray);
        kysymysnro.setForeground(Color.white);
        kysymysnro.setFont(new Font("Serif", Font.BOLD, 20));
        pohja.add(kysymysnro);

        ajastin = new Timer(1000, new AjastimenKuuntelija(this));

        ajanNäyttö = new JLabel("Aika: " + aika);
        ajanNäyttö.setLocation(850, 630);
        ajanNäyttö.setSize(150, 50);
        ajanNäyttö.setOpaque(true);
        ajanNäyttö.setBackground(Color.gray);
        ajanNäyttö.setForeground(Color.white);
        ajanNäyttö.setFont(new Font("Serif", Font.BOLD, 30));
        pohja.add(ajanNäyttö);


    }

    public void luoKuva() {
        try {
            JLabel label = new JLabel(new ImageIcon(ImageIO.read(new File(musa.lista2.get(0).kuvannimi))));
            ikkuna3.setContentPane(label);
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
        alkuaineet.luoKaikkiKysymykset();
        kysyKysymys(alkuaineet.helpotKysymykset.get(helpot));
        try {
            Thread.sleep(4000);
        } catch (Exception e) {           
        }
        musa.pelimusa.loop();
        ajastin.start();
    }

    public void kysyKysymys(Alkuaine aine) {
        väärät = 0;
        kysyttyAine = aine;
        symboli.setText("             " + kysyttyAine.symboli);
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
            helpot = helpot + 1;
            kokonaispisteet = kokonaispisteet + (1 * kerroin);
            pistetilanne.setText("Pisteet: " + kokonaispisteet);
            if (helpot < 11) {
                kysyKysymys(alkuaineet.helpotKysymykset.get(helpot));
            }
            if (helpot == 11) {
                vaikeusaste.setText("Vaikeusaste: keskivaikea");
                vihje.setText("Edellinen Oikein! Siirrytään keskivaikeisiin.");
                kysyKysymys(alkuaineet.keskivaikeatKysymykset.get(keskivaikeat));
                return;
            }
        }
        if (helpot == 11 && keskivaikeat < 46 && vaikeat == 0) {
            keskivaikeat = keskivaikeat + 1;
            kokonaispisteet = kokonaispisteet + (2 * kerroin);
            pistetilanne.setText("Pisteet: " + kokonaispisteet);
            if (keskivaikeat < 46) {
                kysyKysymys(alkuaineet.keskivaikeatKysymykset.get(keskivaikeat));
            }
            if (keskivaikeat == 46) {
                vaikeusaste.setText("Vaikeusaste: vaikea");
                vihje.setText("Edellinen Oikein! Siirrytään vaikeisiin.");
                kysyKysymys(alkuaineet.vaikeatKysymykset.get(vaikeat));
                return;
            }
        }
        if (helpot == 11 && keskivaikeat == 46 && vaikeat < 54) {
            vaikeat = vaikeat + 1;
            kokonaispisteet = kokonaispisteet + (3 * kerroin);
            pistetilanne.setText("Pisteet: " + kokonaispisteet);
            if (vaikeat < 54) {
                kysyKysymys(alkuaineet.vaikeatKysymykset.get(vaikeat));
            }
            if (vaikeat == 54) {
                voitit();
            }
        }
    }

    public void annaVihje() {
        aika = 15;
        väärät = väärät + 1;
        vihje.setText(kysyttyAine.vihje);
        vastauskenttä.setText("");
    }

    public void luoKysymysikkuna() {
        kysymys = new JFrame();
        luoKysymysKuva();
        kysymys.setLocation(100, 10);
        luoKysymystenKomponentit();

        kysymys.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        kysymys.pack();
        kysymys.setResizable(false);
        kysymys.setVisible(true);
    }

    public void luoKysymystenKomponentit() {
        Container pohja2 = kysymys.getContentPane();

        symboli = new JLabel("Symboli tulee tähän.");
        symboli.setOpaque(false);
        symboli.setForeground(Color.white);
        symboli.setSize(400, 50);
        symboli.setFont(new Font("Serif", Font.BOLD, 34));
        symboli.setLocation(330, 10);
        pohja2.add(symboli);

        vihje = new JLabel("Vihje tulee tähän. Vastausaikaa 15 sekuntia.");
        vihje.setOpaque(false);
        vihje.setForeground(Color.white);
        vihje.setSize(1070, 30);
        vihje.setFont(new Font("Serif", Font.BOLD, 14));
        vihje.setLocation(10, 75);
        pohja2.add(vihje);

        JLabel tekstiKentänSelitys = new JLabel("Ole hyvä ja kirjoita vastauksesi allaolevaan tekstikenttään.");
        tekstiKentänSelitys.setSize(800, 20);
        tekstiKentänSelitys.setForeground(Color.white);
        tekstiKentänSelitys.setOpaque(false);
        tekstiKentänSelitys.setLocation(10, 120);
        tekstiKentänSelitys.setFont(new Font("Serif", Font.BOLD, 14));
        pohja2.add(tekstiKentänSelitys);

        vastauskenttä = new JTextField();
        vastauskenttä.setLocation(10, 140);
        vastauskenttä.setSize(500, 20);
        vastauskenttä.setFont(new Font("Serif", Font.BOLD, 14));
        vastauskenttä.setText("Kirjoita 'pelaa' tähän, jos haluat pelata, muuten lopetetaan.");
        pohja2.add(vastauskenttä);

        JButton ok = new JButton("ok");
        ok.setLocation(10, 170);
        ok.setSize(50, 30);
        ok.setForeground(Color.white);
        ok.setBackground(Color.white);
        ok.setOpaque(false);
        ok.setFocusPainted(false);
        ok.setContentAreaFilled(true);
        ok.setBorderPainted(true);
        ok.setFont(new Font("Serif", Font.BOLD, 16));
        ok.setBorder(new LineBorder(Color.white));
        ok.addActionListener(new NappulanKuuntelija3(this));
        kysymys.getRootPane().setDefaultButton(ok);
        pohja2.add(ok);
    }

    public void luoKysymysKuva() {
        try {
            JLabel label = new JLabel(new ImageIcon(ImageIO.read(new File("/home/timo/symbolipeli/ohjelma/src/kysymys.jpeg"))));
            kysymys.setContentPane(label);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void voitit() {
        ajastin.stop();
        kysymys.dispose();
        musa.pelimusa.stop();
        musa.voitto();
        voitto = new JFrame();
        voitto.setLocation(200, 300);
        try {
            JLabel label = new JLabel(new ImageIcon(ImageIO.read(new File("/home/timo/symbolipeli/ohjelma/src/voitto.jpeg"))));
            voitto.setContentPane(label);
        } catch (Exception e) {
            System.out.println(e);
        }
        voittoKomponentit();

        voitto.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        voitto.pack();
        voitto.setResizable(false);
        voitto.setVisible(true);
    }

    public void voittoKomponentit() {
        Container pohja4 = voitto.getContentPane();

        JLabel pinnat = new JLabel("Pisteesi: " + kokonaispisteet);
        pinnat.setSize(300, 40);
        pinnat.setForeground(Color.gray);
        pinnat.setOpaque(false);
        pinnat.setLocation(140, 250);
        pinnat.setFont(new Font("Serif", Font.BOLD, 20));
        pohja4.add(pinnat);

        JLabel gameover = new JLabel("Voitit Pelin!");
        gameover.setSize(300, 35);
        gameover.setForeground(Color.gray);
        gameover.setOpaque(false);
        gameover.setLocation(110, 0);
        gameover.setFont(new Font("Serif", Font.BOLD, 30));
        pohja4.add(gameover);

        JButton lopeta = new JButton("Lopeta Peli");
        lopeta.setLocation(120, 300);
        lopeta.setSize(150, 30);
        lopeta.setForeground(Color.gray);
        lopeta.setBackground(Color.gray);
        lopeta.setOpaque(false);
        lopeta.setFocusPainted(false);
        lopeta.setContentAreaFilled(true);
        lopeta.setBorderPainted(true);
        lopeta.setFont(new Font("Serif", Font.BOLD, 16));
        lopeta.setBorder(new LineBorder(Color.gray));
        lopeta.addActionListener(new NappulanKuuntelija3(this));
        pohja4.add(lopeta);
    }

    public void hävisit() {
        ajastin.stop();
        kysymys.dispose();
        musa.pelimusa.stop();
        musa.häviö();
        häviö = new JFrame();
        häviö.setLocation(200, 300);
        try {
            JLabel label = new JLabel(new ImageIcon(ImageIO.read(new File("/home/timo/symbolipeli/ohjelma/src/häviö.jpeg"))));
            häviö.setContentPane(label);
        } catch (Exception e) {
            System.out.println(e);
        }
        hävisitKomponentit();

        häviö.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        häviö.pack();
        häviö.setResizable(false);
        häviö.setVisible(true);
    }

    public void hävisitKomponentit() {
        Container pohja3 = häviö.getContentPane();

        JLabel pinnat = new JLabel("Pisteesi: " + kokonaispisteet);
        pinnat.setSize(300, 40);
        pinnat.setForeground(Color.white);
        pinnat.setOpaque(false);
        pinnat.setLocation(180, 260);
        pinnat.setFont(new Font("Serif", Font.BOLD, 20));
        pohja3.add(pinnat);

        JLabel gameover = new JLabel("Game Over!");
        gameover.setSize(300, 40);
        gameover.setForeground(Color.white);
        gameover.setOpaque(false);
        gameover.setLocation(150, 140);
        gameover.setFont(new Font("Serif", Font.BOLD, 30));
        pohja3.add(gameover);

        JLabel oikea = new JLabel("Oikea vastaus olisi ollut: " + kysyttyAine.nimi);
        oikea.setSize(400, 30);
        oikea.setForeground(Color.white);
        oikea.setOpaque(false);
        oikea.setLocation(90, 170);
        oikea.setFont(new Font("Serif", Font.BOLD, 16));
        pohja3.add(oikea);

        JButton lopeta = new JButton("Lopeta Peli");
        lopeta.setLocation(175, 340);
        lopeta.setSize(150, 30);
        lopeta.setForeground(Color.white);
        lopeta.setBackground(Color.white);
        lopeta.setOpaque(false);
        lopeta.setFocusPainted(false);
        lopeta.setContentAreaFilled(true);
        lopeta.setBorderPainted(true);
        lopeta.setFont(new Font("Serif", Font.BOLD, 16));
        lopeta.setBorder(new LineBorder(Color.white));
        lopeta.addActionListener(new NappulanKuuntelija3(this));
        pohja3.add(lopeta);

    }

    public void kysyNimimerkki() {
        if (laskuri2 == 0) {
            nimimerkki = new JFrame();
            laskuri2 = laskuri2 + 1;
            try {
                JLabel label = new JLabel(new ImageIcon(ImageIO.read(new File("/home/timo/symbolipeli/ohjelma/src/kysymys.jpeg"))));
                nimimerkki.setContentPane(label);
            } catch (Exception e) {
                System.out.println(e);
            }
            nimimerkki.setLocation(300, 300);
            Container pohja4 = nimimerkki.getContentPane();

            JLabel tähänVastaus = new JLabel("Ole hyvä ja kirjoita nimesi allaolevaan tekstikenttään.");
            tähänVastaus.setSize(800, 20);
            tähänVastaus.setForeground(Color.white);
            tähänVastaus.setOpaque(false);
            tähänVastaus.setLocation(10, 80);
            tähänVastaus.setFont(new Font("Serif", Font.BOLD, 14));
            pohja4.add(tähänVastaus);

            nimikenttä = new JTextField();
            nimikenttä.setLocation(10, 100);
            nimikenttä.setSize(200, 20);
            nimikenttä.setFont(new Font("Serif", Font.BOLD, 14));
            pohja4.add(nimikenttä);

            JButton ok = new JButton("Tallenna Nimimerkki");
            ok.setLocation(10, 130);
            ok.setSize(230, 30);
            ok.setForeground(Color.white);
            ok.setBackground(Color.white);
            ok.setOpaque(false);
            ok.setFocusPainted(false);
            ok.setContentAreaFilled(true);
            ok.setBorderPainted(true);
            ok.setFont(new Font("Serif", Font.BOLD, 16));
            ok.setBorder(new LineBorder(Color.white));
            ok.addActionListener(new NappulanKuuntelija3(this));
            pohja4.add(ok);

            nimimerkki.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
            nimimerkki.pack();
            nimimerkki.setResizable(false);
            nimimerkki.setVisible(true);
        }
    }

    public boolean paaseeListalle(int pisteet) {
        if (pisteet > pistelista.get(pistelista.size() - 1).pisteet) {
            return true;
        } else {
            return false;
        }
    }

    public void lataaPistelista() {
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
                kirjoittaja.println(pistelista.get(i).nimi);
                kirjoittaja.println(pistelista.get(i).pisteet);
            }
            kirjoittaja.close();
        } catch (Exception e) {
            System.out.println("Virhe!");
        }
    }

    public void vähennäAikaa() {
        if (aika > 0) {
            aika = aika - 1;
            ajanNäyttö.setText("Aika: " + aika);
        }
        if (aika == 0) {
            if (väärät == 0) {
                annaVihje();
                return;
            }
            if (väärät == 1) {
                ajastin.stop();
                hävisit();
            }
        }
    }
}
