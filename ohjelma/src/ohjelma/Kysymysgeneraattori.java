
package ohjelma;

import java.util.ArrayList;
import java.util.Collections;

/**
 *Tämä luokka generoi pelissä käytettävät kysymykset.
 * 
 * @author timo
 */
public class Kysymysgeneraattori {
    
    public ArrayList<Alkuaine> helpotKysymykset;
    public ArrayList<Alkuaine> keskivaikeatKysymykset;
    public ArrayList<Alkuaine> vaikeatKysymykset;
    
    /**
     * Luokan konstruktori, joka luo kolme ArrayListiä. 
     */
    
    public Kysymysgeneraattori() {
        helpotKysymykset = new ArrayList<Alkuaine>();
        keskivaikeatKysymykset = new ArrayList<Alkuaine>();
        vaikeatKysymykset = new ArrayList<Alkuaine>();
    }
    
    /**
     * Luo vaikeusasteeltaan helpoimmat kysymykset ja sekoittaa ne.
     */
    
    public void luoHelpotKysymykset() {
        helpotKysymykset.add(new Alkuaine("vety", "H", "Universumin yleisin alkuaine."));
        helpotKysymykset.add(new Alkuaine("helium", "He", "Ilmapallot."));
        helpotKysymykset.add(new Alkuaine("rauta", "Fe", "Ruostuu helposti."));
        helpotKysymykset.add(new Alkuaine("hiili", "C", "Elämä perustuu tähän."));
        helpotKysymykset.add(new Alkuaine("happi", "O", "Keuhkot."));
        helpotKysymykset.add(new Alkuaine("rikki", "S", "Tulikivi."));
        helpotKysymykset.add(new Alkuaine("kloori", "Cl", "Uima-altaat."));
        helpotKysymykset.add(new Alkuaine("fluori", "F", "Erittäin vahva hapetin, hampaat."));
        helpotKysymykset.add(new Alkuaine("typpi", "N", "Ilma koostuu lähinnä tästä."));
        helpotKysymykset.add(new Alkuaine("uraani", "U", "Little Boy."));
        helpotKysymykset.add(new Alkuaine("alumiini", "Al", "Tuotetaan bauksiitista."));
        Collections.shuffle(helpotKysymykset);
    }
    
    /**
     * Luo keskivaikeat kysymykset ja sekoittaa ne.
     */
    
    public void luoKeskivaikeatKysymykset() {
        keskivaikeatKysymykset.add(new Alkuaine("argon", "Ar", "Maapallon yleisin jalokaasu."));
        keskivaikeatKysymykset.add(new Alkuaine("antimoni", "Sb", "Tämän aineen sulfidia käytettiin silmien meikkaukseen mm. muinaisessa egyptissä"));
        keskivaikeatKysymykset.add(new Alkuaine("arseeni", "As", "Käytetään myrkkynä mm. Hercule Poirot -kirjoissa."));
        keskivaikeatKysymykset.add(new Alkuaine("barium", "Ba", "Tämän yhdisteitä käytetään ilotulitteissa vihreän värin aikaansaamiseksi."));
        keskivaikeatKysymykset.add(new Alkuaine("beryllium", "Be", "Kevein maa-alkalimetalli."));
        keskivaikeatKysymykset.add(new Alkuaine("boori", "B", "Jaksollisen järjestelmän kolmas ryhmä on nimetty tämän aineen mukaan."));
        keskivaikeatKysymykset.add(new Alkuaine("bromi", "Br", "Halogeeni, neste huoneenlämmössä."));
        keskivaikeatKysymykset.add(new Alkuaine("cesium", "Cs", "Käytetään atomikelloissa. Tämän avulla myös määritetään sekunti."));
        keskivaikeatKysymykset.add(new Alkuaine("elohopea", "Hg", "Nestemäinen metalli."));
        keskivaikeatKysymykset.add(new Alkuaine("fosfori", "P", "Valontuoja, Lucifer."));
        keskivaikeatKysymykset.add(new Alkuaine("hopea", "Ag", "Voidaa käyttää aterimissa."));
        keskivaikeatKysymykset.add(new Alkuaine("jodi", "I", "Kiinteä halogeeni, violetti."));
        keskivaikeatKysymykset.add(new Alkuaine("kadmium", "Cd", "Tätä voi käyttää teräksen galvanoinnissa."));
        keskivaikeatKysymykset.add(new Alkuaine("kalium", "K", "Potaska."));
        keskivaikeatKysymykset.add(new Alkuaine("kalsium", "Ca", "Luut, maito."));
        keskivaikeatKysymykset.add(new Alkuaine("koboltti", "Co", "Nimetty saksalaisessa mytologiassa esiintyvän hahmon mukaan (kobold)."));
        keskivaikeatKysymykset.add(new Alkuaine("kromi", "Cr", "Tätä käytetään metallien pinnoittamiseen"));
        keskivaikeatKysymykset.add(new Alkuaine("krypton", "Kr", "Melkein samanniminen kun teräsmiehen heikkous."));
        keskivaikeatKysymykset.add(new Alkuaine("ksenon", "Xe", "Jalokaasu, jota voi käyttää valon tuottamiseen."));
        keskivaikeatKysymykset.add(new Alkuaine("kulta", "Au", "Rahanarvo voidaan kytkeä tähän metalliin."));
        keskivaikeatKysymykset.add(new Alkuaine("kupari", "Cu", "Tämän metallin pinta muuttuu vihreäksi, kun se hapettuu."));
        keskivaikeatKysymykset.add(new Alkuaine("litum", "Li", "Kevyin metalli."));
        keskivaikeatKysymykset.add(new Alkuaine("lyijy", "Pb", "Tästä alkuaineesta tehtyjen vesijohtojen on spekuloitu olleen yksi syy rooman rappeutumiseen."));
        keskivaikeatKysymykset.add(new Alkuaine("magnesium", "Mg", "Tämän metallin polttaminen synnyttää erittäin kirkkaan valkoisen liekin."));
        keskivaikeatKysymykset.add(new Alkuaine("mangaani", "Mn", "Nimi tulee latinan sanasta magnes (magneetti)."));
        keskivaikeatKysymykset.add(new Alkuaine("natrium", "Na", "Tämän alkuaineen ioneja löytyy mm. pöytäsuolasta."));
        keskivaikeatKysymykset.add(new Alkuaine("neon", "Ne", "Tätä jalokaasua käytetään mm. mainoslampuissa."));
        keskivaikeatKysymykset.add(new Alkuaine("nikkeli", "Ni", "Nimetty saksalaisessa mytologiassa esiintyvän hahmon mukaan (kupfernickel)."));
        keskivaikeatKysymykset.add(new Alkuaine("palladium", "Pd", "Nimetty erään asteroidin mukaan, joka puolestaan nimettiin Pallas Athenan mukaan."));
        keskivaikeatKysymykset.add(new Alkuaine("pii", "Si", "On esitetty, että elämä voisi perustua hiilen sijasta myös tähän alkuaineeseen."));
        keskivaikeatKysymykset.add(new Alkuaine("platina", "Pt", "Vetypolttokennoissa joudutaan usein käyttämään tätä kallista jalometallia katalyyttinä."));
        keskivaikeatKysymykset.add(new Alkuaine("plutonium", "Pu", "Fat Man."));
        keskivaikeatKysymykset.add(new Alkuaine("polonium", "Po", "Litvinenko myrkytettiin tällä radioaktiivisella aineella, kun sitä lisättiin hänen teehensä."));
        keskivaikeatKysymykset.add(new Alkuaine("radium", "Ra", "Tämä radioaktiivinen aine kulkeutuu helposti luustoon, koska se muistuttaa kalsiumia."));
        keskivaikeatKysymykset.add(new Alkuaine("radon", "Rn", "Tätä radioaktiivista jalokaasua voi esiintyä sisäilmassa."));
        keskivaikeatKysymykset.add(new Alkuaine("rubidium", "Rb", "Punainen jalokivi."));
        keskivaikeatKysymykset.add(new Alkuaine("seleeni", "Se", "Celine Dionin etunimi."));
        keskivaikeatKysymykset.add(new Alkuaine("sinkki", "Zn", "Teräs voidaan galvanoida tällä metallilla."));
        keskivaikeatKysymykset.add(new Alkuaine("strontium", "Sr", "Löydettiin Strontian-nimisestä kylästä Skotlannissa."));
        keskivaikeatKysymykset.add(new Alkuaine("tallium", "Tl", "Käytetään mm. rotanmyrkyissä."));
        keskivaikeatKysymykset.add(new Alkuaine("tina", "Sn", "Vuodenvaihteessa tätä metallia ensin sulatetaan ja sitten heitetään veteen."));
        keskivaikeatKysymykset.add(new Alkuaine("titaani", "Ti", "Zeus kumppaneineen syrjäyttivät nämä mytologiset hahmot."));
        keskivaikeatKysymykset.add(new Alkuaine("torium", "Th", "Nimetty viikinkien ukkosenjumalan mukaan."));
        keskivaikeatKysymykset.add(new Alkuaine("vanadiini", "V", "Johdettu jumalatar Freyjan vaihtoehtoisesta nimestä"));
        keskivaikeatKysymykset.add(new Alkuaine("vismutti", "Bi", "Käytetään mm. kestomagneettien valmistukseen."));
        keskivaikeatKysymykset.add(new Alkuaine("volframi", "W", "Tämän aineen karbideja käytetään mm. Leopardin alikalipeeriammuksissa."));
        Collections.shuffle(keskivaikeatKysymykset);
    }
    
    /**
     * Luo vaikeammat kysymykset ja sekoittaa ne. 
     */
    
    public void luoVaikeatKysymykset() {
        vaikeatKysymykset.add(new Alkuaine("aktinium", "Ac", "Tämä aine on erittäin radioAKTIIVINEN."));
        vaikeatKysymykset.add(new Alkuaine("amerikium", "Am", "Radioaktiivinen aine, jota löytyy palovaroittimista. Nimetty erään maan mukaan."));
        vaikeatKysymykset.add(new Alkuaine("astatiini", "At", "Nimi tulee kreikan sanasta astatos (epävakaa)"));
        vaikeatKysymykset.add(new Alkuaine("berkelium", "Bk", "Kuka oli se piispa ja filosofi, joka oli empiristi, mutta ei uskonut materialistisen maailman olemassaoloon?"));
        vaikeatKysymykset.add(new Alkuaine("bohrium", "Bh", "Nimetty erään fyysikon mukaan. Hänen mukaansa on nimetty myös yksi virheellinen atomimalli"));
        vaikeatKysymykset.add(new Alkuaine("cerium", "Ce", "Roomalaisten viljan ja kasvillisuuden jumalatar oli nimeltään?."));
        vaikeatKysymykset.add(new Alkuaine("curium", "Cm", "Säteilevä tiedenainen."));
        vaikeatKysymykset.add(new Alkuaine("darmstadtium", "Ds", "Darmstadt, Saksa, 1994."));
        vaikeatKysymykset.add(new Alkuaine("dubnium", "Db", "Dubna, Venäjä, 1967."));
        vaikeatKysymykset.add(new Alkuaine("dysprosium", "Dy", "Nimi tulee kreikan sanasta dysprositos (vaikeasti saatavissa oleva)."));
        vaikeatKysymykset.add(new Alkuaine("einsteinium", "Es", "Suhteellisuusteoria."));
        vaikeatKysymykset.add(new Alkuaine("erbium", "Er", "Tämän aineen suolat ovat ruusunpunaisia."));
        vaikeatKysymykset.add(new Alkuaine("europium", "Eu", "Zeus kaappasi erään naisen härän hahmossa. Mikä oli tämän naisen nimi?"));
        vaikeatKysymykset.add(new Alkuaine("fermium", "Fm", "Minkä nimisiä ovat ne alkeishiukkaset, jotka noudattavat Paulin kieltosääntöä ja joiden spini on puoliluku?"));
        vaikeatKysymykset.add(new Alkuaine("gadolinium", "Gd", "Nimetty kuuluisan suomalaisen kemistin mukaan."));
        vaikeatKysymykset.add(new Alkuaine("gallium", "Ga", "Julius Caesar kirjoitti eräästä sotaretkestään kuuluisan teoksen, ******* sota."));
        vaikeatKysymykset.add(new Alkuaine("germanium", "Ge", "Tärkeä puolijohdemateriaali."));
        vaikeatKysymykset.add(new Alkuaine("hafnium", "Hf", "Kööpenhaminan latinankielinen nimi on Hafnia."));
        vaikeatKysymykset.add(new Alkuaine("hassium", "Hs", "Hessenin osavaltion latinankielinen nimi on Hassia."));
        vaikeatKysymykset.add(new Alkuaine("holmium", "Ho", "Tukholman latinankielinen nimi on Holmia."));
        vaikeatKysymykset.add(new Alkuaine("indium", "In", "Tämän aineen spektriviivat ovat indigon värisiä."));
        vaikeatKysymykset.add(new Alkuaine("iridium", "Ir", "Nimetty kreikkalaisen jumalan mukaan, joka oli sateenkaaren henkilöitymä. Silmät."));
        vaikeatKysymykset.add(new Alkuaine("kalifornium", "Cf", "Nimetty erään yhdysvaltojen osavaltion mukaan."));
        vaikeatKysymykset.add(new Alkuaine("lantaani", "La", "Ensimmäinen lantanoidi."));
        vaikeatKysymykset.add(new Alkuaine("lawrencium", "Lr", "Eräs brittiläinen upseeri tunnettiin nimellä ******** of Arabia."));
        vaikeatKysymykset.add(new Alkuaine("lutetium", "Lu", "Pariisin latinankielinen nimi oli Lutetia."));
        vaikeatKysymykset.add(new Alkuaine("meitnerium", "Mt", "Nimetty kuuluisan naisfyysikon (ei Curie) mukaan."));
        vaikeatKysymykset.add(new Alkuaine("mendelevium", "Md", "Jaksollinen järjestelmä, venäläinen."));
        vaikeatKysymykset.add(new Alkuaine("molybdeeni", "Mo", "Nimi tulee kreikan sanasta molybdos, joka tarkoittaa lyijyä."));
        vaikeatKysymykset.add(new Alkuaine("neodyymi", "Nd", "Apostoli Tuomas tunnetaan myös nimellä Didymos (kreikaksi kaksonen). Neos on myös kreikkaa ja tarkoittaa uutta."));
        vaikeatKysymykset.add(new Alkuaine("neptunium", "Np", "Roomalaisten meren jumala oli nimeltään?"));
        vaikeatKysymykset.add(new Alkuaine("nobelium", "No", "Nimetty dynamiitin keksijän mukaan."));
        vaikeatKysymykset.add(new Alkuaine("niobium", "Nb", "Nimetty kreikan mytologiassa esiintyvän naisen mukaan. Tällä naisella oli 14 lasta, jotka kaikki tapettiin."));
        vaikeatKysymykset.add(new Alkuaine("osmium", "Os", "Osme on kreikkaa ja tarkoittaa hajua. Tämän aineen tetraoksidi haisee!")); 
        vaikeatKysymykset.add(new Alkuaine("praseodyymi", "Pr", "Apostoli Tuomas tunnetaan myös nimellä Didymos (kreikaksi kaksonen). Vaalenvihreä on kreikaksi prasios."));
        vaikeatKysymykset.add(new Alkuaine("prometium", "Pm", "Nimetty sen titaanin mukaan, joka varasti tulen jumalilta ja antoi sen ihmisille."));
        vaikeatKysymykset.add(new Alkuaine("protaktinium", "Pa", "Protos on kreikkaa ja tarkoittaa ennen. Aktinium on taas aktinoidi."));
        vaikeatKysymykset.add(new Alkuaine("renium", "Re", "Reinjoki on latinaksi Rhenus."));
        vaikeatKysymykset.add(new Alkuaine("rodium", "Rh", "Rhodon on kreikkaa ja tarkoittaa ruusua."));
        vaikeatKysymykset.add(new Alkuaine("rutenium", "Ru", "Rhutenia on latinankielinen nimi, joka tarkoitti aluetta, joka sijaitsi itä-euroopassa."));
        vaikeatKysymykset.add(new Alkuaine("rutherfordium", "Rf", "Nimetty sen fyysikon mukaan, joka löysi atomin ytimen."));
        vaikeatKysymykset.add(new Alkuaine("röntgenium", "Rg", "Jarrutussäteily."));
        vaikeatKysymykset.add(new Alkuaine("samarium", "Sm", "Luukkaan evankeliumissa on kertomus laupiaasta ***************."));
        vaikeatKysymykset.add(new Alkuaine("seaborgium", "Sg", "Ainoa alkuaine, joka on julkisesti nimetty vielä elossaolevan ihmisen mukaan."));
        vaikeatKysymykset.add(new Alkuaine("skandium", "Sc", "Toinen yleisesti käytetty nimi pohjoismaille."));
        vaikeatKysymykset.add(new Alkuaine("tantaali", "Ta", "Nimetty Lyydian kuninkaan ja Zeuksen pojan mukaan."));
        vaikeatKysymykset.add(new Alkuaine("teknetium", "Tc", "Kreikan sana technetos tarkoittaa keinotekoista."));
        vaikeatKysymykset.add(new Alkuaine("telluuri", "Te", "Tellus on latinaa ja tarkoittaa maata."));
        vaikeatKysymykset.add(new Alkuaine("terbium", "Tb", "Tämä aine löydettiin Ytterbyssä, Ruotsissa."));
        vaikeatKysymykset.add(new Alkuaine("tulium", "Tm", "Ultima Thule on antiikin ja keskiajan käsite, jolla tarkoitettiin pohjoisessa sijaitsevaa aluetta"));
        vaikeatKysymykset.add(new Alkuaine("ytterbium", "Yb", "Saanut nimensä ruotsalaisen kylän Ytterbyn mukaan."));
        vaikeatKysymykset.add(new Alkuaine("yttrium", "Y", "Nimetty Ytterbyn kylän mukaan. Aineen löysi Gadolin"));
        vaikeatKysymykset.add(new Alkuaine("zirkonium", "Zr", "Nimi tulee arabian sanasta zargus, joka tarkoittaa kullanväristä."));
        vaikeatKysymykset.add(new Alkuaine("frankium", "Fr", "Kaikkein helpoiten reagoiva metalli."));
        Collections.shuffle(vaikeatKysymykset);
    }
}
