package ohjelma;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import org.junit.*;
import static org.junit.Assert.*;

public class MusiikkikirjastoTest {
    public Musiikkikirjasto testi;
    
    public MusiikkikirjastoTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
        testi = new Musiikkikirjasto();
    }
    
    @After
    public void tearDown() {
    }
   
    @Test
    public void testaaAloitusmusa() {
        int luku = 0;
        if (testi.palautaAloituslaulu() == null) {
            luku = 1;
        }
        assertEquals(0, luku);
    }
    
    @Test
    public void testaaVoittomusa() {
        int luku = 0;
        if (testi.palautaVoittolaulu() == null) {
            luku = 1;
        }
        assertEquals(0, luku);
    }
    
    @Test
    public void testaaHäviömusa() {
        int luku = 0;
        if (testi.palautaHäviölaulu() == null) {
            luku = 1;
        }
        assertEquals(0, luku);
    }
    
    @Test
    public void testaaPäävalikkomusa() {
        int luku = 0;
        if (testi.palautaPäävalikkolaulu() == null) {
            luku = 1;
        }
        assertEquals(0, luku);
    }
    
    @Test
    public void testaaPelimusa() {
        int luku = 0;
        if (testi.palautaPelilaulu() == null) {
            luku = 1;
        }
        assertEquals(0, luku);
    }
    
    @Test
    public void testaaLauluJaKuva() {
        int luku = 0;
        for (LauluJaKuva LK : testi.PalautaLauluJaKuvaLista()) {
            if (LK.palautaKuva() == null || LK.palautaLaulu() == null) {
                luku = luku + 1;
            }
        }
        assertEquals(0, luku);
        assertEquals(8, testi.PalautaLauluJaKuvaLista().size());
    }
   
   
}
