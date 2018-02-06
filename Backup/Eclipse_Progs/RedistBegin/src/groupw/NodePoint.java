package groupw;

/**
 *
 * @author chris_000
 */
public class NodePoint {

    private int y;
    private int x;

    /**
     * Constructor of a NoidPoint meant to ensure acceptable values
     * are passed, rows and columns must be 0 or larger and smaller than 10. 
     * This class will include 0 as a row and column.
     * @param rows
     * @param columns 
     */
    NodePoint(int rows, int columns) {
        
        if(rows<0){
            this.y=0;
        }
        else if(rows>9){
            this.y=9;
        }
        else {
        y = rows;
        }
        if(columns<0){
            this.x=0;
        }
        else if(columns>9){
            this.x=9;
        }
        else {
        x = columns;
        }
    }

    /**
     * Accepts values that are 0 or larger all values lower will set row to 0
     * The maximum height at this time is 9.
     * @param rows 
     */
    public void setHeight(int rows) {
        if(rows<0){
            this.y=0;
        }
        else if(rows>9){
            this.y=9;
        }
        else {
        y = rows;
        }
    }

    public int getHeight() {
        return this.y;
    }

    /**
     * Accepts values that are 0 or larger all values lower will set row to 0
     * The maximum height at this time is 9.
     * @param columns 
     */
    void setWidth(int columns) {
        if(columns<0){
            this.x=0;
        }
        else if(columns>9){
            this.x=9;
        }
        else {
        x = columns;
        }
    }

    public int getWidth() {
        return this.x;
    }

}
