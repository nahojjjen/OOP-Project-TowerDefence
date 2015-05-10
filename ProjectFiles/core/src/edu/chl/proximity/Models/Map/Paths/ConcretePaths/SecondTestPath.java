package edu.chl.proximity.Models.Map.Paths.ConcretePaths;

import edu.chl.proximity.Utilities.ProximityVector;
import edu.chl.proximity.Models.Map.Paths.Path;

/**
 * @author Linda Evaldsson and Johan Swanberg
 * @date 2015-04-13
 */
public class SecondTestPath extends Path{


    @Override
    public void initiatePathHitbox() {


    }

    @Override
    public void initiatePoints() {
        if (waypoint != null) {
            if (waypoint.isEmpty()) {
                waypoint.add(new ProximityVector(3, 101));
                waypoint.add(new ProximityVector(549, 78));
                waypoint.add(new ProximityVector(550, 144));
                waypoint.add(new ProximityVector(474, 144));
                waypoint.add(new ProximityVector(473, 207));
                waypoint.add(new ProximityVector(561, 206));
                waypoint.add(new ProximityVector(557, 271));
                waypoint.add(new ProximityVector(354, 279));
                waypoint.add(new ProximityVector(358, 189));
                waypoint.add(new ProximityVector(97, 202));
                waypoint.add(new ProximityVector(117, 405));
                waypoint.add(new ProximityVector(490, 406));
            }

        }
    }
}
