package ohjelma;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import javax.swing.JLabel;

/**
 * Tämän luokan avulla voidaan ladata pistelista ja muokata sitä.
 *
 * @author Timo Pekkanen
 */
public class Pistelista implements PistelistaRajapinta {

    private ArrayList<Pelitulos> pistelista;

    /**
     * Luokan konstruktori, joka luo pistelistan ArrayListinä.
     */
    public Pistelista() {
        this.pistelista = new ArrayList<Pelitulos>();
        lataaPistelista();
    }

    /**
     * Tämä metodi lataa pistelista-ArrayListiin tekstitiedostossa olevat
     * tiedot.
     */
    private void lataaPistelista() {
        try {
            Scanner lukija = new Scanner(new File("src/top10.txt"));
            while (lukija.hasNextLine()) {
                pistelista.add(new Pelitulos(lukija.nextLine(), Integer.parseInt(lukija.nextLine())));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        Collections.sort(pistelista);
    }

    /**
     * Metodi laskee, pääseekö tulos pistelistalle.
     *
     * @param pisteet Pelaajan pisteet.
     * @return Metodi palauttaa true tai false, pisteistä riippuen.
     */
    @Override
    public boolean pääseeköPelitulosListalle(int pisteet) {
        if (pisteet > pistelista.get(pistelista.size() - 1).palautaPisteet()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     *  Tämä metodi muodostaa tuloksista JLabeleita annetulle pohjalle.
     */
    @Override
    public void rakennaPistelistanJLabelesitys(Container pohja) {
        ArrayList<JLabel> tekstit = new ArrayList<JLabel>();
        for (Pelitulos nimi : pistelista) {
            tekstit.add(new JLabel(nimi.palautaNimi()));
        }
        int luku = 1;
        int x = 25;
        int y = 10;
        for (int i = 0; i < pistelista.size(); i = i + 1) {
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

    /**
     * Metodi lisää tuloksen pistelistaan.
     *
     * Metodi myös järjestää pistelistan lisäyksen jälkeen.
     *
     * @param nimi Pistelistaan lisätty nimimerkki.
     * @param pisteet Pistelistaan lisätyt pisteet.
     */
    @Override
    public void lisääPelitulosPistelistaan(String nimi, int pisteet) {
        if (pääseeköPelitulosListalle(pisteet) == true) {
            pistelista.remove(pistelista.size() - 1);
            pistelista.add(new Pelitulos(nimi, pisteet));
            Collections.sort(pistelista);
        }
    }

    /**
     * Tämä metodi tallentaa pistelista-ArrayListin tiedot tekstitiedostoon.
     */
    @Override
    public void tallennaPistelista(String osoite) {
        try {
            PrintWriter kirjoittaja = new PrintWriter(new File(osoite));
            for (int i = 0; i < pistelista.size(); i = i + 1) {
                kirjoittaja.println(pistelista.get(i).palautaNimi());
                kirjoittaja.println(pistelista.get(i).palautaPisteet());
            }
            kirjoittaja.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
