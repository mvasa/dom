package myCardGame;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.swing.*;

/**
 * A player of the three card race game.
 *
 * @author Dr. Jody Paul
 * @version 1.1 $Id$
 */
public class ThreeCardRacePlayer extends GamePlayer {

    private List<String> sequence = new ArrayList<String>(3);
    /**
     * Construct a default non-interactive player.
     */
    public ThreeCardRacePlayer() {
        this("Player", false);
    }

    /**
     * Construct a player with specified name and interactivity.
     * @param name the name of this player
     * @param interactive true if this is an interactive player; false otherwise
     */
    public ThreeCardRacePlayer(final String name, final boolean interactive) {
        super(name, interactive);
    }

    /**
     * Return this player's sequence of choice.
     * If player is interactive, elicits sequence from the user.
     * Returned sequence must not match the parameter.
     * @param sequence the other player's sequence; null or invalid size if none
     * @return the player's chosen sequence
     */
    public List<String> getSequence(final List<String> sequence) {
        if(isInteractive() && (this.sequence.size() < 3)) {
            askPlayerColor();
            if(sequence == null) {
                return this.sequence;

            }
            else if(equalLists(this.sequence, sequence)) {
                System.out.println("User picked invalid choice; identical to opponent");
                askPlayerColor();

            }
            else {
                System.out.println("Something bad must of happened");
            }
        }
        else if(!isInteractive()) {
            generateRandomSequence();
            if(equalLists(this.sequence, sequence)) {
                generateRandomSequence();
            }
        }
        return this.sequence;
    }

    private void setSequence(String color) {
        sequence.add(color);
    }

    private void askPlayerColor() {

        for(int i = 0; i < 3; i++) {
            String color = JOptionPane.showInputDialog("Enter red or black sequence # " + i).toUpperCase();

            switch(color.toUpperCase().trim()){
                case "RED":
                    setSequence(color);
                    break;
                case "BLACK":
                    setSequence(color);
                    break;
                default:
                    System.out.println("Verify you chose red or black");
                    askPlayerColor();
            }
        }
    }

    private boolean equalLists(List<String> s1, List<String> s2 ) {
        if(s1.get(0).equals(s2.get(0)) && s1.get(1).equals(s2.get(1)) && s1.get(2).equals(s2.get(2))) {
            return true;
        }
        else return false;
    }

    private void generateRandomSequence() {
        while(sequence.size() < 3) {setSequence(this.getColor());}
    }

    private String getColor() {
        return (Math.rint(Math.random()) == 1)? "BLACK" : "RED";
    }

    public static void main(String[] args) {
        ThreeCardRacePlayer player
                = new ThreeCardRacePlayer("Human", true);

        List<String> seq = new ArrayList<String>();
        seq.add("RED");
        seq.add("BLACK");
        seq.add("RED");
        List<String> choice = player.getSequence(seq);
    }


}//END CLASS
