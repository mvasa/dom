package swefun.cardgames;

import java.util.List;
/**
 * A player of the three card race game.
 *
 * @author Dr. Jody Paul
 * @version 1.1 $Id$
 */
public class ThreeCardRacePlayer extends GamePlayer {
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
        return null;
    }
}