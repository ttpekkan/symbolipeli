package ohjelma;

import java.io.File;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.*;
import static org.junit.Assert.*;
import org.junit.*;

public class Peli_ikkunaTest {

    private Peli_ikkuna testi;
    private Field kenttäA;
    private Field kenttäB;
    private Field kenttäC;
    private Field kenttäD;
    private Field kenttäE;
    private Field kenttäF;
    private Field kenttäH;
    private Field kenttäI;
    private Field kenttäJ;
    private Field kenttäK;
    private Field kenttäL;
    private JLabel moneskokysymysMenossa;
    private JLabel vihje;
    private JLabel symboli;
    private JLabel pinnat;
    private JFrame ikkuna;
    private int moneskoKysymys;
    private int aika;
    private HaePelinArvoja peli;
    private JButton ok;
    private JButton sulje;
    private JTextField vastaus;
    private JTextField nimikenttä;
    private IlmoitaTappio_Ikkuna häviö;
    private IlmoitaVoitto_Ikkuna voitto;

    public Peli_ikkunaTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
        try {
            testi = new Peli_ikkuna();
            ikkuna = new JFrame();
            testi.run(ikkuna);
            kenttäA = Peli_ikkuna.class.getDeclaredField("moneskoKysymysMenossa");
            kenttäA.setAccessible(true);
            moneskokysymysMenossa = (JLabel) kenttäA.get(testi);
            moneskoKysymys = Character.getNumericValue(moneskokysymysMenossa.getText().charAt(moneskokysymysMenossa.getText().length() - 1));
            kenttäB = Peli_ikkuna.class.getDeclaredField("vihje");
            kenttäB.setAccessible(true);
            vihje = null;
            kenttäC = Peli_ikkuna.class.getDeclaredField("peli");
            kenttäC.setAccessible(true);
            peli = (HaePelinArvoja) kenttäC.get(testi);
            kenttäD = Peli_ikkuna.class.getDeclaredField("symboli");
            kenttäD.setAccessible(true);
            symboli = (JLabel) kenttäD.get(testi);
            kenttäE = Peli_ikkuna.class.getDeclaredField("ok_nappula");
            kenttäE.setAccessible(true);
            ok = (JButton) kenttäE.get(testi);
            kenttäF = Peli_ikkuna.class.getDeclaredField("vastauskenttä");
            kenttäF.setAccessible(true);
            vastaus = (JTextField) kenttäF.get(testi);
            kenttäH = Peli_ikkuna.class.getDeclaredField("pistetilanne");
            kenttäH.setAccessible(true);
            pinnat = (JLabel) kenttäH.get(testi);
            kenttäI = Peli_ikkuna.class.getDeclaredField("aika");
            kenttäI.setAccessible(true);
            aika = (int) kenttäI.getInt(testi);
            kenttäJ = Peli_ikkuna.class.getDeclaredField("hävisit");
            kenttäJ.setAccessible(true);
            kenttäK = Peli_ikkuna.class.getDeclaredField("voitit");
            kenttäK.setAccessible(true);
            kenttäL = Peli_ikkuna.class.getDeclaredField("sulje");
            kenttäL.setAccessible(true);
            sulje = (JButton) kenttäL.get(testi);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @After
    public void tearDown() {
        Musiikkikirjasto.pysäytäPelilaulu();
        Musiikkikirjasto.jatkuvaToistoPäävalikkolaulu();
        testi.sulje();
        ikkuna.dispose();
    }

    @Test
    public void testaaAloitusJaLäpäisyVihjeidenKanssa() {
        try {
            assertEquals(symboli.getText(), "------------->Hg");
            assertEquals(moneskoKysymys, 0);
            ok.doClick();
            symboli = (JLabel) kenttäD.get(testi);
            assertFalse(symboli.getText().equals("------------->Hg"));
            moneskokysymysMenossa = (JLabel) kenttäA.get(testi);
            moneskoKysymys = Character.getNumericValue(moneskokysymysMenossa.getText().charAt(moneskokysymysMenossa.getText().length() - 1));
            assertEquals(moneskoKysymys, 1);
            for (int i = 0; i < 111; i = i + 1) {
                vihje = (JLabel) kenttäB.get(testi);
                if (i == 11) {
                    if (vihje.getText().equals("Debye: Edellinen Oikein! Siirrytään keskivaikeisiin.")) {
                        assertEquals(0, 0);
                    } else if (vihje.getText().equals("Lewis: Edellinen Oikein! Siirrytään keskivaikeisiin.")) {
                        assertEquals(0, 0);
                    } else if (vihje.getText().equals("Arrhenius: Edellinen Oikein! Siirrytään keskivaikeisiin.")) {
                        assertEquals(0, 0);
                    } else if (vihje.getText().equals("Pauling: Edellinen Oikein! Siirrytään keskivaikeisiin.")) {
                        assertEquals(0, 0);
                    } else {
                        fail("Vihje luultavasti väärin");
                    }
                }
                if (i == 57) {
                    if (vihje.getText().equals("Debye: Siirrytään vaikeisiin.")) {
                        assertEquals(0, 0);
                    } else if (vihje.getText().equals("Lewis: Siirrytään vaikeisiin.")) {
                        assertEquals(0, 0);
                    } else if (vihje.getText().equals("Arrhenius: Siirrytään vaikeisiin.")) {
                        assertEquals(0, 0);
                    } else if (vihje.getText().equals("Pauling: Siirrytään vaikeisiin.")) {
                        assertEquals(0, 0);
                    } else {
                        fail("Vihje luultavasti väärin");
                    }
                }
                vastaus.setText("Kryptoniitti");
                ok.doClick();
                vastaus.setText(peli.palautaOikeaVastaus());
                ok.doClick();
            }
            String pisteet = pinnat.getText();
            assertEquals(pisteet, "Pisteet: " + 265);
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    @Test
    public void HäviöIkkunanToiminta() {
        try {
            ok.doClick();
            ok.doClick();
            ok.doClick();
            häviö = (IlmoitaTappio_Ikkuna) kenttäJ.get(testi);
            if (häviö == null) {
                fail("Voitto ei toimi!");
            }
            if (peli.pääseeListalle() == true) {
                fail("Pistelistassa häikkää!");
            }
            Field kenttä = IlmoitaTappio_Ikkuna.class.getDeclaredField("nimikenttä");
            kenttä.setAccessible(true);
            if ((JTextField) kenttä.get(häviö) != null) {
                fail("Häviöikkunassa häikkää!");
            }
            assertEquals(häviö.palautaNimikentänNimi(), "");
            sulje.doClick();
        } catch (Exception e) {
            System.out.println(e);
            fail("Virhe!");
        }
    }

    @Test
    public void häviöIkkunanPisteenlisäys() {
        try {
            ok.doClick();
            ok.doClick();
            Field kenttä2 = Peli.class.getDeclaredField("pelaajanPisteet");
            kenttä2.setAccessible(true);
            kenttä2.setInt(peli, 600);
            ok.doClick();
            häviö = (IlmoitaTappio_Ikkuna) kenttäJ.get(testi);
            Field kenttä = IlmoitaTappio_Ikkuna.class.getDeclaredField("nimikenttä");
            kenttä.setAccessible(true);
            nimikenttä = (JTextField) kenttä.get(häviö);
            nimikenttä.setText("Julius Ankanpää");
            assertEquals(nimikenttä.getText(), häviö.palautaNimikentänNimi());
            testi.lisääNimi(nimikenttä.getText(), "src/testi.txt");
            testaaNimenLisäys(600);
        } catch (Exception e) {
            System.out.println(e);
            fail("Virhe!");
        }
    }

    @Test
    public void testaaMaxPisteetJaNimenLisäys() {
        try {
            ok.doClick();
            for (int i = 0; i < 111; i = i + 1) {
                vastaus.setText(peli.palautaOikeaVastaus());
                ok.doClick();
            }
            String pisteet = pinnat.getText();
            assertEquals(pisteet, "Pisteet: " + 530);
            voitto = (IlmoitaVoitto_Ikkuna) kenttäK.get(testi);
            if (voitto == null) {
                fail("Voitto ei toimi!");
            }
            if (peli.pääseeListalle() == false) {
                fail("Pistelistassa häikkää!");
            }
            Field kenttä = IlmoitaVoitto_Ikkuna.class.getDeclaredField("nimikenttä");
            kenttä.setAccessible(true);
            nimikenttä = (JTextField) kenttä.get(voitto);
            nimikenttä.setText("Julius Ankanpää");
            assertEquals(nimikenttä.getText(), voitto.palautaNimikentänNimi());
            testi.lisääNimi(nimikenttä.getText(), "src/testi.txt");
            testaaNimenLisäys(530);
        } catch (Exception e) {
            System.out.println(e);
            fail("Virhe!");
        }
    }

    @Test
    public void testaaAjanToiminen() {
        try {
            Field kenttä5 = Peli_ikkuna.class.getDeclaredField("hävisit");
            kenttä5.setAccessible(true);
            assertEquals(15, aika);
            vihje = (JLabel) kenttäB.get(testi);
            String neuvo = vihje.getText();
            ok.doClick();
            Thread.sleep(5000);
            aika = (int) kenttäI.getInt(testi);
            assertEquals(10, aika, 1);
            vastaus.setText(peli.palautaOikeaVastaus());
            ok.doClick();
            aika = (int) kenttäI.getInt(testi);
            assertEquals(15, aika);
            Thread.sleep(17000);
            aika = (int) kenttäI.getInt(testi);
            assertEquals(15, aika, 2);
            if (peli.annaVihje().equals(neuvo)) {
                fail("Vihje väärin!");
            }
            Thread.sleep(17000);
            häviö = (IlmoitaTappio_Ikkuna) kenttäJ.get(testi);
            aika = (int) kenttäI.getInt(testi);
            assertEquals(0, aika);
            if (häviö == null) {
                fail("Häviöikkuna ei toimi");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void testaaNimenLisäys(int pisteet) {
        try {
            ArrayList<Pelitulos> lista = new ArrayList<Pelitulos>();
            Scanner lukija = new Scanner(new File("src/testi.txt"));
            while (lukija.hasNextLine()) {
                lista.add(new Pelitulos(lukija.nextLine(), Integer.parseInt(lukija.nextLine())));
            }
            assertEquals("Julius Ankanpää", lista.get(0).palautaNimi());
            assertEquals(pisteet, lista.get(0).palautaPisteet());
            PrintWriter kirjoittaja = new PrintWriter(new File("src/testi.txt"));
            for (int i = 0; i < lista.size(); i = i + 1) {
                kirjoittaja.println("Herttainen Herttua");
                kirjoittaja.println(0);
            }
            kirjoittaja.close();
        } catch (Exception e) {
            System.out.println(e);
            fail("Tiedostoon kirjoittaminen!");
        }
    }
}
