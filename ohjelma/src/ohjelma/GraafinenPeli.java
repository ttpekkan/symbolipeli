/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ohjelma;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Point;
import java.io.File;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;

/**
 *
 * @author timo
 */
public class GraafinenPeli implements Runnable {

    public JFrame ikkuna3;
    public Container pohja;
    public ArrayList<Tulos> pistelista;
    public Musiikkikirjasto musa;
    public static int laskuri = 0;

    public void run() {
        ikkuna3 = new JFrame();
        if (laskuri == 0) {
            laskuri = laskuri + 1;
            ikkuna3.setTitle("Symbolipeli");
            Point piste = new Point(130, 200);
            ikkuna3.setLocation(piste);
            UIManager.put("Button.select", Color.green);
            musa = new Musiikkikirjasto();
            musa.uusiPeli();
            luoKuva();
            luoKomponentit();

            ikkuna3.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
            ikkuna3.setResizable(true);
            ikkuna3.pack();
            ikkuna3.setVisible(true);
        } else {
            ikkuna3.dispose();
        }

    }

    public void luoKomponentit() {
        pohja = ikkuna3.getContentPane();

        JButton nappula = new JButton("Sulje Peli");
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
        nappula.addActionListener(new NappulanKuuntelija3(this));
        pohja.add(nappula);
    }

    public void luoKuva() {
        try {
            JLabel label = new JLabel(new ImageIcon(ImageIO.read(new File(musa.lista2.get(0).kuvannimi))));
            ikkuna3.setContentPane(label);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    /*
     * public void luoKuva(String tiedosto) { try { JLabel label = new
     * JLabel(new ImageIcon(ImageIO.read(new
     * File("/home/timo/symbolipeli/ohjelma/src/" + tiedosto))));
     * ikkuna3.setContentPane(label); } catch (Exception e) {
     * System.out.println(e); } }
     *
     * public void luoTaustakuva() { if
     * (musa.lista2.get(0).kuvannimi.equals("stageselect")) {
     * luoKuva("stageselect.jpg"); } if
     * (musa.lista2.get(0).kuvannimi.equals("mylittlepony")) {
     * luoKuva("mylittlepony.png"); } if
     * (musa.lista2.get(0).kuvannimi.equals("lovegravy")) {
     * luoKuva("lovegravy.JPG"); } if
     * (musa.lista2.get(0).kuvannimi.equals("teaparty")) {
     * luoKuva("teaparty.jpeg"); } if
     * (musa.lista2.get(0).kuvannimi.equals("always")) { luoKuva("always.jpg");
     * } if (musa.lista2.get(0).kuvannimi.equals("future")) {
     * luoKuva("future.png"); } if
     * (musa.lista2.get(0).kuvannimi.equals("avaus")) { luoKuva("avaus.jpg"); }
     * if (musa.lista2.get(0).kuvannimi.equals("otsikko")) {
     * luoKuva("otsikko.jpg"); } }
     */
}
