package edu.chl.proximity.Models.Map.Paths.ConcretePaths;

import edu.chl.proximity.Models.Map.Paths.Path;
import edu.chl.proximity.Utilities.ProximityVector;

/**
 * @author Linda Evaldsson and Johan Swanberg
 * @date 2015-04-13
 *
 * A class for handling a concrete path.
 */
public class StandardPath extends Path{


    @Override
    public void initiatePoints() {
        if (waypoints != null) {
            if (waypoints.isEmpty()) {
                waypoints.add(new ProximityVector(0, 100));
                waypoints.add(new ProximityVector(855, 100));
                waypoints.add(new ProximityVector(855, 173));
                waypoints.add(new ProximityVector(734, 173));
                waypoints.add(new ProximityVector(734, 243));
                waypoints.add(new ProximityVector(855, 243));
                waypoints.add(new ProximityVector(855, 309));
                waypoints.add(new ProximityVector(584, 309));
                waypoints.add(new ProximityVector(584, 206));
                waypoints.add(new ProximityVector(122, 206));
                waypoints.add(new ProximityVector(122, 518));
                waypoints.add(new ProximityVector(759, 518));

                /*
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
            */}

        }
    }
}
