package edu.chl.proximity.Models.Map.Paths;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Rectangle;
import edu.chl.proximity.Utilities.ProximityShapeRenderer;
import edu.chl.proximity.Utilities.ProximityVector;

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

    protected List<ProximityVector> waypoints = new ArrayList();
    protected List<Rectangle> pathHitbox =  new ArrayList();

    /**
     * get an instance of this path
     */
    public Path() {
        initiatePoints();
    }



    /**
     * get a list of all waypoints in this path
     * @return a list of points, in order- the points are waypoints on the path.
     */
    public List<ProximityVector> getWaypoints() {
        return waypoints;
    }


    /**
     * add all the waypoints in the path
     */
    public abstract void initiatePoints() ;

    /**
     * get the waypoints in the path
     *
     * @param waypointNumber what point to get
     * @return a point corresponding to the number input
     */
    public ProximityVector getWaypoint(int waypointNumber) {
        if(waypoints != null && waypoints.size() > waypointNumber) {
            return waypoints.get(waypointNumber);
        }
        return waypoints.get(waypoints.size()-1); //test-wise we only get to this line of code
        //if we run the program at around x10000 speed, and then the creeps sometimes accidentally skip
        //the last waypoints and attempt to get a waypoints after the last one, so now it error corrects by simply returning
        //the last one by default if out of bounds.
    }

    /**
     * automatically renders the lines between the waypoints of the current path
     * Uses the shaperenderer, so you need to stop the SpriteBatch before calling this method,
     * or you get a completely white blank screen.
     * @param shapeRenderer what shaperenderer to use to draw the lines
     */
    public void render(ProximityShapeRenderer shapeRenderer) {

        shapeRenderer.setColor(new Color(0.4f, 0.6f, 0.9f, 0));

        for (int i = 1; i<waypoints.size(); i++){
            shapeRenderer.renderLine(waypoints.get(i-1).x  ,waypoints.get(i-1).y, waypoints.get(i).x,waypoints.get(i).y);
        }
    }


}
