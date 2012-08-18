package ohjelma;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;

/**
 * Tämän luokan avulla lisätään komponentteja JFrame-ikkunoihin.
 *
 * Koska komponenttien tekemiseen liittyy paljon toistoa, päätin tehdä niiden
 * valmistamiseen apuluokan. Parametrien avulla saadaan halutun kaltaisia
 * komponentteja ikkunoihin.
 *
 * @author Timo Pekkanen
 */
public class KomponenttienLataus {

    private JFrame ikkunaJohonKomponenttejaLisätään;

    /**
     * Luokan konstruktori.
     *
     * @param ikkuna JFrame, johon komponentteja halutaan lisätä.
     */
    public KomponenttienLataus(JFrame parametrinaAnnettuIkkuna) {
        this.ikkunaJohonKomponenttejaLisätään = parametrinaAnnettuIkkuna;
    }

    /**
     * Tämän metodin avulla lisätään halutut ominaisuudet nappulaan.
     *
     * @param nappula Nappula, jota muokataan.
     * @param xSijainti Nappulan sijainti x-tasossa.
     * @param ySijainti Nappulan sijainti y-tasossa.
     * @param xKoko Nappulan leveys.
     * @param yKoko Nappulan korkeus.
     * @param väriTausta Nappulan taustan väri.
     * @param tausta Näytetäänkö nappulan tausta.
     * @param väriTeksti Nappulassa olevan tekstin väri.
     * @param kohdistusMaalaus Huomataanko, kun nappula "fokusataan".
     * @param täytäAlue Maalataanko nappula, kun sitä painetaan.
     * @param näytäRajat Näkyykö nappulan rajat.
     * @param fonttikoko Nappulassa olevan tekstin fontin koko.
     * @param rajanvärit Minkä värinen on nappulan raja.
     * @param pohja Mihin pohjaan nappula lisätään.
     */
    public void luoNappula(JButton nappula, int xSijainti, int ySijainti, int xKoko,
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
     * Tämän metodin avulla lisätään halutut ominaisuudet tekstiin.
     * 
     * @param teksti JLabel, jota muokataan.
     * @param xSijainti Tekstin sijainti x-tasossa.
     * @param ySijainti Tekstin sijainti y-tasossa.
     * @param xKoko JLabelin leveys.
     * @param yKoko JLabelin korkeus.
     * @param tausta Näytetäänkö JLabelin tausta.
     * @param väriTausta JLabelin taustaväri.
     * @param väriKirjoitus Kirjoituksen väri.
     * @param fonttikoko Kirjoituksen fonttikoko.
     * @param pohja Pohja mihin teksti lisätään. 
     */
    public void luoTeksti(JLabel teksti, int xSijainti, int ySijainti, int xKoko,
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
     * Tämän metodin avulla luodaan halutun kaltainen tekstikenttä.
     * 
     * 
     * @param kenttä Tekstikenttä, jota muokataan.
     * @param xSijainti Tekstikentän sijainti x-tasossa.
     * @param ySijainti Tekstikentän sijainti y-tasossa.
     * @param xKoko Teksikentän leveys.
     * @param yKoko Tekstikentä korkeus.
     * @param fonttikoko Teksikentän fontin koko.
     * @param kentänTeksti Mitä tekstikenttään on kirjoitettu.
     * @param pohja Mihin pohjaan tekstikenttä lisätään.
     */
    public void luoTekstikenttä(JTextField kenttä, int xSijainti, int ySijainti,
            int xKoko, int yKoko, int fonttikoko, String kentänTeksti,
            Container pohja) {
        kenttä.setLocation(xSijainti, ySijainti);
        kenttä.setSize(xKoko, yKoko);
        kenttä.setFont(new Font("Serif", Font.BOLD, fonttikoko));
        kenttä.setText(kentänTeksti);
        pohja.add(kenttä);
    }
    
    /**
     * Lista niistä toiminnoista, jotka ovat aina samat, kun tehdään JFrame.
     */
    public void neIkkunanavausToiminnotJotkaAinaSamat() {
        ikkunaJohonKomponenttejaLisätään.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        ikkunaJohonKomponenttejaLisätään.setResizable(false);
        ikkunaJohonKomponenttejaLisätään.pack();
        ikkunaJohonKomponenttejaLisätään.setVisible(true);
    }
    
    /**
     *Tämän metodin luodaan tehdään halutusta kuvasta ContentPane. 
     *
     * @param tiedosto Kuvatiedosto. jota halutaan käyttää.
     */
    public void luoContentPaneKuvasta(String tiedosto) {
        try {
            JLabel kuva = new JLabel(new ImageIcon(ImageIO.read(new File("src/" + tiedosto))));
            ikkunaJohonKomponenttejaLisätään.setContentPane(kuva);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
