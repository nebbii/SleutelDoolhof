/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sleuteldoolhof;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author nebbii
 */
public class SpeelveldTest {
    
    public SpeelveldTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of checkInBounds method, of class Speelveld.
     */
    @Test
    public void testCheckInBoundsMinusX() {
        System.out.println("testCheckInBoundsMinusX");
        int x = -2;
        int y = 0;
        int vb = 10;
        int vh = 10;
        boolean expResult = false;
        boolean result = Speelveld.checkInBounds(x, y, vb, vh);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of checkInBounds method, of class Speelveld.
     */
    @Test
    public void testCheckInBoundsMinusY() {
        System.out.println("checkInBounds");
        int x = 3;
        int y = 11;
        int vb = 10;
        int vh = 10;
        boolean expResult = false;
        boolean result = Speelveld.checkInBounds(x, y, vb, vh);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of checkInBounds method, of class Speelveld.
     */
    @Test
    public void testCheckInBoundsXRange() {
        System.out.println("testCheckInBoundsXRange");
        int x = 5;
        int y = 0;
        int vb = 10;
        int vh = 10;
        boolean expResult = true;
        boolean result = Speelveld.checkInBounds(x, y, vb, vh);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of checkInBounds method, of class Speelveld.
     */
    @Test
    public void testCheckInBoundsYRange() {
        System.out.println("checkInBounds");
        int x = 3;
        int y = 9;
        int vb = 10;
        int vh = 10;
        boolean expResult = true;
        boolean result = Speelveld.checkInBounds(x, y, vb, vh);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of moveSpeler method, of class Speelveld.
     */
    @Test
    public void testMoveSpeler() {
        System.out.println("moveSpeler");
        Speler speler = new Speler(0,0);
        Speelveld.loadPuzzle1(speler);
        String richting = "down";
        int vb = 10;
        int vh = 10;
        Speelveld.moveSpeler(speler, richting, vb, vh);
        boolean expResult = true;
        boolean result = (speler.getYpos()==1);
        
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    /**
     * Test of moveSpeler method, of class Speelveld.
     * De speler loopt 100 richting het eind, maar er staat een barricade in de weg.
     * Het verwachtte resultaat is dat de speler er niet doorheen komt
     */
    @Test
    public void testMoveSpelerTegenBarricade() {
        System.out.println("moveSpeler");
        Speler speler = new Speler(0,0);
        Speelveld.loadPuzzle2(speler);
        String richting = "right";
        int vb = 10;
        int vh = 10;
        for(int i=0;i<100;i++) {
            Speelveld.moveSpeler(speler, richting, vb, vh);
        }
        boolean expResult = true;
        boolean result = (speler.getXpos()==7);
        
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
}
