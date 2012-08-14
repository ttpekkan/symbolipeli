package ohjelma;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public class PistelistanKuuntelija implements ActionListener {

    private Pistelista ohjelma;

    public PistelistanKuuntelija(Pistelista ohjelma) {
        this.ohjelma = ohjelma;
    }

    public void actionPerformed(ActionEvent e) {
        JButton nappula = ((JButton) e.getSource());
        if (nappula.getText().equals("Sulje Pistelista")) {
            ohjelma.vähennäIkkunoidenMäärää();
            ohjelma.palautaPisteListaIkkuna().dispose();
        }
    }
}