
package ohjelma;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.SwingUtilities;

public class NappulanKuuntelija2 implements ActionListener {
    public Pistelista ohjelma;
    
    public NappulanKuuntelija2(Pistelista ohjelma) {
        this.ohjelma = ohjelma;
    }
    
    public void actionPerformed(ActionEvent e) {
        JButton nappula = ((JButton)e.getSource());
        if (nappula.getText().equals("Sulje Pistelista")) {
            ohjelma.laskuri = ohjelma.laskuri - 1;
            ohjelma.ikkuna2.dispose();
        }
    }
    
}