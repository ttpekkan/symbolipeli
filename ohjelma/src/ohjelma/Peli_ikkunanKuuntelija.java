package ohjelma;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JButton;

/**
 * Tämä luokka valvoo niitä nappeja, joita painetaan pelissä.
 *
 * @author Timo Pekkanen
 */
public class Peli_ikkunanKuuntelija implements ActionListener, WindowListener {

    private Peli_ikkuna ohjelma;

    /**
     * Luokan konstruktori.
     *
     * @param ohjelma Ohjelma on PeliIkkuna-olio.
     */
    public Peli_ikkunanKuuntelija(Peli_ikkuna ohjelma) {
        this.ohjelma = ohjelma;

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
            ohjelma.sulje();
        }
        if (nappula.getText().equals("ok")) {
            okNappula();
        }
        if (nappula.getText().equals("Lopeta Peli")) {
            if (ohjelma.häviöVaiVoitto() == true) {
                ohjelma.lisääNimi(ohjelma.palautaVoittoNimi(), "src/top10.txt");
            } else {
                ohjelma.lisääNimi(ohjelma.palutaHäviöNimi(), "src/top10.txt");
            }
            ohjelma.sulje();
        }
    }

    private void okNappula() {
        if (ohjelma.mitäTehdäänNumero() == 0) {
            ohjelma.peliAlkaa();
        } else if (ohjelma.mitäTehdäänNumero() == 1) {
            ohjelma.päivitäKomponentit(2);
        } else if (ohjelma.mitäTehdäänNumero() == 2) {
            ohjelma.päivitäKomponentit(1);
        } else if (ohjelma.mitäTehdäänNumero() == 3) {
            ohjelma.annaVihje();
        } else {
            ohjelma.lopetus();
        }
    }
    
    @Override
    public void windowClosing(WindowEvent we) {
        ohjelma.sulje();
    }
    @Override
    public void windowOpened(WindowEvent we) {}
    @Override
    public void windowClosed(WindowEvent we) {}
    @Override
    public void windowIconified(WindowEvent we) {}
    @Override
    public void windowDeiconified(WindowEvent we) {}
    @Override
    public void windowActivated(WindowEvent we) {}
    @Override
    public void windowDeactivated(WindowEvent we) {}
  
}