package edu.chl.proximity.Models.Map.Paths.ConcretePaths;

import edu.chl.proximity.Utilities.ProximityVector;
import edu.chl.proximity.Models.Map.Paths.Path;

/**
 * @author Linda Evaldsson
 * @date 2015-04-09
 */
public class TestPath extends Path {

    /**
     * because this is a test path, it does not have a hitbox -> its possible to place towers on top of it
     */
    @Override
    public void initiatePathHitbox() {


    }

    @Override
    public void initiatePoints() {
        if(waypoint != null) {
            if (waypoint.isEmpty()) {
                waypoint.add(new ProximityVector(140, 99));
                waypoint.add(new ProximityVector(267, 56));
                waypoint.add(new ProximityVector(414, 43));
                waypoint.add(new ProximityVector(320, 200));
                waypoint.add(new ProximityVector(68, 211));
                waypoint.add(new ProximityVector(72, 108));
                waypoint.add(new ProximityVector(184, 302));
                waypoint.add(new ProximityVector(87, 380));
                waypoint.add(new ProximityVector(92, 460));
                waypoint.add(new ProximityVector(386, 431));
                waypoint.add(new ProximityVector(500, 336));
                waypoint.add(new ProximityVector(564, 420));
                waypoint.add(new ProximityVector(594, 239));
                waypoint.add(new ProximityVector(594, 78));
            }
        }
    }
}
