import java.util.List;
import java.util.ArrayList;
/**
 * Experimental simulation to test hypotheses
 *   associated with the envelope game.
 *
 * @author Dr. Jody Paul
 * @version 1.2b
 */
public class Experiment {
    /** The standard number of envelopes for the Monty Hall problem. */
    public static final int STANDARD_NUMBER_OF_ENVELOPES = 3;

    /** The collection of envelopes used during the game. */
    private List<Envelope> envelopes;

    /** Statistical information tallies. */
    private long keepOriginalTally, switchTally, otherTally;

    /**
     * Set up experiment with the standard number of envelopes.
     */
    public Experiment() {
        this(STANDARD_NUMBER_OF_ENVELOPES);
    }

    /**
     * Set up the experiment with a specified number of envelopes.
     * @param numEnvelopes the number of envelopes to use for this simulation
     */
    public Experiment(final int numEnvelopes) {
        this.envelopes = new ArrayList<Envelope>();
        for (int i = 0; i < numEnvelopes; i++) {
            this.envelopes.add(new Envelope());
        }
        setupEnvelopes();
        resetTallies();
    }

    /**
     * Access the ordered collection of envelopes as a list.
     * @return a list of the envelopes in this simulation
     */
    private List<Envelope> getEnvelopes() {
        return new ArrayList<Envelope>(this.envelopes);
    }

    /**
     * Configure simulation so that a random envelope contains the prize.
     */
    private void setupEnvelopes() {
        for (Envelope envelope : this.envelopes) {
            envelope.setPrize(null);
        }
        this.envelopes.get(selectRandomEnvelope()).setPrize(new Prize());
    }

    /**
     * Reset tallies prior to beginning simulation run.
     */
    private void resetTallies() {
        this.keepOriginalTally = this.switchTally = this.otherTally = 0L;
    }

    /**
     * Check for valid configuration.
     * One and only one of the envelopes must have a prize.
     * @return true if envelope configuration is valid; false otherwise
     */
    private boolean checkConfiguration() {
        int numberOfPrizes = 0;
        for (Envelope envelope : this.envelopes) {
            if (envelope.hasPrize()) {
                ++numberOfPrizes;
            }
        }
        return (numberOfPrizes == 1);
    }

    /**
     * Select an envelope index at random.
     * @return the index of the randomly selected envelope
     */
    private int selectRandomEnvelope() {
        return (int) (Math.random() * this.envelopes.size());
    }

    /**
     * Reveal an envelope that does not have a prize and isn't to be skipped.
     * @param envelopeToSkip the index of the envelope that is to be skipped
     * @return the index of an envelope that doesn't contain prize and isn't
     *         identified as being one to skip
     */
    private int revealNonprizeEnvelope(final int envelopeToSkip) {
        int envelopeToReveal = -this.envelopes.size(); // Invalid value.
        for (int i = 0; i < this.envelopes.size(); i++) {
            if ((i != envelopeToSkip) && !(this.envelopes.get(i).hasPrize())) {
                envelopeToReveal = i;
            }
        }
        return envelopeToReveal;
    }

    /**
     * Offer an envelope as an alternative to the one selected.
     * @param skip1 index of an envelope that cannot be offered
     * @param skip2 index of an envelope that cannot be offered
     * @return the index of an envelope to offer as an alternative
     */
    private int offerAlternativeEnvelope(final int skip1, final int skip2) {
        for (int i = 0; i < this.envelopes.size(); i++) {
            if ((i != skip1) || (i != skip2)) {
                return i;
            }
        }
        return -this.envelopes.size(); // Error condition!
    }

    /**
     * Simulate the play of one round of the envelope game.
     * This involves<br>
     * (1) setting up the envelopes so that one envelope contains the prize;<br>
     * (2) having the contestant select an envelope at random;<br>
     * (3) revealing a non-selected envelope as not having the prize;<br>
     * (4) offering the contestant the opportunity to exchange their
     *     chosen envelope for one that has not yet been revealed;<br>
     * (5) determining whether the contestant won or lost depending
     *     on whether they kept their original choice or switched
     *     to the alternative offering and updating the appropriate
     *     tally.
     */
    //needs separate methods to test strategy 1 and strategy 2
    //there's no point in testing both at the same time
    //it'd work better if 
    //first half of the runs used strategy 1
    //second half of the runs used strategy 2
    //and hen report those findings as tallies for strategy 1 and strategy 2
    private void playOneRound() {
        setupEnvelopes();
        int contestantEnvelope = selectRandomEnvelope();
        int revealedEnvelope = revealNonprizeEnvelope(contestantEnvelope);
        int offeredEnvelope = offerAlternativeEnvelope(contestantEnvelope,
                                                       revealedEnvelope);
        /*new boolean variables for the if statements
         * 
         * ========================================================================================
         * boolean prizeInContestentEnvelope = this.envelopes.get(contestantEnvelope).hasPrize()  
         * boolean prizeInOfferedEnvelope = this.envelopes.get(offeredEnvelope).hasPrize()
         * ========================================================================================
         * 
         * change placement of braces as well
         * this is the copy pasted if statement with the brace changes
         * and if statement changes
         * 
         * ========================================================================================
         *  if (prizeInContestentEnvelope) {
            	++keepOriginalTally;
        	} 
        	else if (prizeInOfferedEnvelope) {
            	++switchTally;
        	}
        	else {
            	++otherTally;
        	}
         * ==========================================================================================
         * 
         * Consistent placement of braces and clear 
         * indentation highly improves readability.
         * 
         * */
        if (this.envelopes.get(contestantEnvelope).hasPrize()) {
            ++keepOriginalTally;
        } else if (this.envelopes.get(offeredEnvelope).hasPrize()) {
            ++switchTally;
        } else {
            ++otherTally;
        }
    }


    /** How often to validate configuration. */
    private static final long CHECK_INTERVAL = 500;
    /**
     * Reset environment and simulate the game for a specified number of rounds.
     * @param numberOfRounds the number of rounds to simulate
     * @return array with number of wins for keep [0], switch [1], other [2]
     */
    public long[] simulatePlay(final long numberOfRounds) {
        long[] results = new long[this.envelopes.size()];
        resetTallies();
        for (int i = 0; i < numberOfRounds; i++) {
            if (0 == i % CHECK_INTERVAL) {
                checkConfiguration();
            }
            playOneRound();
        }
        results[0] = keepOriginalTally;
        results[1] = switchTally;
        results[2] = otherTally;
        return results;
    }
}