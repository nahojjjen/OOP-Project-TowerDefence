package edu.chl.proximity.Utilities;

import com.badlogic.gdx.math.Vector2;

import java.awt.*;

/**
 * Created by Johan on 2015-04-02.
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
     * get the distance between two points
     *
     * @param p1 first point
     * @param p2 second point
     * @return length between these points
     */
    public static double distanceBetween(Vector2 p1, Vector2 p2) {

        if (p1 != null && p2 != null) { //make sure there are 2 real poiints to measure from
            Point distanceVector = new Point((int)p2.x - (int)p1.x, (int)p2.y - (int)p1.y);
            double length = Math.sqrt(distanceVector.getX() * distanceVector.getX() + distanceVector.getY() * distanceVector.getY()); //C = sqrt(a^2+b^2)
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
            Point distanceVector = new Point((int)p2.x - (int)p1.x, (int)p2.y - (int)p1.y);
            double length = distanceVector.getX() * distanceVector.getX() + distanceVector.getY() * distanceVector.getY(); //C^2 = (a^2+b^2)
            return length;
        }
        return 0;
    }
}
