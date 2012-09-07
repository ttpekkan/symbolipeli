package ohjelma;

import java.awt.Component;
import java.awt.Container;
import java.io.File;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import static org.junit.Assert.assertEquals;
import org.junit.*;

public class PistelistaTest {

    public Pistelista lista;

    public PistelistaTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
        lista = new Pistelista();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void pääseeListalle() {
        for (int i = 0; i < 10; i = i + 1) {
            lista.lisääPelitulosPistelistaan("Katala Karhu", 564);
        }
        boolean totta = true;
        if (lista.pääseeköPelitulosListalle(-1000000000) == true) {
            totta = false;
        }
        assertEquals(totta, true);
        if (lista.pääseeköPelitulosListalle(1000000000) == true) {
            totta = true;
        }
        assertEquals(totta, true);
        lista.tallennaPistelista("src/testi.txt");
        File uusi = new File("src/testi.txt");
        if (!uusi.exists()) {
            assertEquals(0, 1);
        }
    }
    
    @Test
    public void JLabelit() {
        JFrame a = new JFrame();
        Container b = a.getContentPane();
        lista.rakennaPistelistanJLabelesitys(b);
        Component[] c = b.getComponents();
        a.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        a.setResizable(false);
        a.pack();
        a.setVisible(true);
        int luku = 0;
        if (c.length == 10) {
            luku = 1;
        }
        assertEquals(1, luku);
    }
    
}
