/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.chl.fohProximity.model;

import java.awt.Point;

/**
 *
 * @author Johan
 */
public class Util {
    
    
            
        /**
         * Calculates the angle between two points
         * Adds 90 degrees to compensate for image rotation
         * todo unit-test: 0 degrees, two intersecting points, null points, normal points, negative points
         * @param firstPoint the first point of the vector
         * @param secondPoint the second point of the vector
         * @return The angle in degrees between two points + 90
         */
        private float getVectorAngle(Point firstPoint, Point secondPoint){
            if (firstPoint != null && secondPoint!=null){ //make sure there is a real vector
                Point vector = new Point(secondPoint.getX()-firstPoint.getX(), secondPoint.getY()-firstPoint.getY()); //to get a vector, subtract point a from point b
                float hypotenuse = (float) Math.sqrt(vector.getX()*vector.getX() + vector.getY()*vector.getY()); 
                if (hypotenuse == 0){
                    return 0;
                }
                float angle = (float) Math.acos(vector.getX()/hypotenuse);
                float angleDegrees = (float) Math.toDegrees(angle);
                if (vector.getY() > 0){
                    return angleDegrees+90;
                }
                    return -1*angleDegrees+90;
                }
            return 90;
        }
}
