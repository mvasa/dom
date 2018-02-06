
package groupw;

/**
 *
 * @author chris_000
 */
public class ProgramRunner {

     public static void main(String args[]) {
        NodeMap testMap = new NodeMap(4,4);
        testMap.print();
        Marker testMarker = new Marker(testMap);
        testMarker.createDistricts();
        System.out.println();
        testMarker.map.print();
        System.out.println();
        
       System.out.println("printing row districts");
        testMap.createRowDistricts();
        
        //System.out.println("printing column districts");
        //testMap.createColumnDistricts();
        
        
    }
}


