
package ohjelma;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.SwingUtilities;

public class NappulanKuuntelija implements ActionListener {
    public Päävalikko ohjelma;
    
    public NappulanKuuntelija(Päävalikko ohjelma) {
        this.ohjelma = ohjelma;
    }
    
    public void actionPerformed(ActionEvent e) {
        JButton nappula = ((JButton)e.getSource());
        if (nappula.getText().equals("Lopeta")) {
            System.exit(0);
        } 
        if (nappula.getText().equals("Pistelista")) {
            Pistelista uusi = new Pistelista();
            SwingUtilities.invokeLater(uusi);
        }
        if (nappula.getText().equals("Aloita Peli")) {
            ohjelma.musa.päävalikkoMusa.stop();
            Peli uusi = new Peli();
            SwingUtilities.invokeLater(uusi);
        }
    }
    
}
