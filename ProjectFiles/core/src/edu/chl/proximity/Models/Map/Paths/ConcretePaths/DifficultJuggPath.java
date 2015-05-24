package edu.chl.proximity.Models.Map.Paths.ConcretePaths;

import edu.chl.proximity.Models.Map.Paths.Path;
import edu.chl.proximity.Utilities.ProximityVector;

/**
 * @author Johan
 * @date 5/22/2015.
 */
public class DifficultJuggPath extends Path {



    @Override
    public void initiatePoints() {
        if (waypoints != null) {
            if (waypoints.isEmpty()) {
                waypoints.add(new ProximityVector(972, 8));
                waypoints.add(new ProximityVector(341, 530));
                waypoints.add(new ProximityVector(640, 525));
                waypoints.add(new ProximityVector(161, 134));
            }
        }
    }
}
