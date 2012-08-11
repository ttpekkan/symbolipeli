package ohjelma;

import java.io.FileNotFoundException;
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
    public void testaaKonstruktori() {
        assertEquals(0, testi.lista.size(), 0.0);
        assertEquals(0, testi.yksiBiisi.size());
    }

    @Test
    public void testaaTaustamusa() {
        testi.aloitaTaustaMusa();
        assertEquals(1, testi.yksiBiisi.size());
    }
    
    @Test
    public void testaaAloitusmusa() {
        testi.aloitusKlippi();
        assertEquals(1, testi.yksiBiisi.size());
    }
    
    @Test
    public void testaaVoitto() {
        testi.voitto();
        assertEquals(1, testi.yksiBiisi.size());
    }
    
    @Test
    public void testaaHäviö() {
        testi.häviö();
        assertEquals(1, testi.yksiBiisi.size());
    }
    
    @Test
    public void uusiPeli() {
        testi.uusiPeli();
        assertEquals(1, testi.yksiBiisi.size());
        assertEquals(8, testi.lista.size());
    }
    
    @Test
    public void testaalisääBiisi() {
        testi.lisääBiisi("win.wav");
        assertEquals(1, testi.lista.size());
    }
    
    @Test
    public void testaalisääBiisit() {
        testi.lisääBiisit();
        assertEquals(8, testi.lista.size());
}
}
