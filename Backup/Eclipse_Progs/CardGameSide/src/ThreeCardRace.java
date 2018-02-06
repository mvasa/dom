package swefun.cardgames;

import swefun.cardgames.playingcards.Suit;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
/**
 * Driver for the Three-Card Race game.
 *
 * <P>The three-card race game is a two-player contest (players A and B).
 * The game is played with a standard 52-card deck.
 * Only the color of the cards, red or black, is significant;
 *     ranks do not matter.</P>
 *
 * Play of the game:
 * <OL>
 * <LI>Player A designates a length-three sequence indicating the colors
 *     of three sequential cards (sequence A).</LI>
 * <LI>Player B designates a length-three sequence indicating the colors
 *     of three sequential cards (sequence B).</LI>
 * <LI>The deck is suffled.</LI>
 * <LI>Cards are dealt face up one at a time from the deck
 *     until three sequential cards are revealed whose colors
 *     exactly match a designated sequence (A or B).
 *     The player whose sequence was matched earns a point
 *     ("takes the trick") and all dealt cards are discarded.</LI>
 * <LI>The game continues by repeating the previous step
 *     until the deck is exhausted.</LI>
 * <LI>The player with the highest point total (greatest number of
 *     tricks taken) wins the game.</LI>
 * </OL>
 *
 * @author Dr. Jody Paul
 * @version 1.1 $Id$
 */
public class ThreeCardRace extends GameRunner {
    /** Color constants for this game. */
    public static final Set<String> COLORS = new HashSet<>();

    /** Number of suit colors required for three card race game. */
    public static final int NUM_COLORS = 2;

    /** Length of color sequence. */
    public static final int SEQUENCE_LENGTH = 3;

    /** Number of players in this game. */
    public static final int NUM_PLAYERS = 2;

    static {
        for (Suit s : Suit.values()) {
            COLORS.add(s.color());
        }
    }

    /**
     * Construct Three Card Game Runner.
     * Creates one interactive player and one non-interactive player.
     * Uses colors from Suit.
     */
    public ThreeCardRace() {
        this(new ThreeCardRacePlayer("Human", true),
             new ThreeCardRacePlayer("Automaton", false));
    }

    /**
     * Construct Three Card Game Runner.
     * Uses colors from Suit; exits if invalid number of suit colors.
     * @param playerA the first player
     * @param playerB the second player
     */
    public ThreeCardRace(final ThreeCardRacePlayer playerA,
                         final ThreeCardRacePlayer playerB) {
    }

    /**
     * Play one game and report results.
     *
     * @param showTrace if true, then the method displays each card dealt
     *                  and identifies tricks as they are taken
     * @return map that associates each player with the number
     *         of tricks taken by that player
     */
    public Map<ThreeCardRacePlayer, Integer> playGame(final boolean showTrace) {
        // Outline of play:
        //  Verify players.
        //  Create a standard deck.
        //  Get sequences from players.
        //  Shuffle the deck.
        //  Deal entire deck, marking tricks as they are taken,
        //    and displaying trace if specified by parameter.
        // Report results.
        return new HashMap<ThreeCardRacePlayer, Integer>();
    }

    /**
     * Sample driver for Three-Card Race game.
     * Replace as desired.
     * @param args currently ignored
     */
    public static void main(final String[] args) {
        System.out.println("Three-Card Race [Version: "
            + version("$Revision$") + "]");
        ThreeCardRacePlayer player1 = new ThreeCardRacePlayer("Auto", false);
        ThreeCardRacePlayer player2 = new ThreeCardRacePlayer("Auto", false);
        
        ThreeCardRace tcrGame = new ThreeCardRace(player1, player2);
        							//new ThreeCardRacePlayer("Auto1", false),
                                  //new ThreeCardRacePlayer("Auto2", false));
        
        //check if either auto 1 or auto 2 have interactive == false
        //if false call generate random colors three times
        //then call set sequence three times
        if(player1.interactive == false){
        	int i = 0;
        	for(i = 0; i < 3; i++){
        		//ThreeCardRacePlayer.generateRandomColor();
        		player1.setSequence();
        	}        	
        	int k = 0;
        	for(k = 0; k < 3; k++){
        		player1.askPlayerColor();
        	}        	
        }
        if(player2.interactive == false){
        	int i = 0;
        	for(i = 0; i < 3; i++){
        		//ThreeCardRacePlayer.generateRandomColor();
        		player2.setSequence();
        	}
        	int k = 0;
        	for(k = 0; k < 3; k++){
        		player2.askPlayerColor();
        	}        	
        }
        tcrGame.playGame(true);
    }
}