package ohjelma;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Point;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;

/**
 * Tällä luokalla luodaan pelin päävalikko.
 * 
 * @author Timo Pekkanen
 */

public class Päävalikko implements Runnable {
    
    /**
     * Päävalikon attribuutit.
     * päävalikkoikkuna: Päävalikkoikkuna.
     * pohja: Päävalikkoikkunan pohja.
     * musa: Tämän avulla huolehditaan äänentoistosta.
     */
    
    public void Attribuutit() {     
    }
    private JFrame päävalikkoikkuna;
    private Container pohja;
    private Musiikkikirjasto musa;
    
    /**
     * Tämä metodi luo päävalikkoikkunan.
     */

    public void run() {
        päävalikkoikkuna = new JFrame();
        päävalikkoikkuna.setTitle("Symbolipeli");
        Point piste = new Point(230, 180);
        päävalikkoikkuna.setLocation(piste);
        UIManager.put("Button.select", Color.green);
        luoKuva();

        musa = new Musiikkikirjasto();
        musa.palautaPäävalikkolaulu().loop();
        luoKomponentit();

        päävalikkoikkuna.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        päävalikkoikkuna.setResizable(false);
        päävalikkoikkuna.pack();
        päävalikkoikkuna.setVisible(true);
    }
    
    /**
     * Tämä metodi luo päävalikkoikkunankomponentit.
     */

    private void luoKomponentit() {
        pohja = päävalikkoikkuna.getContentPane();

        JButton nappula1 = new JButton("Aloita Peli");
        JButton nappula2 = new JButton("Pistelista");
        JButton nappula3 = new JButton("Lopeta");
        nappula1.setLocation(296, 25);
        nappula2.setLocation(296, 150);
        nappula3.setLocation(296, 450);
        luoNappulat(nappula1);
        luoNappulat(nappula2);
        luoNappulat(nappula3);
    }
    
    /**
     * Tämä metodi palauttaa Musiikkikirjaston.
     * @return Musiikkikirjasto.
     */

    public Musiikkikirjasto palautaMusiikkikirjasto() {
        return musa;
    }
    
    /**
     * Tämä metodi luo päävalikkoikkunan taustakuvan.
     */

    private void luoKuva() {
        try {
            JLabel label = new JLabel(new ImageIcon(ImageIO.read(new File("/home/timo/symbolipeli/ohjelma/src/taustakuva.jpg"))));
            päävalikkoikkuna.setContentPane(label);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    /**
     * Tämän metodin avulla määrätään päävalikkoikkunan ominaisuudet (paitsi sijainti).
     * @param nappula Nappula, jota halutaan muokata. 
     */

    private void luoNappulat(JButton nappula) {
        nappula.setSize(200, 50);
        nappula.setBackground(Color.white);
        nappula.setOpaque(false);
        nappula.setForeground(Color.green.darker());
        nappula.setFocusPainted(false);
        nappula.setContentAreaFilled(true);
        nappula.setBorderPainted(true);
        nappula.setFont(new Font("Serif", Font.BOLD, 20));
        nappula.setBorder(new LineBorder(Color.GREEN.darker()));
        nappula.addActionListener(new PäävalikonKuuntelija(this));
        pohja.add(nappula);
    }
}