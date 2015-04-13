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
import java.util.Iterator;

/**
 * Created by Tindra on 02/04/15. Modified by Johan Swanberg
 */
public abstract class Creep extends BoardObject {

    //private Vector2 nextWayPoint;
    private int nextWayPointID;
    private double distanceToNextWayPoint;
    private Path path;
    private Sound devolveSound;
    private int speed;
    private double moveAngle;
    private double rndRotation;

    /**
     * create a new creep with an image and a speed
     * @param image what image the creep should have (it will rotate a random amount automatically)
     * @param speed what speed the creep will have
     */
    public Creep(Image image, int speed) {

        super(new Vector2(0, 300), image, 0);
        this.speed = speed;

        Map map = GameData.getInstance().getMap();
        path = map.getPath();
        rndRotation = (Math.random()*15) - 7.5;
        initiateMovement();
    }

    /**
     * get the number of the waypoint this creep is currently traveling towards
     * Guaranteed to be >=0
     * @return (int) the number of the waypoint the creep is traveling towards
     *
     */
    public int getNextWayPointID(){
        return nextWayPointID;
    }

    /**
     * get the distance this creep has to the next waypoing (squared because of calculation optimization)
     * This value can be negative since for one frame the creep is considered to have "passed" the waypoint,
     * and not yet chosen the next waypoint.
     * @return (double) the distance this creep has to the next waypoint
     */
    public double getDistanceToNextWayPoint(){
        return distanceToNextWayPoint;
    }

    /**
     * Give the creep the first angle & direction to the first waypoint
     */
    private void initiateMovement() {
        this.setPosition(new Vector2(path.getWaypoint(0)));
        nextWayPointID = 0;
        distanceToNextWayPoint = 9999999;
        aimTowardsNextWaypoint();
    }

    /**
     * show the "poof" particleEffect that creeps do when they die
     */
    public void displayDeathEffect(){
        Map map = GameData.getInstance().getMap();
        map.getParticleManager().getCreepDiesEffect().createEffect(getPosition().x, getPosition().y);
    }

    /**
     * rotate the creeps image a random amount (a creep is assigned a random rotation amount on creation)
     */
    public void rotate() {
        this.rotate(rndRotation);
    }

    /**
     * Devolve this creep, aka "kill" / remove the creep, call this method when the creep has taken fatal damage
     */
    public abstract void devolve();

    /**
     * move the creep based on its speed
     * The movement direction is based on what waypoint is the next
     * step for the creep, the new movement angle to the next waypoint is calculated when
     * the creep is "on" its current waypoint //implementation comment, but relevant for using method
     */
    public void move() {
        //System.out.println(path.getWaypoint(nextWayPointID));
        if (reachedWaypoint(path.getWaypoint((nextWayPointID)))){
            distanceToNextWayPoint = 999999999; //this is a way of resetting the lenght, to make sure that the creep doesn't misstake the old lenght when approaching a new waypoint - remove to see bug
            aimTowardsNextWaypoint();
        }
        repositionCreep();

    }
    /**
     * Get the angle the creep requires to to travel from origin point to next
     * waypoint.
     *
     * This method is a wrapper method for PointCalculations.getVectorAngle so that this
     * method exists in Path.
     *
     * @return the angle the object needs to travel to travel from origin to
     * nextWaypoint in degrees.
     */
    public  double getAngleToNextPoint() {
        if (this.getPosition() != null && path.getWaypoint(nextWayPointID)!= null) {
            double angle = PointCalculations.getVectorAngle(this.getPosition(), path.getWaypoint(nextWayPointID));
            //System.out.println("angle:" + angle);
            return angle;

        }
        System.out.println("Error in abstractCreep: trying to get angle to next point- invalid point");
        //dont handle this as exception because try-catch takes resources & the error is not fatal, instead default to no rotation.
        return 0;
    }

    /**
     * the method that changes the value "position" of the creep, used in the
     * move() method.
     */
    private void repositionCreep(){
        Vector2 newPosition;
        //do not remove below comments, useful for misc debugging:
        //System.out.println("real x movement:" + (Math.cos(Math.toRadians(moveAngle)) * speed));
        //System.out.println("real y movement:" + (Math.sin(Math.toRadians(moveAngle)) * speed));


        float xLenght = (float)(Math.cos(Math.toRadians(moveAngle)) * speed); //+0.5 to round to correct int aka 0.9 is 1
        float yLenght = (float)(Math.sin(Math.toRadians(moveAngle)) * speed);


        //System.out.println("x movement= " + xLenght + " y-momement:" + yLenght);
        newPosition = new Vector2(getPosition().x + xLenght, getPosition().y + yLenght);
        setPosition(newPosition);
    }

    /**
     * Sets the moveAngle of the creep to face the next waypoint
     */
    private void aimTowardsNextWaypoint(){

        nextWayPointID++;
        if(nextWayPointID >= path.getWaypoints().size()) {
            //TODO: instead decrease base health & destroy creep
            nextWayPointID = 0;
            System.out.println("Creep: Base takes damage!");
        }
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
        if (distanceToNextWayPoint > olddistanceToNextWayPoint){ //if you're no longer approaching the waypoint, you're leaving it
            return true;
        }
        return false;
    }
}
