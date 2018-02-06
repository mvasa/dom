package groupw;

/**
 *
 * @author chris_000
 */
public class Node {

    private int district;
    private boolean marked;
    private boolean edge;
    private int party;

    /**
     * Default constructor. 
     * District ==0
     * edge== false
     * party ==0;
     */
    Node() {
        district = 0;
        marked = false;
        edge = false;
        party = 0;
    }

    /**
     * Constructor that will accept a parameter to indicate if a Node should be
     * considered an edge of region. 
     * @param edge 
     * district = 0; 
     * party=0;
     */
    Node(boolean edge) {
        district = 0;
        marked = false;
        this.edge = edge;
        party = 0;
        if (edge) {
            marked = true;
        }
    }

    /**
     * Constructor that will accept parameters to indicate party, and if this 
     * node will be considered an edge.
     * @param edge
     * @param party 
     */
    Node(boolean edge, int party) {
        district = 0;
        marked = false;
        this.edge = edge;
        this.party = party;
    }

    public int getDistrict() {
        return this.district;
    }

    /**
     * Sets the node to hold  the District value passed.
     * @param newDistrict 
     */
    public void setDirsting(int newDistrict) {
        this.district = newDistrict;
    }

    boolean isMarked() {
        return this.marked;
    }

    boolean isEdge() {
        return this.edge;
    }
    
    /**
     * Sets a node to be an edge or not.
     * @param edge 
     */
    public void setEdge(boolean edge) {
        this.edge = edge;
    }

    /**
     * 
     * @return party of the node as an int 
     */
    public int getParty() {
        return this.party;
    }
    
    /**
     * Sets the party for the node. 
     * @param party 
     */
    public void setParty(int party) {
        this.party = party;
    }

    /**
     * 
     * @return String with the district number included.  
     */
    @Override
    /**
     * returns the district number;
     */
    public String toString() {
        return ((Integer) district).toString();
    }
}
