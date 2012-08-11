package ohjelma;

import java.util.Scanner;

/**
 * Tämä luokka on varsinainen symbolipeli.
 * @author timo
 */

public class Peli {

    public int helpot;
    public int keskivaikeat;
    public int vaikeat;
    public int oikeat;
    public int riko;
    public int pisteet;
    public int helpotPisteet;
    public int keskivaikeatPisteet;
    public int vaikeatPisteet;
    public int kokonaispisteet;
    public Kysymysgeneraattori kysymykset;
    public Scanner input = new Scanner(System.in);

/**
 * Luokan konstruktori.
 * 
 * Riko attribuutin avulla rikotaan silmukka, jos antaa kaksi väärä  vastausta.
 * Oikeat attribuuti pitää kirjaa siitä, kuinka montaa oikeaa vastausta on antanut kysymysarjan sisällä.
 * Helpot attribuutit pitää kirjaa siitä, kuinka monta helppoa kysymystä sai oikein.
 * Keskivaikeat tekee saman keskivaikeille kysymyksille.
 * Vaikeat tekee saman vaikeille kysymyksille.
 * Pisteet attribuutti pitää kirjaa pisteistä kysymyssarjan sisällä.
 * helpotPisteet attribuutti pitää kirjaa niistä pisteistä, joita sai helpoista kysymyksistä.
 * keskivaikeatPisteet attribuutti pitää kirjaa niistä pisteistä, joita sai keskivaikeista kysymyksistä.
 * vaikeatPisteet attribuutti pitää kirjaa niistä pisteistä, joita sai vaikeista kysymykstistä. 
 * Kysymys attribuutti on Kysymysgeneraattori olio.
 * Viimeisellä rivillä luodaan kysymykset ja vihjeet. 
 */    
    
    public Peli() {
        riko = 0;
        oikeat = 0;
        helpot = 0;
        keskivaikeat = 0;
        vaikeat = 0;
        pisteet = 0;
        helpotPisteet = 0;
        keskivaikeatPisteet = 0;
        vaikeatPisteet = 0;
        kokonaispisteet = 0;
        kysymykset = new Kysymysgeneraattori();
        kysymykset.luoKaikkiKysymykset();
    }
    
    /**
     * Tämä metodi aloittaa pelin. Ensin kysytään helpot ja sitten vaikeat.
     * 
     * Metodi myös ilmottaa pisteet pelin lopussa. 
     */

    public void aloitaPeli() {
        System.out.println("");
        System.out.println("Ohjelma antaa alkuaineen kemiallisen symbolin. Kirjoita symbolia vastaavan aineen nimi.");
        System.out.println("");
        kysyHelpot();
        if (helpot == 11) {
            System.out.println("Siirrytään vaikeampiin!");
            System.out.println("");
            kysyKeskivaikeat();
        }
        if (keskivaikeat == 46) {
            System.out.println("Siirrytään vaikeampiin!");
            System.out.println("");
            kysyVaikeat();
        }
        if (vaikeat == 54) {
            System.out.println("");
            System.out.println("Kaikki oikein!");
            System.out.println("Ratkaisit pelin");
        }
        System.out.println("");
        kokonaispisteet = helpotPisteet + keskivaikeatPisteet + vaikeatPisteet;
        System.out.println("Pisteesti ovat: " + kokonaispisteet);
    }
    
    /**
     * Metodi, jonka avulla ohjelma kysyy kysymykset.
     * 
     * Metodi myös antaa vihjeet ja oikean vastauksen, jos vastaa kahdesti väärin. 
     * @param aine Parametri aine on Alkuaine olio. 
     */

    public void kysyKysymys(Alkuaine aine) {
        int laskuri = 0;
        System.out.println(aine.symboli);
        System.out.print("Anna vastaus: ");
        String vastaus = input.nextLine();
        if (vastaus.equals(aine.nimi)) {
            System.out.println("Oikein!");
            System.out.println("");
            pisteet = pisteet + 2;
            oikeat = oikeat + 1;
        } else {
            System.out.println("Väärin!");
            laskuri = laskuri + 1;
        }
        if (laskuri == 1) {
            System.out.println("Vihje: " + aine.vihje);
            System.out.print("Yritä uudestaan: ");
            String vastaus2 = input.nextLine();
            if (vastaus2.equals(aine.nimi)) {
                System.out.println("Oikein!");
                System.out.println("");
                pisteet = pisteet + 1;
                oikeat = oikeat + 1;
            } else {
                System.out.println("Väärin!");
                laskuri = laskuri + 1;
            }
        }
        if (laskuri > 1) {
            riko = 1;
            System.out.println("");
            System.out.println("Game Over!");
            System.out.println("");
            System.out.println("Oikea vastaus olisi ollut '" + aine.nimi + "'");
        }
    }
    
    /**
     * Metodi, jonka avulla ohjelma kysyy helpot kysymykset.
     * 
     * Pitää myös kirjaa oikeista vastauksista ja pisteistä.
     */
    
    public void kysyHelpot() {
        pisteet = 0;
        oikeat = 0;
        for (Alkuaine nimi : kysymykset.helpotKysymykset) {
            kysyKysymys(nimi);
            if (riko == 1) {
                break;
            }
        }
        helpotPisteet = pisteet;
        helpot = oikeat;
    }
    
    /**
     * Metodi, jonka avulla ohjelma kysyy keskivaikeat kysymykset.
     * 
     * Pitää myös kirjaa oikeista vastauksista ja pisteistä.
     */
    
    public void kysyKeskivaikeat() {
        pisteet = 0;
        oikeat = 0;
        for (Alkuaine nimi : kysymykset.keskivaikeatKysymykset) {
            kysyKysymys(nimi);
            if (riko == 1) {
                break;
            }
        }
        keskivaikeatPisteet = 2 * pisteet;
        keskivaikeat = oikeat;
    }
    
    /**
     * Metodi, jonka avulla ohjelma kysyy vaikeat kysymykset.
     * 
     * Pitää myös kirjaa oikeista vastauksista ja pisteistä.
     */
    
    public void kysyVaikeat() {
        pisteet = 0;
        oikeat = 0;
        for (Alkuaine nimi : kysymykset.vaikeatKysymykset) {
            kysyKysymys(nimi);
            if (riko == 1) {
                break;
            }
        }
        vaikeatPisteet = 4 * pisteet;
        vaikeat = oikeat;
    }
}
