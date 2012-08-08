
package ohjelma;

import java.util.Scanner;

public class Päävalikko {
    
    public Scanner input = new Scanner(System.in);
    
    public void valinta() {
        System.out.println("Tervetuloa Symbolipeliin");
        System.out.println("Kirjoita 'pelaa', jos haluat pelata.");
        System.out.println("Kirjoita 'pistelista', jos haluat tarkistaa pistelistan.");
        System.out.println("Kirjoita 'lopeta', jos haluta lopettaa ohjelman.");
        String vastaus = input.nextLine();
    }
    
    
}
