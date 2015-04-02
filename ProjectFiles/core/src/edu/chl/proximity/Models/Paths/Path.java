package edu.chl.proximity.Models.Paths;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.math.Rectangle;
import edu.chl.proximity.Utilities.Image;
import edu.chl.proximity.Utilities.PointCalculations;

/**
 * The path the creep follows, and where you cannot place towers.
 * @author Johan
 */
public class Path {

    private static List<Point> waypoint = new ArrayList();
    private static Image image;
    private static Path thisPath;
    private static List<Rectangle> pathHitbox =  new ArrayList();

    /**
     * get an instance of this path
     */
    public Path() {
        initiate();
    }

    /**
     * get the singleton of this path
     *
     * @return an instance of this Path
     */
    public static Path getPath() {
        if (thisPath == null) {
            thisPath = new Path();
        }
        return thisPath;
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
    private void initiatePathHitbox(){
        if(pathHitbox.size() < 1){

            pathHitbox.add(new Rectangle(0, 700, 1050, 30));
            pathHitbox.add(new Rectangle(1025, 530, 30, 180));
            pathHitbox.add(new Rectangle(940, 530, 100, 30));
            pathHitbox.add(new Rectangle(940, 530, 30, 120));
            pathHitbox.add(new Rectangle(440, 620, 500, 30));
            pathHitbox.add(new Rectangle(450, 450, 30, 200));
            pathHitbox.add(new Rectangle(90, 450, 370, 30));
            pathHitbox.add(new Rectangle(90, 85, 30, 380));
            pathHitbox.add(new Rectangle(90, 85, 230, 30));
            pathHitbox.add(new Rectangle(300, 85, 30, 300));
            pathHitbox.add(new Rectangle(300, 360, 750, 30));
            pathHitbox.add(new Rectangle(1025, 100, 30, 270));
            pathHitbox.add(new Rectangle(835, 100, 200, 30));
            pathHitbox.add(new Rectangle(835, 100, 30, 150));
            pathHitbox.add(new Rectangle(650, 250, 200, 30));


        }
    }

    /**
     * get the hitbox of the path
     * @return a list of rectangles covering the hitbox of the path
     */
    public List<Rectangle> getHitbox(){
        initiatePathHitbox();
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
    public void initiate() {
        if (waypoint.isEmpty()) {
            waypoint.add(new Point(2, 716));
            waypoint.add(new Point(435, 728));
            waypoint.add(new Point(821, 730));
            waypoint.add(new Point(1027, 724));
            waypoint.add(new Point(1016, 552));
            waypoint.add(new Point(957, 550));
            waypoint.add(new Point(949, 638));
            waypoint.add(new Point(674, 646));
            waypoint.add(new Point(451, 628));
            waypoint.add(new Point(457, 543));
            waypoint.add(new Point(472, 459));
            waypoint.add(new Point(265, 466));
            waypoint.add(new Point(109, 462));
            waypoint.add(new Point(104, 284));
            waypoint.add(new Point(117, 101));
            waypoint.add(new Point(313, 99));
            waypoint.add(new Point(322, 229));
            waypoint.add(new Point(304, 376));
            waypoint.add(new Point(592, 406));
            waypoint.add(new Point(886, 415));
            waypoint.add(new Point(1033, 401));
            waypoint.add(new Point(1047, 228));
            waypoint.add(new Point(1033, 120));
            waypoint.add(new Point(933, 98));
            waypoint.add(new Point(845, 106));
            waypoint.add(new Point(848, 272));
            waypoint.add(new Point(672, 260));

            initiatePathHitbox();
        }
    }

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
     *
     * Get the angle an object requires to to travel from origin point to next
     * waypoint.
     *
     * This method is a wrapper method for Util.getVectorAngle so that this
     * method exists in Path.
     *
     * @param origin What point the object should travel from
     * @param nextWaypoint what point the object should aim at
     * @return the angle the object needs to travel to travel from origin to
     * nextWaypoint
     */
    public static double getAngleToNextPoint(Point origin, Point nextWaypoint) {
        if (origin != null && nextWaypoint != null) {
            double angle = PointCalculations.getVectorAngle(origin, nextWaypoint);
            return angle;
        }
        System.out.println("Path error: invalid point");
        return 0;
    }

    /**
     * get the image of the Path (singleton)
     *
     * @return the image of the base
     */
    public static Image getImage() {

            if (image == null) {
                image = new Image("src/Data/Path/path.png");
            }
        return image;

    }
}
