/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package groupw;

import org.junit.Before;

import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.junit.*;
/**
 *
 * @author chris_000
 */
public class NodeMapTest {
    
    public NodeMapTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @Before
    public void setUp() {
    }

    @Test
    public void testPrintFavoredParty() {
    	 System.out.println("printFavoredParty");
         int district = 0;
         //test for both parties equal
         int aParty = 0;
         int bParty = 0;
         NodeMap instance = new NodeMap();
         instance.printFavoredParty(district, aParty, bParty);
         assertThat(district, equalTo(0));
         assertTrue(bParty == aParty);
         
         //test for party A favored
         int aPartyGreater = 1;
         int bPartyLower = 0;
         NodeMap CompareAGreater = new NodeMap();
         CompareAGreater.printFavoredParty(district, aPartyGreater, bPartyLower);
         assertTrue(aPartyGreater > bPartyLower);
         
         //test for party B favored
         int aPartyLower = 0;
         int bPartyGreater = 1;
         NodeMap CompareBGreater = new NodeMap();
         CompareBGreater.printFavoredParty(district, aPartyLower, bPartyGreater);
         assertTrue(bPartyGreater > aPartyLower);

    }

    @Test
    public void testGetDistrictSize() {
    	  System.out.println("getDistrictSize");
          NodeMap instance = new NodeMap(15,15);
          int expResult = 3;
          int result = instance.getDistrictSize();
          assertEquals(expResult, result);
    }

    @Test
    public void testGetNumberOfDistricts() {
        System.out.println("getNumberOfDistricts");
        NodeMap instance = new NodeMap(15,15);
        int expResult = 75;
        int result = instance.getNumberOfDistricts();
        assertEquals(expResult, result);
        
    }

    @Test
    public void testIsEdge() {
        NodeMap instance = new NodeMap();
        boolean expResult = false;
        boolean result = instance.isEdge(null);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testIsMarked() {
        System.out.println("isMarked");
        NodePoint point = null;
        NodeMap instance = new NodeMap();
        boolean expResult = false;
        boolean result = instance.isMarked(point);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testSetDistrct() {
        NodePoint point = new NodePoint(-1,-1);
        int dist = -10;
        NodeMap instance = new NodeMap(5,5);
        instance.setDistrct(point, dist);
        assert(true);
    }
    
}
