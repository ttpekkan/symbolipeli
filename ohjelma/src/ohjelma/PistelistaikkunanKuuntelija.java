package ohjelma;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JButton;

/**
 * Tämä luokka kuuntelee pistelistaikkunan napinpainalluksia.
 *
 * @author Timo Pekkanen
 */
public class PistelistaikkunanKuuntelija implements ActionListener, WindowListener {

    private Pistelistaikkuna pistelistaikkuna;

    /**
     * Luokan konstruktori.
     *
     * @param pistelistaikkuna Olio, jota halutaan kuunnella.
     */
    public PistelistaikkunanKuuntelija(Pistelistaikkuna pistelistaikkuna) {
        this.pistelistaikkuna = pistelistaikkuna;
    }

    /**
     * Metodi määrää, mitä tehdään, kun nappia painetaan.
     *
     * @param e Napinpainallus.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton nappula = ((JButton) e.getSource());
        if (nappula.getText().equals("Sulje Pistelista")) {
            pistelistaikkuna.suljePistelistaikkuna();
        }
    }

    /**
     * Toiminnot jotka tehdään, jos suljetaan ikkuna ikkunan x-painikkeesta.
     *
     * @param we Ikkunan painallus.
     */
    @Override
    public void windowClosing(WindowEvent we) {
        pistelistaikkuna.suljePistelistaikkuna();
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