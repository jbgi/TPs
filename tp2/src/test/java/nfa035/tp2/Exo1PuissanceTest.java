package nfa035.tp2;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class Exo1PuissanceTest {

    @Test
    public void testPuissanceNormale() {
        assertEquals("2 puissance 3 = 8", 8.0, Exo1Puissance.puissance(2, 3), 1e-5);
    }

    @Test(timeout = 1000)
    public void testPuissance0() {
        assertEquals("2 puissance 0 = 1", 1.0, Exo1Puissance.puissance(2, 0), 1e-5);
    }

    @Test(timeout = 1000)
    public void test0Puissance0() {
        assertEquals("0 puissance 0 = 1", 1.0, Exo1Puissance.puissance(0, 0), 1e-5);
    }

    @Test(timeout = 1000)
    public void testPuissanceNegative() {
        assertEquals("2 puissance -1", 0.5, Exo1Puissance.puissance(2, -1), 1e-5);
    }

    @Test(timeout = 1000)
    public void testPuissanceParite() {
        assertEquals("-2 puissance 2", 4, Exo1Puissance.puissance(-2, 2), 1e-5);
    }

    @Test(expected = ArithmeticException.class)
    public void testException() {
        final double r = Exo1Puissance.puissance(0, -1);
    }

}
