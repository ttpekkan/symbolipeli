package ohjelma;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JTextField;

public class NappulanKuuntelija3 implements ActionListener {

    public Peli ohjelma;

    public NappulanKuuntelija3(Peli ohjelma) {
        this.ohjelma = ohjelma;
    }

    public void suljePelinPääikkuna() {
        ohjelma.ajastin.stop();
        ohjelma.musa.pelimusa.stop();
        ohjelma.musa.aloitaTaustaMusa();
        ohjelma.laskuri = ohjelma.laskuri - 1;
        if (ohjelma.kysymys != null) {
            ohjelma.kysymys.dispose();
        }
        if (ohjelma.häviö != null) {
            ohjelma.häviö.dispose();
        }
        if (ohjelma.nimimerkki != null) {
            ohjelma.laskuri2 = ohjelma.laskuri2 - 1;
            ohjelma.nimimerkki.dispose();
        }
        if (ohjelma.voitto != null) {
            ohjelma.voitto.dispose();
        }
        ohjelma.ikkuna3.dispose();
    }

    public void okKysymysIkkuna() {
        if (ohjelma.kysymysnumero == 0 && !ohjelma.vastauskenttä.getText().equals("pelaa")) {
            suljePelinPääikkuna();
            return;
        }
        if (ohjelma.kysymysnumero == 0 && ohjelma.vastauskenttä.getText().equals("pelaa")) {
            ohjelma.kysymysnumero = ohjelma.kysymysnumero + 1;
            ohjelma.aloitaPeli();
            return;
        }
        if (ohjelma.kysyttyAine != null) {           
            if (ohjelma.väärät == 0 && ohjelma.vastauskenttä.getText().equals(ohjelma.kysyttyAine.nimi)) {
                ohjelma.päivitäTilanne(2);
                return;
            }
            if (ohjelma.väärät == 1 &&  ohjelma.vastauskenttä.getText().equals(ohjelma.kysyttyAine.nimi)) {
                ohjelma.päivitäTilanne(1);
                return;
            }
            if (ohjelma.väärät == 0 && !ohjelma.vastauskenttä.getText().equals(ohjelma.kysyttyAine.nimi)) {
                ohjelma.annaVihje();
                return;
            }
            if (ohjelma.väärät == 1 && !ohjelma.vastauskenttä.getText().equals(ohjelma.kysyttyAine.nimi)) {
                ohjelma.ajastin.stop();
                ohjelma.hävisit();
            }
        }
    }
 
    
    public void lisääNimiIkkuna() {
        ohjelma.pistelistanimi = ohjelma.nimikenttä.getText();
            if (ohjelma.pistelistanimi != null) {
                if (ohjelma.pistelistanimi.length() > 0) {
                    ohjelma.lisääTulos(ohjelma.pistelistanimi, ohjelma.kokonaispisteet);
                }
            }
            ohjelma.tallennaPistelista();
            suljePelinPääikkuna();
    }


    public void actionPerformed(ActionEvent e) {
        JButton nappula = ((JButton) e.getSource());
        if (nappula.getText().equals("Sulje Peli")) {
            suljePelinPääikkuna();
        }
        if (nappula.getText().equals("ok")) {
            okKysymysIkkuna();
        }
        if (nappula.getText().equals("Lopeta Peli")) {
            if (ohjelma.paaseeListalle(ohjelma.kokonaispisteet) == true) {
                ohjelma.kysyNimimerkki();
            } else {
                suljePelinPääikkuna();
            }
        }
        if (nappula.getText().equals("Tallenna Nimimerkki")) {
            lisääNimiIkkuna();
        }

    }
}