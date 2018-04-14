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
        System.out.println("checkInBounds");
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
        System.out.println("checkInBounds");
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
     * Test of sleutel pickup, op dezelfde plek staand
     */
    @Test
    public void checkSleutelOppakkenSameSpot() {
        Vlak[][] vlakGrid = new Vlak[10][10];
        boolean result = false;
        
        for(int i=0;i<10;i++) {     
            for(int j=0;j<10;j++) {
                vlakGrid[i][j] = new Vlak(i, j);
            }
        }
        
        vlakGrid[1][2].objects.add(new Sleutel(1,2,100));
        
        Speler speler = new Speler(1, 2);
        
        int speler_x = speler.getXpos();
        int speler_y = speler.getYpos();
        
        for(VlakObject vlakobj : vlakGrid[speler_x][speler_y].objects) {
            if(vlakobj instanceof Sleutel) {
                result = true;
                speler.setHuidigeSleutel(((Sleutel) vlakobj).getWaarde());
                vlakGrid[speler_x][speler_y].objects.remove(0);
            }
        }
        boolean expResult = true;
        assertEquals(expResult, result);
    }
}
