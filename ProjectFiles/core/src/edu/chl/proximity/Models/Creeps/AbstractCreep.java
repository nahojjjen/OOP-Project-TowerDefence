package edu.chl.proximity.Models.Creeps;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import edu.chl.proximity.Models.BoardObject;
import edu.chl.proximity.Models.Maps.Map;
import edu.chl.proximity.Models.Paths.Path;

import java.awt.*;

/**
 * Created by Tindra on 02/04/15.
 */
public abstract class AbstractCreep extends BoardObject {

    private Point nextWayPoint;
    private int distanceToNextWayPoint;
    private Path path;
    private Sound devolveSound;
    private double speed;

    public AbstractCreep(Texture texture, int speed) {
        Map map = new Map();
        super(new Point(700,0), texture, 0 );
        nextWayPoint = null; //gets set by move method
        distanceToNextWayPoint = 9999999;
        path = map.getPath();

    }

    public void devolve() {    }



    /**
     * create an abstract crerp
     */
    public Creep() {

        path = Map.getPath();
        position = new Point(0,700);
        speed = 3;
        aimTowardsNextWaypoint(); //make sure that all spawned creeps go towards the first point

    }

    /**
     * get creep x coordinate
     * @return x coordinate
     */
    public int getx() {
        return position.getX();
    }

    /**
     * get creep point
     * @return point with creep coordinate on map
     */
    public Point getPoint() {
        return position;
    }

    /**
     * get creep y coordinate on map
     * @return creep y coordinate
     */
    public int gety() {
        return position.getY();
    }

    public double getAngle(){
        return angle;
    }

    /**
     * move the creep based on its speed
     * The movement direction is based on what waypoint is the next
     * step for the creep, the new movement angle to the next waypoint is calculated when
     * the creep is "on" its current waypoint //implementation comment, but relevant for using method
     */
    public void move() {

        if (reachedWaypoint(path.getWaypoint(nextWaypoint))){
            nextWaypoint++;
            lenghtToNextWaypoint = 999999999; //this is a way of resetting the lenght, to make sure that the creep doesn't misstake the old lenght when approaching a new waypoint - remove to see bug
            aimTowardsNextWaypoint();
        }
        repositionCreep();

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
        int xLenght = (int) ((Math.cos(Math.toRadians(angle)) * speed)+0.5);
        int yLenght = (int) ((Math.sin(Math.toRadians(angle)) * speed)+0.5);


        //System.out.println("x movement= " + xLenght + " y-momement:" + yLenght);
        newPosition = new Point(position.getX() + xLenght, position.getY() + yLenght);
        position = newPosition;
    }

    /**
     * Sets the angle of the creep to face the next waypoint
     */
    private void aimTowardsNextWaypoint(){
        angle= path.getAngleToNextPoint(position, path.getWaypoint(nextWaypoint));


    }

    /**
     * See if the creep has reached the next waypoint
     *
     * @param waypoint what waypoint to check for
     * @return true if within distance of waypoint
     */
    private boolean reachedWaypoint(Point waypoint){
        double oldLenghtToNextWayPoint = lenghtToNextWaypoint;
        lenghtToNextWaypoint = Util.distanceBetweenNoSqrt(position, waypoint);
        //System.out.println("distance = " + distance);
        if (lenghtToNextWaypoint > oldLenghtToNextWayPoint){ //if you're no longer approaching the waypoint, you're leaving it
            return true;
        }
        return false;
        //return lenghtToNextWaypoint < 10;
    }

    /**
     * get the image of the creep (singleton)
     * @return The image of the creep
     */
    public Image getImage() {
        try {
            if (image == null){
                image = new Image("src/Data/enemies/polygon.png");
            }
            return image;
        } catch (SlickException e) {
            System.out.println("slickexception in base");
        }
        return null;
    }

}
