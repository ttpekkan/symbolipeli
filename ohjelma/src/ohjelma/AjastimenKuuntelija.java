package ohjelma;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AjastimenKuuntelija implements ActionListener {

    private PeliIkkuna ohjelma;

    public AjastimenKuuntelija(PeliIkkuna ohjelma) {
        this.ohjelma = ohjelma;
    }

    public void actionPerformed(ActionEvent e) {
        ohjelma.vähennäAikaa();
    }
}
