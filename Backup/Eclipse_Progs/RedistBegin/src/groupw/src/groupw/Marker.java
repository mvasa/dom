
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
    
    Marker(NodeMap map){
        this.map=map;
        nodesCounted=0;
        ArrayList<NodePoint> visitedNodeList= new ArrayList();
        this.visitedNodes=visitedNodeList;
        this.startingNode = new NodePoint(0,0);
        district=0;
    }
    
    public void createDistricts(){
        createDistrict();
    }
    
    void createDistrict(){
        setStartPoint();
        this.map.setDistrct(startingNode,district );
        this.visitedNodes.clear();
        this.visitedNodes.add(startingNode);
        while(this.visitedNodes.size()<map.getDistrictSize()){
             NodePoint nextNode =getNextNode();
            
       }
    }
    
    private void setStartPoint(){
        while(map.isEdge(startingNode)){
        int x=map.getHeight()-2;
        int y=map.getWidth()-2;
        x=(int)(Math.random()*x);
        x=x+1;
        y=(int)(Math.random()*y);
        y=y+1;
        startingNode=new NodePoint(x,y);
        }
    }
    
    private NodePoint getNextNode(){
        NodePoint potentialNode = this.visitedNodes.get(this.visitedNodes.size()-1);
        while(false==validPoint(potentialNode)){
           int x=(int)(Math.random()-.5);
           x=1*x;
           potentialNode.setHeight(potentialNode.getWidth()+x);
           int y=(int) (Math.random()-.5);
           y=1*y;
           potentialNode.setHeight(potentialNode.height+y);
        }
        return potentialNode;
    } 
    
    private boolean validPoint(NodePoint point){
        if(map.isEdge(point)|| map.isMarked(point) )
            return false;
        else 
            return true;
    }
    

}
