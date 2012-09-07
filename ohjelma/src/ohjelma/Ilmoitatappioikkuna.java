package ohjelma;

import java.awt.Color;
import java.awt.Container;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 * Tämä luokka luo JDialogista häviöikkunan.
 *
 * Tämän luokan avulla muokataan Peli_ikkunassa olevaa JDialogia.
 *
 * @author Timo Pekkanen
 */
public class Ilmoitatappioikkuna {

    private boolean pääseeköListalle;
    private Container pohja;
    private int pelaajanPisteet;
    private JButton suljepelinappula;
    private JDialog ilmoitatappioikkuna;
    private JTextField nimikenttä;
    private String oikeaVastaus;

    /**
     * Luokan konstruktori.
     *
     * @param lopetusikkuna JDialog, johon komponentteja lisätään.
     * @param pelaajanPisteet Parametri kertoo, kuinka paljon pisteitä
     * pelaajalla on pelin loppuessa.
     * @param pääseeköListalle Parametri kertoo, pääseekö pelaajan tulos
     * pistelistalle. Jos true, jdialogiin luodaan tekstikenttä.
     * @param oikeaVastaus Parametri kertoo, mikä olisi ollut oikea vastaus
     * siihen kysymykseen, jota pelaaja ei saanut oikein.
     */
    public Ilmoitatappioikkuna(JDialog lopetusikkuna, int pelaajanPisteet, boolean pääseeköListalle, String oikeaVastaus) {
        this.ilmoitatappioikkuna = lopetusikkuna;
        this.pelaajanPisteet = pelaajanPisteet;
        this.pääseeköListalle = pääseeköListalle;
        this.oikeaVastaus = oikeaVastaus;

    }

    /**
     * Tämä metodi käynnistää häviöikkunan.
     */
    public void run() {
        ilmoitatappioikkuna.setLocation(250, 30);
        MuokkaaKomponenttia.luoDialoginContentPaneKuvasta("lose.png", ilmoitatappioikkuna);
        rakennaIlmoitaTappioIkkunanKomponentit();
        MuokkaaKomponenttia.suoritaNeDialoginavausToiminnotJotkaAinaSamat(ilmoitatappioikkuna);
    }

    /**
     * Tämän metodin avulla luodaan komponentit jdialogiin.
     */
    private void rakennaIlmoitaTappioIkkunanKomponentit() {
        pohja = ilmoitatappioikkuna.getContentPane();
        JLabel ilmoitaPisteet = new JLabel("Pisteesi: " + pelaajanPisteet);
        MuokkaaKomponenttia.muokkaaJLabelia(ilmoitaPisteet, 350, 260, 300, 40, false, Color.red, Color.white, 24, pohja);
        JLabel gameover = new JLabel("Game Over!");
        MuokkaaKomponenttia.muokkaaJLabelia(gameover, 320, 200, 400, 45, false, Color.red, Color.white, 40, pohja);
        JLabel oikeaVastausOlisiOllut = new JLabel("Oikea vastaus olisi ollut: " + oikeaVastaus);
        MuokkaaKomponenttia.muokkaaJLabelia(oikeaVastausOlisiOllut, 320, 340, 520, 30, false, Color.red, Color.white, 18, pohja);
        suljepelinappula = new JButton("Lopeta Peli");
        MuokkaaKomponenttia.muokkaaJButtonia(suljepelinappula, 345, 610, 180, 50, Color.white, true, Color.blue, false,
                true, true, 16, Color.blue, pohja);
        ilmoitatappioikkuna.getRootPane().setDefaultButton(suljepelinappula);
        if (pääseeköListalle == true) {
            JLabel nimi = new JLabel("Kirjoita nimesi tekstikenttään, jos haluat liittyä kemistien eliittiin.");
            MuokkaaKomponenttia.muokkaaJLabelia(nimi, 75, 420, 700, 25, false, Color.red, Color.white, 18, pohja);
            nimikenttä = new JTextField();
            MuokkaaKomponenttia.muokkaaJTextFieldiä(nimikenttä, 285, 460, 300, 40, 24, "", pohja);
        }
    }

    /**
     * Tämä metodi palauttaa nimikentään asetetun merkkijonon. Jos nimikenttää
     * ei ole luotu ja metodia jostain syystä kutsutaan, niin metodi palauttaa
     * tyhjän merkkijonon.
     *
     * @return Merkkijono, joka palautetaan.
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
    public void lisääSuljePeliNappulaActionListeneriin(Peli_ikkuna peli_ikkuna) {
        suljepelinappula.addActionListener(new Peli_ikkunanKuuntelija(peli_ikkuna));
    }
}
