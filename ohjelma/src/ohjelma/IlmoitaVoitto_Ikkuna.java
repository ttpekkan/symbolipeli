package ohjelma;

import java.awt.Color;
import java.awt.Container;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 * Tämä tekee lopetusikkunasta voittoikkuna.
 *
 * @author Timo Pekkanen
 */
public class IlmoitaVoitto_Ikkuna {

    private JDialog ilmoitaVoittoIkkuna;
    private int pelaajanPisteet;
    private Container pohja;
    private boolean pääseeköListalle;
    private JTextField nimikenttä;
    private JButton suljePeliNappula;

    /**
     * IlmoitaVoitto_Ikkunan konstruktori.
     *
     * @param lopetusikkuna Ikkuna, johon kaikki tavara lisätään.
     * @param pelaajanPisteet Pelaajan loppupisteet.
     * @param pääseeköListalle Jos tulos tarpeeksi hyvä, niin true, muuten
     * false.
     */
    public IlmoitaVoitto_Ikkuna(JDialog lopetusikkuna, int pelaajanPisteet, boolean pääseeköListalle) {
        this.ilmoitaVoittoIkkuna = lopetusikkuna;
        this.pelaajanPisteet = pelaajanPisteet;
        this.pääseeköListalle = pääseeköListalle;
    }

    /**
     * Tällä metodilla luodaan häviöikkuna.
     */
    public void run() {
        ilmoitaVoittoIkkuna.setLocation(250, 30);
        MuokkaaKomponenttia.luoDialoginContentPaneKuvasta("voitto.png", ilmoitaVoittoIkkuna);
        voittoKomponentit();
        MuokkaaKomponenttia.suoritaNeDialoginavausToiminnotJotkaAinaSamat(ilmoitaVoittoIkkuna);
    }

    /**
     * Metodi luo tarvittavat komponentit voittoikkunaan.
     */
    private void voittoKomponentit() {
        pohja = ilmoitaVoittoIkkuna.getContentPane();
        JLabel ilmoitaPisteet = new JLabel("Pisteesi: " + pelaajanPisteet);
        MuokkaaKomponenttia.muokkaaJLabelia(ilmoitaPisteet, 350, 240, 300, 40, false, Color.red, Color.white, 24, pohja);
        JLabel voitto = new JLabel("Voitit Pelin!");
        MuokkaaKomponenttia.muokkaaJLabelia(voitto, 300, 200, 400, 45, false, Color.red, Color.white, 40, pohja);
        suljePeliNappula = new JButton("Lopeta Peli");
        MuokkaaKomponenttia.muokkaaJButtonia(suljePeliNappula, 340, 590, 180, 50, Color.white, true, Color.blue, false,
                true, true, 16, Color.blue, pohja);
        ilmoitaVoittoIkkuna.getRootPane().setDefaultButton(suljePeliNappula);
        if (pääseeköListalle == true) {
            JLabel nimi = new JLabel("Kirjoita nimesi tekstikenttään, jos haluat liittyä kemistien eliittiin.");
            MuokkaaKomponenttia.muokkaaJLabelia(nimi, 75, 290, 700, 25, false, Color.red, Color.white, 18, pohja);
            nimikenttä = new JTextField();
            MuokkaaKomponenttia.muokkaaJTextFieldiä(nimikenttä, 285, 340, 300, 40, 24, "", pohja);
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
    public void lisääActionListeneriin(Peli_ikkuna peliIkkuna) {
        suljePeliNappula.addActionListener(new Peli_ikkunanKuuntelija(peliIkkuna));
    }
}
