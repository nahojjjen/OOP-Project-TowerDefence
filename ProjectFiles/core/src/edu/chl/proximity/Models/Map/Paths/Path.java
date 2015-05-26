package edu.chl.proximity.Models.Map.Paths;

import com.badlogic.gdx.graphics.Color;
<<<<<<< HEAD
=======

import edu.chl.proximity.Models.Utils.ProximityShapeRenderer;

>>>>>>> Minor restructuring
import edu.chl.proximity.Utilities.PointCalculations;
import edu.chl.proximity.Utilities.ProximityShapeRenderer;
import edu.chl.proximity.Utilities.ProximityVector;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Johan Swanberg
 * @date 2015-04-03
 *
 * The path the creep follows, and where you cannot place towers.
 *
 * 08/04 modified by Linda Evaldsson. Added intersetcs-method.
 * 04-24 modified by Johan Swanberg, removed unused texture
 * 24/05 modified by Hanna Romer. Added hiboxcheck for path.
 */
public abstract class Path {

    protected List<ProximityVector> waypoints = new ArrayList();

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
        if (waypoints != null) {
            if (waypoints.size() > waypointNumber) {
                return waypoints.get(waypointNumber);
            }
            return waypoints.get(waypoints.size() - 1); //give last waypoint if searching for after last
        }
            return null;
        }


    public boolean isPointOnPath(ProximityVector point){
        for(int a=0;a<waypoints.size()-1;a++){
            if(isPointBetween(waypoints.get(a), waypoints.get(a+1),point)){
                return true;
            }
        }
        return false;
    }

    public boolean isPointBetween(ProximityVector a, ProximityVector b, ProximityVector p){
        Double atb=Math.sqrt(PointCalculations.distanceBetweenNoSqrt(a,b));
        Double atp=Math.sqrt(PointCalculations.distanceBetweenNoSqrt(a,p));
        Double btp=Math.sqrt(PointCalculations.distanceBetweenNoSqrt(b,p));
        return ((atp+btp)-atb <=0.01);
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
