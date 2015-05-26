package edu.chl.proximity.Models.Map.Paths.ConcretePaths;

import edu.chl.proximity.Models.Map.Paths.Path;
import edu.chl.proximity.Utilities.ProximityVector;

/**
 * @author Linda Evaldsson
 * @date 2015-04-09
 */
public class TestPath extends Path {



    @Override
    public void initiatePoints() {
        if(waypoints != null) {
            if (waypoints.isEmpty()) {
                waypoints.add(new ProximityVector(140, 99));
                waypoints.add(new ProximityVector(267, 56));
                waypoints.add(new ProximityVector(414, 43));
                waypoints.add(new ProximityVector(320, 200));
                waypoints.add(new ProximityVector(68, 211));
                waypoints.add(new ProximityVector(72, 108));
                waypoints.add(new ProximityVector(184, 302));
                waypoints.add(new ProximityVector(87, 380));
                waypoints.add(new ProximityVector(92, 460));
                waypoints.add(new ProximityVector(386, 431));
                waypoints.add(new ProximityVector(500, 336));
                waypoints.add(new ProximityVector(564, 420));
                waypoints.add(new ProximityVector(594, 239));
                waypoints.add(new ProximityVector(594, 78));
            }
        }
    }
}
