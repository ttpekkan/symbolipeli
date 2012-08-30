package ohjelma;

import java.util.ArrayList;
import java.util.Collections;

public class Peli implements HaeArvoja  {

    private Alkuaine kysyttyAine;
    private int kysymysnumero;
    private String symboli;
    private String vihje;
    private String vaikeusaste;
    private String oikeaVastaus;
    private int moneskoHelppoKysytään;
    private int moneskoKeskivaikeaKysytään;
    private int moneskoVaikeaKysytään;
    private int pelaajanPisteet;
    private int yrityskerta;
    private ArrayList<String> henkilöt;
    private ArrayList<String> onnittelut;
    private Kysymysgeneraattori alkuaineet;
    private Pistelista pistelista;

    public Peli() {
        lisääHenkilöt();
        lisääOnnittelut();
        alkuaineet = new Kysymysgeneraattori();
        pistelista = new Pistelista();
        kysymysnumero = 1;
        moneskoHelppoKysytään = 0;
        moneskoKeskivaikeaKysytään = 0;
        moneskoVaikeaKysytään = 0;
        kysyKysymys(alkuaineet.palautaHelppoKysymys(moneskoHelppoKysytään));
        vihje = "";
        vaikeusaste = "helppo";
        yrityskerta = 0;
    }

    private void lisääHenkilöt() {
        henkilöt = new ArrayList<String>();
        henkilöt.add("Pauling");
        henkilöt.add("Arrhenius");
        henkilöt.add("Lewis");
        henkilöt.add("Debye");
    }

    private void lisääOnnittelut() {
        onnittelut = new ArrayList<String>();
        onnittelut.add("Mainiota!");
        onnittelut.add("Hienoa!");
        onnittelut.add("Mahtavaa!");
        onnittelut.add("Täysin oikein!");
        onnittelut.add("Vaikuttavaa!");
    }

    private String palautaSatunnainenNimi() {
        Collections.shuffle(henkilöt);
        return henkilöt.get(0);
    }

    private String palautaSatunnainenOnnittelu() {
        Collections.shuffle(onnittelut);
        return onnittelut.get(0);
    }

    private void kysyKysymys(Alkuaine aine) {
        kysyttyAine = aine;
        symboli = kysyttyAine.aineenSymboli();
        oikeaVastaus = kysyttyAine.aineenNimi();
    }

    @Override
    public void päivitäTilanne(int kerroin) {
        vihje = (palautaSatunnainenNimi() + ": " + palautaSatunnainenOnnittelu());
        yrityskerta = 0;
        kysymysnumero = kysymysnumero + 1;
        if (moneskoHelppoKysytään < 11 && moneskoKeskivaikeaKysytään == 0 && moneskoVaikeaKysytään == 0) {
            kysyHelppo(kerroin);
            if (moneskoHelppoKysytään == 11) {
                vaikeusaste = "keskivaikea";
                vihje = (palautaSatunnainenNimi() + ": Edellinen Oikein! Siirrytään keskivaikeisiin.");
                kysyKysymys(alkuaineet.palautaKeskivaikeaKysymys(moneskoKeskivaikeaKysytään));
                return;
            }
        }
        if (moneskoHelppoKysytään == 11 && moneskoKeskivaikeaKysytään < 46 && moneskoVaikeaKysytään == 0) {
            kysyKeskivaikea(kerroin);
            if (moneskoKeskivaikeaKysytään == 46) {
                vaikeusaste = ("vaikea");
                vihje = (palautaSatunnainenNimi() + ": Siirrytään vaikeisiin.");
                kysyKysymys(alkuaineet.palautaVaikeaKysymys(moneskoVaikeaKysytään));
                return;
            }
        }
        if (moneskoHelppoKysytään == 11 && moneskoKeskivaikeaKysytään == 46 && moneskoVaikeaKysytään < 54) {
            kysyVaikea(kerroin);
            if (moneskoVaikeaKysytään == 54) {
                kysymysnumero = kysymysnumero - 1;
                lopetus();
            }
        }
    }

    private void kysyHelppo(int kertoluku) {
        moneskoHelppoKysytään = moneskoHelppoKysytään + 1;
        pelaajanPisteet = pelaajanPisteet + (1 * kertoluku);
        if (moneskoHelppoKysytään < 11) {
            kysyKysymys(alkuaineet.palautaHelppoKysymys(moneskoHelppoKysytään));
        }
    }

    private void kysyKeskivaikea(int kertoluku) {
        moneskoKeskivaikeaKysytään = moneskoKeskivaikeaKysytään + 1;
        pelaajanPisteet = pelaajanPisteet + (2 * kertoluku);
        if (moneskoKeskivaikeaKysytään < 46) {
            kysyKysymys(alkuaineet.palautaKeskivaikeaKysymys(moneskoKeskivaikeaKysytään));
        }
    }

    private void kysyVaikea(int kertoluku) {
        moneskoVaikeaKysytään = moneskoVaikeaKysytään + 1;
        pelaajanPisteet = pelaajanPisteet + (3 * kertoluku);
        if (moneskoVaikeaKysytään < 54) {
            kysyKysymys(alkuaineet.palautaVaikeaKysymys(moneskoVaikeaKysytään));
        }
    }

    private void lopetus() {      
        if (moneskoVaikeaKysytään == 54) {
            vihje = (palautaSatunnainenNimi() + ": Mestarillinen suoritus!");
        } else {
            vihje = (palautaSatunnainenNimi() + ": Voi voi minkä menit tekemään! No, harjoitus tekee mestarin.");
        }
    }

    @Override
    public String annaVihje() {
        yrityskerta = yrityskerta + 1;
        return (palautaSatunnainenNimi() + ": " + kysyttyAine.aineenVihje());       
    }
    
    @Override
    public String palautaOnnittelu() {
        String palautus = vihje;
        return palautus;
    }
    
    @Override
    public String palautaOikeaVastaus() {
        String palautus = oikeaVastaus;
        return palautus;
    }
    
    @Override
    public String palautaSymboli() {
        String palautus = symboli;
        return palautus;
    }
    
    @Override
    public int palautaKysymysnumero() {
        int palautus = kysymysnumero;
        return palautus;
    }
    
    @Override
    public int palautaPisteet() {
        int palautus = pelaajanPisteet;
        return palautus;
    }
    
    @Override
    public int palautaYrityskerta() {
        int palautus = yrityskerta;
        return palautus;
    }
    
    @Override
    public boolean pääseeListalle() {
        boolean palautus = pistelista.pääseeListalle(pelaajanPisteet);
        return palautus;
    }
    
    @Override
    public void lisääNimi(String nimi, String tiedosto) {
        pistelista.lisääTulos(nimi, pelaajanPisteet);
        pistelista.tallennaPistelista(tiedosto);
    }
    
    @Override
    public String palautaVaikeusaste() {
        String palautus = vaikeusaste;
        return palautus;
    }
    
    @Override
    public int onko54() {
        int palautus = moneskoVaikeaKysytään;
        return palautus;
    }
}
