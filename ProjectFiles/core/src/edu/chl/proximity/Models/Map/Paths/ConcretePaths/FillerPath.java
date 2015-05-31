package edu.chl.proximity.Models.Map.Paths.ConcretePaths;

import edu.chl.proximity.Models.Map.Paths.Path;
import edu.chl.proximity.Utilities.ProximityVector;

/**
 * @author Linda Evaldsson
 * @date 2015-04-09
 *
 * A class for handling a concrete path.
 */
public class FillerPath extends Path {



    @Override
    public void initiatePoints() {
        if(waypoints != null) {
            if (waypoints.isEmpty()) {

                waypoints.add(new ProximityVector(132, 0));
                waypoints.add(new ProximityVector(169, 258));
                waypoints.add(new ProximityVector(337, 265));
                waypoints.add(new ProximityVector(738, 319));
                waypoints.add(new ProximityVector(638, 556));
                waypoints.add(new ProximityVector(186, 611));
                waypoints.add(new ProximityVector(678, 38));
            }
        }
    }
}
