

package myCardGame; 
//import playingcards.Suit;
//import playingcards.Deck;
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
    /** Color constants for this game. */
    public static final Set<String> COLORS = new HashSet<>();

    /** Number of suit colors required for three card race game. */
    public static final int NUM_COLORS = 2;

    /** Length of color sequence. */
    public static final int SEQUENCE_LENGTH = 3;

    /** Number of players in this game. */
    public static final int NUM_PLAYERS = 2;

    private LinkedList<String> currentSequence = new LinkedList<String>();
    private ThreeCardRacePlayer player1 = new ThreeCardRacePlayer();
    private ThreeCardRacePlayer player2 = new ThreeCardRacePlayer();
    private List<String> p1Sequence = new ArrayList<>();
    private List<String> p2Sequence = new ArrayList<>();
    private Map<ThreeCardRacePlayer, Integer> playMap = new HashMap<>();
    private static Set<List<String>> playerSet = new HashSet<>();
//new lists I'm adding to hold current dealt cards(suit and rank lists)
    //list increment used to 
    //go through rank and currentDealt when printDealt is called
    private List<String> currentDealt = new ArrayList<String>();
    private List<String> rankList = new ArrayList<String>();
    private int listIncrement = 0;
    private Card newCard = new Card();
    private int numOfCards = 0;
    
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
        this.player1 = playerA;
        this.player2 = playerB;


    }
    //////////////////////////////////////////////////manoj vasa addition> delete if not needed.
    //this section of code isnt being used
    /*private boolean checkPlayerSequenceColors(sequence1, sequence2){
    	
    	List<String> sequence1 = new ArrayList<String>;
    	sequence1 = playerA.getSequence();
    	List<String> sequence2 = new ArrayList<String>;
    	sequence2 =	playerB.getSequence();
    	
    	if(sequence1.equals(sequence2)){
    		return true;
    		println ("sequences are equal. bad!");
    	}
    	else return false;
    	
    	
    }*/
    //////////////////////////////////////////////////

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
        Deck newDeck = getShuffledDeck();
        playMap.put(player1, 0);
        playMap.put(player2, 0);
        //  Get sequences from players.
        p1Sequence = player1.getSequence(null);
        p2Sequence = player2.getSequence(p1Sequence);

        //  Shuffle the deck.
        //  Deal entire deck, marking tricks as they are taken,
        //    and displaying trace if specified by parameter.
        if(showTrace == true){
        dealCards(newDeck);
        }
        else{
        	hideTraceAndDealCards(newDeck);
        }
        dealRest(newDeck);
        nextStatement();
        // Report results.
        getWinner(player1, player2);
        return playMap;
    }

    private void getWinner(ThreeCardRacePlayer p1, ThreeCardRacePlayer p2) {
        int score1 = playMap.get(p1);
        int score2 = playMap.get(p2);
        if(score1 > score2) {
            System.out.println();
        	System.out.println(p1.name() + " WINS!");
        	System.out.println("Player1 Score: " + score1);
        	System.out.println("Player2 Score: " + score2);
        	System.out.println("number of cards dealt " + numOfCards );
        }
        else if(score2 > score1) {
        	System.out.println();
        	System.out.println(p2.name() + " WINS");
        	System.out.println("Player1 Score: " + score1);
        	System.out.println("Player2 Score: " + score2);
        	System.out.println("number of cards dealt " + numOfCards );
        }
        else if(score1 == score2) {
        	System.out.println();
        	System.out.println(p1.name() + " AND " + p2.name() + " HAVE TIED");
        	System.out.println("Player1 Score: " + score1);
        	System.out.println("Player2 Score: " + score2);
        	System.out.println("number of cards dealt " + numOfCards );
        }

    }

    private Deck getShuffledDeck() {
        Deck newDeck = new Deck();
        newDeck.shuffle();
        return newDeck;
    }

    
    private void dealCards(Deck deck) {
        dealThree(deck);
        while(deck.size() > 3) {
        	System.out.println();
        	System.out.println("Comparing current " + currentSequence.get(0) + " " + currentSequence.get(1) + " " +
                                currentSequence.get(2));
            System.out.println("Player 1: " + p1Sequence.get(0) + " " + p1Sequence.get(1) + " " + p1Sequence.get(2));
            System.out.println("Player 2: " + p2Sequence.get(0) + " " + p2Sequence.get(1) + " " + p2Sequence.get(2));
            printCards(deck);
            System.out.println();
            

            if (isMatch(currentSequence, p1Sequence)) {
                isTrick(player1);
                dealThree(deck);
            }
            else if(isMatch(currentSequence,p2Sequence)) {
                isTrick(player2);
                dealThree(deck);
            }
            else {
                dealOne(deck);
            }
        }
        
    }
    private void dealRest(Deck deck){
    	if(deck != null){
    		dealOne(deck);
    		if(currentSequence.size() == 3){
    			System.out.println();
            	System.out.println("Comparing current " + currentSequence.get(0) + " " + currentSequence.get(1) + " " +
                                    currentSequence.get(2));
                System.out.println("Player 1: " + p1Sequence.get(0) + " " + p1Sequence.get(1) + " " + p1Sequence.get(2));
                System.out.println("Player 2: " + p2Sequence.get(0) + " " + p2Sequence.get(1) + " " + p2Sequence.get(2));
                printCards(deck);
                System.out.println();
                

                if (isMatch(currentSequence, p1Sequence)) {
                    isTrick(player1);
                    dealRest(deck);
                }
                else if(isMatch(currentSequence,p2Sequence)) {
                    isTrick(player2);
                    dealRest(deck);
                }
                else {
                    dealRest(deck);
                }

    		}
    		dealOne(deck);
    	}
    	else nextStatement();
    	/*else if(deck == null){
    		 nextStatement();
    	}*/
    	
    }
    private void nextStatement(){
    	System.out.println();
    }
    private void hideTraceAndDealCards(Deck deck){
    	dealThree(deck);
    	
    	while(deck.size()>2) {
    		 if (isMatch(currentSequence, p1Sequence)) {
                 //isTrick(player1)
    			 hideTraceIsTrick(player1);
                 dealThree(deck);
                 
             }
             else if(isMatch(currentSequence,p2Sequence)) {
                 //isTrick(player2);
                 hideTraceIsTrick(player2);
                 dealThree(deck);
                 
             }
             else {
                 dealOne(deck);
                 
             }	 
    	}
    }
    	
    
    private void printCards(Deck deck){
    	if(deck != null){
        	listIncrement = 0;
            System.out.print("Current dealt cards: ");
            for(String s: currentDealt){
            	
            		System.out.print(rankList.get(listIncrement)+ " of " + currentDealt.get(listIncrement)+ "     ");
            		listIncrement++;
            }
            System.out.println();
            System.out.print("number of cards dealt: " + numOfCards);
            
            
            
        }
    }

    private void dealOne(Deck deck) {
            //currentSequence.pollLast();
            //currentSequence.push(deck.deal().suit().color());
            currentSequence.pollFirst();
            
            //currentSequence.add(deck.deal().suit().color());
            //this is the next card added to the field
            //currentDealt.add(currentSequence.get(2));
            //deckIterator++;
           
            //deals first card again 
            numOfCards++;
            newCard = deck.deal();
            currentSequence.add(newCard.suit().color());
            currentDealt.add(newCard.suit().toString());
            rankList.add(newCard.rank().toString());
            
            
    }

    private void dealThree(Deck deck) {
      /*  currentSequence.add(deck.deal().suit().color());
      //first card added to dealt cards
        currentDealt.add(Card.retrieveSuit());
        //currentDealt.add(deck.getCard(deckIterator).suit().toString());
        //deckIterator++;
       
        currentSequence.add(deck.deal().suit().color());
      //second card added to dealt cards
        currentDealt.add(currentSequence.get(1));
        //currentDealt.add(deck.getCard(deckIterator).suit().toString());
        //deckIterator++;
        
        currentSequence.add(deck.deal().suit().color());
       //third card added to dealt cards
        currentDealt.add(currentSequence.get(2));
        //currentDealt.add(deck.getCard(deckIterator).suit().toString());
        //deckIterator++;
        ///////////////////////////////////////////////////////////////////
        //new process*/
    	
    	//first card
    	numOfCards++;
    	newCard = deck.deal();
        currentSequence.add(newCard.suit().color());
        currentDealt.add(newCard.suit().toString());
        rankList.add(newCard.rank().toString());
        
        //second card
        numOfCards++;
        newCard = deck.deal();
        currentSequence.add(newCard.suit().color());
        currentDealt.add(newCard.suit().toString());
        rankList.add(newCard.rank().toString());
        
        //third card
        numOfCards++;
        newCard = deck.deal();
        currentSequence.add(newCard.suit().color());
        currentDealt.add(newCard.suit().toString());
        rankList.add(newCard.rank().toString());
        
        
    }

    private void isTrick(ThreeCardRacePlayer player) {
    	System.out.println();
    	System.out.println("Upping " + player.name());
        System.out.println("Removing: " + currentSequence.get(0));
        currentSequence.remove();
        System.out.println("Removing: " + currentSequence.get(0));
        currentSequence.remove();
        System.out.println("Removing: " + currentSequence.get(0));
        currentSequence.remove();
        playMap.put(player,playMap.get(player) + 1);
      //when sequences match whole dealt cards are removed
        currentDealt.clear();
        //deckIterator = 0;
    }
    private void hideTraceIsTrick(ThreeCardRacePlayer player) {
    	System.out.println();
    	//System.out.println("Upping " + player.name());
        //System.out.println("Removing: " + currentSequence.get(0));
        currentSequence.remove();
        //System.out.println("Removing: " + currentSequence.get(0));
        currentSequence.remove();
        //System.out.println("Removing: " + currentSequence.get(0));
        currentSequence.remove();
        playMap.put(player,playMap.get(player) + 1);
      //when sequences match whole dealt cards are removed
        currentDealt.clear();
        //deckIterator = 0;
    }

    private boolean isMatch(LinkedList cs, List<String> ps){
        if(cs.get(0).equals(ps.get(0)) && cs.get(1).equals(ps.get(1))  && cs.get(2).equals(ps.get(2))) {
            return true;
        }

        else return false;
    }

    private LinkedList<String> markTricks(LinkedList<String> currentSequence) {

        return null;
    }

    


    /**
     * Sample driver for Three-Card Race game.
     * Replace as desired.
     * @param args currently ignored
     */
    public static void main(final String[] args) {
        System.out.println("Three-Card Race [Version: "
            + version("$Revision$") + "]");
        ThreeCardRace tcrGame = new ThreeCardRace(
                                  new ThreeCardRacePlayer("Human", true),
                                  new ThreeCardRacePlayer("Auto", false));

        tcrGame.playGame(true);
    }
}
