package edu.chl.proximity.Models.Paths.ConcretePaths;

import com.badlogic.gdx.math.Rectangle;
import edu.chl.proximity.Models.Paths.Path;

import java.awt.*;
import java.util.List;

/**
 * Created by Johan on 2015-04-02.
 */
public class FirstPath extends Path {

   private List<Rectangle> pathHitbox;
    private List<Point> waypoint;

    public FirstPath() {
        pathHitbox = super.getHitbox();
        waypoint = super.getWaypoints();
    }

    /**
     * add rectangles covering the surface of the path
     */
    public void initiatePathHitbox(){
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

    public void initiatePoints(){

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

        }
    }
}
