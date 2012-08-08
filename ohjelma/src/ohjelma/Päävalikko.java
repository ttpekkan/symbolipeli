
package ohjelma;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * Pelin päävalikko.
 * @author timo
 */

public class Päävalikko {
    public Musiikkikirjasto musa;
    public int pisteet;
    public ArrayList<Tulos> pistelista;
  
    public Scanner input = new Scanner(System.in);
    
    public Päävalikko() {
        musa = new Musiikkikirjasto();
        musa.aloitaTaustaMusa();
        pisteet = 0;
        pistelista = new ArrayList<Tulos>();
        try {
            Scanner lukija = new Scanner(new File("src/top10.txt"));
            while (lukija.hasNextLine()) {
                pistelista.add(new Tulos(lukija.nextLine(), Integer.parseInt(lukija.nextLine())));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        Collections.sort(pistelista);
    }
    
    /**
     * Pelaaja valitsee, mitä haluaa tehdä. 
     */
        
    public void valinta() {        
        System.out.println("Tervetuloa Symbolipeliin");
        System.out.println("Kirjoita 'pelaa', jos haluat pelata.");
        System.out.println("Kirjoita 'pistelista', jos haluat tarkistaa pistelistan.");
        System.out.println("Muuten lopetetaan.");
        System.out.print("Valinta: ");
        String vastaus = input.nextLine();
        if (vastaus.equals("pelaa")) {
            aloitaPeli();
        } else if (vastaus.equals("pistelista")) {
            tulostaPistelista();
            musa.päävalikkoMusa.stop();
            System.exit(0);
        } else {
            System.out.println("Lopetit ohjelman.");
            System.exit(0);
        }
    }
    
    /**
     * Aloittaa uuden pelin.
     */
    
    public void aloitaPeli() {
        musa.päävalikkoMusa.stop();
        musa.uusiPeli();
        Peli uusi = new Peli();
        uusi.aloitaPeli();
        musa.pelimusa.stop();
        if (uusi.vaikeat == 54) {
            musa.voitto();
        } else {
            musa.häviö();
        }
        pisteet = uusi.kokonaispisteet;
        tulostaRankki();
        if (paaseeListalle(pisteet) == true) {
            System.out.print("Anna nimesi: ");
            String nimimerkki = input.nextLine();
            lisaaTulos(nimimerkki, pisteet);
            tallennaPistelista();
        }
        System.exit(0);
    }
    
    public void tulostaRankki() {
        if (pisteet < 10) {
            System.out.println("Olet vasta aloittelija.");
        } else if (pisteet < 30) {
            System.out.println("Osaat jo jotakin, mutta olet vielä noviisi."); 
        } else if (pisteet < 100) {
            System.out.println("Olet wannebe-kemisti.");
        } else if (pisteet < 300) {
            System.out.println("Olet jo melkein kemisti.");
        } else if (pisteet < 500) {
            System.out.println("Olet melkoinen fakiiri.");
        } else if (pisteet < 638) {
            System.out.println("Olet kemiaguru!");
        } else {
            System.out.println("Olet täydellinen kemisti!!!!");
        }
    }
    
    public void tulostaPistelista() {
        for (int i = 0; i < 10; i = i + 1) {
            System.out.println(pistelista.get(i).nimi + "  " + pistelista.get(i).pisteet);
        }
    }
    
   public boolean paaseeListalle(int pisteet) {
       if (pisteet > pistelista.get(pistelista.size() - 1).pisteet) {
           return true;
       } else {
           return false;
       }
   }
   
   public void lisaaTulos(String nimi, int pisteet) {
        pistelista.remove(pistelista.size() - 1);
        pistelista.add(new Tulos(nimi, pisteet));
        Collections.sort(pistelista);
   }
   
   public void tallennaPistelista() {
       try {
           PrintWriter kirjoittaja = new PrintWriter(new File("src/top10.txt"));
           for (int i = 0; i < pistelista.size(); i = i + 1) {
               kirjoittaja.println(pistelista.get(i).nimi);
               kirjoittaja.println(pistelista.get(i).pisteet);
           }
           kirjoittaja.close();
       } catch (Exception e) {
           System.out.println("Virhe!");
       }
   }
    
    
}
