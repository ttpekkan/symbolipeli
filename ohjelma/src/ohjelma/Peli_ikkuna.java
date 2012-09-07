package ohjelma;

import java.awt.Color;
import java.awt.Container;
import javax.swing.*;

/**
 * Tämä luokka luo graafisen peli-ikkunan.
 *
 * Peli-ikkunaa muokataan sitä mukaan, kun peli etenee. Peli-ikkuna luodaan
 * valikko-ikkunan päälle. Pelin loppuessa, peli-ikkuna kumitetaan ja valikko-
 * ikkuna luodaan uudestaan. Tämä tehdään perinnän avulla.
 *
 *      @author Timo Pekkanen
 */
public class Peli_ikkuna extends Valikkoikkuna {

    private JButton ok_nappula;
    private JButton sulje_nappula;
    private Pelirajapinta peli;
    private Ilmoitatappioikkuna häviöikkuna;
    private Ilmoitavoittoikkuna voittoikkuna;
    private int aika;
    private JDialog lopetusikkuna;
    private JFrame peli_ikkuna;
    private JLabel ajannäyttö;
    private JLabel moneskoKysymysMenossa;
    private JLabel pelinAntamaAloitusohje;
    private JLabel pelaajanPisteet;
    private JLabel alkuaineenSymboli;
    private JLabel tekstikentänselitys;
    private JLabel pelinVaikeusaste;
    private JLabel vihje;
    private JTextField vastauskenttä;
    private Timer ajastin;

    /**
     * Tämä metodi käynnistää peli-ikkunan.
     *
     * @param ikkuna JFrame, johon peli-ikkuna luodaan.
     */
    @Override
    public void run(JFrame ikkuna) {
        peli_ikkuna = ikkuna;
        peli = new Symbolipeli();
        aika = 15;
        peli_ikkuna.setTitle("Symbolipeli");
        peli_ikkuna.setLocation(130, 180);
        UIManager.put("Button.select", Color.green);
        Musiikkikirjasto.soitaAloituslaulu();
        MuokkaaKomponenttia.luoContentPaneKuvasta(Musiikkikirjasto.palautaKuvannimi(), peli_ikkuna);
        rakennaPeli_ikkunanKomponentit();
        MuokkaaKomponenttia.suoritaNeIkkunanavausToiminnotJotkaAinaSamat(peli_ikkuna);
        peli_ikkuna.addWindowListener(new Peli_ikkunanKuuntelija(this));

    }

    /**
     * Metodi luo halutut komponentit peli_ikkunaan.
     */
    private void rakennaPeli_ikkunanKomponentit() {
        Container pohja = peli_ikkuna.getContentPane();
        pelaajanPisteet = new JLabel("Pisteet: " + 0);
        MuokkaaKomponenttia.muokkaaJLabelia(pelaajanPisteet, 805, 360, 140, 30, false, Color.GRAY, Color.white, 20, pohja);
        pelinVaikeusaste = new JLabel("Vaikeusaste: helppo");
        MuokkaaKomponenttia.muokkaaJLabelia(pelinVaikeusaste, 805, 326, 290, 30, false, Color.GRAY, Color.white, 20, pohja);
        moneskoKysymysMenossa = new JLabel("Kysymys: " + 0);
        MuokkaaKomponenttia.muokkaaJLabelia(moneskoKysymysMenossa, 805, 290, 160, 30, false, Color.GRAY, Color.white, 20, pohja);
        ajastin = new Timer(1000, new AjastimenKuuntelija(this));
        pelinAntamaAloitusohje = new JLabel("Oikaan yläkulmaan annetaan alkuaineen symboli. Kirjoita sitä vastaava alkuaine tekstikenttään.");
        MuokkaaKomponenttia.muokkaaJLabelia(pelinAntamaAloitusohje, 40, 430, 1000, 30, true, Color.black, Color.white.brighter(), 18, pohja);
        ajannäyttö = new JLabel("Aika: " + aika);
        MuokkaaKomponenttia.muokkaaJLabelia(ajannäyttö, 840, 530, 200, 50, false, Color.GRAY, Color.white, 40, pohja);
        alkuaineenSymboli = new JLabel("------------->Hg");
        MuokkaaKomponenttia.muokkaaJLabelia(alkuaineenSymboli, 20, 20, 1075, 165, false, Color.red, Color.white, 130, pohja);
        tekstikentänselitys = new JLabel("Ole hyvä ja anna vastauksesi pienellä.");
        MuokkaaKomponenttia.muokkaaJLabelia(tekstikentänselitys, 50, 622, 300, 40, false, Color.red, Color.white, 14, pohja);
        vihje = new JLabel("Arrhenius, Lewis, Debye ja Pauling tarpeen mukaan jakavat neuvoja tähän kohtaan.");
        MuokkaaKomponenttia.muokkaaJLabelia(vihje, 17, 585, 1070, 30, false, Color.red, Color.white, 14, pohja);
        vastauskenttä = new JTextField();
        MuokkaaKomponenttia.muokkaaJTextFieldiä(vastauskenttä, 375, 615, 300, 40, 24, "Aloitus: Paina OK", pohja);
        ok_nappula = new JButton("ok");
        MuokkaaKomponenttia.muokkaaJButtonia(ok_nappula, 876, 671, 190, 66, Color.white, false, Color.green, false, true,
                false, 0, Color.white, pohja);
        ok_nappula.addActionListener(new Peli_ikkunanKuuntelija(this));
        sulje_nappula = new JButton("Sulje Peli");
        MuokkaaKomponenttia.muokkaaJButtonia(sulje_nappula, 911, 780, 125, 47, Color.white, false,
                Color.green.darker(), false, true, false, 0, Color.green.darker(), pohja);
        sulje_nappula.addActionListener(new Peli_ikkunanKuuntelija(this));
        peli_ikkuna.getRootPane().setDefaultButton(ok_nappula);
    }

    /**
     * Tämän metodin avulla muutetaan vihje-JLabelia.
     *
     * Kumittaa myös vastauskentän.
     */
    private void annaVihje() {
        aika = 15;
        vihje.setText(peli.palautaAlkuaineenVihje());
        vastauskenttä.setText("");
    }

    /**
     * Tämä metodi päivittää joitakin peli-ikkunan komponenteista.
     *
     * Metodi hakee uudet arvot komponentteihin symbolipeli-luokasta.
     */
    private void päivitäKomponentit() {
        if (peli.onkoVastattuOikeinKaikkiinKysymyksiin() == false) {
            aika = 15;
            vihje.setText(peli.palautaOnnitteluOikeastaVastauksesta());
            vastauskenttä.setText("");
            moneskoKysymysMenossa.setText("Kysymys: " + peli.palautaKysymysnumero());
            pelaajanPisteet.setText("Pisteet: " + peli.palautaPelaajanPisteet());
            alkuaineenSymboli.setText("                  " + peli.palautaAlkuaineenSymboli());
            pelinVaikeusaste.setText("Vaikeusaste: " + peli.palautaPelinVaikeusaste());
        }
        if (peli.onkoVastattuOikeinKaikkiinKysymyksiin() == true) {
            lopetaPeli();
        }
    }

    /**
     * Tämän metodin avulla tehdään peli-ikkunaan tarvittavat muutokset, kun
     * peli halutaan aloittaa.
     */
    private void aloitaPeli() {
        Musiikkikirjasto.pysäytäAloituslaulu();
        Musiikkikirjasto.jatkuvaToistoPelilaulu();
        vastauskenttä.setText("");
        pelinAntamaAloitusohje.setVisible(false);
        tekstikentänselitys.setVisible(false);
        moneskoKysymysMenossa.setText("Kysymys: " + peli.palautaKysymysnumero());
        alkuaineenSymboli.setText("                  " + peli.palautaAlkuaineenSymboli());
        pelinVaikeusaste.setText("Vaikeusaste: " + peli.palautaPelinVaikeusaste());
        ajastin.start();
    }

    /**
     * Tämä metodi laukaisee JDialogin, kun peli lopetetaan (häviö tai voitto).
     */
    private void lopetaPeli() {
        Musiikkikirjasto.pysäytäPelilaulu();
        ajastin.stop();
        vastauskenttä.setText("");
        moneskoKysymysMenossa.setText("Kysymys: " + peli.palautaKysymysnumero());
        pelaajanPisteet.setText("Pisteet: " + peli.palautaPelaajanPisteet());
        vihje.setText(peli.palautaOnnitteluOikeastaVastauksesta());
        vastauskenttä.setEnabled(false);
        ok_nappula.setEnabled(false);
        lopetusikkuna = new JDialog(peli_ikkuna, false);
        lopetusikkuna.addWindowListener(new Peli_ikkunanKuuntelija(this));
        if (peli.onkoVastattuOikeinKaikkiinKysymyksiin() == true) {
            Musiikkikirjasto.soitaVoittolaulu();
            voittoikkuna = new Ilmoitavoittoikkuna(lopetusikkuna, peli.palautaPelaajanPisteet(), peli.pääseeköPelitulosListalle());
            voittoikkuna.run();
            voittoikkuna.lisääSuljePeliNappulaActionListeneriin(this);
        } else {
            Musiikkikirjasto.soitaHäviölaulu();
            häviöikkuna = new Ilmoitatappioikkuna(lopetusikkuna, peli.palautaPelaajanPisteet(), peli.pääseeköPelitulosListalle(), peli.palautaAlkuaineenOikeaVastaus());
            häviöikkuna.run();
            häviöikkuna.lisääSuljePeliNappulaActionListeneriin(this);
        }
    }

    /**
     * Metodi palauttaa voittoikkunan tekstikentässä olevan merkkijonon.
     *
     * @return Merkkijono, joka palautettiin.
     */
    private String palautaVoittoNimi() {
        return voittoikkuna.palautaNimikentänNimi();
    }

    /**
     * Metodi palauttaa häviöikkunan tekstikentässä olevan merkkijonon.
     *
     * @return Merkkijono, joka palautettiin.
     */
    private String palutaHäviöNimi() {
        return häviöikkuna.palautaNimikentänNimi();
    }

    /**
     * Tämä metodi vähentää pelin aikaa yhdellä.
     *
     * Jos aika on nolla, metodi painaa pelin ok-nappulaa.
     */
    public void vähennäAikaa() {
        if (aika > 0) {
            aika = aika - 1;
            ajannäyttö.setText("Aika: " + aika);
        } else {
            ok_nappula.doClick();
        }
    }

    /**
     * Toiminnot, jotka tehdään, kun suljetaan peli_ikkuna.
     */
    public void suljePeli_Ikkuna() {
        Musiikkikirjasto.pysäytäAloituslaulu();
        Musiikkikirjasto.pysäytäPelilaulu();
        Musiikkikirjasto.jatkuvaToistoValikkolaulu();
        ajastin.stop();
        if (peli_ikkuna != null) {
            peli_ikkuna.getContentPane().removeAll();
            super.run(peli_ikkuna);
        }
        if (lopetusikkuna != null) {
            lopetusikkuna.dispose();
        }
    }

    /**
     * Metodi päivittää pelitilannetta.
     *
     * Kun ok-nappia on painettu, metodi hakee tiedon siitä, mikä on
     * symbolipelin tilanne. Tästä tilanteesta riippuu, mitä tehdään
     * seuraavaksi.
     */
    public void painettiinOkNappia() {
        String mitäTehdään = peli.syötäPeliinVastaus(vastauskenttä.getText());
        if (mitäTehdään.equals("aloita")) {
            aloitaPeli();
        } else if (mitäTehdään.equals("päivitä2")) {
            päivitäKomponentit();
        } else if (mitäTehdään.equals("päivitä1")) {
            päivitäKomponentit();
        } else if (mitäTehdään.equals("vihje")) {
            annaVihje();
        } else {
            lopetaPeli();
        }
    }

    /**
     * Tämän metodin avulla lisätään pelaajan antama nimimerkki pistelistaan.
     *
     * Jos nimimerkki tyhjä, nimimerkkiä ei lisätä. Jos tulos ei pääse listalle,
     * niin voitto- ja häviöikkuna palauttavat automaattisesti tyhjän
     * nimimerkin, jolloin nimeä ei tietenkään lisätä.
     *
     * @param tiedosto Tieodosto, johon nimimerkki halutaan lisätä.
     */
    public void lisääNimiPistelistaan(String tiedosto) {
        if (voittoikkuna != null) {
            if (palautaVoittoNimi().length() > 0) {
                peli.lisääPelitulosPistelistaan(palautaVoittoNimi(), tiedosto);
            }
        }
        if (häviöikkuna != null) {
            if (palutaHäviöNimi().length() > 0) {
                peli.lisääPelitulosPistelistaan(palutaHäviöNimi(), tiedosto);
            }
        }
    }
}