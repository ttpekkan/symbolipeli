package ohjelma;

import java.awt.Color;
import java.awt.Container;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 * Tämän luokan avulla lopetusikkunasta tehdään häviöikkuna.
 *
 * @author Timo Pekkanen
 */
public class IlmoitaHäviö_Ikkuna {

    private boolean pääseeListalle;
    private Container pohja;
    private int pelaajanPisteet;
    private JButton suljePeliNappula;
    private JFrame ilmoitaHäviöIkkuna;
    private JTextField nimikenttä;
    private KomponenttienLataus komponentit;
    private String oikeaVastaus;

    /**
     * IlmoitaHäviö_Ikkunan konstruktori.
     *
     * @param lopetusikkuna Ikkuna, johon kaikki tavara lisätään.
     * @param pelaajanPisteet Pelaajan loppupisteet.
     * @param oikeaVastaus Vastaus, jonka pelaajan olisi pitänyt antaa.
     * @param pääseeköListalle Jos tulos tarpeeksi hyvä, niin true, muuten
     * false.
     */
    public IlmoitaHäviö_Ikkuna(JFrame lopetusikkuna, int pelaajanPisteet, boolean pääseeköListalle, String oikeaVastaus) {
        this.ilmoitaHäviöIkkuna = lopetusikkuna;
        this.pelaajanPisteet = pelaajanPisteet;
        this.pääseeListalle = pääseeköListalle;
        this.oikeaVastaus = oikeaVastaus;

    }

    /**
     * Tällä metodilla luodaan häviöikkuna.
     */
    public void run() {
        komponentit = new KomponenttienLataus(ilmoitaHäviöIkkuna);
        ilmoitaHäviöIkkuna.setLocation(250, 30);
        komponentit.luoContentPaneKuvasta("häviö.png");
        ilmoitaHäviöIkkunanKomponentit();
        komponentit.neIkkunanavausToiminnotJotkaAinaSamat();
    }

    /**
     * Metodi luo tarvittavat komponentit häviöikkunaan.
     */
    private void ilmoitaHäviöIkkunanKomponentit() {
        pohja = ilmoitaHäviöIkkuna.getContentPane();
        JLabel ilmoitaPisteet = new JLabel("Pisteesi: " + pelaajanPisteet);
        komponentit.luoTeksti(ilmoitaPisteet, 350, 260, 300, 40, false, Color.red, Color.white, 24, pohja);
        JLabel gameover = new JLabel("Game Over!");
        komponentit.luoTeksti(gameover, 320, 200, 400, 45, false, Color.red, Color.white, 40, pohja);
        JLabel oikeaVastausOlisiOllut = new JLabel("Oikea vastaus olisi ollut: " + oikeaVastaus);
        komponentit.luoTeksti(oikeaVastausOlisiOllut, 320, 340, 520, 30, false, Color.red, Color.white, 18, pohja);
        suljePeliNappula = new JButton("Lopeta Peli");
        komponentit.luoNappula(suljePeliNappula, 345, 610, 180, 50, Color.white, true, Color.blue, false,
                true, true, 16, Color.blue, pohja);
        ilmoitaHäviöIkkuna.getRootPane().setDefaultButton(suljePeliNappula);
        if (pääseeListalle == true) {
            JLabel nimi = new JLabel("Kirjoita nimesi tekstikenttään, jos haluat liittyä kemistien eliittiin.");
            komponentit.luoTeksti(nimi, 75, 420, 700, 25, false, Color.red, Color.white, 18, pohja);
            nimikenttä = new JTextField();
            komponentit.luoTekstikenttä(nimikenttä, 285, 460, 300, 40, 24, "", pohja);
        }
    }

    /**
     * Metodi palauttaa pelaajan antaman nimen, jos olosuhteet oikeat.
     *
     * @return Pelaajan antama nimimerkki.
     */
    public String palautaNimikentänNimi() {
        if (nimikenttä != null) {
            return nimikenttä.getText();
        } else {
            return "";
        }
    }

    /**
     * Tämän metodin avulla suljePeliNappula lisätään ActionListeneriin.
     *
     * @param peli_ikkuna Nappula lisätään tämän ikkunan ActionListeneriin.
     */
    public void lisääActionListeneriin(Peli_ikkuna peli_ikkuna) {
        suljePeliNappula.addActionListener(new Peli_ikkunanKuuntelija(peli_ikkuna));
    }
}
