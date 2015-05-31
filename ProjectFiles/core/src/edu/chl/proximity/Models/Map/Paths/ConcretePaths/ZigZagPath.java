package edu.chl.proximity.Models.Map.Paths.ConcretePaths;

import edu.chl.proximity.Models.Map.Paths.Path;
import edu.chl.proximity.Utilities.ProximityVector;

/**
 * @author Johan Swanberg
 * @date 2015-05-22
 *
 * A class for handling a concrete path.
 */
public class ZigZagPath extends Path {


    @Override
    public void initiatePoints() {
        if (waypoints != null) {
            if (waypoints.isEmpty()) {
                waypoints.add(new ProximityVector(973, 4));
                waypoints.add(new ProximityVector(807, 519));
                waypoints.add(new ProximityVector(643, 45));
                waypoints.add(new ProximityVector(528, 510));
                waypoints.add(new ProximityVector(377, 49));
                waypoints.add(new ProximityVector(318, 548));
                waypoints.add(new ProximityVector(157, 46));
                waypoints.add(new ProximityVector(59, 670));

            }
        }
    }
}

