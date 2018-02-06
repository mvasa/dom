package groupw;

import java.util.ArrayList;

/**
 *
 * @author chris_000
 */
public class ProgramRunner {

    public static void main(String args[]) {
//        Interface testInterface = new Interface();
//        testInterface.getInputForFavoredParty();
//        ArrayList<Integer> dimensions = testInterface.getDimensions();
//        NodeMap testMap = new NodeMap(dimensions.get(0).intValue(), dimensions.get(1).intValue());
//        testMap.print();
        NodeMap testMap = new NodeMap(5, 5);
        testMap.assignParties();
        testMap.printParties();
        Marker testMarker = new Marker(testMap);
        testMarker.createDistricts();
//        testMarker.map.assignParties();
//        testMarker.map.printParties();
         testMap.createRowDistricts();
        testMap.createColumnDistricts();
    }
}
