package edu.chl.proximity.Models.Map.Creeps;

import edu.chl.proximity.Models.BoardObject;
import edu.chl.proximity.Models.Map.Particles.ParticleManager;
import edu.chl.proximity.Models.Map.Paths.Path;
import edu.chl.proximity.Models.ResourceSystem.Resources;
import edu.chl.proximity.Models.Utils.Image;
import edu.chl.proximity.Utilities.PointCalculations;
import edu.chl.proximity.Models.Utils.ProximityRandom;
import edu.chl.proximity.Utilities.ProximityVector;

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

    private int nextWayPointID;
    private double distanceToNextWayPoint;
    private double speed;
    private double backUpSpeed;
    private double moveAngle;
    private double randomRotation;
    private ProximityVector velocity;
    private int slowDownTime;
    private ParticleManager particleManager;
    private Path path;

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
     * Create a new creep, but copy the position on the path from another creep
     * @param image what image the creep should have (it will rotate a random amount automatically)
     * @param speed what speed the creep will have
     * @param oldCreep The old creep from which the location on the screen is taken.
     */
    public Creep(Image image, double speed, Creep oldCreep) {
        this(null, image, speed, oldCreep.getParticleManager(), oldCreep.getPath());
        this.setCenter(new ProximityVector(oldCreep.getCenter().x, oldCreep.getCenter().y));
        nextWayPointID = oldCreep.nextWayPointID;
        distanceToNextWayPoint = oldCreep.distanceToNextWayPoint;
        moveAngle = getAngleToNextPoint();
    }

    /**
     * setup method for things that does not change normally
     */
    public void setupCreep(double speed) {
        this.speed = speed;
        this.backUpSpeed = speed;
        randomRotation = (ProximityRandom.getRandomDouble()*15) - 7.5;
    }

    /**
     * get the number of the waypoints this creep is currently traveling towards
     * Guaranteed to be >=0
     * @return (int) the number of the waypoints the creep is traveling towards
     *
     */
    public int getNextWayPointID(){
        return nextWayPointID;
    }

    /**
     * get the distance this creep has to the next waypoing (squared because of calculation optimization)
     * This value can be negative since for one frame the creep is considered to have "passed" the waypoints,
     * and not yet chosen the next waypoints.
     * @return (double) the distance this creep has to the next waypoints
     */
    public double getDistanceToNextWayPoint(){
        return distanceToNextWayPoint;
    }

    /**
     * Give the creep the first angle & direction to the first waypoints
     */
    private void initiateMovement() {
        if (path != null){
            this.setCenter(new ProximityVector(path.getWaypoint(0)));
        }
        nextWayPointID = 0;
        aimTowardsNextWaypoint();
        distanceToNextWayPoint = Double.MAX_VALUE;

    }

    /**
     * show the "poof" particleEffect that creeps do when they die
     */
    public void displayDeathEffect(){
        if (particleManager != null){
            particleManager.getCreepDiesEffect().createEffect(this.getCenter());
        }
    }

    /**
     * destroys the creep
     */
    public void destroy() {
        if (this.getCreepLineIndex() == 1){
            displayDeathEffect();
        }

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
     * Get the current creep line index (creep devolution index)
     */
    public abstract int getCreepLineIndex();

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
     * The movement direction is based on what waypoints is the next
     * step for the creep, the new movement angle to the next waypoints is calculated when
     * the creep is "on" its current waypoints //implementation comment, but relevant for using method
     */
    public void move() {
        if (reachedWaypoint(path.getWaypoint((nextWayPointID)))){
            distanceToNextWayPoint = Double.MAX_VALUE; //this is a way of resetting the length
            aimTowardsNextWaypoint();
        }
        repositionCreep();
        checkIfSpeedUp();
        checkIfReachedBase();
    }

    /**
     * Get the angle the creep requires to to travel from origin point to next
     * waypoints.
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
            return angle;
        }
        return 0;
        //dont handle this as exception because try-catch takes resources & the error is not fatal, instead default to no angle
    }

    /**
     * Makes the creep reposition one step closer to the base, by following its current movement direction
     */
    private void repositionCreep(){
        float xLength = (float)(Math.cos(Math.toRadians(moveAngle)) * speed);
        float yLength = (float)(Math.sin(Math.toRadians(moveAngle)) * speed);
        this.setPosition(new ProximityVector(getPosition().x + xLength, getPosition().y + yLength));
    }

    /**
     * Checks if the creep has reached the base, and mark itself as dead if it does.
     * The controller checks all creeps when they die if they die from projectiles
     * or colliding with the base, and acts accordingly
     */
    private void checkIfReachedBase(){
        if (reachedLastWayPoint()){
            this.devolve();
        }
    }

    /**
     * returns true if the distance to the last waypoint is less than 2
     * @return
     */
    public boolean reachedLastWayPoint() {
        if (path != null){
            return reachedWaypoint(path.getWaypoint(path.getWaypoints().size()));
        }
        return false; //if there is no path, it hasnt reached the last waypoint.
    }

    /**
     * Sets the moveAngle of the creep to face the next waypoints
     */
    private void aimTowardsNextWaypoint(){
        nextWayPointID++;
        moveAngle = getAngleToNextPoint();
    }

    /**
     * See if the creep has reached the next waypoints
     *
     * @param waypoint what waypoints to check for
     * @return true if within distance of waypoints
     */
    private boolean reachedWaypoint(ProximityVector waypoint){
        distanceToNextWayPoint = PointCalculations.distanceBetweenNoSqrt(getCenter(), waypoint); //used for targeting methods
        return PointCalculations.distanceBetweenNoSqrt(this.getCenter(),waypoint) < 5*5;
    }

    /**
     * Slows down a creep by a % and a duration (number of frames)
     * @param percentage how many percent slower the creep should be
     * @param nbrOfTicks how many frames the creep should be slowed
     */
    public void slowDown(double percentage, int nbrOfTicks) {
        Double newSpeed = (1 - percentage / 100) * backUpSpeed;
        if(slowDownTime<0 || newSpeed<=speed) {
            speed = newSpeed;
            slowDownTime = nbrOfTicks;
        }
    }

    /**
     * Check if the creep has completed a slow duration and in that case return it to normal speed
     */
    public void checkIfSpeedUp(){
        if(slowDownTime>0){
            slowDownTime--;
            if (slowDownTime % 15 == 0){
                particleManager.getDustCreepEffect().createEffect(getCenter());
            }

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

    public double getSpeed() {
        return speed;
    }
}
