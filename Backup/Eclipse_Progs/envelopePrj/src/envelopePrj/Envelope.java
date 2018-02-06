package envelopePrj;

/**
 * Envelope that can contain a prize.
 *
 * @author Dr. Jody Paul
 * @version 1.1
 */
public class Envelope {
    /** Prize attribute. */
    private Prize myPrize;

    /** Constructs an envelope with no prize. */
    public Envelope() {
        this(null);
    }

    /**
     * Constructs an envelope with the parameter as the prize.
     * @param prize the prize for this envelope
     */
    public Envelope(final Prize prize) {
        this.myPrize = prize;
    }

    /**
     * Predicate for whether or not prize is null.
     * @return true if prize is not null; false otherwise
     */
    public boolean hasPrize() {
        return this.myPrize != null;
    }

    /**
     * Mutator to change the prize of this envelope.
     * @param prize the new prize for this envelope
     */
    public void setPrize(final Prize prize) {
        this.myPrize = prize;
    }
}
