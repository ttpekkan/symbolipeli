package ohjelma;

import java.util.ArrayList;
import org.junit.*;
import static org.junit.Assert.*;

public class KysymysgeneraattoriTest {

    public Kysymysgeneraattori testi;

    public KysymysgeneraattoriTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
        testi = new Kysymysgeneraattori();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testPalautaHelpotKysymykset() {
        assertEquals(11, testi.palautaHelpotKysymykset().size());
    }

    @Test
    public void testPalautaKeskivaikeatKysymykset() {
        assertEquals(46, testi.palautaKeskivaikeatKysymykset().size());
    }

    @Test
    public void testPalautaVaikeatKysymykset() {
        assertEquals(54, testi.palautaVaikeatKysymykset().size());
    }
}
