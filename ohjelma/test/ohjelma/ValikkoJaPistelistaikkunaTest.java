/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ohjelma;

import java.awt.Component;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.lang.reflect.Field;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import static org.junit.Assert.*;
import org.junit.*;

/**
 *
 * @author timo
 */
public class ValikkoJaPistelistaikkunaTest {
    
    public ValikkoJaPistelistaikkunaTest() {
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
    public void TestaaNappuloidenMääränJaToiminnan() {
        try {
            Robot robot = new Robot();
            JFrame ikkuna = new JFrame();       
            Valikkoikkuna pääohjelma = new Valikkoikkuna();
            pääohjelma.run(ikkuna);
            Field kenttä = Valikkoikkuna.class.getDeclaredField("päävalikkoikkuna");
            kenttä.setAccessible(true);
            JFrame testi = (JFrame) kenttä.get(pääohjelma);
            Component[] komponentit = testi.getContentPane().getComponents();
            assertEquals(komponentit.length, 3);
            assertEquals(testi.getContentPane().getHeight(), 694, 30);
            assertEquals(testi.getContentPane().getWidth(), 799, 30);
            Field pistelista = Valikkoikkuna.class.getDeclaredField("pistelista");
            pistelista.setAccessible(true);
            JButton nappula1 = (JButton) pistelista.get(pääohjelma);
            Field aloita = Valikkoikkuna.class.getDeclaredField("aloita");
            aloita.setAccessible(true);
            JButton nappula2 = (JButton) aloita.get(pääohjelma);
            assertEquals(0, MuokkaaKomponenttia.palautaLaskurinArvo());
            nappula1.doClick();
            assertEquals(1, MuokkaaKomponenttia.palautaLaskurinArvo());
            nappula1.doClick();
            nappula1.doClick();
            nappula2.doClick();
            assertEquals(testi.getContentPane().getHeight(), 694, 30);
            assertEquals(testi.getContentPane().getWidth(), 799, 30);
            assertEquals(1, MuokkaaKomponenttia.palautaLaskurinArvo());
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
            nappula2.doClick();       
            assertEquals(testi.getContentPane().getHeight(), 833, 30);
            assertEquals(testi.getContentPane().getWidth(), 1110, 30);
            komponentit = testi.getContentPane().getComponents();
            assertEquals(komponentit.length, 11);
            assertEquals(0, MuokkaaKomponenttia.palautaLaskurinArvo());
            ikkuna.dispose();
        } catch (Exception e) {
            System.out.println(e);
            fail("Virhe!");          
        }
    }
    
    @Test
    public void TestaaPistelista() {
        try {
            JFrame uusi = new JFrame();
            Pistelistaikkuna testi = new Pistelistaikkuna();
            testi.run(uusi);
            Field dialog = Pistelistaikkuna.class.getDeclaredField("pistelistaikkuna");
            dialog.setAccessible(true);
            JDialog testidialog = (JDialog) dialog.get(testi);
            Component[] komponentit = testidialog.getContentPane().getComponents();
            assertEquals(11, komponentit.length);
            testidialog.dispatchEvent(new WindowEvent(testidialog, WindowEvent.WINDOW_ICONIFIED));
            testidialog.dispatchEvent(new WindowEvent(testidialog, WindowEvent.WINDOW_DEICONIFIED));
            testidialog.dispatchEvent(new WindowEvent(testidialog, WindowEvent.WINDOW_CLOSING));
            assertEquals(false, testidialog.isVisible());
            testi.run(uusi);
            Field sulje = Pistelistaikkuna.class.getDeclaredField("sulje");
            sulje.setAccessible(true);
            JButton nappula = (JButton) sulje.get(testi);
            nappula.doClick();
            assertEquals(false, testidialog.isVisible());
        } catch (Exception e) {
            System.out.println(e);
            fail("Virhe!");          
        }
    }
}
