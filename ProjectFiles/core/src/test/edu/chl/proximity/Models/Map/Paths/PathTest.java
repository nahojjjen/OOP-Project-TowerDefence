package test.edu.chl.proximity.Models.Map.Paths;

import edu.chl.proximity.Models.Map.Paths.ConcretePaths.FirstPath;
import edu.chl.proximity.Models.Map.Paths.Path;
import edu.chl.proximity.Utilities.ProximityVector;
import junit.framework.TestCase;

/**
 * @author Johan
 * @date 2015-05-29.
 */
public class PathTest extends TestCase {

    public void testGetWaypoints() throws Exception {
        Path path = new FirstPath();
        assertTrue(path.getWaypoints().size() > 0);
    }


    public void testGetWaypoint() throws Exception {
        Path path = new FirstPath();
        assertTrue(path.getWaypoint(-1).equals(path.getWaypoint(0)));
        assertTrue(path.getWaypoint(9999).equals(path.getWaypoint(123)));
        assertFalse(path.getWaypoint(2).equals(path.getWaypoint(3)));
    }

    public void testIsPointOnPath() throws Exception {
        Path path = new FirstPath();
        ProximityVector firstPoint = path.getWaypoint(0);
        assertTrue(path.isPointOnPath(firstPoint));
        assertFalse(path.isPointOnPath(new ProximityVector(123, 123))); //point not on path
        assertFalse(path.isPointOnPath(null));

    }

    public void testIsPointBetween() throws Exception {
        Path path = new FirstPath();
        ProximityVector firstPoint = path.getWaypoint(0);
        assertTrue(path.isPointBetween(path.getWaypoint(0), path.getWaypoint(1), firstPoint));
        assertFalse(path.isPointBetween(null, null, null));
        assertFalse(path.isPointBetween(path.getWaypoint(0),path.getWaypoint(1),null));
    }

}