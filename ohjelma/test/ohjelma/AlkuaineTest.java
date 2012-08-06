
package ohjelma;

import java.util.ArrayList;
import org.junit.*;
import static org.junit.Assert.*;


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
        testi = new Alkuaine("vety", "H", "universumin yleisin alkuaine");
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void alkuaineTesti() {
              
        assertEquals("vety", testi.nimi);
        assertEquals("H", testi.symboli);
        assertEquals("universumin yleisin alkuaine", testi.vihje);
    }
}
