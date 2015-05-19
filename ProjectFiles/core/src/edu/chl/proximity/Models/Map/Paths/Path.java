package edu.chl.proximity.Models.Map.Paths;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.math.Rectangle;
import edu.chl.proximity.Utilities.ProximityVector;
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

    protected List<ProximityVector> waypoint = new ArrayList();
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
    public List<ProximityVector> getWaypoints() {
        return waypoint;
    }

    /**
     * add rectangles covering the surface of the path
     */
    public abstract void initiatePathHitbox();


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
    public ProximityVector getWaypoint(int waypointNumber) {
        if(waypoint != null && waypoint.size() > waypointNumber) {
            return waypoint.get(waypointNumber);
        }
        return waypoint.get(waypoint.size()-1); //test-wise we only get to this line of code
        //if we run the program at around x10000 speed, and then the creeps sometimes accidentally skip
        //the last waypoint and attempt to get a waypoint after the last one, so now it error corrects by simply returning
        //the last one by default if out of bounds.
    }


}
