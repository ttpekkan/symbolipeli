
package ohjelma;

import java.util.ArrayList;
import org.junit.*;
import static org.junit.Assert.*;

public class KysymysgeneraattoriTest {
    
    public Kysymysgeneraattori testi;
    public ArrayList<String> lista;
    
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
        testi.lataaHelpotKysymykset();
        testi.lataaKeskivaikeatKysymykset();
        testi.lataaVaikeatKysymykset();
        lista = new ArrayList<String>();
        lista.add("Ac");
        lista.add("Al");
        lista.add("Am");
        lista.add("Sb");
        lista.add("Ar");
        lista.add("As");
        lista.add("At");
        lista.add("Ba");
        lista.add("Bk");
        lista.add("Be");
        lista.add("Bh");
        lista.add("B");
        lista.add("Br");
        lista.add("Ce");
        lista.add("Cs");
        lista.add("Cm");
        lista.add("Ds");
        lista.add("Db");
        lista.add("Dy");
        lista.add("Es");
        lista.add("Hg");
        lista.add("Er");
        lista.add("Eu");
        lista.add("Fm");
        lista.add("F");
        lista.add("P");
        lista.add("Fr");
        lista.add("Gd");
        lista.add("Ga");
        lista.add("Ge");
        lista.add("Hf");
        lista.add("O");
        lista.add("Hs");
        lista.add("He");
        lista.add("C");
        lista.add("Ho");
        lista.add("Ag");
        lista.add("In");
        lista.add("Ir");
        lista.add("I");
        lista.add("Cd");
        lista.add("Cf");
        lista.add("K");
        lista.add("Ca");
        lista.add("Cl");
        lista.add("Co");
        lista.add("Cr");
        lista.add("Kr");
        lista.add("Xe");
        lista.add("Au");
        lista.add("Cu");
        lista.add("La");
        lista.add("Lr");
        lista.add("Li");
        lista.add("Lu");
        lista.add("Pb");
        lista.add("Mg");
        lista.add("Mn");
        lista.add("Mt");
        lista.add("Md");
        lista.add("Mo");
        lista.add("Na");
        lista.add("Nd");
        lista.add("Ne");
        lista.add("Np");
        lista.add("Ni");
        lista.add("Nb");
        lista.add("No");
        lista.add("Os");
        lista.add("Pd");
        lista.add("Si");
        lista.add("Pt");
        lista.add("Pu");
        lista.add("Po");
        lista.add("Pr");
        lista.add("Pm");
        lista.add("Pa");
        lista.add("Ra");
        lista.add("Rn");
        lista.add("Fe");
        lista.add("Re");
        lista.add("S");
        lista.add("Rh");
        lista.add("Rb");
        lista.add("Ru");
        lista.add("Rf");
        lista.add("Rg");
        lista.add("Sm");
        lista.add("Sg");
        lista.add("Se");
        lista.add("Zn");
        lista.add("Sc");
        lista.add("Sr");
        lista.add("Tl");
        lista.add("Ta");
        lista.add("Tc");
        lista.add("Te");
        lista.add("Tb");
        lista.add("Sn");
        lista.add("Ti");
        lista.add("Th");
        lista.add("Tm");
        lista.add("N");
        lista.add("U");
        lista.add("V");
        lista.add("H");
        lista.add("Bi");
        lista.add("W");
        lista.add("Yb");
        lista.add("Y");
        lista.add("Zr");       
    }
    
    @After
    public void tearDown() {
    }
  
    @Test
    public void testLuoHelpotKysymykset() {
        int luku = 0;
        for (Alkuaine sana : testi.helpotKysymykset) {
            if (lista.contains(sana.symboli)) {
                continue;
            } else {
                luku = luku + 1;
            }
        }
        assertEquals(0, luku);
    }
 
    @Test
    public void testLuoKeskivaikeatKysymykset() {
        int luku = 0;
        for (Alkuaine sana : testi.keskivaikeatKysymykset) {
            if (lista.contains(sana.symboli)) {
                continue;
            } else {
                luku = luku + 1;
            }
        }
        assertEquals(0, luku);
    }

    @Test
    public void testLuoVaikeatKysymykset() {
        int luku = 0;
        for (Alkuaine sana : testi.vaikeatKysymykset) {
            if (lista.contains(sana.symboli)) {
                continue;
            } else {
                luku = luku + 1;
            }
        }
        assertEquals(0, luku);
    }
    
    @Test
    public void tarkistaMäärä() {
        assertEquals(lista.size(), ((testi.helpotKysymykset.size() + testi.keskivaikeatKysymykset.size() + testi.vaikeatKysymykset.size())));
    }
}
