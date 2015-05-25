package edu.chl.proximity.Models.Map.Paths;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Rectangle;

import edu.chl.proximity.Utilities.ProximityShapeRenderer;

import edu.chl.proximity.Utilities.PointCalculations;

import edu.chl.proximity.Utilities.ProximityVector;

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
    public boolean isPointInHitbox(ProximityVector point){

        /*
        for(int p1=0;p1<waypoints.size()-1;p1++){
            int p2=p1+1;
            if(getDistanceToLinePQ(getWaypoint(p1),getWaypoint(p2) ,point) < 10){
                System.out.println("Distance again: " + getDistanceToLinePQ(getWaypoint(p1),getWaypoint(p2) ,point));
                return true;
            }
        }
        */
        return false;

    }

    private double getDistanceToLinePQ(ProximityVector p, ProximityVector q,ProximityVector pointToCheck){

        ProximityVector p_q=new ProximityVector(q.x-p.x,q.y-p.y);
        ProximityVector p_ptc=new ProximityVector(p.x-pointToCheck.x,p.y-pointToCheck.y);

        ProximityVector ptcL=new ProximityVector(0,0);//p.x,p.y);
        ptcL.add(p_q.times((p_ptc.x*p_q.x+p_ptc.y*p_q.y)/(p_q.x*p_q.x + p_q.y*p_q.y)));
        double pqDistance=Math.sqrt(PointCalculations.distanceBetweenNoSqrt(p,q));
        double pptcLDistance=Math.sqrt(PointCalculations.distanceBetweenNoSqrt(p,ptcL));
        double qptcLDistance=Math.sqrt(PointCalculations.distanceBetweenNoSqrt(q,ptcL));

        if((Math.abs(pptcLDistance)+Math.abs(qptcLDistance))-Math.abs(pqDistance) <5) {
            double x0 = pointToCheck.x;
            double y0 = pointToCheck.y;

            double a = p.y - q.y;
            double b = q.x - p.x;
            double c = p.x * q.y - q.x * p.y;
            double distance = (Math.abs(a * x0 + b * y0 + c) / Math.sqrt(a * a + b * b));
            System.out.println("TO LINE: " + distance);
            return distance;
        }else{
            System.out.println("TO POINT: ");
            if(pptcLDistance<qptcLDistance){
                return Math.sqrt(PointCalculations.distanceBetweenNoSqrt(p, pointToCheck));
            }else{
                return Math.sqrt(PointCalculations.distanceBetweenNoSqrt(q,pointToCheck));
            }
        }

    }

    private ProximityVector getClosestPoint(ProximityVector A, ProximityVector B, ProximityVector P){
        ProximityVector a_to_p= new ProximityVector(P.x-A.x,P.y-A.y); //Storing vector A->P
        ProximityVector a_to_b= new ProximityVector(B.x-A.x,B.x-A.x); //Storing vector A->B

        float atb2 = (a_to_b.x*a_to_b.x + a_to_b.y*a_to_b.y); //Finding sqared magnitude of a_to_b

        float atp_dot_atb = (a_to_p.x*a_to_b.x+a_to_p.y*a_to_b.y);//Finding dot-product of a_to_p and a_to_b

        float distance= atp_dot_atb/atb2;
        float x=A.x+a_to_b.x*distance;
        float y=A.y+a_to_b.y*distance;
        return new ProximityVector(x,y);

    }



    private double getAngle(ProximityVector first, ProximityVector second){
        double ans=PointCalculations.getVectorAngle(first,second);

        //ans= Math.toDegrees(Math.atan2(first.x-second.x,first.y-second.y));
        if(ans<0){
            ans+=360;
        }
        if(ans>180){
            ans=360-ans;
        }
        return ans;
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
