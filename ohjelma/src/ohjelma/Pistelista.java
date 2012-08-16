package ohjelma;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Point;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;

/**
 * Tämä luokan avulla avataan pistelistaikkuna.
 *
 * @author Timo Pekkanen
 */

public class Pistelista implements Runnable {
    
    /**
     * Pistelistan attribuutit.
     * pistelistaikkuna: Pistelistaikkuna.
     * pohja: Pistelistaikkunan pohja.
     * pistelista: ArrayList, joka sisältää tulokset.
     * laskuri: Laskuri, jonka avulla pidetään huolta, että vain yksi pistelistaikkuna on avattu kerralla.
     */
    
    public void Attribuutit() {     
    }
    private JFrame pistelistaikkuna;
    private Container pohja;
    private ArrayList<Tulos> pistelista;
    private static int laskuri = 0;
    
    /**
     * Käynnistää pistelistaikkunan ja määrää sen ominaisuudet.
     */

    public void run() {
        if (laskuri == 0) {
            pistelistaikkuna = new JFrame();
            laskuri = laskuri + 1;
            pistelistaikkuna.setTitle("Symbolipeli");
            Point piste = new Point(130, 200);
            pistelistaikkuna.setLocation(piste);
            UIManager.put("Button.select", Color.green);
            luoKuva();
            luoKomponentit();

            pistelistaikkuna.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
            pistelistaikkuna.setResizable(false);
            pistelistaikkuna.pack();
            pistelistaikkuna.setVisible(true);
        }
    }
    
    /**
     * Tämä metodi vähentää laskuri-attribuuttia yhdellä. 
     */

    public void vähennäIkkunoidenMäärää() {
        laskuri = laskuri - 1;
    }
    
    /**
     * Tämä metodi palauttaa pistelistaikkunan.
     * @return Pistelistaikkuna.
     */

    public JFrame palautaPisteListaIkkuna() {
        return pistelistaikkuna;
    }
    
    /**
     * Tämä metodi luo halutut komponentit pistelistaikkunaan.
     */

    private void luoKomponentit() {
        pohja = pistelistaikkuna.getContentPane();

        JButton nappula = new JButton("Sulje Pistelista");
        nappula.setLocation(400, 50);
        nappula.setSize(300, 75);
        nappula.setBackground(Color.green.darker());
        nappula.setOpaque(true);
        nappula.setForeground(Color.BLUE.darker());
        nappula.setFocusPainted(false);
        nappula.setContentAreaFilled(true);
        nappula.setBorderPainted(true);
        nappula.setFont(new Font("Serif", Font.BOLD, 26));
        nappula.setBorder(new LineBorder(Color.green));
        nappula.addActionListener(new PistelistanKuuntelija(this));
        pohja.add(nappula);

        lisääJLabelit();
    }
    
    /**
     * Tämä metodi luo taustakuvan pistelistaikkunalle.
     */

    private void luoKuva() {
        try {
         // JLabel label = new JLabel(new ImageIcon(ImageIO.read(new File("/home/timo/symbolipeli/ohjelma/src/pistelista.jpg"))));
            JLabel label = new JLabel(new ImageIcon(ImageIO.read(new File("src/pistelista.png"))));
            pistelistaikkuna.setContentPane(label);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    /**
     * Tämä metodi lataa pistelistan tulokset ArrayListiin.
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
     * Tämä metodi muodostaa tuloksista JLabel-esityksen. 
     */

    private void lisääJLabelit() {
        lataaPistelista();
        ArrayList<JLabel> tekstit = new ArrayList<JLabel>();
        for (Tulos nimi : pistelista) {
            tekstit.add(new JLabel(nimi.palautaNimi()));
        }
        int luku = 1;
        int x = 25;
        int y = 10;
        for (int i = 0; i < 10; i = i + 1) {
            tekstit.get(i).setText(luku + ". " + pistelista.get(i).palautaNimi() + "  " + pistelista.get(i).palautaPisteet());
            tekstit.get(i).setLocation(x, y);
            tekstit.get(i).setSize(500, 40);
            tekstit.get(i).setOpaque(false);
            tekstit.get(i).setForeground(Color.green.darker());
            tekstit.get(i).setFont(new Font("Serif", Font.BOLD, 20));
            pohja.add(tekstit.get(i));
            y = y + 45;
            luku = luku + 1;
        }
    }
}
