package ohjelma;

import java.awt.Color;
import java.awt.Container;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 * Tämä tekee lopetusikkunasta voittoikkunan.
 * 
 * Tämän luokan avulla muokataan Peli_ikkunassa olevaa JDialogia. 
 *
 * @author Timo Pekkanen
 */
public class Ilmoitavoittoikkuna {

    private boolean pääseeköListalle;
    private Container pohja;
    private int pelaajanPisteet;
    private JButton suljepelinappula;
    private JDialog ilmoitavoittoikkuna;
    private JTextField nimikenttä;

    /**
     * Luokan konstruktori. 
     * 
     * @param lopetusikkuna JDialog, johon komponentteja lisätään.
     * @param pelaajanPisteet Parametri kertoo, kuinka paljon pisteitä
     * pelaajalla on pelin loppuessa.
     * @param pääseeköListalle Parametri kertoo, pääseekö pelaajan tulos
     * pistelistalle. Jos true, jdialogiin luodaan tekstikenttä.
     */
    public Ilmoitavoittoikkuna(JDialog lopetusikkuna, int pelaajanPisteet, boolean pääseeköListalle) {
        this.ilmoitavoittoikkuna = lopetusikkuna;
        this.pelaajanPisteet = pelaajanPisteet;
        this.pääseeköListalle = pääseeköListalle;
    }

    /**
     * Tämä metodi käynnistää voittoikkunan.
     */
    public void run() {
        ilmoitavoittoikkuna.setLocation(250, 30);
        MuokkaaKomponenttia.luoDialoginContentPaneKuvasta("voitto.png", ilmoitavoittoikkuna);
        rakennaIlmoitaVoittoIkkunanKomponentit();
        MuokkaaKomponenttia.suoritaNeDialoginavausToiminnotJotkaAinaSamat(ilmoitavoittoikkuna);
    }

    /**
     * Tämän metodin avulla luodaan komponentit jdialogiin. 
     */
    private void rakennaIlmoitaVoittoIkkunanKomponentit() {
        pohja = ilmoitavoittoikkuna.getContentPane();
        JLabel ilmoitaPisteet = new JLabel("Pisteesi: " + pelaajanPisteet);
        MuokkaaKomponenttia.muokkaaJLabelia(ilmoitaPisteet, 350, 240, 300, 40, false, Color.red, Color.white, 24, pohja);
        JLabel voitto = new JLabel("Voitit Pelin!");
        MuokkaaKomponenttia.muokkaaJLabelia(voitto, 300, 200, 400, 45, false, Color.red, Color.white, 40, pohja);
        suljepelinappula = new JButton("Lopeta Peli");
        MuokkaaKomponenttia.muokkaaJButtonia(suljepelinappula, 340, 590, 180, 50, Color.white, true, Color.blue, false,
                true, true, 16, Color.blue, pohja);
        ilmoitavoittoikkuna.getRootPane().setDefaultButton(suljepelinappula);
        if (pääseeköListalle == true) {
            JLabel nimi = new JLabel("Kirjoita nimesi tekstikenttään, jos haluat liittyä kemistien eliittiin.");
            MuokkaaKomponenttia.muokkaaJLabelia(nimi, 75, 290, 700, 25, false, Color.red, Color.white, 18, pohja);
            nimikenttä = new JTextField();
            MuokkaaKomponenttia.muokkaaJTextFieldiä(nimikenttä, 285, 340, 300, 40, 24, "", pohja);
        }
    }

    /**
     * Tämä metodi palauttaa nimikentään asetetun merkkijonon. Jos nimikenttää
     * ei ole luotu ja metodia jostain syystä kutsutaan, niin metodi palauttaa
     * tyhjän merkkijonon.
     *
     *  @return Merkkijono, joka palautetaan.
     */
    public String palautaNimikentänNimi() {
        if (nimikenttä != null) {
            return nimikenttä.getText();
        } else {
            return "";
        }
    }

    /**
     * JDialogin kuuntelu tehdään peli_ikkunan kuuntelijan avulla ja tämän
     * metodin avulla jdialogin ainoa nappula lisätään siihen kuuntelijaan.
     *
     * @param peli_ikkuna Olio, jonka kuuntelijaan suljePeliNappula lisätään.
     */
    public void lisääSuljePeliNappulaActionListeneriin(Peli_ikkuna peliIkkuna) {
        suljepelinappula.addActionListener(new Peli_ikkunanKuuntelija(peliIkkuna));
    }
}
