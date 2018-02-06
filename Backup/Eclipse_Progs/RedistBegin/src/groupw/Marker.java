
package groupw;

import java.util.ArrayList;

/**
 *
 * @author chris_000
 */
public class Marker {

    private NodePoint startingNode;
    private int nodesCounted;
    ArrayList<NodePoint> visitedNodes;
    NodeMap map;
    private int district;
    
    /**
     * Constructor for Marker that takes a NodeMap
     * @param map NodeMap
     */
    Marker(NodeMap nodeMap){
        this.map=nodeMap;
        nodesCounted=0;
        ArrayList<NodePoint> visitedNodeList= new ArrayList();
        this.visitedNodes=visitedNodeList;
        this.startingNode = new NodePoint(0,0);
        district=0;
    }
    
    /**
     * Public method to create districts based on the default values
     */
    public void createDistricts(){
        createDistrict();
    }
    
    /**
     * private method that holds the logic to create one contiguous district. 
     * This will alter the district that nodes hold in the map. 
     */
    void createDistrict(){
        setStartPoint();
        this.map.setDistrct(startingNode,district );
        this.visitedNodes.clear();
        this.visitedNodes.add(startingNode);
        
    }
    
    /**
     * This should choose a random non edge starting point for the starting node
     * for a district this logic is to be used in createDistrict
     */
    private void setStartPoint(){
        while(map.isEdge(startingNode)||map.isMarked(startingNode)){
        int x=map.getHeight()-2;
        int y=map.getWidth()-2;
        x=(int)(Math.random()*x);
        x=x+1;
        y=(int)(Math.random()*y);
        y=y+1;
        startingNode=new NodePoint(x,y);
        }
    }
    /**
     * This holds the logic for getting the next node to try for creating
     * a district
     * @return NodePoint 
     */
    private NodePoint getNextNode(){
        NodePoint potentialNode = this.visitedNodes.get(this.visitedNodes.size()-1);
        while(false==validPoint(potentialNode)){
           int x=(int)(Math.random()-.5);
           x=1*x;
           potentialNode.setHeight(potentialNode.getWidth()+x);
           int y=(int) (Math.random()-.5);
           y=1*y;
           potentialNode.setHeight(potentialNode.getHeight()+y);
        }
        return potentialNode;
    } 
    
    /**
     * 
     * @param point
     * @return true if the node point is a non edge and non marked point
     */
    private boolean validPoint(NodePoint point){
        if(map.isEdge(point)|| map.isMarked(point) )
            return false;
        else 
            return true;
    }
    

}
