package edu.chl.proximity.Models.Map.Creeps;

import com.badlogic.gdx.audio.Sound;
import edu.chl.proximity.Models.Map.Particles.ParticleManager;
import edu.chl.proximity.Utilities.ProximityVector;
import edu.chl.proximity.Models.BoardObject;
import edu.chl.proximity.Models.Utils.Image;
import edu.chl.proximity.Models.Map.Paths.Path;
import edu.chl.proximity.Models.Player.ResourceSystem.Resources;
import edu.chl.proximity.Utilities.PointCalculations;
import edu.chl.proximity.Utilities.ProximityRandom;

/**
 * @author Linda Evaldsson
 * @date 2015-04-02
 *
 * ---
 * Unknown date modified by Johan Swanberg
 *
 * 08/04 modified by Linda Evaldsson. Refactoring to Creep instead of AbstractCreep. speed-variable changed to int instead of double.
 * 16/04 modified by Simon Gislén. Added support for creep devolution.
 * 23/04 Modified by Simon. Adding resources and XP when killing creeps
 * 24/04 modified by Johan, creeps now use their center when calculating movement instead of upper left corner
 * 12/05 modified by Linda Evaldsson. Removed Map. Added Path and ParticleManager instead.
 * 
 * An abstract class for creeps. Concrete creeps extends this class.
 */
public abstract class Creep extends BoardObject {

    //private ProximityVector nextWayPoint;
    private int nextWayPointID;
    private double distanceToNextWayPoint;
    private Sound devolveSound;
    private double speed;
    private double backUpSpeed;
    private double moveAngle;
    private double randomRotation;
    private ProximityVector velocity;
    private int slowDownTime;
    private boolean isDead = false;
    private ParticleManager particleManager;
    private Path path;

    public void markAsDead(){
        isDead = true;
    }

    public boolean isDead(){return isDead;}

    /**
     * create a new creep with an image and a speed
     * @param image what image the creep should have (it will rotate a random amount automatically)
     * @param speed what speed the creep will have
     */
    public Creep(ProximityVector position, Image image, double speed, ParticleManager particleManager, Path path) {
        super(position, image, 0);
        this.particleManager = particleManager;
        this.path = path;
        setupCreep(speed);
        initiateMovement();
    }

    /**
     * @param image what image the creep should have (it will rotate a random amount automatically)
     * @param speed what speed the creep will have
     * @param oldCreep The old creep from which the location on the screen is taken.
     */
    public Creep(Image image, int speed, Creep oldCreep) {
        this(oldCreep.getPosition(), image, speed, oldCreep.getParticleManager(), oldCreep.getPath());
        nextWayPointID = oldCreep.nextWayPointID;
        distanceToNextWayPoint = oldCreep.distanceToNextWayPoint;
        moveAngle = getAngleToNextPoint();
    }

    //Setup method that is common to constructors
    public void setupCreep(double speed) {
        this.speed = speed;
        this.backUpSpeed = speed;
        randomRotation = (ProximityRandom.getRandomDouble()*15) - 7.5;
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
        this.setCenter(new ProximityVector(path.getWaypoint(0)));
        nextWayPointID = 0;
        aimTowardsNextWaypoint();
        distanceToNextWayPoint = Double.MAX_VALUE;

    }

    /**
     * show the "poof" particleEffect that creeps do when they die
     */
    public void displayDeathEffect(){
        particleManager.getCreepDiesEffect().createEffect(this.getCenter());
    }

    /**
     * destroys the creep
     */
    public void destroy() {
        displayDeathEffect();
        this.remove();
    }
    /**
     * rotate the creeps image a random amount (a creep is assigned a random rotation amount on creation)
     */
    public void rotate() {
        this.rotate(randomRotation);
    }

    /**
     * Devolve this creep, aka "kill" / remove the creep, call this method when the creep has taken fatal damage
     */
    public abstract void devolve();

    /**
     * @return Resources the player gets when the player kills the creep.
     */
    public abstract Resources getCreepResource();

    /**
     * @return XP the player gets when the player kills the creep.
     */
    public abstract int getCreepExperiencePoints();

    /**
     * move the creep based on its speed
     * The movement direction is based on what waypoint is the next
     * step for the creep, the new movement angle to the next waypoint is calculated when
     * the creep is "on" its current waypoint //implementation comment, but relevant for using method
     */
    public void move() {
        //System.out.println(path.getWaypoint(nextWayPointID));
        if (reachedWaypoint(path.getWaypoint((nextWayPointID)))){
            distanceToNextWayPoint = Double.MAX_VALUE; //this is a way of resetting the lenght, to make sure that the creep doesn't misstake the old lenght when approaching a new waypoint - remove to see bug
            aimTowardsNextWaypoint();
        }
        repositionCreep();
        checkIfSpeedUp();

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
            double angle = PointCalculations.getVectorAngle(this.getCenter(), path.getWaypoint(nextWayPointID));
            //System.out.println("angle:" + angle);
            return angle;

        }
        System.out.println("In Creep: Error in abstractCreep: trying to get angle to next point- invalid point, trying to calculate angle to null");
        //dont handle this as exception because try-catch takes resources & the error is not fatal, instead default to no rotation.
        return 0;
    }

    /**
     * the method that changes the value "position" of the creep, used in the
     * move() method.
     */
    private void repositionCreep(){
        ProximityVector newPosition;
        //do not remove below comments, useful for misc debugging:
        //System.out.println("real x movement:" + (Math.cos(Math.toRadians(moveAngle)) * speed));
        //System.out.println("real y movement:" + (Math.sin(Math.toRadians(moveAngle)) * speed));


        //System.out.println("x movement= " + xLenght + " y-momement:" + yLenght);

        float xLength = (float)(Math.cos(Math.toRadians(moveAngle)) * speed); //+0.5 to round to correct int aka 0.9 is 1
        float yLength = (float)(Math.sin(Math.toRadians(moveAngle)) * speed);
        velocity = new ProximityVector(xLength, yLength);

        //System.out.println(velocity);
        this.setPosition(new ProximityVector(getPosition().x + velocity.x, getPosition().y + velocity.y));

    }

    public boolean reachedLastWayPoint() {
        return nextWayPointID >= path.getWaypoints().size();
    }

    /**
     * Sets the moveAngle of the creep to face the next waypoint
     */
    private void aimTowardsNextWaypoint(){

        nextWayPointID++;
        if(reachedLastWayPoint()) {
            destroy();
            this.remove();
        }
        else {
            moveAngle = getAngleToNextPoint();
        }
    }

    /**
     * See if the creep has reached the next waypoint
     *
     * @param waypoint what waypoint to check for
     * @return true if within distance of waypoint
     */
    private boolean reachedWaypoint(ProximityVector waypoint){

        double olddistanceToNextWayPoint = distanceToNextWayPoint;
        distanceToNextWayPoint = PointCalculations.distanceBetweenNoSqrt(getCenter(), waypoint);
        if (distanceToNextWayPoint > olddistanceToNextWayPoint){ //if you're no longer approaching the waypoint, you're leaving it
            return true;
        }
        return false;
    }

    /**
     * Slows down a creep by a % and a duration (number of frames)
     * @param percentage how many percent slower the creep should be
     * @param nbrOfTicks how many frames the creep should be slowed
     */
    public void slowDown(double percentage, int nbrOfTicks) {
        Double newSpeed = (1 - percentage / 100) * backUpSpeed;
        if(slowDownTime<0 || newSpeed.intValue()<=speed) {
            speed = newSpeed.intValue();
            slowDownTime = nbrOfTicks;
        }
    }

    /**
     * Check if the creep has completed a slow duration and in that case return it to normal speed
     */
    public void checkIfSpeedUp(){
        if(slowDownTime>0){
            slowDownTime--;
        }else if(slowDownTime==0) {
            speed = backUpSpeed;
            slowDownTime = -1;
        }
    }

    public ParticleManager getParticleManager() {
        return particleManager;
    }

    public Path getPath() {
        return path;
    }
}
