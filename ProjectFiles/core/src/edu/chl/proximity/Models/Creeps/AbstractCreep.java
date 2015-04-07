package edu.chl.proximity.Models.Creeps;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import edu.chl.proximity.Models.BoardObject;
import edu.chl.proximity.Models.Image;
import edu.chl.proximity.Models.Maps.Map;
import edu.chl.proximity.Models.Paths.Path;
import edu.chl.proximity.Utilities.PointCalculations;

import java.awt.Point;

/**
 * Created by Tindra on 02/04/15. Modified by Johan Swanberg
 */
public abstract class AbstractCreep extends BoardObject {

    private Point nextWayPoint;
    private double distanceToNextWayPoint;
    private Path path;
    private Sound devolveSound;
    private double speed;

    public AbstractCreep(Image image, int speed) {

        super(new Point(700,0), image, 0 );
        Map map = Map.getInstance();
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
        if (reachedWaypoint(nextWayPoint)){
            path.getWaypoint(0);
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
        Point newPosition;
        /*
        System.out.println("real x movement:" + (Math.cos(Math.toRadians(angle)) * speed));
        System.out.println("real y movement:" + (Math.sin(Math.toRadians(angle)) * speed));
        */
        int xLenght = (int) ((Math.cos(Math.toRadians(getAngle())) * speed)+0.5); //+0.5 to round to correct int aka 0.9 is 1
        int yLenght = (int) ((Math.sin(Math.toRadians(getAngle())) * speed)+0.5);


        //System.out.println("x movement= " + xLenght + " y-momement:" + yLenght);
        newPosition = new Point((int)getPosition().getX() + xLenght, (int)getPosition().getY() + yLenght);
        setPosition(newPosition);
    }

    /**
     * Sets the angle of the creep to face the next waypoint
     */
    private void aimTowardsNextWaypoint(){
        setAngle(getAngleToNextPoint());
    }

    /**
     * See if the creep has reached the next waypoint
     *
     * @param waypoint what waypoint to check for
     * @return true if within distance of waypoint
     */
    private boolean reachedWaypoint(Point waypoint){
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
