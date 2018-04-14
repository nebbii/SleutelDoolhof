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
     * Test of main method, of class Speelveld.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        Speelveld.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of reset method, of class Speelveld.
     */
    @Test
    public void testReset() {
        System.out.println("reset");
        Speelveld.reset();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of renderCmd method, of class Speelveld.
     */
    @Test
    public void testRenderCmd() {
        System.out.println("renderCmd");
        Vlak[][] vlakGrid = null;
        Speelveld.renderCmd(vlakGrid);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of loadPuzzle1 method, of class Speelveld.
     */
    @Test
    public void testLoadPuzzle1() {
        System.out.println("loadPuzzle1");
        Speler speler = null;
        Speelveld.loadPuzzle1(speler);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of loadPuzzle2 method, of class Speelveld.
     */
    @Test
    public void testLoadPuzzle2() {
        System.out.println("loadPuzzle2");
        Speler speler = null;
        Speelveld.loadPuzzle2(speler);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkWin method, of class Speelveld.
     */
    @Test
    public void testCheckWin() {
        System.out.println("checkWin");
        Speler speler = null;
        boolean expResult = false;
        boolean result = Speelveld.checkWin(speler);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkSleutelOppakken method, of class Speelveld.
     */
    @Test
    public void testCheckSleutelOppakken() {
        System.out.println("checkSleutelOppakken");
        Speler speler = null;
        Speelveld.checkSleutelOppakken(speler);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkInBounds method, of class Speelveld.
     */
    @Test
    public void testCheckInBounds() {
        System.out.println("checkInBounds");
        int x = 0;
        int y = 0;
        int vb = 0;
        int vh = 0;
        boolean expResult = false;
        boolean result = Speelveld.checkInBounds(x, y, vb, vh);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of moveSpeler method, of class Speelveld.
     */
    @Test
    public void testMoveSpeler() {
        System.out.println("moveSpeler");
        Speler speler = null;
        String richting = "";
        int vb = 0;
        int vh = 0;
        Speelveld.moveSpeler(speler, richting, vb, vh);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
