package edu.chl.proximity.Models.Map.Paths.ConcretePaths;

import com.badlogic.gdx.math.Rectangle;
import edu.chl.proximity.Utilities.ProximityVector;
import edu.chl.proximity.Models.Map.Paths.Path;

/**
 *  @author Johan Swanberg
 *  @date 2015-04-02
 */
public class FirstPath extends Path {


    public FirstPath() {

    }

    /**
     * add rectangles covering the surface of the path
     */
    public void initiatePathHitbox(){
        if(pathHitbox != null) {
            if (pathHitbox.size() < 1) {


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
    }

    public void initiatePoints(){
        if(waypoint != null) {
            if (waypoint.isEmpty()) {
                waypoint.add(new ProximityVector(2, 716));
                waypoint.add(new ProximityVector(435, 728));
                waypoint.add(new ProximityVector(821, 730));
                waypoint.add(new ProximityVector(1027, 724));
                waypoint.add(new ProximityVector(1016, 552));
                waypoint.add(new ProximityVector(957, 550));
                waypoint.add(new ProximityVector(949, 638));
                waypoint.add(new ProximityVector(674, 646));
                waypoint.add(new ProximityVector(451, 628));
                waypoint.add(new ProximityVector(457, 543));
                waypoint.add(new ProximityVector(472, 459));
                waypoint.add(new ProximityVector(265, 466));
                waypoint.add(new ProximityVector(109, 462));
                waypoint.add(new ProximityVector(104, 284));
                waypoint.add(new ProximityVector(117, 101));
                waypoint.add(new ProximityVector(313, 99));
                waypoint.add(new ProximityVector(322, 229));
                waypoint.add(new ProximityVector(304, 376));
                waypoint.add(new ProximityVector(592, 406));
                waypoint.add(new ProximityVector(886, 415));
                waypoint.add(new ProximityVector(1033, 401));
                waypoint.add(new ProximityVector(1047, 228));
                waypoint.add(new ProximityVector(1033, 120));
                waypoint.add(new ProximityVector(933, 98));
                waypoint.add(new ProximityVector(845, 106));
                waypoint.add(new ProximityVector(848, 272));
                waypoint.add(new ProximityVector(672, 260));

            }
        }
    }
}
