package groupw;

import java.util.Arrays;
import java.util.*;

/**
 *
 * @author chris_000
 */
public class NodeMap {

    private int BORDERWIDTH = 0;
    public Node[][] ArrayOfNode;
    private int y;
    private int x;
    private Map<Node, Integer> districtTracker = new HashMap<>();
    private ArrayList<Integer> districtSizeOptions = new ArrayList<Integer>();

    //List nodes = new ArrayList();
    NodeMap() {

    }

    NodeMap(int y, int x) {
        this.y = y + BORDERWIDTH;
        this.x = x + BORDERWIDTH;
        Node[][] ArrayOfNodes = new Node[this.y][this.x];
        ArrayOfNode = ArrayOfNodes;
        this.setBorders();
        this.setInterior();
    }

    /**
     * Sets the outside of the region.
     */
    private void setBorders() {
        for (int i = 0; i < x; i++) {
            this.ArrayOfNode[i][0] = new Node(true);
            this.ArrayOfNode[i][y - 1] = new Node(true);
        }
        for (int i = 0; i < y; i++) {
            this.ArrayOfNode[0][i] = new Node(true);
            this.ArrayOfNode[x - 1][i] = new Node(true);
        }
    }

    /**
     * Sets the inside of the region.
     */
    private void setInterior() {
        for (int i = 1; i < y - 1; i++) {
            for (int j = 1; j < x - 1; j++) {
                this.ArrayOfNode[i][j] = new Node(false, 1);
            }
        }
    }

    @Override
    public String toString() {
        return Arrays.toString(this.ArrayOfNode);
    }

    /**
     * Sets the party and outputs the parties to the users.
     */
    public void assignParties() {
        for (int j = 0; j < y; j++) {
            for (int i = 0; i < x; i++) {
                if (Math.rint(Math.random()) == 1) {
                    this.ArrayOfNode[j][i].setParty(1);
//                    System.out.print("A" + this.ArrayOfNode[j][i].getParty() + " ");
                } else {
                    this.ArrayOfNode[j][i].setParty(0);
//                    System.out.print("B" + this.ArrayOfNode[j][i].getParty() + " ");
                }
            }

        }
    }

    public void printParties() {
        for (int j = 0; j < y; j++) {
            for (int i = 0; i < x; i++) {
                if (this.ArrayOfNode[j][i].getParty() == 1) {
                    System.out.print("A" + this.ArrayOfNode[j][i].getParty() + " ");
                } else {
                    System.out.print("B" + this.ArrayOfNode[j][i].getParty() + " ");
                }
            }
            System.out.println("");
        }
    }

    /**
     * Creates districts based on rows. Once district is created a separate
     * method is called to output the information about the parties within that
     * district. For the map key = voter party, value = district number.
     */
    public void createRowDistricts() {
        int district = 0;
        for (int r = 0; r < x; r++) {
            district++;
            for (int c = 0; c < y; c++) {
                districtTracker.put(this.ArrayOfNode[r][c], district);
            }
        }
        outputDistricRowInformation();
        callFavoredParty();
    }

    public void callFavoredParty() {

        int inc = 0;
        int district = 1;
        int districtSize = x;
        int numOfDistricts = y;
        for (inc = 0; inc < numOfDistricts; inc++) {
            checkFavoredParty(district, districtSize);
            district++;
        }
    }

    /**
     * Outputs the row district information about the included parties.
     */
    public void outputDistricRowInformation() {
        System.out.print("---------------------------------------\n");
        System.out.print("These are the districts created by rows\n");
        System.out.print("---------------------------------------");
        for (int r = 0; r < x; r++) {
            System.out.println();
            for (int c = 0; c < y; c++) {
                System.out.print("Party: " + this.ArrayOfNode[r][c].getParty());
                System.out.print(" district: " + districtTracker.get(this.ArrayOfNode[r][c]));
                System.out.println(" location: " + r + " , " + c);
            }
        }
        System.out.println();
    }

    /**
     * Creates districts based on columns. Once district is created a separate
     * method is called to output the information about the parties within that
     * district. For the map key = voter party, value = district number.
     */
    public void createColumnDistricts() {
        int district = 0;
        for (int r = 0; r < x; r++) {
            district++;
            for (int c = 0; c < y; c++) {
                districtTracker.put(this.ArrayOfNode[c][r], district);
            }
        }
        outputDistrictColumnInformation();

        callFavoredParty();
    }

    /**
     * Outputs the columns district information about the included parties.
     */
    public void outputDistrictColumnInformation() {
        System.out.print("------------------------------------------\n");
        System.out.print("These are the districts created by columns\n");
        System.out.print("------------------------------------------");
        for (int i = 0; i < x; i++) {
            System.out.println();
            for (int j = 0; j < y; j++) {
                System.out.print("Party: " + this.ArrayOfNode[j][i].getParty());
                System.out.print(" district: " + districtTracker.get(this.ArrayOfNode[j][i]));
                System.out.println(" location: " + j + " , " + i);
            }
        }
        System.out.println();
    }

    /**
     * Checks the district to determine which party is favored within the
     * district.
     *
     * @param district the district to be checked.
     * @param districtSize the size of the district to be checked.
     */
    public void checkFavoredParty(int district, int districtSize) {
        int aFavored = 0;
        int bFavored = 0;
        int districtCounter = 0;
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                if (this.ArrayOfNode[i][j].getParty() == 1
                        && districtTracker.get(this.ArrayOfNode[i][j]) == district) {
                    aFavored++;
                    districtCounter++;
                    if (districtCounter == districtSize) {
                        printFavoredParty(district, aFavored, bFavored);
                        districtCounter = 0;
                    }
                } else if (this.ArrayOfNode[i][j].getParty() == 0
                        && districtTracker.get(this.ArrayOfNode[i][j]) == district) {
                    bFavored++;
                    districtCounter++;
                    if (districtCounter == districtSize) {
                        printFavoredParty(district, aFavored, bFavored);
                        districtCounter = 0;
                    }
                }
            }//end inner loop  
        }//end outer loop
    }//end of method

    /**
     * prints the favored party.
     *
     * @param district
     * @param aParty
     * @param bParty
     */
    public void printFavoredParty(int district, int aParty, int bParty) {
        if (aParty > bParty) {
            dialogForPrintFavoredParty(district, "A", aParty, bParty);
        } else if (bParty > aParty) {
            dialogForPrintFavoredParty(district, "B", aParty, bParty);
        } else if (aParty == bParty) {
            System.out.println("Neither of the parties are favored in district "
                    + district + "");
            System.out.println("The number of voters in district " + district
                    + " associated with party A: " + aParty);
            System.out.println("The number of voters in district " + district
                    + " associated with party B: " + bParty);
            System.out.println();
        }
    }

    /**
     * The dialog to be output to the user based on the favored party within a
     * district.
     *
     * @param district
     * @param favoredParty
     * @param aParty
     * @param bParty
     */
    public void dialogForPrintFavoredParty(int district, String favoredParty,
            int aParty, int bParty) {
        System.out.println("The favored party in district " + district
                + " is party " + favoredParty);
        System.out.println("The number of voters in district " + district
                + " associated with party A: " + aParty);
        System.out.println("The number of voters in district " + district
                + " associated with party B: " + bParty);
        System.out.println();
    }

    private int setDistrictSize() {
        int regionSize = y * x;
        int defaultSize = 0;
        for (int digits = 1; digits < 10; digits++) {
            if (regionSize % digits == 0) {
                districtSizeOptions.add(digits);
            }
        }
        defaultSize = districtSizeOptions.get(1);
        //System.out.println("Size of the districts in the region: " + districtSizeOptions.get(1));
        return defaultSize;
    }

    public int getDistrictSize() {
        return setDistrictSize();
    }

    private int setNumberOfDistricts() {
        int regionSize = y * x;
        int numOfDistricts = regionSize / getDistrictSize();
        return numOfDistricts;
    }

    public int getNumberOfDistricts() {
        return setNumberOfDistricts();
    }

    /**
     * This checking if particular node is at the edge of our map.
     *
     * @param point to be checked.
     * @return true if node is the edge, false if node is not the edge.
     */
    public boolean isEdge(NodePoint point) {
        return this.ArrayOfNode[point.getHeight()][point.getWidth()].isEdge();
    }

    public int getHeight() {
        return this.y;
    }

    public int getWidth() {
        return this.x;
    }

    public boolean isMarked(NodePoint point) {
        return this.ArrayOfNode[point.getHeight()][point.getWidth()].isMarked();
    }

    public void setDistrct(NodePoint point, int dist) {
        this.ArrayOfNode[point.getHeight()][point.getWidth()].setDirsting(dist);
    }

}
