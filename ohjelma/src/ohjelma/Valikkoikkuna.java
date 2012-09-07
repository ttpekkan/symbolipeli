package ohjelma;

import java.awt.Color;
import java.awt.Container;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.WindowConstants;

/**
 * Tämä luokka on pelin valikko.
 *
 * @author Timo Pekkanen
 */
public class Valikkoikkuna {

    private JButton aloita;
    private JButton lopeta;
    private JButton pistelista;
    private Container pohja;
    private JFrame päävalikkoikkuna;
    private Pistelistaikkuna pistelistaikkuna;

    /**
     * Tämä metodi käynnistää valikon.
     *
     * @param ikkuna JFrame, johon valikko luodaan.
     */
    public void run(JFrame ikkuna) {
        päävalikkoikkuna = ikkuna;
        päävalikkoikkuna.setLocation(230, 180);
        UIManager.put("Button.select", Color.green);
        MuokkaaKomponenttia.luoContentPaneKuvasta("taustakuva.png", päävalikkoikkuna);
        luoValikonKomponentit();
        Musiikkikirjasto.jatkuvaToistoValikkolaulu();
        MuokkaaKomponenttia.asetaLaskurinArvo(0);
        MuokkaaKomponenttia.suoritaNeIkkunanavausToiminnotJotkaAinaSamat(päävalikkoikkuna);
        päävalikkoikkuna.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    /**
     * Luo valikon komponentit.
     */
    private void luoValikonKomponentit() {
        pohja = päävalikkoikkuna.getContentPane();
        aloita = new JButton("Aloita Peli");
        MuokkaaKomponenttia.muokkaaJButtonia(aloita, 46, 450, 200, 50, Color.white, true, Color.green.darker(), false, true, true, 20, Color.green.darker(), pohja);
        aloita.addActionListener(new ValikkoikkunanKuuntelija(this));
        pistelista = new JButton("Pistelista");
        MuokkaaKomponenttia.muokkaaJButtonia(pistelista, 296, 450, 200, 50, Color.white, true, Color.green.darker(), false, true, true, 20, Color.green.darker(), pohja);
        pistelista.addActionListener(new ValikkoikkunanKuuntelija(this));
        lopeta = new JButton("Lopeta");
        MuokkaaKomponenttia.muokkaaJButtonia(lopeta, 546, 450, 200, 50, Color.white, true, Color.green.darker(), false, true, true, 20, Color.green.darker(), pohja);
        lopeta.addActionListener(new ValikkoikkunanKuuntelija(this));
        päävalikkoikkuna.getRootPane().setDefaultButton(aloita);

    }

    /**
     * Tämä metodin luo pistelista-JDialogin.
     */
    public void näytäPistelista() {
        pistelistaikkuna = new Pistelistaikkuna();
        MuokkaaKomponenttia.asetaLaskurinArvo(1);
        pistelistaikkuna.run(päävalikkoikkuna);
    }

    /**
     * Metodi käynnistää peli-ikkunan.
     *
     * Peli-ikkuna luodaan valikkoikkunan päälle.
     */
    public void näytäPeli_ikkuna() {
        päävalikkoikkuna.getContentPane().removeAll();
        Peli_ikkuna peli_ikkuna = new Peli_ikkuna();
        peli_ikkuna.run(päävalikkoikkuna);
    }
}