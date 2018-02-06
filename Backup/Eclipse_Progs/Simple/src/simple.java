package swefun.cardgames;

import swefun.cardgames.playingcards.Suit;
import swefun.cardgames.playingcards.Deck;
import swefun.cardgames.playingcards.Card;
import java.util.*;

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
 * <LI>The deck is shuffled.</LI>
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
    /**
     * Color constants for this game.
     */
    public static final Set<String> COLORS = new HashSet<>();

    /**
     * Number of suit colors required for three card race game.
     */
    public static final int NUM_COLORS = 2;

    /**
     * Length of color sequence.
     */
    public static final int SEQUENCE_LENGTH = 3;

    /**
     * Number of players in this game.
     */
    public static final int NUM_PLAYERS = 2;

    private LinkedList<Card> currentSequence = new LinkedList<Card>();
    private LinkedList<String> currentString = new LinkedList<>();
    private ThreeCardRacePlayer player1 = new ThreeCardRacePlayer();
    private ThreeCardRacePlayer player2 = new ThreeCardRacePlayer();
    private List<String> p1Sequence = new ArrayList<>();
    private List<String> p2Sequence = new ArrayList<>();
    private Map<ThreeCardRacePlayer, Integer> playMap = new HashMap<>();
    private static boolean show;

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
     *
     * @param playerA the first player
     * @param playerB the second player
     */
    public ThreeCardRace(final ThreeCardRacePlayer playerA,
                         final ThreeCardRacePlayer playerB) {
        this.player1 = playerA;
        this.player2 = playerB;
    }

    /**
     * Play one game and report results.
     *
     * @param showTrace if true, then the method displays each card dealt
     *                  and identifies tricks as they are taken
     * @return map that associates each player with the number
     * of tricks taken by that player
     */
    public Map<ThreeCardRacePlayer, Integer> playGame(final boolean showTrace) {
        Deck newDeck = getShuffledDeck();
        playMap.put(player1, 0);
        playMap.put(player2, 0);
        p1Sequence = player1.getSequence(null);
        p2Sequence = player2.getSequence(p1Sequence);
        if(showTrace) {
            showHistory();
        }
        dealCards(newDeck);
        getWinner(player1, player2);
        return playMap;
    }

    private void showHistory() {
        show = true;
    }

    private void getWinner(ThreeCardRacePlayer p1, ThreeCardRacePlayer p2) {
        int score1 = playMap.get(p1);
        int score2 = playMap.get(p2);
        if (score1 > score2) {
            System.out.println(p1.name() + " WINS! " + score1 + " to " + score2);
        } else if (score2 > score1) {
            System.out.println(p2.name() + " WINS! " + score2 + " to " + score1);
        } else if (score1 == score2) {
            System.out.println(p1.name() + " and " + p2.name() + " HAVE TIED " + score1 + " to " + score2);
        }
    }

    private Deck getShuffledDeck() {
        Deck newDeck = new Deck();
        newDeck.shuffle();
        return newDeck;
    }

    private void dealCards(Deck deck) {
        dealThree(deck);
        while (deck.size() > 2) {
            convertToColor(currentSequence);
            if (isMatch(currentString, p1Sequence)) {
                isTrick(player1);
                dealThree(deck);
            } else if (isMatch(currentString, p2Sequence)) {
                isTrick(player2);
                dealThree(deck);
            } else {
                dealOne(deck);
            }
        }
    }

    private void convertToColor(LinkedList<Card> currentSequence) {
        currentString.clear();
        for(Card c : currentSequence) {
            currentString.add(c.suit().color());
        }
    }

    private void dealOne(Deck deck) {
        currentSequence.pollLast();
        currentSequence.push(deck.deal());
        if(show) {
            System.out.println(" ");
            System.out.println("Dealt : " + currentSequence);
        }
    }

    private void dealThree(Deck deck) {
        currentSequence.add(deck.deal());
        currentSequence.add(deck.deal());
        currentSequence.add(deck.deal());
        if(show) {
            System.out.println(" ");
            System.out.println("Dealt: " + currentSequence);
        }
    }

    private void isTrick(ThreeCardRacePlayer player) {
        if(show) {
            System.out.println(" ");
            System.out.println("Player " + player + " took the trick " + currentSequence);
        }
        currentSequence.clear();
        playMap.put(player, playMap.get(player) + 1);
    }

    private boolean isMatch(LinkedList cs, List<String> ps) {
        if (cs.get(0).equals(ps.get(0)) && cs.get(1).equals(ps.get(1)) && cs.get(2).equals(ps.get(2))) {
            return true;
        } else return false;
    }


    /**
     * Sample driver for Three-Card Race game.
     * Replace as desired.
     *
     * @param args currently ignored
     */
    public static void main(final String[] args) {
        System.out.println("Three-Card Race [Version: "
            + version("$Revision$") + "]");
        ThreeCardRace tcrGame = new ThreeCardRace(
                                  new ThreeCardRacePlayer("Auto1", false),
                                  new ThreeCardRacePlayer("Auto2", false));
        tcrGame.playGame(true);
    }
}
