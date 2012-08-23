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
 * Tämä luokka kontrolloi pistelistaan liittyviä tapahtumia. 
 * 
 * @author Timo Pekkanen
 */
public class Pistelista {

    private ArrayList<Pelitulos> pistelista;
    
    /**
     * Luokan konstruktori, joka luo pistelistan ArrayListinä. 
     */
    public Pistelista() {
        this.pistelista = new ArrayList<Pelitulos>();
        lataaPistelista();
    }

    /**
     * Metodi laskee, pääseekö tulos pistelistalle.
     *
     * @param pisteet Pelaajan pisteet.
     * @return Metodi palauttaa true tai false, pisteistä ja pistelistasta
     * riippuen.
     */
    public boolean pääseeListalle(int pisteet) {
        if (pisteet > pistelista.get(pistelista.size() - 1).palautaPisteet()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Tämä metodi muodostaa tuloksista JLabeleita.
     */
    public void JLabelesitys(Container pohja) {
        ArrayList<JLabel> tekstit = new ArrayList<JLabel>();
        for (Pelitulos nimi : pistelista) {
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
     * Metodi lisää tuloksen pistelistaan.
     *
     * @param nimi Pistelistaan lisätty nimimerkki.
     * @param pisteet Pistelistaan lisätyt pisteet.
     */
    public void lisääTulos(String nimi, int pisteet) {
        if (pääseeListalle(pisteet) == true) {
            pistelista.remove(pistelista.size() - 1);
            pistelista.add(new Pelitulos(nimi, pisteet));
            Collections.sort(pistelista);
        }
    }

    /**
     * Tämä metodi tallentaa pistelista-ArrayListin tiedot tekstitiedostoon.
     */
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
    
    /**
     * Tätä listaa käytetään testauksessa.
     */   
    public void lataaTestilista() {
        this.pistelista = new ArrayList<Pelitulos>();
        try {
            Scanner lukija = new Scanner(new File("src/testi.txt"));
            while (lukija.hasNextLine()) {
                pistelista.add(new Pelitulos(lukija.nextLine(), Integer.parseInt(lukija.nextLine())));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        Collections.sort(pistelista);
    }
}
