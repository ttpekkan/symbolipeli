package ohjelma;

import java.awt.Color;
import java.awt.Container;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.WindowConstants;

/**
 * Tällä luokalla luodaan pelin päävalikko.
 *
 * @author Timo Pekkanen
 */
public class Päävalikkoikkuna implements Runnable {
    private JButton aloita; 
    private JButton lopeta;
    private JButton pistelista;  
    private Container pohja;
    private JFrame päävalikkoikkuna;
    private KomponenttienLataus komponentit;
    private Musiikkikirjasto musa;
   
    /**
     * Tämä metodi luo päävalikkoikkunan.
     */
    @Override
    public void run() {
        päävalikkoikkuna = new JFrame("Symbolipeli");
        päävalikkoikkuna.setLocation(230, 180);
        UIManager.put("Button.select", Color.green);
        komponentit = new KomponenttienLataus(päävalikkoikkuna);
        komponentit.luoContentPaneKuvasta("taustakuva.png");
        luoKomponentit();
        musa = new Musiikkikirjasto();
        musa.jatkuvaToistoPäävalikkolaulu();       
        komponentit.neIkkunanavausToiminnotJotkaAinaSamat();
        päävalikkoikkuna.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    /**
     * Tämä metodi luo päävalikkoikkunankomponentit.
     */
    private void luoKomponentit() {
        pohja = päävalikkoikkuna.getContentPane();
        aloita = new JButton("Aloita Peli");
        komponentit.luoNappula(aloita, 46, 450, 200, 50, Color.white, true, Color.green.darker(), false, true, true, 20, Color.green.darker(), pohja);
        aloita.addActionListener(new PäävalikkoikkunanKuuntelija(this));
        pistelista = new JButton("Pistelista");
        komponentit.luoNappula(pistelista, 296, 450, 200, 50, Color.white, true, Color.green.darker(), false, true, true, 20, Color.green.darker(), pohja);
        pistelista.addActionListener(new PäävalikkoikkunanKuuntelija(this));
        lopeta = new JButton("Lopeta");
        komponentit.luoNappula(lopeta, 546, 450, 200, 50, Color.white, true, Color.green.darker(), false, true, true, 20, Color.green.darker(), pohja);
        lopeta.addActionListener(new PäävalikkoikkunanKuuntelija(this));
        päävalikkoikkuna.getRootPane().setDefaultButton(aloita);
    }

    /**
     * Tämän metodin avulla pysäytetään päävalikkolaulu.
     */
    public void lopetaLaulu() {
        musa.pysäytäPäävalikkolaulu();
    }
}