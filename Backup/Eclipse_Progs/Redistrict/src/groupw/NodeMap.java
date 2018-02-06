package groupw;

import java.util.Arrays;
import java.util.*;

/**
 *
 * @author chris_000
 */
public class NodeMap {
    public Node[][] ArrayOfNode;
    int height;
    int width;
    Map<Node, Integer> districtTracker = new HashMap<>();
    ArrayList<Integer> districtSizeOptions = new ArrayList<Integer>();
    NodeMap(){
       
    }
    NodeMap(int height, int width){ 
         this.height=height+2;
         this.width=width+2;
         Node[][] ArrayOfNodes = new Node[this.height][this.width];
         ArrayOfNode=ArrayOfNodes;
         this.setBorders();
         this.setInterior();
    }    
    
    private void setBorders(){
        for(int i=0;i<width;i++){
            this.ArrayOfNode[i][0]=new Node(true);
            this.ArrayOfNode[i][height-1]=new Node(true);
        }
        for(int i=0; i<height; i++){
           this.ArrayOfNode[0][i]=new Node(true);
           this.ArrayOfNode[width-1][i]=new Node(true);
        }
    }
    
    private void setInterior(){
        for(int i=1;i<height-1;i++){
            for(int j=1;j<width-1;j++){
                this.ArrayOfNode[i][j]=new Node(false,1);
            }
        }
    }
  @Override
  public String toString(){
     return Arrays.toString(this.ArrayOfNode);
  }
  public void print(){
      for(int j=0;j<height;j++){
          for(int i=0;i<width;i++){
              //System.out.print(this.ArrayOfNode[j][i]+" ");
 		  //assigns party to 1 or 0 depending on random number
        	  //and prints 
        	  if(Math.rint(Math.random()) == 1){
        		  this.ArrayOfNode[j][i].setParty(1);
        		  System.out.print("A" + this.ArrayOfNode[j][i].getParty() + " ");
        	  }
        	  else{
        		  this.ArrayOfNode[j][i].setParty(0);
        		  System.out.print("B" + this.ArrayOfNode[j][i].getParty() + " ");
        	  }

              
          }
          System.out.println("");
      }
  }
  public void createRowDistricts(){
	  int r = 0;
	  int c = 0;
	  int district = 0;
	  
	  //this loop puts all the voters in the region 
	  //into a map of key value pairs
	  //key = voter party
	  //value = district number
	  for(r = 0; r < width; r++){
		  district++;
		  
		  
		  for(c = 0; c < height; c++){
			  
			  districtTracker.put(this.ArrayOfNode[r][c], district); 
		  }
	  }
	  //this loop prints out the each voter party and each district number
	  for(r = 0; r < width; r++){  
		  System.out.println();
		  for(c = 0; c < height; c++){
			  System.out.print("Party: " + this.ArrayOfNode[r][c].getParty());			  
			  System.out.print(" district: " + districtTracker.get(this.ArrayOfNode[r][c]));  
                          System.out.println(" location: "+ r +" , "+c);
		  }  
	  }
	 int inc = 0;
	 district = 1;
	 int districtSize = width;
	 int numOfDistricts = height;
	 for(inc = 0; inc < numOfDistricts; inc ++){
		 checkFavoredParty(district, districtSize);
		 district++;
	 }
	 System.out.println("YES!");
		  
}

//////////////////////////////testing column districts

public void createColumnDistricts(){
	  int r = 0;
	  int c = 0;
	  int district = 0;
	  //int numOfDistricts = 0;
	  //this loop puts all the voters in the region 
	  //into a map of key value pairs
	  //key = voter party
	  //value = district number
	  for(r = 0; r < width; r++){
		  district++;
		 // numOfDistricts++;
		  
		  for(c = 0; c < height; c++){
			   
			  districtTracker.put(this.ArrayOfNode[c][r], district); 
		  }
	  }
	  //this loop prints out the each voter party and each district number
	  for(r = 0; r < width; r++){  
		  System.out.println();
		  for(c = 0; c < height; c++){
			  System.out.print("Party: " + this.ArrayOfNode[c][r].getParty());			  
			  System.out.println(" district: " + districtTracker.get(this.ArrayOfNode[c][r]));  
		  }  
	  }
	 int inc = 0;
	 district = 0;
	 int districtSize = height;
	 int numOfDistricts = width;
	 for(inc = 0; inc < numOfDistricts; inc ++){
		 district++;
		 checkFavoredParty(district, districtSize);
		 
	 }
	 //getNumberOfDistricts();
	 //getDistrictSize();
	// System.out.println("district size options: "+ districtSizeOptions);
		  
}

public void checkFavoredParty(int district, int districtSize){
	int aFavored = 0;
	int bFavored = 0;
	int i = 0;
	int j = 1;
	int districtCounter = 0;
	
	for(i = 0; i < width; i++){  
		for(j = 0; j < height; j++){
			if(this.ArrayOfNode[i][j].getParty() == 1 && 
				districtTracker.get(this.ArrayOfNode[i][j]) == district){
				aFavored++;
				districtCounter++;
				if(districtCounter == districtSize){
					printFavoredParty(district, aFavored, bFavored);
					districtCounter = 0;				
				}
			}
			else if(this.ArrayOfNode[i][j].getParty() == 0 && 
				districtTracker.get(this.ArrayOfNode[i][j]) == district){
				bFavored++;
				districtCounter++;
				if(districtCounter == districtSize){
					printFavoredParty(district, aFavored, bFavored);
					districtCounter = 0;		
				}
			}			
		 }//end inner loop  
	 }//end outer loop
}//end of method

public void printFavoredParty(int district,int aParty, int bParty){
	if(aParty > bParty){
		System.out.println("The favored party in district " + district +" is party A");
		System.out.println("The number of voters in district " + district +" associated with party A: " + aParty);
		System.out.println("The number of voters in district " + district +" associated with party B: " + bParty);		
		System.out.println();
	}
	else if(bParty > aParty){
		System.out.println("The favored party in district " + district +" is party B");
		System.out.println("The number of voters in district " + district +" associated with party A: " + aParty);
		System.out.println("The number of voters in district " + district +" associated with party B: " + bParty);		
		System.out.println();
	}
	else if(aParty == bParty){
		System.out.println("Neither of the parties are favored in district " + district +"");
		System.out.println("The number of voters in district " + district +" associated with party A: " + aParty);
		System.out.println("The number of voters in district " + district +" associated with party B: " + bParty);		
		System.out.println();
	}					
}

private int setDistrictSize(){
	int regionSize = height*width;
	int digits = 1;
	int defaultSize = 0;
	for(digits = 1; digits < 10; digits++ ){
		if(regionSize % digits == 0){
			districtSizeOptions.add(digits);
		}
	}
	defaultSize = districtSizeOptions.get(1);
	//System.out.println("Size of the districts in the region: " + districtSizeOptions.get(1));
	return defaultSize;

}
public int getDistrictSize(){
	return setDistrictSize();
}

private int setNumberOfDistricts(){
	int regionSize = height*width;
	int numOfDistricts = regionSize/getDistrictSize();
	return numOfDistricts;
}

public int getNumberOfDistricts(){
	return setNumberOfDistricts();
}




  
  public boolean isEdge(NodePoint point ){
        return this.ArrayOfNode[point.height][point.width].isEdge();
      }
  public boolean isMarked(NodePoint point){
      return this.ArrayOfNode[point.height][point.width].isMarked();
  }
  public void setDistrct(NodePoint point, int dist){
      this.ArrayOfNode[point.height][point.width].setDirsting(dist);
  }

}
