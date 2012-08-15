package ohjelma;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.net.URL;
import org.junit.*;
import static org.junit.Assert.*;

public class LauluJaKuvaTest {
    public LauluJaKuva testi;
    public AudioClip musa;
    
    public LauluJaKuvaTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
        musa = null;
        try {
            File currentDir = new File(".");
            URL currentDirURL = currentDir.toURL();
            URL url = new URL(currentDirURL, "src/win.wav");
            musa = Applet.newAudioClip(url);
        } catch (Exception e) {
            System.out.println(e);
        }
        testi = new LauluJaKuva(musa, "Pelle Hermanni");
        
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testPalautaLaulu() {
        assertEquals(musa, testi.palautaLaulu());
    }

    @Test
    public void testPalautaKuva() {
       assertEquals("Pelle Hermanni", testi.palautaKuva());
    }
}
