package ohjelma;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Point;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class Päävalikko implements Runnable {

    private JFrame ikkuna;
    private Container pohja;
    private Musiikkikirjasto musa;

    public void run() {
        ikkuna = new JFrame();
        ikkuna.setTitle("Symbolipeli");
        Point piste = new Point(230, 180);
        ikkuna.setLocation(piste);
        UIManager.put("Button.select", Color.green);
        luoKuva();

        musa = new Musiikkikirjasto();
        musa.palautaPäävalikkolaulu().loop();
        luoKomponentit();

        ikkuna.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        ikkuna.setResizable(false);
        ikkuna.pack();
        ikkuna.setVisible(true);
    }

    private void luoKomponentit() {
        pohja = ikkuna.getContentPane();

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

    public Musiikkikirjasto palautaMusiikkikirjasto() {
        return musa;
    }

    private void luoKuva() {
        try {
            JLabel label = new JLabel(new ImageIcon(ImageIO.read(new File("/home/timo/symbolipeli/ohjelma/src/taustakuva.jpg"))));
            ikkuna.setContentPane(label);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

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