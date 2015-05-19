package test.edu.chl.proximity.Models;

import edu.chl.proximity.Models.Map.Maps.StandardMap;
import edu.chl.proximity.Models.Utils.Image;
import edu.chl.proximity.Utilities.ProximityVector;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Johan on 2015-05-02.
 * A testing class for boardObject
 *
 * 13/05 Modified by Simon Gislen to update BoardObject constructor
 */
public class BoardObjectTest {


    @Test
    public void testGetPosition() throws Exception {
        BoardObjectConcreteTest test = new BoardObjectConcreteTest(new ProximityVector(100,100), null, 90, 50, 50);
        assertTrue(test.getPosition().equals(new ProximityVector(100,100)));
    }

    @Test
    public void testSetPosition() throws Exception {

        BoardObjectConcreteTest test = new BoardObjectConcreteTest(new ProximityVector(100,100), null, 90, 50, 50);
        test.setPosition(new ProximityVector(123,123));
        assertTrue(test.getPosition().equals(new ProximityVector(123,123))); //normal case

        test.setPosition(null);
        assertTrue(test.getPosition() == null); //null case

        test.setPosition(new ProximityVector(-123,-123));
        assertTrue(test.getPosition().equals(new ProximityVector(-123,-123)));//negative case
    }

    @Test
    public void testGetImage() throws Exception {
        BoardObjectConcreteTest test = new BoardObjectConcreteTest(new ProximityVector(100,100), null, 90, 50, 50);
        assertTrue(test.getImage() == null);
    }



    @Test
    public void testGetAngle() throws Exception {
        BoardObjectConcreteTest test = new BoardObjectConcreteTest(new ProximityVector(100,100), null, 90, 50, 50);
        assertTrue(test.getAngle() == 90);
        BoardObjectConcreteTest test2 = new BoardObjectConcreteTest(new ProximityVector(100,100), null, -90, 50, 50);
        assertTrue(test2.getAngle() == -90);
        BoardObjectConcreteTest test3 = new BoardObjectConcreteTest(new ProximityVector(100,100), null, 0, 50, 50);
        assertTrue(test3.getAngle() == 0);
        BoardObjectConcreteTest test4 = new BoardObjectConcreteTest(new ProximityVector(100,100), null, 360, 50, 50);
        assertTrue(test4.getAngle() == 360);
    }

    @Test
    public void testSetAngle() throws Exception {
        BoardObjectConcreteTest test = new BoardObjectConcreteTest(new ProximityVector(100,100), null, 90, 50, 50);
        test.setAngle(0);
        assertTrue(test.getAngle() == 0);
        test.setAngle(999);
        assertTrue(test.getAngle() == 999);
    }

    @Test
    public void testRotate() throws Exception {
        BoardObjectConcreteTest test = new BoardObjectConcreteTest(new ProximityVector(100,100), null, 90, 50, 50);
        test.rotate(90);
        assertTrue(test.getAngle() == 180);
        test.rotate(-90);
        assertTrue(test.getAngle() == 90);
        double currentAngle = test.getAngle();
        test.rotate(0);
        assertTrue(test.getAngle() == currentAngle );
    }


    @Test
    public void testGetAndSetWidth() throws Exception {
        BoardObjectConcreteTest test = new BoardObjectConcreteTest(new ProximityVector(100,100), null, 90, 0, 0);
        assertTrue(test.getWidth() == 0);
        test.setWidth(100);
        assertTrue(test.getWidth() == 100);
        test.setWidth(-100);
        assertTrue(test.getWidth() == -100);
    }



    @Test
    public void testGetAndSetHeight() throws Exception {
        BoardObjectConcreteTest test = new BoardObjectConcreteTest(new ProximityVector(100,100), null, 90, 0, 0);
        assertTrue(test.getHeight() == 0);
        test.setHeight(100);
        assertTrue(test.getHeight() == 100);
        test.setHeight(-100);
        assertTrue(test.getHeight() == -100);
    }

    @Test
    public void testIsOutsideView() throws Exception {
        BoardObjectConcreteTest test = new BoardObjectConcreteTest(new ProximityVector(100,100), null, 90, 0, 0);
        assertFalse(test.isOutsideView()); //normal case
        test.setPosition(new ProximityVector(0, 0));
        assertFalse(test.isOutsideView());  //edge of display upper left corner
        test.setPosition(new ProximityVector(1280, 720));
        assertFalse(test.isOutsideView()); //edge of display lower right corner
        test.setPosition(null);
        assertTrue(test.isOutsideView()); // null case
        test.setPosition(new ProximityVector(1281, 123));
        assertTrue(test.isOutsideView()); // null case

    }

    @Test public void testRemove(){
        BoardObjectConcreteTest test = new BoardObjectConcreteTest(new ProximityVector(12,12), null, 1,1,1);
       assertFalse(test.isRemoved());
        test.remove();
        assertTrue(test.isRemoved());
        test.unRemove();
        assertFalse(test.isRemoved());
    }
    @Test
    public void testFaceTarget() throws Exception {
        BoardObjectConcreteTest test = new BoardObjectConcreteTest(new ProximityVector(100,100), null, 90, 0, 0);
        test.faceTarget(test.getPosition());
        assertTrue(test.getAngle() == 0);        //trying to face itself test, undefined mathematically
        test.faceTarget(new ProximityVector(100, 200));
        assertTrue(test.getAngle() == 90);      //normal case
        test.setAngle(172);
        test.faceTarget(null);
        assertTrue(test.getAngle() == 172);       //testing null case, null should not have changed the angle
    }

    @Test
    public void testAddList(){
        BoardObjectConcreteTest test = new BoardObjectConcreteTest(new ProximityVector(12,12), null, 1,1,1);
        test.getAddList().add(test);
        assertTrue(test.getAddList().size() == 1);
        test.clearAddList();
        assertTrue(test.getAddList().size() == 0);
    }
    @Test
    public void testGetandSetCenter() throws Exception {
        BoardObjectConcreteTest test = new BoardObjectConcreteTest(new ProximityVector(100,100), null, 90, 0, 0);
        assertTrue(test.getCenter().equals(new ProximityVector(100,100)));
        test.setCenter(new ProximityVector(0, 0));
        assertTrue(test.getCenter().equals(new ProximityVector(0,0)));
    }



    @Test
    public void testContainsPoint() throws Exception {
        BoardObjectConcreteTest test = new BoardObjectConcreteTest(new ProximityVector(100,100), null, 90, 0, 0);
        test.setWidth(100);
        test.setHeight(100);
        assertFalse(test.containsPoint(new ProximityVector(-100, -100))); //outside screen test
        test.setPosition(new ProximityVector(0, 0));
        assertTrue(test.containsPoint(new ProximityVector(50, 50))); //normal case
        assertTrue(test.containsPoint(new ProximityVector(100,100)));   //edge case
    }

    @Test
    public void testClone() throws Exception {

        BoardObjectConcreteTest test = new BoardObjectConcreteTest(new ProximityVector(100,100), null, 90, 0, 0);
        Object testCloneObject = test.clone();
        BoardObjectConcreteTest testClone = (BoardObjectConcreteTest) testCloneObject;
        assertTrue(test.getAngle() == testClone.getAngle());
        assertTrue(test.getPosition().equals(testClone.getPosition()));
        assertTrue(test.getHeight() == testClone.getHeight());
        assertTrue(test.getImage() == testClone.getImage());
        test.setPosition(new ProximityVector(123,123));
        assertFalse(test.getPosition().equals(testClone.getPosition()));
        test.setWidth(1232);
        assertFalse(test.getWidth() == testClone.getWidth());
        test.setAngle(1);
        assertFalse(test.getAngle() == testClone.getAngle());


    }
}