package ohjelma;

import java.awt.Component;
import java.awt.event.WindowEvent;
import java.lang.reflect.Field;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import static org.junit.Assert.*;
import org.junit.*;

public class Peli_ikkunaTest {
    public Peli_ikkuna testi;
    public Musiikkikirjasto musa;

    public Peli_ikkunaTest() {
        musa = new Musiikkikirjasto();
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
            Thread.sleep(2000);
            musa.pysäytäAloituslaulu();
            Thread.sleep(200);
            musa.pysäytäPelilaulu();
            Thread.sleep(200);
            musa.pysäytäPäävalikkolaulu();
            testi = new Peli_ikkuna();
            Thread.sleep(200);
            testi.run();
            Thread.sleep(2000);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testaaIkkunoidenMäärä() {
        try {
            Field kenttä = Peli_ikkuna.class.getDeclaredField("laskuri");
            kenttä.setAccessible(true);
            Peli_ikkuna testilistaB = new Peli_ikkuna();
            if (kenttä.getInt(testi) != 1) {
                System.out.println(kenttä.getInt(testi));
                fail("Ei pitäisi pystyä luomaan uutta ikkunaa");
            }
            testi.sulje();
        } catch (Exception e) {
            System.out.println(e);
            fail("");
        }
    }

    @Test
    public void testaaKomponenttienMäärä() {
        try {
            Field kenttä = Peli_ikkuna.class.getDeclaredField("peli_ikkuna");
            kenttä.setAccessible(true);
            JFrame testikkuna = (JFrame) kenttä.get(testi);
            Component[] komps = testikkuna.getContentPane().getComponents();
            assertEquals(komps.length, 11);
            testikkuna.dispatchEvent(new WindowEvent(testikkuna, WindowEvent.WINDOW_ICONIFIED));
            testikkuna.dispatchEvent(new WindowEvent(testikkuna, WindowEvent.WINDOW_DEICONIFIED));
            testikkuna.dispatchEvent(new WindowEvent(testikkuna, WindowEvent.WINDOW_CLOSING));
        } catch (Exception e) {
            System.out.println(e);
            fail("");
        }
    }

    @Test
    public void testaaSulkemisenToiminta() {
        try {
            Field kenttä1 = Peli_ikkuna.class.getDeclaredField("laskuri");
            kenttä1.setAccessible(true);
            assertEquals(1, kenttä1.getInt(testi));
            Field kenttä2 = Peli_ikkuna.class.getDeclaredField("sulje");
            kenttä2.setAccessible(true);
            JButton testnappula = (JButton) kenttä2.get(testi);
            testnappula.doClick();
            assertEquals(0, kenttä1.getInt(testi));
        } catch (Exception e) {
            System.out.println(e);
            fail("");
        }
    }

    @Test
    public void testaaEkanVastauksenToiminta() {
        try {
            Field kenttä1 = Peli_ikkuna.class.getDeclaredField("laskuri");
            kenttä1.setAccessible(true);
            assertEquals(1, kenttä1.getInt(testi));
            Field kenttä2 = Peli_ikkuna.class.getDeclaredField("ok_nappula");
            kenttä2.setAccessible(true);
            JButton testnappula = (JButton) kenttä2.get(testi);
            Field kenttä3 = Peli_ikkuna.class.getDeclaredField("vastauskenttä");
            kenttä3.setAccessible(true);
            JTextField vastaus = (JTextField) kenttä3.get(testi);
            vastaus.setText("!!pelaa!!");
            testnappula.doClick();
            assertEquals(0, kenttä1.getInt(testi));
            vastaus.setText("pelaa");
            testi.run();
            assertEquals(1, kenttä1.getInt(testi));
            testi.sulje();
        } catch (Exception e) {
            System.out.println(e);
            fail("");
        }
    }

    @Test
    public void testaaKysymyksetJaVihjeet() {
        int luku = 0;
        try {
            Field kenttäA = Peli_ikkuna.class.getDeclaredField("kysymysnumero");
            kenttäA.setAccessible(true);
            Field kenttäB = Peli_ikkuna.class.getDeclaredField("vihje");
            kenttäB.setAccessible(true);
            JLabel vihje = null;
            Field kenttäC = Peli_ikkuna.class.getDeclaredField("kysyttyAine");
            kenttäC.setAccessible(true);
            Alkuaine aine = null;
            Field kenttä1 = Peli_ikkuna.class.getDeclaredField("laskuri");
            kenttä1.setAccessible(true);
            assertEquals(1, kenttä1.getInt(testi));
            Field kenttä2 = Peli_ikkuna.class.getDeclaredField("ok_nappula");
            kenttä2.setAccessible(true);
            JButton testnappula = (JButton) kenttä2.get(testi);
            Field kenttä3 = Peli_ikkuna.class.getDeclaredField("vastauskenttä");
            kenttä3.setAccessible(true);
            JTextField vastaus = (JTextField) kenttä3.get(testi);
            vastaus.setText("pelaa");
            testnappula.doClick();
            luku = kenttäA.getInt(testi);
            if (luku != 1) {
                fail("Kysymysnumero väärä");
            }
            for (int i = 0; i < 111; i = i + 1) {
                aine = (Alkuaine) kenttäC.get(testi);
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
                testnappula.doClick();
                if (luku != i + 1) {
                    fail("Kysymysnumero väärä");
                }
                vastaus.setText(aine.aineenNimi());
                testnappula.doClick();
                luku = luku + 1;
            }
            Field kenttäD = Peli_ikkuna.class.getDeclaredField("pelaajanPisteet");
            kenttäD.setAccessible(true);
            int pisteet = kenttäD.getInt(testi);
            if (pisteet != 265) {
                fail("pisteet väärin");
            }
            testi.sulje();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Test
    public void testaaAjastin() {
        try {
            Field kenttäA = Peli_ikkuna.class.getDeclaredField("aika");
            kenttäA.setAccessible(true);
            int aika = kenttäA.getInt(testi);
            Field kenttäB = Peli_ikkuna.class.getDeclaredField("vihje");
            kenttäB.setAccessible(true);
            JLabel vihje = (JLabel) kenttäB.get(testi);
            String vihje1 = vihje.getText();
            testi.aloitaPeli();
            if (aika > 15 || aika < 14) {
                fail("Aika virheellinen!");
            }
            Thread.sleep(5000);
            aika = kenttäA.getInt(testi);
            if (aika > 11 || aika < 9) {
                fail("Aika virheellinen!");
            }
            testi.päivitäTilanne(2);
            aika = kenttäA.getInt(testi);
            if (aika > 15 || aika < 14) {
                fail("Aika virheellinen!");
            }
            Thread.sleep(16000);
            vihje = (JLabel) kenttäB.get(testi);
            String vihje2 = vihje.getText();
            if (vihje1.equals(vihje2)) {
                fail("Vihjeet eivät toimi");
            }
            aika = kenttäA.getInt(testi);
            if (aika < 10) {
                fail("Aika virheellinen!");
            }
            Thread.sleep(17000);
            vihje = (JLabel) kenttäB.get(testi);
            if (vihje.getText().equals("Pauling: Voi voi minkä menit tekemään! No, harjoitus tekee mestarin.")) {
                assertEquals(0, 0);
            } else if (vihje.getText().equals("Arrhenius: Voi voi minkä menit tekemään! No, harjoitus tekee mestarin.")) {
                assertEquals(0, 0);
            } else if (vihje.getText().equals("Lewis: Voi voi minkä menit tekemään! No, harjoitus tekee mestarin.")) {
                assertEquals(0, 0);
            } else if (vihje.getText().equals("Debye: Voi voi minkä menit tekemään! No, harjoitus tekee mestarin.")) {
                assertEquals(0, 0);
            } else {
                System.out.println(vihje.getText());
                fail("Vihjeet eivät toimi");
            }
            testi.sulje();
        } catch (Exception e) {
            System.out.println(e);
        }
    } 

    @Test
    public void väärätTest() {
        try {
            Field kenttä1 = Peli_ikkuna.class.getDeclaredField("yrityskerta");
            kenttä1.setAccessible(true);
            Field kenttä2 = Peli_ikkuna.class.getDeclaredField("ok_nappula");
            kenttä2.setAccessible(true);
            JButton testnappula = (JButton) kenttä2.get(testi);
            Field kenttä3 = Peli_ikkuna.class.getDeclaredField("vastauskenttä");
            kenttä3.setAccessible(true);
            JTextField vastaus = (JTextField) kenttä3.get(testi);
            Field kenttä4 = Peli_ikkuna.class.getDeclaredField("vihje");
            kenttä4.setAccessible(true);
            JLabel vihje = (JLabel) kenttä4.get(testi);
            testi.aloitaPeli();
            vastaus.setText("Maximilien Robespierre");
            testnappula.doClick();
            testnappula.doClick();
            if (vihje.getText().equals("Pauling: Voi voi minkä menit tekemään! No, harjoitus tekee mestarin.")) {
                assertEquals(0, 0);
            } else if (vihje.getText().equals("Arrhenius: Voi voi minkä menit tekemään! No, harjoitus tekee mestarin.")) {
                assertEquals(0, 0);
            } else if (vihje.getText().equals("Lewis: Voi voi minkä menit tekemään! No, harjoitus tekee mestarin.")) {
                assertEquals(0, 0);
            } else if (vihje.getText().equals("Debye: Voi voi minkä menit tekemään! No, harjoitus tekee mestarin.")) {
                assertEquals(0, 0);
            } else {
                System.out.println(vihje.getText());
                fail("Vihjeet eivät toimi");
            }
            testi.sulje();
        } catch (Exception e) {
            System.out.println(e);
        }
    } 

    @Test
    public void HäviöIkkuna() {
        try {
            JFrame testikkuna = new JFrame();
            IlmoitaHäviö_Ikkuna häviö = new IlmoitaHäviö_Ikkuna(testikkuna, 600, true, "Ankkalampi");
            häviö.run();
            Field kenttä1 = IlmoitaHäviö_Ikkuna.class.getDeclaredField("nimikenttä");
            kenttä1.setAccessible(true);
            JTextField nimikenttä = (JTextField) kenttä1.get(häviö);
            assertEquals(häviö.palautaNimikentänNimi(), "");
            nimikenttä.setText("Aleksanteri Suuri");
            Field kenttä2 = IlmoitaHäviö_Ikkuna.class.getDeclaredField("oikeaVastaus");
            kenttä2.setAccessible(true);
            String oikea = (String) kenttä2.get(häviö);
            if (!oikea.equals("Ankkalampi")) {
                fail("oikeaVastaus ei täsmää!");
            }
            assertEquals(nimikenttä.getText(), häviö.palautaNimikentänNimi());
            testi.sulje();
        } catch (Exception e) {
            System.out.println(e);
        }
    } 

    @Test
    public void VoittoIkkuna() {
        try {
            JFrame testikkuna = new JFrame();
            IlmoitaVoitto_Ikkuna voitto = new IlmoitaVoitto_Ikkuna(testikkuna, 600, true);
            voitto.run();
            Field kenttä1 = IlmoitaVoitto_Ikkuna.class.getDeclaredField("nimikenttä");
            kenttä1.setAccessible(true);
            JTextField nimikenttä = (JTextField) kenttä1.get(voitto);
            assertEquals("", voitto.palautaNimikentänNimi());
            nimikenttä.setText("Aleksanteri Suuri");
            assertEquals(nimikenttä.getText(), voitto.palautaNimikentänNimi());
            testi.sulje();
        } catch (Exception e) {
            System.out.println(e);
        }
    } 

    @Test
    public void lisääNimiHäviö() {
        try {
            testi.aloitaPeli();
            testi.päivitäTilanne(2);
            Field kenttä1 = Peli_ikkuna.class.getDeclaredField("pelaajanPisteet");
            kenttä1.setAccessible(true);
            kenttä1.setInt(testi, 600);
            testi.lopetus();
            assertEquals("", testi.palautaHäviöNimi());
            testi.lisääNimi("src/testi.txt", "Kammottava Kummitus");
            Field kenttä2 = Peli_ikkuna.class.getDeclaredField("pistelista");
            kenttä2.setAccessible(true);
            Pistelista lista = (Pistelista) kenttä2.get(testi);
            lista.lataaTestilista();
            Field kenttä3 = Pistelista.class.getDeclaredField("pistelista");
            kenttä3.setAccessible(true);
            ArrayList<Pelitulos> lista2 = (ArrayList<Pelitulos>) kenttä3.get(lista);
            if (!lista2.get(0).palautaNimi().equals("Kammottava Kummitus") || lista2.get(0).palautaPisteet() != 600) {
                fail("Virhe tuloksen lisäämisessä!");
            }
            lista2.remove(0);
            lista2.add(new Pelitulos("Herttainen Herttua", 0));
            lista.tallennaPistelista("src/testi.txt");
            JButton väliaikainen = new JButton("Lopeta Peli");
            väliaikainen.addActionListener(new Peli_ikkunanKuuntelija(testi));
            väliaikainen.doClick();
        } catch (Exception e) {
            System.out.println(e);
        }
    } 
    @Test
    public void lisääNimiVoitto() {
        try {
            testi.aloitaPeli();
            Field kenttäA = Peli_ikkuna.class.getDeclaredField("moneskoHelppoKysytään");
            kenttäA.setAccessible(true);
            kenttäA.setInt(testi, 11);
            Field kenttäB = Peli_ikkuna.class.getDeclaredField("moneskoKeskivaikeaKysytään");
            kenttäB.setAccessible(true);
            kenttäB.setInt(testi, 46);
            Field kenttäC = Peli_ikkuna.class.getDeclaredField("moneskoVaikeaKysytään");
            kenttäC.setAccessible(true);
            kenttäC.setInt(testi, 53);
            Field kenttäD = Peli_ikkuna.class.getDeclaredField("kysymysnumero");
            kenttäD.setAccessible(true);
            kenttäD.setInt(testi, 111);
            testi.päivitäTilanne(2);
            Field kenttä1 = Peli_ikkuna.class.getDeclaredField("pelaajanPisteet");
            kenttä1.setAccessible(true);
            kenttä1.setInt(testi, 600);  
            assertEquals("", testi.palautaVoittoNimi());
            testi.lisääNimi("src/testi.txt", "Kammottava Kummitus");
            Field kenttä2 = Peli_ikkuna.class.getDeclaredField("pistelista");
            kenttä2.setAccessible(true);
            Pistelista lista = (Pistelista) kenttä2.get(testi);
            lista.lataaTestilista();
            Field kenttä3 = Pistelista.class.getDeclaredField("pistelista");
            kenttä3.setAccessible(true);
            ArrayList<Pelitulos> lista2 = (ArrayList<Pelitulos>) kenttä3.get(lista);
            if (!lista2.get(0).palautaNimi().equals("Kammottava Kummitus") || lista2.get(0).palautaPisteet() != 600) {
                fail("Virhe tuloksen lisäämisessä!");
            }
            lista2.remove(0);
            lista2.add(new Pelitulos("Herttainen Herttua", 0));
            lista.tallennaPistelista("src/testi.txt");
            JButton väliaikainen = new JButton("Lopeta Peli");
            väliaikainen.addActionListener(new Peli_ikkunanKuuntelija(testi));
            väliaikainen.doClick();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    @Test
    public void testaaMaksimipisteet() {
        int luku = 0;
        try {
            Field kenttäA = Peli_ikkuna.class.getDeclaredField("kysymysnumero");
            kenttäA.setAccessible(true);
            Field kenttäB = Peli_ikkuna.class.getDeclaredField("vihje");
            kenttäB.setAccessible(true);
            JLabel vihje = null;
            Field kenttäC = Peli_ikkuna.class.getDeclaredField("kysyttyAine");
            kenttäC.setAccessible(true);
            Alkuaine aine = null;
            Field kenttä1 = Peli_ikkuna.class.getDeclaredField("laskuri");
            kenttä1.setAccessible(true);
            assertEquals(1, kenttä1.getInt(testi));
            Field kenttä2 = Peli_ikkuna.class.getDeclaredField("ok_nappula");
            kenttä2.setAccessible(true);
            JButton testnappula = (JButton) kenttä2.get(testi);
            Field kenttä3 = Peli_ikkuna.class.getDeclaredField("vastauskenttä");
            kenttä3.setAccessible(true);
            JTextField vastaus = (JTextField) kenttä3.get(testi);
            vastaus.setText("pelaa");
            testnappula.doClick();
            luku = kenttäA.getInt(testi);
            if (luku != 1) {
                fail("Kysymysnumero väärä");
            }
            for (int i = 0; i < 111; i = i + 1) {
                aine = (Alkuaine) kenttäC.get(testi);
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
                vastaus.setText(aine.aineenNimi());
                testnappula.doClick();
                luku = luku + 1;
            }
            Field kenttäD = Peli_ikkuna.class.getDeclaredField("pelaajanPisteet");
            kenttäD.setAccessible(true);
            int pisteet = kenttäD.getInt(testi);
            if (pisteet != 530) {
                fail("pisteet väärin");
            }
            testi.sulje();
        } catch (Exception e) {
            System.out.println(e);
        }
    } 
    
    @Test
    public void hävionNull() {
        JFrame testframe = new JFrame();
        IlmoitaHäviö_Ikkuna test = new IlmoitaHäviö_Ikkuna(testframe, -324323, false, "Julius Ankanpää");
        test.run();
        assertEquals("", test.palautaNimikentänNimi());
    }
    
    @Test
    public void voittoNull() {
        JFrame testframe = new JFrame();
        IlmoitaVoitto_Ikkuna test = new IlmoitaVoitto_Ikkuna(testframe, -324323, false);
        test.run();
        assertEquals("", test.palautaNimikentänNimi());
    }
}
