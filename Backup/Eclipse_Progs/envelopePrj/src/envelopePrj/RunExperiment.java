/**
 * Driver to run experiments.
 *
 * @author Dr. Jody Paul
 * @version 1.1
 */
public final class RunExperiment {
    /** Number of times to run simulated game. */
    public static final long NUMBER_OF_RUNS = 1000000;

    /** Number of envelopes per game. */
    public static final int NUMBER_OF_ENVELOPES = 3;

    /** Conversion from fraction to percentage. */
    private static final double PERCENT = 100.0;
    /** Rounding of percentage to integer. */
    private static final double ROUND = 0.5;

    /**
     * Hidden constructor for utility class.
     */
    private RunExperiment() { }

    /**
     * Report simulation results.
     * @param args ignored
     */
    /*
     * make variables for the calculations
     * done in the print statements. display variables
     * in print statements instead of calculation.
     * ===============================================================================
     * int strategyOneResult = ROUND + (PERCENT * results[0] / NUMBER_OF_RUNS
     * int strategyTwoResult =  ROUND + (PERCENT * results[1] / NUMBER_OF_RUNS
     * ===============================================================================
     * 
     * now the print statements would look like this
     * 
     * ===============================================================================
     *  System.out.println("Play simulated "
                           + NUMBER_OF_RUNS + " times using "
                           + NUMBER_OF_ENVELOPES + " envelopes.");
        
        System.out.println(" Wins using strategy (1) = " + results[0] 
        						+ " (" + strategyOneResult + "%)");
        
        System.out.println(" Wins using strategy (2) = " + results[1] 
        						+ " (" + strategyTwoResult + "%)");
     * ===============================================================================
     * adding one space between each of these print 
     * statements makes them easier to distinguish.
     * */
    public static void main(final String[] args) {
    	int strategyOneResult = ROUND + (PERCENT * results[0] / ;
    	int strategyTwoResult =  ROUND + (PERCENT * results[1] / NUMBER_OF_RUNS;
    		      
    	Experiment exp = new Experiment(NUMBER_OF_ENVELOPES);
        long[] results = exp.simulatePlay(NUMBER_OF_RUNS);
        System.out.println("Play simulated "
                           + NUMBER_OF_RUNS + " times using "
                           + NUMBER_OF_ENVELOPES + " envelopes.");
        
        System.out.println(" Wins using strategy (1) = " + results[0] + " ("
            + (int) (ROUND + (PERCENT * results[0] / NUMBER_OF_RUNS)) + "%)");
        
        System.out.println(" Wins using strategy (2) = " + results[1] + " ("
            + (int) (ROUND + (PERCENT * results[1] / NUMBER_OF_RUNS)) + "%)");
    }
}
