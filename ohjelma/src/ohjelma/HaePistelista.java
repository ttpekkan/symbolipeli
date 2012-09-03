package ohjelma;

import java.awt.Container;

/**
 * Rajapinta, jonka kautta luokat kommunikoivat pistelista luokan kanssa.
 *
 * Olisi hyödyllinen, jos pistelista-luokan tapaisia luokkia olisi useampia.
 *
 * @author Timo Pekkanen
 */
public interface HaePistelista {

    public boolean pääseeListalle(int pisteet);

    public void JLabelesitys(Container pohja);

    public void lisääTulos(String nimi, int pisteet);

    public void tallennaPistelista(String osoite);
}
