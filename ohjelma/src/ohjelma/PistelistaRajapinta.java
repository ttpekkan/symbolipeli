package ohjelma;

import java.awt.Container;

/**
 * Rajapinta, jona kautta symbolipeli ja pistelistaikkuna kommunikoivat
 * pistelistan kanssa.
 *
 * Voi olla hyödyllinen, jos pistelistoja pitäisi jostain syystä olla useampia.
 *
 * @author Timo Pekkanen
 */
public interface PistelistaRajapinta {

    public boolean pääseeköPelitulosListalle(int pisteet);

    public void rakennaPistelistanJLabelesitys(Container pohja);

    public void lisääPelitulosPistelistaan(String nimi, int pisteet);

    public void tallennaPistelista(String osoite);
}
