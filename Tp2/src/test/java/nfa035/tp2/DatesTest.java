package nfa035.tp2;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class DatesTest {

    @Test
    public void testNomMois1() {
        assertEquals("janvier", Dates.nomMois(1));
    }

    @Test
    public void testNomMois12() {
        assertEquals("décembre", Dates.nomMois(12));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNomMoisEx1() {
        Dates.nomMois(0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNomMoisEx2() {
        Dates.nomMois(13);
    }

    @Test
    public void testEstBissextile2004() {
        assertTrue(Dates.estBissextile(2004));
    }

    @Test
    public void testEstBissextile2000() {
        assertTrue(Dates.estBissextile(2000));
    }

    @Test
    public void testEstBissextile1900() {
        assertFalse(Dates.estBissextile(1900));
    }

    @Test
    public void testEstBissextile2005() {
        assertFalse(Dates.estBissextile(2005));
    }

    @Test
    public void testCommeString() {
        assertEquals("20 mai 2014", Dates.commeString(new int[] { 20, 5, 2014 }));
    }

    @Test
    public void testEstDateCorrecte() {
        assertFalse(Dates.estDateCorrecte(new int[] { 29, 2, 2013 }));
    }

    @Test
    public void testNombreDeJoursDansMois() {
        assertEquals(31, Dates.nombreDeJoursDansMois(1, 2000));
    }

    @Test
    public void testDateSuivante() {
        assertArrayEquals(new int[] { 1, 2, 2000 }, Dates.dateSuivante(new int[] { 31, 1, 2000 }));
    }

}
