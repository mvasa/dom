
package groupw;

/**
 *
 * @author chris_000
 */
public class Node {

    private int population;
    private int district; 
    private boolean marked;
    private boolean edge; 
    private int party;
    
    Node(){
        population=1;
        district=0;
        marked=false;
        edge=false; 
        party=0;
    }
    
    Node(boolean edge){
        population=1;
        district=0;
        marked=false;
        this.edge=edge;
        party=0;
        if (edge){
            marked=true;
        }
    }
    
    Node(boolean edge, int party){
        population=1;
        district=0;
        marked=false;
        this.edge=edge;
        this.party=party;
    }
    
    public int getpopulation(){
        return this.population;
    }
   
    public int getDistrict(){
        return this.district;
    }
    public void setDirsting(int newDistrict){
        this.district=newDistrict;
    }
    public boolean isMarked(){
        return this.marked;
    }
    public boolean isEdge(){
        return this.edge;
    }
    public void setEdge(boolean edge){
        this.edge=edge;
    }
    public int getParty(){
        return this.party;
    }
    public void setParty(int party)
    {   this.party=party ;
    }
    
    @Override
    public String toString(){
        return ((Integer)district).toString();
    }
}
