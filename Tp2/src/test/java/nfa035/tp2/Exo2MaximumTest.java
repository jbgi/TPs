package nfa035.tp2;

import static org.junit.Assert.assertEquals;

import java.util.Random;

import org.junit.Test;

public class Exo2MaximumTest {

    private final Random random = new Random();

    @Test(expected = IllegalArgumentException.class)
    public void test_Exception() {
        Exo2Maximum.maximum(new double[0]);
    }

    @Test
    public void max_tab_1_Element_est_cet_element() {
        for (int i = 0; i < 100; i++) {
            // given
            final double d = nimporte_quel_double();
            // when
            final double result = Exo2Maximum.maximum(new double[] { d });
            // then
            assertEquals(d, result, 0.0);
        }
    }

    @Test
    public void max_tab_2_avec_plus_petit_en_premier() {
        // given
        final double d1 = 1.0;
        final double d2 = 2.0;

        // when
        final double result = Exo2Maximum.maximum(new double[] { d1, d2 });

        // then
        assertEquals(d2, result, 0.0);
    }

    @Test
    public void max_tab_2_avec_plus_grand_en_premier() {
        // given
        final double d1 = 1.0;
        final double d2 = 2.0;

        // when
        final double result = Exo2Maximum.maximum(new double[] { d2, d1 });

        // then
        assertEquals(d2, result, 0.0);
    }

    @Test
    public void max_tab_2_avec_2_elements_egals() {
        // given
        final double d1 = 2.0;
        final double d2 = d1;

        // when
        final double result = Exo2Maximum.maximum(new double[] { d2, d1 });

        // then
        assertEquals(d2, result, 0.0);
    }

    @Test
    public void max_avec_infini_est_l_infini() {
        for (int i = 0; i < 100; i++) {
            // given
            final double d = nimporte_quel_double();
            // when
            final double result = Exo2Maximum.maximum(new double[] { d, Double.POSITIVE_INFINITY });
            // then
            assertEquals(Double.POSITIVE_INFINITY, result, 0.0);
        }
    }

    @Test
    public void max_element_avec_neg_infini_est_cet_element() {
        for (int i = 0; i < 100; i++) {
            // given
            final double d = nimporte_quel_double();
            // when
            final double result = Exo2Maximum.maximum(new double[] { d, Double.NEGATIVE_INFINITY });
            // then
            assertEquals(d, result, 0.0);
        }
    }

    /**
     * @return un double au hazard
     */
    private double nimporte_quel_double() {
        return random.nextDouble() + random.nextLong();
    }

}
