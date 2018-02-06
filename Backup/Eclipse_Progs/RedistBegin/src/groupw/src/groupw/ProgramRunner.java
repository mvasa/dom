package groupw;

import java.util.ArrayList;

/**
 *
 * @author chris_000
 */
public class ProgramRunner {

    public static void main(String args[]) {
//        Interface testInterface = new Interface();
//        testInterface.getInput();
//        ArrayList<Integer> dimensions = testInterface.getDimensions();
//        NodeMap testMap = new NodeMap(dimensions.get(0).intValue(), dimensions.get(1).intValue());
//        testMap.print();
        NodeMap testMap = new NodeMap(5, 5);
        testMap.print();
        Marker testMarker = new Marker(testMap);
        testMarker.createDistricts();
        System.out.println();
        testMarker.map.print();
        System.out.println();
        System.out.println("All the values in the map: ");
        testMap.createRowDistricts();
    }
}
