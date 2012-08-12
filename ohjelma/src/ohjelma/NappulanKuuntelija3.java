
package ohjelma;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public class NappulanKuuntelija3 implements ActionListener {
    public GraafinenPeli ohjelma;
    
    public NappulanKuuntelija3(GraafinenPeli ohjelma) {
        this.ohjelma = ohjelma;
    }
    
    public void actionPerformed(ActionEvent e) {
        JButton nappula = ((JButton)e.getSource());
        if (nappula.getText().equals("Sulje Peli")) {
            ohjelma.musa.pelimusa.stop();
            ohjelma.musa.aloitaTaustaMusa();
            ohjelma.laskuri = ohjelma.laskuri - 1;
            ohjelma.ikkuna3.dispose();
        }
    }
    
}