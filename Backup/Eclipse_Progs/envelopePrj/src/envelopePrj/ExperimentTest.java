import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
/**
 * Tests for class Experiment.
 *
 * @author  Dr. Jody Paul
 * @version 1.1
 */
public class ExperimentTest extends junit.framework.TestCase {
    /** Minimum number of times to run simulated game. */
    public static final long MIN_RUNS = 10;
    /** Medium number of times to run simulated game. */
    public static final long MED_RUNS = 10000;
    /** Maximum number of times to run simulated game. */
    public static final long MAX_RUNS = 1000000;

    /** Number of envelopes per game. */
    public static final int NUMBER_OF_ENVELOPES = 3;

    /** Conversion from fraction to percentage. */
    private static final double PERCENT = 100.0;
    /** Rounding of percentage to integer. */
    private static final double ROUND = 0.5;
    /** Tolerance for equality of results. */
    private static final double TOLERANCE = 0.01;

    /**
     * Verify simulation results.
     */
    @Test
    public void testSimulatePlayTest() {
        Experiment exp = new Experiment();
        exp = new Experiment(NUMBER_OF_ENVELOPES);
        long[] results = exp.simulatePlay(MIN_RUNS);
        assertEquals(NUMBER_OF_ENVELOPES, results.length);
        results = exp.simulatePlay(MED_RUNS);
        assertEquals(NUMBER_OF_ENVELOPES, results.length);
        assertTrue("results[0] > 0", results[0] > 0);
        assertTrue("results[1] > 0", results[1] > 0);
        results = exp.simulatePlay(MAX_RUNS);
        assertEquals(NUMBER_OF_ENVELOPES, results.length);
        // Commented-out assertions for specific hypotheses.
        // Assert that hypothesis (1) is true.
//         assertTrue("results[0] < results[1]", results[0] < results[1]);
        // Assert that (1) is twice as good as (2).
//         assertEquals(2,
//           (int) ((results[1] * PERCENT) / (results[0] * PERCENT) + ROUND));
        // Assert that hypothesis (2) is true.
//         assertTrue("results[0] > results[1]", results[0] > results[1]);
        // Assert that hypothesis (3) is true.
//         assertEquals("results[0] = results[1]",
//                      results[0] / (double) MAX_RUNS,
//                      results[1] / (double) MAX_RUNS,
//                      TOLERANCE);
    }
}
