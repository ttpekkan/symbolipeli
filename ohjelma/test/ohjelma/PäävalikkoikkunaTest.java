/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ohjelma;

import java.awt.Component;
import java.lang.reflect.Field;
import javax.swing.JButton;
import javax.swing.JFrame;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.*;

/**
 *
 * @author timo
 */
public class PäävalikkoikkunaTest {

    public Päävalikkoikkuna testi;
    public Pistelistaikkuna testilista;
    public Peli_ikkuna testipeli;

    public PäävalikkoikkunaTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
        testi = new Päävalikkoikkuna();
        testi.run();
        testilista = new Pistelistaikkuna();
        testilista.run();
        testilista.sulje();
        testipeli = new Peli_ikkuna();
        testipeli.run();
        testipeli.sulje();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testaaKomponenttienmäärä() {
        try {
            Field kenttä = Päävalikkoikkuna.class.getDeclaredField("päävalikkoikkuna");
            kenttä.setAccessible(true);
            JFrame testikkuna = (JFrame) kenttä.get(testi);
            Component[] komps = testikkuna.getContentPane().getComponents();
            assertEquals(komps.length, 3);
        } catch (Exception e) {
            System.out.println(e);
            fail("");
        }
    }

    @Test
    public void testaaPistelista() {
        try {
            Field kenttä1 = Pistelistaikkuna.class.getDeclaredField("laskuri");
            kenttä1.setAccessible(true);
            assertEquals(0, kenttä1.getInt(testilista));           
            Field kenttä2 = Päävalikkoikkuna.class.getDeclaredField("pistelista");
            kenttä2.setAccessible(true);
            JButton testnappula = (JButton) kenttä2.get(testi);
            testnappula.doClick();
            try {
                Thread.sleep(1000);
                Pistelistaikkuna testilistaB = new Pistelistaikkuna();
                if (kenttä1.getInt(testilista) != 1) {
                    System.out.println(kenttä1.getInt(testilista));
                    fail("Ei pitäisi pystyä luomaan uutta ikkunaa");
                }
                Thread.sleep(5000);
                testilista.sulje();
            } catch (Exception e) {
                System.out.println(e);
                fail("");
            }

        } catch (Exception e) {
            System.out.println(e);
            fail("Kenttää ei onnistuttu tekemään.");
        }
    }

    @Test
    public void testaaPelinAloitus() {
        try {
            Field kenttä = Peli_ikkuna.class.getDeclaredField("laskuri");
            kenttä.setAccessible(true);
            assertEquals(0, kenttä.getInt(testipeli));
            Field kenttä2 = Päävalikkoikkuna.class.getDeclaredField("aloita");
            kenttä2.setAccessible(true);
            JButton testnappula = (JButton) kenttä2.get(testi);
            testnappula.doClick();
            try {
                Thread.sleep(1500);
                assertEquals(kenttä.getInt(testipeli), 1);
                testipeli.sulje();
                Thread.sleep(1500);
            } catch (Exception e) {
                System.out.println(e);
                fail("");
            }
        } catch (Exception e) {
            System.out.println(e);
            fail("");
        }
    }
}