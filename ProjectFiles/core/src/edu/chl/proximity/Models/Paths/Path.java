package edu.chl.proximity.Models.Paths;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import edu.chl.proximity.Utilities.Image;
import edu.chl.proximity.Utilities.PointCalculations;

/**
 * The path the creep follows, and where you cannot place towers.
 * @author Johan
 */
public abstract class Path {

    private  List<Point> waypoint = new ArrayList();
    private  List<Rectangle> pathHitbox =  new ArrayList();
    private Texture texture;

    /**
     * get an instance of this path
     */
    public Path() {
        initiatePathHitbox();
        initiatePoints();
    }



    /**
     * get a list of all waypoints in this path
     * @return a list of points, in order- the points are waypoints on the path.
     */
    public List<Point> getWaypoints() {
        return waypoint;
    }

    /**
     * add rectangles covering the surface of the path
     */
    public abstract void initiatePathHitbox();

    /**
     * get the hitbox of the path
     * @return a list of rectangles covering the hitbox of the path
     */
    public List<Rectangle> getHitbox(){
        return pathHitbox;
    }


    /**
     * check if a rectangle intersects the path
     * @param p the rectangle to check if it intersects (example, the base of a tower)
     * @return true if the rectangle touches / is inside the path
     */
    public boolean intersects(Rectangle p) {

        //Rectangle inputPoint = new Rectangle(p.getX(), p.getY(), 1, 1);
        for (Rectangle hitbox : pathHitbox) {
            if (hitbox.contains(p) || hitbox.overlaps(p)) {
                return true;
            }
        }
        return false; //if we loop through all of the path and nothing intersects

    }

    /**
     * add all the waypoints in the path
     */
    public abstract void initiatePoints() ;

    /**
     * get the waypoint in the path
     *
     * @param i what point to get
     * @return a point corresponding to the number input
     */
    public Point getWaypoint(int i) {
        return waypoint.get(i);
    }


    /**
     * get the image of the Path (singleton)
     *
     * @return the image of the base
     */
    public  Texture getImage() {
        return texture;

    }
}
