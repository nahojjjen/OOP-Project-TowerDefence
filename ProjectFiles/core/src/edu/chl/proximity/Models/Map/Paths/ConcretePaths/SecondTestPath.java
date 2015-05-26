package edu.chl.proximity.Models.Map.Paths.ConcretePaths;

import edu.chl.proximity.Models.Map.Paths.Path;
import edu.chl.proximity.Utilities.ProximityVector;

/**
 * @author Linda Evaldsson and Johan Swanberg
 * @date 2015-04-13
 */
public class SecondTestPath extends Path{


    @Override
    public void initiatePoints() {
        if (waypoints != null) {
            if (waypoints.isEmpty()) {
                waypoints.add(new ProximityVector(3, 101));
                waypoints.add(new ProximityVector(549, 78));
                waypoints.add(new ProximityVector(550, 144));
                waypoints.add(new ProximityVector(474, 144));
                waypoints.add(new ProximityVector(473, 207));
                waypoints.add(new ProximityVector(561, 206));
                waypoints.add(new ProximityVector(557, 271));
                waypoints.add(new ProximityVector(354, 279));
                waypoints.add(new ProximityVector(358, 189));
                waypoints.add(new ProximityVector(97, 202));
                waypoints.add(new ProximityVector(117, 405));
                waypoints.add(new ProximityVector(490, 406));
            }

        }
    }
}
