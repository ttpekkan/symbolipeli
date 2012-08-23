/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ohjelma;

import java.awt.Component;
import java.awt.event.WindowEvent;
import java.lang.reflect.Field;
import javax.swing.JButton;
import javax.swing.JFrame;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.*;

public class PistelistaikkunaTest {

    public Pistelistaikkuna testi;

    public PistelistaikkunaTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
        testi = new Pistelistaikkuna();
        testi.run();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testaaIkkunoidenMäärä() {
        try {
            Field kenttä = Pistelistaikkuna.class.getDeclaredField("laskuri");
            kenttä.setAccessible(true);
            Pistelistaikkuna testilistaB = new Pistelistaikkuna();
            Thread.sleep(1000);
            if (kenttä.getInt(testi) != 1) {
                System.out.println(kenttä.getInt(testi));
                fail("Ei pitäisi pystyä luomaan uutta ikkunaa");
            }             
            testi.sulje();
        } catch (Exception e) {
            System.out.println(e);
            fail("");
        }
    }
    
    @Test
    public void testaaKomponenttienMäärä() {
        try {
            Field kenttä = Pistelistaikkuna.class.getDeclaredField("pistelistaikkuna");
            kenttä.setAccessible(true);
            JFrame testikkuna = (JFrame) kenttä.get(testi);
            Component[] komps = testikkuna.getContentPane().getComponents();
            assertEquals(komps.length, 11);
            testikkuna.dispatchEvent(new WindowEvent(testikkuna, WindowEvent.WINDOW_ICONIFIED));
            testikkuna.dispatchEvent(new WindowEvent(testikkuna, WindowEvent.WINDOW_DEICONIFIED));
            testikkuna.dispatchEvent(new WindowEvent(testikkuna, WindowEvent.WINDOW_CLOSING));
        } catch (Exception e) {
            System.out.println(e);
            fail("");
        }
    }
    
    @Test
    public void testaaNappulanToiminta() {
        try {
            Field kenttä1 = Pistelistaikkuna.class.getDeclaredField("laskuri");
            kenttä1.setAccessible(true);
            assertEquals(1, kenttä1.getInt(testi));
            Field kenttä2 = Pistelistaikkuna.class.getDeclaredField("sulje");
            kenttä2.setAccessible(true);
            JButton testnappula = (JButton) kenttä2.get(testi);
            testnappula.doClick();
            assertEquals(0, kenttä1.getInt(testi));
        } catch (Exception e) {
            System.out.println(e);
            fail("");
        }
    }
}
