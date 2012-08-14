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
public class Pistelista implements Runnable {

    public JFrame ikkuna2;
    public Container pohja;
    public ArrayList<Tulos> pistelista;
    public static int laskuri = 0;

    public void run() {
        ikkuna2 = new JFrame();
        if (laskuri == 0) {
             laskuri = laskuri + 1;
            ikkuna2.setTitle("Symbolipeli");
            Point piste = new Point(130, 200);
            ikkuna2.setLocation(piste);
            UIManager.put("Button.select", Color.green);
            luoKuva();
            luoKomponentit();

            ikkuna2.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
            ikkuna2.setResizable(false);
            ikkuna2.pack();
            ikkuna2.setVisible(true);
        } else {
            ikkuna2.dispose();
        }
    }

    public void luoKomponentit() {
        pohja = ikkuna2.getContentPane();

        JButton nappula = new JButton("Sulje Pistelista");
        nappula.setLocation(400, 50);
        nappula.setSize(300, 75);
        nappula.setBackground(Color.white);
        nappula.setOpaque(false);
        nappula.setForeground(Color.green.darker());
        nappula.setFocusPainted(false);
        nappula.setContentAreaFilled(true);
        nappula.setBorderPainted(true);
        nappula.setFont(new Font("Serif", Font.BOLD, 26));
        nappula.setBorder(new LineBorder(Color.green.darker()));
        nappula.addActionListener(new NappulanKuuntelija2(this));
        pohja.add(nappula);

        lataaPistelista();
        lisääJLabelit();
    }

    public void luoKuva() {
        try {
            JLabel label = new JLabel(new ImageIcon(ImageIO.read(new File("/home/timo/symbolipeli/ohjelma/src/pistelista.jpg"))));
            ikkuna2.setContentPane(label);
        } catch (Exception e) {
            System.out.println(e);
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

    public void lisääJLabelit() {
        ArrayList<JLabel> tekstit = new ArrayList<JLabel>();
        for (Tulos nimi : pistelista) {
            tekstit.add(new JLabel(nimi.nimi));
        }
        int luku = 1;
        int x = 25;
        int y = 10;
        for (int i = 0; i < 10; i = i + 1) {
            tekstit.get(i).setText(luku + ". " + pistelista.get(i).nimi + "  " + pistelista.get(i).pisteet);
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
