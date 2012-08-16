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
    public void testaaLauluJaKuva() {
        int luku = 0;
        if (testi.palautaKuvannimi() == null) {
            luku = luku + 1;
        }
        assertEquals(0, luku);
    }
   
   
}
