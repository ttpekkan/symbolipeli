package ohjelma;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public class PeliIkkunanKuuntelija implements ActionListener {

    private PeliIkkuna ohjelma;

    public PeliIkkunanKuuntelija(PeliIkkuna ohjelma) {
        this.ohjelma = ohjelma;
    }

    private void suljePelinPääikkuna() {
        ohjelma.palautaMusiikkikirjasto().palautaAloituslaulu().stop();
        ohjelma.palautaAjastin().stop();
        ohjelma.palautaMusiikkikirjasto().palautaPelilaulu().stop();
        ohjelma.palautaMusiikkikirjasto().palautaPäävalikkolaulu().loop();
        if (ohjelma.palautaKysymysikkuna() != null) {
            ohjelma.palautaKysymysikkuna().dispose();
        }
        if (ohjelma.palautaHäviöikkuna() != null) {
            ohjelma.palautaHäviöikkuna().dispose();
        }
        if (ohjelma.palautaKysynimiIkkuna() != null) {
            ohjelma.vähennäKysynimiIkkunalaskuri();
            ohjelma.palautaKysynimiIkkuna().dispose();
        }
        if (ohjelma.palautaVoittoikkuna() != null) {
            ohjelma.palautaVoittoikkuna().dispose();
        }
        ohjelma.vähennäPääikkunalaskuri();
        ohjelma.palautaPelinPääikkuna().dispose();
    }

    private void okKysymysIkkuna() {
        if (ohjelma.palautaKysymysnumero() == 0 && !ohjelma.palautaVastauskenttä().getText().equals("pelaa")) {
            suljePelinPääikkuna();
        } else if (ohjelma.palautaKysymysnumero() == 0 && ohjelma.palautaVastauskenttä().getText().equals("pelaa")) {
            ohjelma.AsetaKysymynumero();
            ohjelma.aloitaPeli();
        } else if (ohjelma.palautaVäärät() == 0 && ohjelma.palautaVastauskenttä().getText().equals(ohjelma.palautaKysyttyAine().palautaNimi())) {
            ohjelma.päivitäTilanne(2);
        } else if (ohjelma.palautaVäärät() == 1 && ohjelma.palautaVastauskenttä().getText().equals(ohjelma.palautaKysyttyAine().palautaNimi())) {
            ohjelma.päivitäTilanne(1);
        } else if (ohjelma.palautaVäärät() == 0 && !ohjelma.palautaVastauskenttä().getText().equals(ohjelma.palautaKysyttyAine().palautaNimi())) {
            ohjelma.annaVihje();
        } else {
            ohjelma.palautaMusiikkikirjasto().palautaAloituslaulu().stop();
            ohjelma.palautaAjastin().stop();
            ohjelma.hävisit();
        }
    }

    private void lisääNimiIkkuna() {
        ohjelma.palautaPistelistanimi();
        if (ohjelma.palautaPistelistanimi().length() > 0) {
            ohjelma.lisääTulos(ohjelma.palautaPistelistanimi(), ohjelma.palautaKokonaispisteet());
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
            if (ohjelma.pääseeListalle(ohjelma.palautaKokonaispisteet()) == true) {
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