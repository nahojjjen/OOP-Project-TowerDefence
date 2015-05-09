package edu.chl.proximity.Models.Map.Paths;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import edu.chl.proximity.Models.BoardObject;

/**
 * @author Johan Swanberg
 * @date 2015-04-03
 *
 * The path the creep follows, and where you cannot place towers.
 *
 * 08/04 modified by Linda Evaldsson. Added intersetcs-method.
 * 04-24 modified by Johan Swanberg, removed unused texture
 */
public abstract class Path {

    protected List<Vector2> waypoint = new ArrayList();
    protected List<Rectangle> pathHitbox =  new ArrayList();

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
    public List<Vector2> getWaypoints() {
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
     *
     * @param o the boardObject to check if it intersects
     * @return
     */
    public boolean intersects(BoardObject o) {
        return intersects(new Rectangle((int)o.getPosition().x, (int)o.getPosition().y, o.getWidth(), o.getHeight()));
    }

    /**
     * add all the waypoints in the path
     */
    public abstract void initiatePoints() ;

    /**
     * get the waypoint in the path
     *
     * @param waypointNumber what point to get
     * @return a point corresponding to the number input
     */
    public Vector2 getWaypoint(int waypointNumber) {
        if(waypoint != null && waypoint.size() > waypointNumber) {
            return waypoint.get(waypointNumber);
        }
        return waypoint.get(waypoint.size()-1); //test-wise we only get to this line of code
        //if we run the program at around x10000 speed, and then the creeps sometimes accidentally skip
        //the last waypoint and attempt to get a waypoint after the last one, so now it error corrects by simply returning
        //the last one by default if out of bounds.
    }


}
