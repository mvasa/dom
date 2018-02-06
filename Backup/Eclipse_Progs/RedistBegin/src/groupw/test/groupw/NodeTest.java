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

/**
 *
 * @author chris_000
 */
public class NodeTest {
    
    public NodeTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @Before
    public void setUp() {
    }



    @Test
    public void testGetDistrict() {
        System.out.println("getDistrict");
        Node instance = new Node();
        int expResult = 0;
        int result = instance.getDistrict();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testSetDirsting() {
        System.out.println("setDirsting");
        int newDistrict = 0;
        Node instance = new Node();
        instance.setDirsting(newDistrict);
        fail("The test case is a prototype.");
    }

    @Test
    public void testIsMarked() {
        System.out.println("isMarked");
        Node instance = new Node();
        boolean expResult = false;
        boolean result = instance.isMarked();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testIsEdge() {
        System.out.println("isEdge");
        Node instance = new Node();
        boolean expResult = false;
        boolean result = instance.isEdge();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testSetEdge() {
        System.out.println("setEdge");
        boolean edge = false;
        Node instance = new Node();
        instance.setEdge(edge);
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetParty() {
        System.out.println("getParty");
        Node instance = new Node();
        int expResult = 0;
        int result = instance.getParty();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testSetParty() {
        System.out.println("setParty");
        int party = 0;
        Node instance = new Node();
        instance.setParty(party);
        fail("The test case is a prototype.");
    }

    @Test
    public void testToString() {
        System.out.println("toString");
        Node instance = new Node();
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }
    
}
