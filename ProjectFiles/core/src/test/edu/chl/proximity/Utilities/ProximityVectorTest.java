package test.edu.chl.proximity.Utilities;

import edu.chl.proximity.Utilities.ProximityRandom;
import edu.chl.proximity.Utilities.ProximityVector;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 *@author Johan
 * @date 2015-05-18.
 */
public class ProximityVectorTest {

    @Test
    public void testEquals() throws Exception {
        ProximityVector v1 = new ProximityVector(100,100);
        ProximityVector v2 = new ProximityVector(100,100);
        ProximityVector v3 = new ProximityVector(10,100);
        assertTrue(v1.equals(v2));
        assertFalse(v1.equals(v3));
        ProximityRandom dumbTest=new ProximityRandom();
        assertFalse(v1.equals(dumbTest));
    }

    @Test
    public void testAddAndSub() throws Exception {

        ProximityVector v1 = new ProximityVector(100,100);
        ProximityVector v2 = new ProximityVector(100,100);
        ProximityVector v3 = null;
        ProximityVector v4 = new ProximityVector(-50,-50);
        assertTrue(v1.x == 100);
        v1.add(v2);
        assertTrue(v1.x == 200);
        v1.add(v3);
        assertTrue(v1.x == 200);
        v1.add(v4);
        assertTrue(v1.x == 150);
        v1.sub(v1);
        assertTrue(v1.x == 0 && v1.y == 0);
        v1.add(v1);
        assertTrue(v1.x == 0 && v1.y == 0);
        v1.sub(v3);
        assertTrue(v1.x == 0 && v1.y == 0);
    }




    @Test
    public void testInvert() throws Exception {

        ProximityVector v1 = new ProximityVector(100,100);
        v1.invert();
        assertTrue(v1.x == -100 && v1.y == -100);

    }
}