package ohjelma;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JButton;

/**
 * Tämä luokka kuuntelee peli-ikkunan ja lopetus-ikkunan nappeja.
 *
 * @author Timo Pekkanen
 */
public class Peli_ikkunanKuuntelija implements ActionListener, WindowListener {

    private Peli_ikkuna peli_ikkuna;

    /**
     * Luokan konstruktori.
     *
     * @param peli_ikkuna Olio, jota halutaan kuunnella.
     */
    public Peli_ikkunanKuuntelija(Peli_ikkuna peli_ikkuna) {
        this.peli_ikkuna = peli_ikkuna;

    }

    /**
     * Tämä metodi valvoo, mitä nappeja painetaan, ja siitä riippuen, suorittaa
     * toimintoja.
     *
     * @param e Napinpainallus.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton nappula = ((JButton) e.getSource());
        if (nappula.getText().equals("Sulje Peli")) {
            peli_ikkuna.suljePeli_Ikkuna();
        }
        if (nappula.getText().equals("ok")) {
            peli_ikkuna.painettiinOkNappia();
        }
        if (nappula.getText().equals("Lopeta Peli")) {
            peli_ikkuna.lisääNimiPistelistaan("src/top10.txt");
            peli_ikkuna.suljePeli_Ikkuna();
        }
    }

    /**
     * Mitä tehdään, kun ikkuna halutaan sulkea.
     *
     * @param we ikkunan sulkemisnapin painallus.
     */
    @Override
    public void windowClosing(WindowEvent we) {
        peli_ikkuna.suljePeli_Ikkuna();
    }

    @Override
    public void windowOpened(WindowEvent we) {
    }

    @Override
    public void windowClosed(WindowEvent we) {
    }

    @Override
    public void windowIconified(WindowEvent we) {
    }

    @Override
    public void windowDeiconified(WindowEvent we) {
    }

    @Override
    public void windowActivated(WindowEvent we) {
    }

    @Override
    public void windowDeactivated(WindowEvent we) {
    }
}