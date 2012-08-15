
package ohjelma;

import org.junit.*;
import static org.junit.Assert.*;

public class TulosTest {
    public Tulos eka;
    public Tulos toka;
    public Tulos kolmas;
    public Tulos neljäs;
    public Tulos viides;
    
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
        eka = new Tulos("Jesaja", 200);
        toka = new Tulos("Jeremia", 150);
        kolmas = new Tulos("Hesekiel", 150);
        neljäs = new Tulos("Daniel", 100);
        viides = new Tulos("Daniel", 100);
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
