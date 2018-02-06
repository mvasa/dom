/**
 * Tests for class Envelope.
 *
 * @author  Dr. Jody Paul
 * @version 1.1
 */
public class EnvelopeTest extends junit.framework.TestCase {

    /**
     * Verify construction of envelopes with and without prizes.
     */
    public void testHasPrize() {
        Envelope envelope1 = new Envelope(null);
        Envelope envelope2 = new Envelope(new Prize());
        assertEquals(false, envelope1.hasPrize());
        assertEquals(true, envelope2.hasPrize());
        envelope1.setPrize(new Prize());
        assertEquals(true, envelope1.hasPrize());
        envelope2.setPrize(null);
        assertEquals(false, envelope2.hasPrize());
    }
}
