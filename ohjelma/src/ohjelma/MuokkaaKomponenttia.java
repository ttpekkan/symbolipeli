package ohjelma;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;

/**
 * Tämän luokan avulla lisätään komponentteja JFrame- ja JDialog-ikkunoihin.
 *
 * Koska komponenttien tekemiseen liittyy paljon toistoa, päätin tehdä niiden
 * valmistamiseen kirjastoluokan. Parametrien avulla saadaan halutun kaltaisia
 * komponentteja ikkunoihin.
 *
 * @author Timo Pekkanen
 */
public class MuokkaaKomponenttia {
    private static int luku;

    /**
     * 
     * @param nappula Nappula, jota muokataan.
     * @param xSijainti Nappulan sijainti x-tasossa.
     * @param ySijainti Nappulan sijainti y-tasossa.
     * @param xKoko Nappulan leveys.
     * @param yKoko Nappulan korkeus.
     * @param taustaväri Nappulan taustan väri.
     * @param näkyyköTausta Halutaanko näyttää nappulan tausta.
     * @param tekstiväri Nappulan tekstin väri.
     * @param näytetäänköKohdistus Näytetäänö, kun nappula on kohdistettu.
     * @param maalataankoNappulaKunPainetaan Maalataanko nappula, kun sitä on painettu.
     * @param näytetäänköRajat Ovatko nappulan rajat näkyviä.
     * @param fonttikoko Nappulan tekstin koko.
     * @param rajanvärit Nappulan rajojen väri.
     * @param pohja Pohja, johon nappula lisätään. 
     */
    public static void muokkaaJButtonia(JButton nappula, int xSijainti, int ySijainti, int xKoko,
            int yKoko, Color taustaväri, boolean näkyyköTausta, Color tekstiväri,
            boolean näytetäänköKohdistus, boolean maalataankoNappulaKunPainetaan, boolean näytetäänköRajat,
            int fonttikoko, Color rajanvärit, Container pohja) {
        nappula.setLocation(xSijainti, ySijainti);
        nappula.setSize(xKoko, yKoko);
        nappula.setBackground(taustaväri);
        nappula.setOpaque(näkyyköTausta);
        nappula.setForeground(tekstiväri);
        nappula.setFocusPainted(näytetäänköKohdistus);
        nappula.setContentAreaFilled(maalataankoNappulaKunPainetaan);
        nappula.setBorderPainted(näytetäänköRajat);
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
     * @param näkyyköTausta Näytetäänkö JLabelin tausta.
     * @param taustaväri JLabelin taustaväri.
     * @param tekstiväri Kirjoituksen väri.
     * @param fonttikoko Kirjoituksen fonttikoko.
     * @param pohja Pohja, mihin teksti lisätään.
     */
    public static void muokkaaJLabelia(JLabel teksti, int xSijainti, int ySijainti, int xKoko,
            int yKoko, boolean näytetäänköTausta, Color taustaväri, Color tekstiväri,
            int fonttikoko, Container pohja) {
        teksti.setLocation(xSijainti, ySijainti);
        teksti.setSize(xKoko, yKoko);
        teksti.setOpaque(näytetäänköTausta);
        teksti.setBackground(taustaväri);
        teksti.setForeground(tekstiväri);
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
    public static void muokkaaJTextFieldiä(JTextField kenttä, int xSijainti, int ySijainti,
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
    public static void suoritaNeIkkunanavausToiminnotJotkaAinaSamat(JFrame ikkuna) {
        ikkuna.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        ikkuna.setResizable(false);
        ikkuna.pack();
        ikkuna.setVisible(true);
    }

    /**
     * Tämän metodin luodaan tehdään halutusta kuvasta ContentPane JFramelle.
     *
     * @param tiedosto Kuvatiedosto. jota halutaan käyttää.
     */
    public static void luoContentPaneKuvasta(String tiedosto, JFrame ikkuna) {
        try {
            JLabel kuva = new JLabel(new ImageIcon(ImageIO.read(new File("src/" + tiedosto))));
            ikkuna.setContentPane(kuva);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
     /**
     * Tämän metodin luodaan tehdään halutusta kuvasta ContentPane JDialogille.
     *
     * @param tiedosto Kuvatiedosto. jota halutaan käyttää.
     */
    public static void luoDialoginContentPaneKuvasta(String tiedosto, JDialog ikkuna) {
        try {
            JLabel kuva = new JLabel(new ImageIcon(ImageIO.read(new File("src/" + tiedosto))));
            ikkuna.setContentPane(kuva);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    /**
     * Lista niistä toiminnoista, jotka ovat aina samat, kun tehdään JDialog.
     */
    public static void suoritaNeDialoginavausToiminnotJotkaAinaSamat(JDialog ikkuna) {
        ikkuna.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        ikkuna.setResizable(false);
        ikkuna.pack();
        ikkuna.setVisible(true);
        ikkuna.setModal(true);
    }
    
    /**
     * Laskurin avulla pidetään kirjaa siitä, onko pistelistaikkuna aktiivinen.
     * 
     * @param syöte Määritellään luvun arvo.
     */
    public static void asetaLaskurinArvo(int syöte) {
        luku = syöte;
    }
    
    /**
     * Metodi kertoo, mikä luvun arvo on.
     * 
     * @return Palautettu luku.
     */
    public static int palautaLaskurinArvo() {
        int palautus = luku;
        return palautus;
    }
}
