package test.edu.chl.proximity.Models.Map.MouseOver;

import edu.chl.proximity.Models.Map.MouseOver.MouseOverBox;
import edu.chl.proximity.Utilities.ProximityVector;
import junit.framework.TestCase;

/**
 * @author Johan
 * @date 2015-05-29.
 */
public class MouseOverBoxTest extends TestCase {

    public void testContainsPoint() throws Exception {
        MouseOverBox box = new MouseOverBox(123, "test");
        assertFalse(box.containsPoint(new ProximityVector(100, 100)));
        box.enable(new ProximityVector(100,100));
        assertTrue(box.containsPoint(new ProximityVector(100, 100)));
        assertTrue(box.containsPoint(new ProximityVector(100,80)));
        assertFalse(box.containsPoint(new ProximityVector(0, 223)));
        assertFalse(box.containsPoint(null));
    }

    public void testEnable() throws Exception {

        MouseOverBox box = new MouseOverBox(123, "test");
        assertTrue(box.getPosition().equals(new ProximityVector(0,0))); //we want popups with unspecified locations to show in upper left corner
        box.enable(new ProximityVector(100, 100));
        assertTrue(box.containsPoint(new ProximityVector(100,100)));



        MouseOverBox boxLong = new MouseOverBox(123, "This is long text that the object will have to wrap to the next row");
        boxLong.enable(new ProximityVector(100,100));
        assertTrue(boxLong.containsPoint(new ProximityVector(120,100)));
    }

    public void testDisable() throws Exception {

        MouseOverBox box = new MouseOverBox(123, "test");
        box.enable(new ProximityVector(100,100));
        assertTrue(box.isEnabled());
        box.disable();
        assertFalse(box.isEnabled());
    }

}