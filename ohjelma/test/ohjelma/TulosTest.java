
package ohjelma;

import java.util.ArrayList;
import java.util.Collections;
import org.junit.*;
import static org.junit.Assert.*;


public class TulosTest {
     public Tulos eka;
     public Tulos toka;
     public Tulos kolmas;
     public Tulos neljäs;
     public Tulos viides;
     public ArrayList<Tulos> lista;
    
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
        eka = new Tulos("Mooses", 100);
        toka = new Tulos("Elia", 150);
        kolmas = new Tulos("Hesekiel", 100);
        neljäs = new Tulos("Eli", 50);
        viides = new Tulos ("Eli", 50);
        lista = new ArrayList<Tulos>();
        lista.add(eka);
        lista.add(toka);
        lista.add(kolmas);
        lista.add(neljäs);
        lista.add(viides);
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testaaKonstruktori() {
        int testiluku = 0;
        if (eka.nimi.equals("Mooses") && eka.pisteet == 100) {
            testiluku = testiluku + 1;
        }
        if (toka.nimi.equals("Elia") && toka.pisteet == 150) {
            testiluku = testiluku + 1;
        }
        assertEquals(2, testiluku, 0.0);
        
    }
    
    @Test
    public void samaNimisamatPisteet() {
        assertEquals(0, neljäs.compareTo(viides));
    }
    
    @Test
    public void testääJärjestys() {
        int testiluku = 0;
        Collections.sort(lista);
        if (lista.get(0).nimi.equals("Elia") && lista.get(1).nimi.equals("Hesekiel") && 
            lista.get(2).nimi.equals("Mooses") && lista.get(3).nimi.equals("Eli")
            && lista.get(4).nimi.equals("Eli")) {
            testiluku = 1;
        }
        assertEquals(1, testiluku);
    }
}
