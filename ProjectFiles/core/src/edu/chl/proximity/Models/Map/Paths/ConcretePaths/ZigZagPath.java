package edu.chl.proximity.Models.Map.Paths.ConcretePaths;

import edu.chl.proximity.Models.Map.Paths.Path;
import edu.chl.proximity.Utilities.ProximityVector;

/**
 * @author Johan
 * @date 5/22/2015.
 */
public class ZigZagPath extends Path {


    @Override
    public void initiatePoints() {
        if (waypoint != null) {
            if (waypoint.isEmpty()) {
                waypoint.add(new ProximityVector(973, 4));
                waypoint.add(new ProximityVector(807, 519));
                waypoint.add(new ProximityVector(643, 45));
                waypoint.add(new ProximityVector(528, 510));
                waypoint.add(new ProximityVector(377, 49));
                waypoint.add(new ProximityVector(318, 548));
                waypoint.add(new ProximityVector(157, 46));
                waypoint.add(new ProximityVector(59, 670));

            }
        }
    }
}

