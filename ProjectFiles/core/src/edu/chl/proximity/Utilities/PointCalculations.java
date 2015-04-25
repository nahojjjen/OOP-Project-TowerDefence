package edu.chl.proximity.Utilities;

import com.badlogic.gdx.math.Vector2;
import edu.chl.proximity.Models.BoardObject;

import java.awt.*;

/**
 * @author Johan Swanberg
 * @author Linda Evaldsson (revised)
 * @date 2015-04-02
 * 25/04 modified by Johan Swanberg, made distance calculation use vector 2 in all steps
 */
public class PointCalculations {

    /**
     * Calculates the angle between two points
     * todo: change to use Tan instead of cos, sin and sqrt.
     * todo unit-test: 0 degrees, two intersecting points, null points, normal points, negative points
     *
     * @param firstPoint the first point of the vector
     * @param secondPoint the second point of the vector
     * @return The angle in degrees between two points
     */
    public static double getVectorAngle(Vector2 firstPoint, Vector2 secondPoint) {
        if (firstPoint != null && secondPoint != null) { //make sure there is a real vector
            Point vector = new Point((int)secondPoint.x - (int)firstPoint.x, (int)secondPoint.y - (int)firstPoint.y); //to get a vector, subtract point a from point b
            double hypotenuse = Math.sqrt(vector.getX() * vector.getX() + vector.getY() * vector.getY());
            if (hypotenuse == 0) {
                return 0;
            }
            double angle = Math.acos(vector.getX() / hypotenuse);
            double angleDegrees = Math.toDegrees(angle);
            if (vector.getY() > 0) {
                return angleDegrees;
            }
            return -1 * angleDegrees;
        }
        return 0;
    }

    /**
     * Tool for creating new Paths. Prints out the code for the path.
     * @param x X position for a point on the path
     * @param y Y position for a point on the path
     */
    public static void createPathTool(int x, int y) {
        System.out.println("waypoint.add(new Vector2(" + x + ", " + y + "));");
    }

    /**
     * get the distance between two points
     *
     * @param p1 first point
     * @param p2 second point
     * @return length between these points
     */
    public static double distanceBetween(Vector2 p1, Vector2 p2) {

        if (p1 != null && p2 != null) { //make sure there are 2 real poiints to measure from
            Vector2 distanceVector = new Vector2((int)p2.x - (int)p1.x, (int)p2.y - (int)p1.y);
            double length = Math.sqrt(distanceVector.x * distanceVector.x + distanceVector.y * distanceVector.y); //C = sqrt(a^2+b^2)
            return length;
        }
        return 0;
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
    public static double distanceBetweenNoSqrt(Vector2 p1, Vector2 p2) {

        if (p1 != null && p2 != null) { //make sure there are 2 real poiints to measure from
            Vector2 distanceVector = new Vector2((int)p2.x - (int)p1.x, (int)p2.y - (int)p1.y);
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
    public static boolean isPointInObject(Vector2 point, BoardObject object) {
        boolean isWithinHorizontally = object.getPosition().x < point.x && object.getPosition().x + object.getWidth() > point.x;
        boolean isWithinVertically = object.getPosition().y < point.y && object.getPosition().y + object.getHeight() > point.y;
        if(isWithinHorizontally && isWithinVertically) {
            return true;
        }
        return false;
    }
}
