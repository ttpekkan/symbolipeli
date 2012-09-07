/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ohjelma;

import org.junit.*;
import static org.junit.Assert.*;

public class SymbolipeliTest {

    public SymbolipeliTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testaaPelilogiikanToimintaIlmanVihjeitä() {
        Symbolipeli testi = new Symbolipeli();
        testi.syötäPeliinVastaus("Aloita");
        for (int i = 0; i < 111; i = i + 1) {
            testi.syötäPeliinVastaus(testi.palautaAlkuaineenOikeaVastaus());
        }
        assertEquals(530, testi.palautaPelaajanPisteet());
        assertEquals(111, testi.palautaKysymysnumero());
        assertEquals(true, testi.pääseeköPelitulosListalle());
    }

    @Test
    public void testaaPelilogiikanToimintaVihjeidenKanssa() {
        Symbolipeli testi = new Symbolipeli();
        testi.syötäPeliinVastaus("Aloita");
        for (int i = 0; i < 111; i = i + 1) {
            testi.syötäPeliinVastaus("Nyt meni porvooseen.");
            testi.syötäPeliinVastaus(testi.palautaAlkuaineenOikeaVastaus());
        }
        assertEquals(265, testi.palautaPelaajanPisteet());
        assertEquals(111, testi.palautaKysymysnumero());
        assertEquals(true, testi.pääseeköPelitulosListalle());
    }

    @Test
    public void testaaKaksiVäärääVastausta() {
        Symbolipeli testi = new Symbolipeli();
        testi.syötäPeliinVastaus("Aloita");
        for (int i = 0; i < 111; i = i + 1) {
            if (i < 50) {
                testi.syötäPeliinVastaus(testi.palautaAlkuaineenOikeaVastaus());
            } else {
                testi.syötäPeliinVastaus("Nyt meni porvooseen.");
            }
        }
        assertEquals(178, testi.palautaPelaajanPisteet());
        assertEquals(51, testi.palautaKysymysnumero());
    }
}
