package ohjelma;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.SwingUtilities;

public class PäävalikonKuuntelija implements ActionListener {

    private Päävalikko ohjelma;

    public PäävalikonKuuntelija(Päävalikko ohjelma) {
        this.ohjelma = ohjelma;
    }

    public void actionPerformed(ActionEvent e) {
        JButton nappula = ((JButton) e.getSource());
        if (nappula.getText().equals("Aloita Peli")) {
            ohjelma.palautaMusiikkikirjasto().palautaPäävalikkolaulu().stop();
            PeliIkkuna uusi = new PeliIkkuna();
            SwingUtilities.invokeLater(uusi);
        } else if (nappula.getText().equals("Lopeta")) {
            System.exit(0);
        } else {
            Pistelista uusi = new Pistelista();
            SwingUtilities.invokeLater(uusi);
        }

    }
}
