
package ohjelma;

import static org.junit.Assert.assertEquals;
import org.junit.*;

public class AlkuaineTest {
    public Alkuaine testi;
    
    public AlkuaineTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
        testi = new Alkuaine("Mansikka", "Ma", "Maksavat mansikoita.");
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testPalautaNimi() {
        assertEquals("Mansikka", testi.aineenNimi());
    }
    @Test
    public void testPalautaSymboli() {
      assertEquals("Ma", testi.aineenSymboli());
    }

    @Test
    public void testPalautaVihje() {
        assertEquals("Maksavat mansikoita.", testi.aineenVihje());
    }
}
