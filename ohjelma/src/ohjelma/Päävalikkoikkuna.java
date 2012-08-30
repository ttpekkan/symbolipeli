package ohjelma;

import java.awt.Color;
import java.awt.Container;
import javax.swing.*;

/**
 * Tällä luokalla luodaan pelin päävalikko.
 *
 * @author Timo Pekkanen
 */
public class Päävalikkoikkuna {

    private JButton aloita;
    private JButton lopeta;
    private JButton pistelista;
    private Container pohja;
    private JFrame päävalikkoikkuna;
    private JMenu menu;

    /**
     * Tämä metodi luo päävalikkoikkunan.
     */
    public void run(JFrame ikkuna) {
        päävalikkoikkuna = ikkuna;
        päävalikkoikkuna.setLocation(230, 180);
        UIManager.put("Button.select", Color.green);
        KomponenttienMuokkaus.luoContentPaneKuvasta("taustakuva.png", päävalikkoikkuna);
        luoKomponentit();
        Musiikkikirjasto.jatkuvaToistoPäävalikkolaulu();
        KomponenttienMuokkaus.neIkkunanavausToiminnotJotkaAinaSamat(päävalikkoikkuna);
        päävalikkoikkuna.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    /**
     * Tämä metodi luo päävalikkoikkunankomponentit.
     */
    private void luoKomponentit() {
        pohja = päävalikkoikkuna.getContentPane();
        aloita = new JButton("Aloita Peli");
        KomponenttienMuokkaus.luoNappula(aloita, 46, 450, 200, 50, Color.white, true, Color.green.darker(), false, true, true, 20, Color.green.darker(), pohja);
        aloita.addActionListener(new PäävalikkoikkunanKuuntelija(this));
        pistelista = new JButton("Pistelista");
        KomponenttienMuokkaus.luoNappula(pistelista, 296, 450, 200, 50, Color.white, true, Color.green.darker(), false, true, true, 20, Color.green.darker(), pohja);
        pistelista.addActionListener(new PäävalikkoikkunanKuuntelija(this));
        lopeta = new JButton("Lopeta");
        KomponenttienMuokkaus.luoNappula(lopeta, 546, 450, 200, 50, Color.white, true, Color.green.darker(), false, true, true, 20, Color.green.darker(), pohja);
        lopeta.addActionListener(new PäävalikkoikkunanKuuntelija(this));
        päävalikkoikkuna.getRootPane().setDefaultButton(aloita);
        
    }

    /**
     * Tämän metodin avulla pysäytetään päävalikkolaulu.
     */
    public void pistelista() {
        Pistelistaikkuna pistelistaikkuna = new Pistelistaikkuna();
        pistelistaikkuna.run(päävalikkoikkuna);
    }

    public void peli_ikkuna() {
        päävalikkoikkuna.getContentPane().removeAll();
        Peli_ikkuna peli_ikkuna = new Peli_ikkuna();
        peli_ikkuna.run(päävalikkoikkuna);
    }
}
