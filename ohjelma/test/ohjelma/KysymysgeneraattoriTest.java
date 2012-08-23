package ohjelma;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import static org.junit.Assert.assertEquals;
import org.junit.*;

public class KysymysgeneraattoriTest {

    public Kysymysgeneraattori testi;
    public ArrayList<String> vertauslista;
    public ArrayList<Alkuaine> vertaus;

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
        vertauslista = new ArrayList<String>();
        lataaKysymykset("helpotkysymykset.txt");
        lataaKysymykset("keskivaikeatkysymykset.txt");
        lataaKysymykset("vaikeatkysymykset.txt");
        vertaus = new ArrayList<Alkuaine>();
        
    }

    @After
    public void tearDown() {
    }
    
    @Test
    public void testaaHelpot() {
        int luku = 0;
        for (int i = 0; i < 11; i = i + 1) {
            if (!vertauslista.contains(testi.palautaHelppoKysymys(i).aineenNimi())) {
                luku = luku + 1;
            }
            if (!vertauslista.contains(testi.palautaHelppoKysymys(i).aineenSymboli())) {
                luku = luku + 1;
            }
            if (!vertauslista.contains(testi.palautaHelppoKysymys(i).aineenVihje())) {
                luku = luku + 1;
            }
        }
        assertEquals(0, luku);
    }
    
    @Test
    public void testaaVaikeat() {
        int luku = 0;
        for (int i = 0; i < 11; i = i + 1) {
            if (!vertauslista.contains(testi.palautaVaikeaKysymys(i).aineenNimi())) {
                luku = luku + 1;
            }
            if (!vertauslista.contains(testi.palautaVaikeaKysymys(i).aineenSymboli())) {
                luku = luku + 1;
            }
            if (!vertauslista.contains(testi.palautaVaikeaKysymys(i).aineenVihje())) {
                luku = luku + 1;
            }
        }
        assertEquals(0, luku);
    }
    
    @Test
    public void testaaKeskivaikeat() {
        int luku = 0;
        for (int i = 0; i < 11; i = i + 1) {
            if (!vertauslista.contains(testi.palautaKeskivaikeaKysymys(i).aineenNimi())) {
                luku = luku + 1;
            }
            if (!vertauslista.contains(testi.palautaKeskivaikeaKysymys(i).aineenSymboli())) {
                luku = luku + 1;
            }
            if (!vertauslista.contains(testi.palautaKeskivaikeaKysymys(i).aineenVihje())) {
                luku = luku + 1;
            }
        }
        assertEquals(0, luku);
    }
   
    public void lataaKysymykset(String tiedosto) {
        try {
            Scanner lukija = new Scanner(new File("src/" + tiedosto));
            while(lukija.hasNextLine()) {
                vertauslista.add(lukija.nextLine());
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
