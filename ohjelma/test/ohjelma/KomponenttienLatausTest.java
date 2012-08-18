
package ohjelma;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import javax.swing.*;
import org.junit.*;
import static org.junit.Assert.*;

public class KomponenttienLatausTest {
    public KomponenttienLataus testi;
    
    public KomponenttienLatausTest() {
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
    public void nappuloidenMäärä() {
        int luku = 0;
        JFrame a = new JFrame();
        testi = new KomponenttienLataus(a);
        testi.luoContentPaneKuvasta("taustakuva.png");
        Container b = a.getContentPane();
        JButton c = new JButton();
        testi.luoNappula(c, 5, 5, 5, 5, Color.white, true, Color.white, true, true, true, 4, Color.darkGray, b);
        JLabel d = new JLabel();
        testi.luoTeksti(d, 5, 5, 5, 5, true, Color.white, Color.white, 4, b);
        JTextField e = new JTextField();
        testi.luoTekstikenttä(e, 5, 5, 5, 5, 4, "fd", b);
        testi.neIkkunanavausToiminnotJotkaAinaSamat();
        Component[] f = b.getComponents();
        assertEquals(3, f.length);
        assertEquals(799, b.getWidth(), 10);
        assertEquals(694, b.getHeight(), 10);        
    }

}
