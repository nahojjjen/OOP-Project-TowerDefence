package edu.chl.proximity.Utilities;

import com.badlogic.gdx.math.MathUtils;
import edu.chl.proximity.Models.BoardObject;

import java.awt.*;

/**
 * @author Johan Swanberg
 * @author Linda Evaldsson (revised)
 * @date 2015-04-02
 * 25/04 modified by Johan Swanberg, made distance calculation use vector 2 in all steps
 */
public class PointCalculations {
    private static MathUtils utils = new MathUtils();
    /**
     * Calculates the angle between two points
     *
     * @param firstPoint the first point of the vector
     * @param secondPoint the second point of the vector
     * @return The angle in degrees between two points
     */
    public static double getVectorAngle(ProximityVector firstPoint, ProximityVector secondPoint) {
        if (firstPoint != null && secondPoint != null) { //make sure there is a real vector
            return utils.radiansToDegrees*(utils.atan2(secondPoint.y-firstPoint.y,secondPoint.x-firstPoint.x));
        }
        return 0;
    }

    /**
     * Tool for creating new Paths. Prints out the code for the path.
     * @param x X position for a point on the path
     * @param y Y position for a point on the path
     */
    public static String createPathTool(int x, int y) {
        System.out.println("waypoint.add(new ProximityVector(" + x + ", " + y + "));");
        return new String("waypoint.add(new ProximityVector(" + x + ", " + y + "));");
    }

    /**
     * get the distance between two points, except no square root to optimize
     * speed useful if what you want to compare against is static and you can
     * hard-code the check against as a tower of 2. So for instance if you want
     * to check if the distance between 2 points is bigger than 10, use this
     * method instead of distanceBetween() and instead check if its bigger than
     * 100.
     *
     * @param p1 first point
     * @param p2 second point
     * @return length between these points
     */
    public static double distanceBetweenNoSqrt(ProximityVector p1, ProximityVector p2) {
        if (p1 != null && p2 != null) { //make sure there are 2 real poiints to measure from
            ProximityVector distanceVector = new ProximityVector(p2.x - p1.x, p2.y - p1.y);
            double length = distanceVector.x * distanceVector.x + distanceVector.y * distanceVector.y; //C^2 = (a^2+b^2)
            return length;
        }
        return 0;
    }

    /**
     * Checks if a point is within a BoardObject
     * @param point The Point that should be controlled
     * @param object The object that should be controlled
     * @return True of the point is within the object entered, false otherwise
     */
    public static boolean isPointInObject(ProximityVector point, BoardObject object) {
        if (point == null || object == null) return false;
        boolean isWithinHorizontally = object.getPosition().x <= point.x && object.getPosition().x + object.getWidth() >= point.x;
        boolean isWithinVertically = object.getPosition().y <= point.y && object.getPosition().y + object.getHeight() >= point.y;
        if(isWithinHorizontally && isWithinVertically) {
            return true;
        }
        return false;
    }
}
