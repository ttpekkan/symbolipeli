
package ohjelma;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AjastimenKuuntelija implements ActionListener {
    public Peli ohjelma;
    
    public AjastimenKuuntelija(Peli ohjelma) {
        this.ohjelma = ohjelma;
    }
    
    public void actionPerformed(ActionEvent e) {
        ohjelma.vähennäAikaa();
    }
    
    
}
