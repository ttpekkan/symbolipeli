package ohjelma;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import javax.swing.*;
import static org.junit.Assert.assertEquals;
import org.junit.*;

public class MuokkaaKomponenttiaTest {

    public MuokkaaKomponenttiaTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testataanIkkunanLuontiaJaKomponenttienLisäämistä() {
        JFrame ikkuna = new JFrame();
        MuokkaaKomponenttia.luoContentPaneKuvasta("taustakuva.png", ikkuna);
        Container pohja = ikkuna.getContentPane();
        JButton aloita = new JButton("Aloita Peli");
        MuokkaaKomponenttia.muokkaaJButtonia(aloita, 46, 450, 200, 50, Color.white, true, Color.green.darker(), false, true, true, 20, Color.green.darker(), pohja);
        JButton pistelista = new JButton("Pistelista");
        MuokkaaKomponenttia.muokkaaJButtonia(pistelista, 296, 450, 200, 50, Color.white, true, Color.green.darker(), false, true, true, 20, Color.green.darker(), pohja);
        JButton lopeta = new JButton("Lopeta");
        MuokkaaKomponenttia.muokkaaJButtonia(lopeta, 546, 450, 200, 50, Color.white, true, Color.green.darker(), false, true, true, 20, Color.green.darker(), pohja);
        MuokkaaKomponenttia.suoritaNeIkkunanavausToiminnotJotkaAinaSamat(ikkuna);
        JLabel pistetilanne = new JLabel("Kysymys: " + 0);
        MuokkaaKomponenttia.muokkaaJLabelia(pistetilanne, 805, 360, 140, 30, false, Color.GRAY, Color.white, 20, pohja);
        JLabel vaikeusaste = new JLabel("Vaikeusaste: helppo");
        MuokkaaKomponenttia.muokkaaJLabelia(vaikeusaste, 805, 326, 290, 30, false, Color.GRAY, Color.white, 20, pohja);
        JTextField vastauskenttä = new JTextField();
        MuokkaaKomponenttia.muokkaaJTextFieldiä(vastauskenttä, 375, 615, 300, 40, 24, "Aloitus: Paina OK", pohja);
        assertEquals(pohja.getWidth(), 799, 30);
        assertEquals(pohja.getHeight(), 694, 30);
        Component[] komponentit = pohja.getComponents();
        assertEquals(komponentit.length, 6);
    }

    @Test
    public void testataanJDialoginLuontiaJaKomponenttienLisäämistä() {
        JDialog dialog = new JDialog();
        MuokkaaKomponenttia.luoDialoginContentPaneKuvasta("voitto.png", dialog);
        Container pohja = dialog.getContentPane();
        JLabel ilmoitaPisteet = new JLabel("Pisteesi: ");
        MuokkaaKomponenttia.muokkaaJLabelia(ilmoitaPisteet, 350, 240, 300, 40, false, Color.red, Color.white, 24, pohja);
        JLabel voitto = new JLabel("Voitit Pelin!");
        MuokkaaKomponenttia.muokkaaJLabelia(voitto, 300, 200, 400, 45, false, Color.red, Color.white, 40, pohja);
        JButton suljePeliNappula = new JButton("Lopeta Peli");
        MuokkaaKomponenttia.muokkaaJButtonia(suljePeliNappula, 340, 590, 180, 50, Color.white, true, Color.blue, false,
                true, true, 16, Color.blue, pohja);
        JLabel nimi = new JLabel("Kirjoita nimesi tekstikenttään, jos haluat liittyä kemistien eliittiin.");
        MuokkaaKomponenttia.muokkaaJLabelia(nimi, 75, 290, 700, 25, false, Color.red, Color.white, 18, pohja);
        JTextField nimikenttä = new JTextField();
        MuokkaaKomponenttia.muokkaaJTextFieldiä(nimikenttä, 285, 340, 300, 40, 24, "", pohja);
        MuokkaaKomponenttia.suoritaNeDialoginavausToiminnotJotkaAinaSamat(dialog);
        assertEquals(pohja.getHeight(), 680, 30);
        assertEquals(pohja.getWidth(), 850, 30);
        Component[] komponentit = pohja.getComponents();
        assertEquals(komponentit.length, 5);
    }
}
