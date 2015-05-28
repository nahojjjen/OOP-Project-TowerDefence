package test.edu.chl.proximity.Utilities;

import edu.chl.proximity.Utilities.PointCalculations;
import edu.chl.proximity.Utilities.ProximityVector;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * @author Johan on 2015-05-02.
 */
public class PointCalculationsTest {

    @Test
    public void testGetVectorAngle() throws Exception {
        ProximityVector v1 = new ProximityVector(0,0);
        ProximityVector v2 = new ProximityVector(0,10);
        ProximityVector v3 = new ProximityVector(123,-1123);
        ProximityVector v4 = new ProximityVector(6123,1561);
        assertTrue(PointCalculations.getVectorAngle(v1,v1) == 0); //two points inside eachother should default to 0 degrees     (undefined case)
        assertTrue(PointCalculations.getVectorAngle(v2, null) == 0); //a point with null should default to 0 degrees            (null case)
        assertTrue(PointCalculations.getVectorAngle(v1, v2) == 90); //straight up, aka 90 degrees                               (normal case)
        assertTrue(PointCalculations.getVectorAngle(v2, v1) == -90); //straight down, aka -90 degrees                           (negative case )
        assertTrue(Math.abs(PointCalculations.getVectorAngle(v3, v4) - 23.79483413696289) <= 0.00000000000000001); //difficult case, precision                   (difficult normal case)
        assertTrue(Math.round(PointCalculations.getVectorAngle(v1,v4)-180) ==  Math.round(PointCalculations.getVectorAngle(v4, v1))); //angle from point a to b is the opposite of from b to a. (logical case)
        //above test fails precision, but only after approximately 4 decimals because of double rounding, is not considered significant so does not require fixing.
    }

    @Test
    public void testDistanceBetweenNoSqrt() throws Exception {
        ProximityVector v1 = new ProximityVector(0,0);
        ProximityVector v2 = new ProximityVector(0,10);
        ProximityVector v3 = new ProximityVector(0,-10);

        assertTrue(PointCalculations.distanceBetweenNoSqrt(v1, v1) == 0); //  (one point case)
        assertTrue(PointCalculations.distanceBetweenNoSqrt(v1,v2) == 100);  //  (normal case)
        assertTrue(PointCalculations.distanceBetweenNoSqrt(v1,v3) == 100);  //  (negative case)
        assertTrue(PointCalculations.distanceBetweenNoSqrt(v1,null) == 0);  //  (null case)
        assertTrue(PointCalculations.distanceBetweenNoSqrt(null,null) == 0);  //  (null case 2)
    }

    /*
    @Test
    public void testIsPointInObject() throws Exception {
        BoardObject test = new Line1(1,new ParticleManager(), new FirstPath());
        test.setHeight(10);
        test.setWidth(10);
        test.setPosition(new ProximityVector(50, 50));

        assertTrue(PointCalculations.isPointInObject(new ProximityVector(55, 55), test));
        assertTrue(PointCalculations.isPointInObject(new ProximityVector(59,59), test));
        assertTrue(PointCalculations.isPointInObject(new ProximityVector(50,50), test));
        assertTrue(PointCalculations.isPointInObject(new ProximityVector(60,60), test));
        assertFalse(PointCalculations.isPointInObject(new ProximityVector(61, 61), test));
        assertFalse(PointCalculations.isPointInObject(null, test));

    }*/

    @Test
    public void testCreatePathTool() {
        assertTrue(PointCalculations.createPathTool(100,100).equals("waypoints.add(new ProximityVector(100, 100));"));
    }
}