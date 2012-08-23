
package ohjelma;

import static org.junit.Assert.assertEquals;
import org.junit.*;

public class TulosTest {
    public Pelitulos eka;
    public Pelitulos toka;
    public Pelitulos kolmas;
    public Pelitulos neljäs;
    public Pelitulos viides;
    
    public TulosTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
        eka = new Pelitulos("Jesaja", 200);
        toka = new Pelitulos("Jeremia", 150);
        kolmas = new Pelitulos("Hesekiel", 150);
        neljäs = new Pelitulos("Daniel", 100);
        viides = new Pelitulos("Daniel", 100);
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testPalautaPisteet() {
        assertEquals(200, eka.palautaPisteet());
    }

    @Test
    public void testPalautaNimi() {
        assertEquals("Hesekiel", kolmas.palautaNimi());
    }

    @Test
    public void testCompareTo() {
        assertEquals(1, toka.compareTo(eka));
        assertEquals(0, neljäs.compareTo(viides));
        assertEquals(-1, eka.compareTo(viides));
        assertEquals(-1, kolmas.compareTo(toka));
        assertEquals(1, toka.compareTo(eka));
        assertEquals(1, toka.compareTo(kolmas));
    }
}
