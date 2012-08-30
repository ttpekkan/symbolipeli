package ohjelma;

import java.awt.Color;
import java.awt.Container;
import javax.swing.*;

/**
 * Tämä luokka kontrolloi tapahtumia, kun peli on aloitettu.
 *
 * @author Timo Pekkanen
 */
public class Peli_ikkuna extends Päävalikkoikkuna {

    private JButton ok_nappula;
    private HaeArvoja peli;
    private JButton sulje;
    private IlmoitaHäviö_Ikkuna hävisit;
    private IlmoitaVoitto_Ikkuna voitit;
    private int aika;
    private JDialog lopetusikkuna;
    private JFrame peli_ikkuna;
    private JLabel ajannäyttö;
    private JLabel moneskoKysymysMenossa;
    private JLabel pelinAntamaAloitusOhje;
    private JLabel pistetilanne;
    private JLabel symboli;
    private JLabel tekstikentänSelitys;
    private JLabel vaikeusaste;
    private JLabel vihje;
    private JTextField vastauskenttä;
    private Timer ajastin;

    /**
     * Käynnistää pelin Pääikkunan ja asettaa sen alkuominaisuudet.
     */
    @Override
    public void run(JFrame ikkuna) {
        peli_ikkuna = ikkuna;
        peli = new Peli();
        aika = 15;
        peli_ikkuna.setTitle("Symbolipeli");
        peli_ikkuna.setLocation(130, 180);
        UIManager.put("Button.select", Color.green);
        Musiikkikirjasto.soitaAloituslaulu();
        KomponenttienMuokkaus.luoContentPaneKuvasta(Musiikkikirjasto.palautaKuvannimi(), peli_ikkuna);
        luoKomponentit();
        KomponenttienMuokkaus.neIkkunanavausToiminnotJotkaAinaSamat(peli_ikkuna);
        peli_ikkuna.addWindowListener(new Peli_ikkunanKuuntelija(this));

    }

    /**
     * Metodi luo halutut komponentit Pelin pääikkunaan.
     */
    private void luoKomponentit() {
        Container pohja = peli_ikkuna.getContentPane();
        pistetilanne = new JLabel("Kysymys: " + 0);
        KomponenttienMuokkaus.luoTeksti(pistetilanne, 805, 360, 140, 30, false, Color.GRAY, Color.white, 20, pohja);
        vaikeusaste = new JLabel("Vaikeusaste: helppo");
        KomponenttienMuokkaus.luoTeksti(vaikeusaste, 805, 326, 290, 30, false, Color.GRAY, Color.white, 20, pohja);
        moneskoKysymysMenossa = new JLabel("Kysymys: " + 0);
        KomponenttienMuokkaus.luoTeksti(moneskoKysymysMenossa, 805, 290, 160, 30, false, Color.GRAY, Color.white, 20, pohja);
        ajastin = new Timer(1000, new AjastimenKuuntelija(this));
        pelinAntamaAloitusOhje = new JLabel("Oikaan yläkulmaan annetaan alkuaineen symboli. Kirjoita sitä vastaava alkuaine tekstikenttään.");
        KomponenttienMuokkaus.luoTeksti(pelinAntamaAloitusOhje, 40, 430, 1000, 30, true, Color.black, Color.white.brighter(), 18, pohja);
        ajannäyttö = new JLabel("Aika: " + aika);
        KomponenttienMuokkaus.luoTeksti(ajannäyttö, 840, 530, 200, 50, false, Color.GRAY, Color.white, 40, pohja);
        symboli = new JLabel("------------->Hg");
        KomponenttienMuokkaus.luoTeksti(symboli, 20, 20, 1075, 165, false, Color.red, Color.white, 130, pohja);
        tekstikentänSelitys = new JLabel("Ole hyvä ja anna vastauksesi pienellä.");
        KomponenttienMuokkaus.luoTeksti(tekstikentänSelitys, 50, 622, 300, 40, false, Color.red, Color.white, 14, pohja);
        vihje = new JLabel("Arrhenius, Lewis, Debye ja Pauling tarpeen mukaan jakavat neuvoja tähän kohtaan.");
        KomponenttienMuokkaus.luoTeksti(vihje, 17, 585, 1070, 30, false, Color.red, Color.white, 14, pohja);
        vastauskenttä = new JTextField();
        KomponenttienMuokkaus.luoTekstikenttä(vastauskenttä, 375, 615, 300, 40, 24, "Aloitus: Paina OK", pohja);
        ok_nappula = new JButton("ok");
        KomponenttienMuokkaus.luoNappula(ok_nappula, 876, 671, 190, 66, Color.white, false, Color.green, false, true,
                false, 0, Color.white, pohja);
        ok_nappula.addActionListener(new Peli_ikkunanKuuntelija(this));
        sulje = new JButton("Sulje Peli");
        KomponenttienMuokkaus.luoNappula(sulje, 911, 780, 125, 47, Color.white, false,
                Color.green.darker(), false, true, false, 0, Color.green.darker(), pohja);
        sulje.addActionListener(new Peli_ikkunanKuuntelija(this));
        peli_ikkuna.getRootPane().setDefaultButton(ok_nappula);
    }

    /**
     * Tämä metodi vähentää aikaa, laukaisee vihjeen tai laukaisee häviön.
     */
    public void vähennäAikaa() {
        if (aika > 0) {
            aika = aika - 1;
            ajannäyttö.setText("Aika: " + aika);
        } else {
            if (peli.palautaYrityskerta() == 0) {
                annaVihje();
            } else {
                ajastin.stop();
                lopetus();
            }
        }
    }

    public void annaVihje() {
        aika = 15;
        vihje.setText(peli.annaVihje());
        vastauskenttä.setText("");
    }

    public void päivitäKomponentit(int kerroin) {
        if (peli.onko54() < 54) {
            aika = 15;
            peli.päivitäTilanne(kerroin);
            vihje.setText(peli.palautaOnnittelu());
            vastauskenttä.setText("");
            moneskoKysymysMenossa.setText("Kysymys: " + peli.palautaKysymysnumero());
            pistetilanne.setText("Pisteet: " + peli.palautaPisteet());
            symboli.setText("                  " + peli.palautaSymboli());
            vaikeusaste.setText("Vaikeusaste: " + peli.palautaVaikeusaste());
        } 
        if (peli.onko54() == 54) {
            lopetus();
        }
    }

    public void peliAlkaa() {
        Musiikkikirjasto.pysäytäAloituslaulu();
        Musiikkikirjasto.jatkuvaToistoPelilaulu();
        vastauskenttä.setText("");
        pelinAntamaAloitusOhje.setVisible(false);
        tekstikentänSelitys.setVisible(false);
        moneskoKysymysMenossa.setText("Kysymys: " + peli.palautaKysymysnumero());
        symboli.setText("                  " + peli.palautaSymboli());
        vaikeusaste.setText("Vaikeusaste: " + peli.palautaVaikeusaste());
        ajastin.start();
    }

    /**
     * Tämä metodi laukaisee lopetusikkunan.
     */
    public void lopetus() {
        Musiikkikirjasto.pysäytäPelilaulu();
        ajastin.stop();
        vastauskenttä.setText("");
        vastauskenttä.setEnabled(false);
        ok_nappula.setEnabled(false);
        lopetusikkuna = new JDialog(peli_ikkuna, false);
        lopetusikkuna.addWindowListener(new Peli_ikkunanKuuntelija(this));
        if (peli.onko54() == 54) {
            Musiikkikirjasto.soitaVoittolaulu();
            vihje.setText(peli.palautaOnnittelu());
            voitit = new IlmoitaVoitto_Ikkuna(lopetusikkuna, peli.palautaPisteet(), peli.pääseeListalle());
            voitit.run();
            voitit.lisääActionListeneriin(this);
        } else {
            Musiikkikirjasto.soitaHäviölaulu();
            vihje.setText(peli.palautaOnnittelu());
            hävisit = new IlmoitaHäviö_Ikkuna(lopetusikkuna, peli.palautaPisteet(), peli.pääseeListalle(), peli.palautaOikeaVastaus());
            hävisit.run();
            hävisit.lisääActionListeneriin(this);
        }
    }

    /**
     * Toiminnot, jotka tehdään, kun suljetaan peli_ikkuna.
     */
    public void sulje() {
        Musiikkikirjasto.pysäytäAloituslaulu();
        Musiikkikirjasto.pysäytäPelilaulu();
        Musiikkikirjasto.jatkuvaToistoPäävalikkolaulu();
        if (peli_ikkuna != null) {
            super.run(peli_ikkuna);
        }
        if (lopetusikkuna != null) {
            lopetusikkuna.dispose();
        }
    }

    public int mitäTehdäänNumero() {
        if (moneskoKysymysMenossa.getText().equals("Kysymys: " + 0)) {
            return 0;
        } else if (vastauskenttä.getText().equals(peli.palautaOikeaVastaus()) && peli.palautaYrityskerta() == 0) {
            return 1;
        } else if (vastauskenttä.getText().equals(peli.palautaOikeaVastaus()) && peli.palautaYrityskerta() == 1) {
            return 2;
        } else if (!vastauskenttä.getText().equals(peli.palautaOikeaVastaus()) && peli.palautaYrityskerta() == 0) {
            return 3;
        } else {
            return 4;
        }
    }
    
     public void lisääNimi(String nimi, String tiedosto) {
        if (voitit != null) {
            if (nimi.length() > 0) {
                peli.lisääNimi(nimi, tiedosto);
            }
        }
        if (hävisit != null) {
            if (nimi.length() > 0) {
                peli.lisääNimi(nimi, tiedosto);
            }
        }
    }
     
    public String palautaVoittoNimi() {
        return voitit.palautaNimikentänNimi();
    }
    
    public String palutaHäviöNimi() {
        return hävisit.palautaNimikentänNimi();
    }
    
    public boolean häviöVaiVoitto() {
        if (hävisit == null) {
            return true;
        } else {
            return false;
        }
    }
}