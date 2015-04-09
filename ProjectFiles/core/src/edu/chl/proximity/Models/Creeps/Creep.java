package edu.chl.proximity.Models.Creeps;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import edu.chl.proximity.Models.BoardObject;
import edu.chl.proximity.Models.GameData;
import edu.chl.proximity.Models.Image;
import edu.chl.proximity.Models.Maps.Map;
import edu.chl.proximity.Models.Paths.Path;
import edu.chl.proximity.Utilities.PointCalculations;

import java.awt.Point;

/**
 * Created by Tindra on 02/04/15. Modified by Johan Swanberg
 */
public abstract class Creep extends BoardObject {

    private Vector2 nextWayPoint;
    private double distanceToNextWayPoint;
    private Path path;
    private Sound devolveSound;
    private int speed;
    private double moveAngle;

    public Creep(Image image, int speed) {

        super(new Vector2(700,0), image, 0 );
        Map map = GameData.getInstance().getMap();
        path = map.getPath();
        nextWayPoint = path.getWaypoint(0); //gets set by move method
        distanceToNextWayPoint = 9999999;

        this.speed = speed;
        aimTowardsNextWaypoint();

    }

    public abstract void devolve();

    /**
     * move the creep based on its speed
     * The movement direction is based on what waypoint is the next
     * step for the creep, the new movement angle to the next waypoint is calculated when
     * the creep is "on" its current waypoint //implementation comment, but relevant for using method
     */
    public void move() {
        System.out.println(nextWayPoint);
        if (reachedWaypoint(nextWayPoint)){
            distanceToNextWayPoint = 999999999; //this is a way of resetting the lenght, to make sure that the creep doesn't misstake the old lenght when approaching a new waypoint - remove to see bug
            aimTowardsNextWaypoint();
        }
        repositionCreep();

    }
    /**
     *
     * Get the angle an object requires to to travel from origin point to next
     * waypoint.
     *
     * This method is a wrapper method for PointCalculations.getVectorAngle so that this
     * method exists in Path.
     *
     * @return the angle the object needs to travel to travel from origin to
     * nextWaypoint
     */
    public  double getAngleToNextPoint() {
        if (this.getPosition() != null && this.nextWayPoint != null) {
            double angle = PointCalculations.getVectorAngle(this.getPosition(), nextWayPoint);
            System.out.println("angle:" + angle);
            return angle;

        }
        System.out.println("Error in abstractCreep: trying to get angle to next point- invalid point");
        return 0;
    }

    /**
     * the method that changes the value "position" of the creep, part of the
     * move() method.
     */
    private void repositionCreep(){
        Vector2 newPosition;

        System.out.println("real x movement:" + (Math.cos(Math.toRadians(moveAngle)) * speed));
        System.out.println("real y movement:" + (Math.sin(Math.toRadians(moveAngle)) * speed));


        float xLenght = (float)(Math.cos(Math.toRadians(moveAngle)) * speed); //+0.5 to round to correct int aka 0.9 is 1
        float yLenght = (float)(Math.sin(Math.toRadians(moveAngle)) * speed);


        System.out.println("x movement= " + xLenght + " y-momement:" + yLenght);
        newPosition = new Vector2(getPosition().x + xLenght, getPosition().y + yLenght);
        setPosition(newPosition);
    }

    /**
     * Sets the angle of the creep to face the next waypoint
     */
    private void aimTowardsNextWaypoint(){
        moveAngle = getAngleToNextPoint();
    }

    /**
     * See if the creep has reached the next waypoint
     *
     * @param waypoint what waypoint to check for
     * @return true if within distance of waypoint
     */
    private boolean reachedWaypoint(Vector2 waypoint){
        double olddistanceToNextWayPoint = distanceToNextWayPoint;
        distanceToNextWayPoint = PointCalculations.distanceBetweenNoSqrt(super.getPosition(), waypoint);
        //System.out.println("distance = " + distance);
        if (distanceToNextWayPoint > olddistanceToNextWayPoint){ //if you're no longer approaching the waypoint, you're leaving it
            return true;
        }
        return false;
        //return distanceToNextWayPoint < 10;
    }
}
