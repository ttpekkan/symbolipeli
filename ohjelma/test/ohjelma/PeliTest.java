
package ohjelma;

import org.junit.*;
import static org.junit.Assert.*;

public class PeliTest {
    public Peli testi;
    
    public PeliTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
        testi = new Peli();
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void konstruktorinTestaus() {
        assertEquals(0, testi.helpot);
        assertEquals(0, testi.helpotPisteet);
        assertEquals(0, testi.keskivaikeat);
        assertEquals(0, testi.keskivaikeatPisteet);
        assertEquals(0, testi.kokonaispisteet);
        assertEquals(0, testi.oikeat);
        assertEquals(0, testi.pisteet);
        assertEquals(0, testi.riko);
        assertEquals(0, testi.vaikeat);
        assertEquals(0, testi.vaikeatPisteet);
    }
    
    

   
}
