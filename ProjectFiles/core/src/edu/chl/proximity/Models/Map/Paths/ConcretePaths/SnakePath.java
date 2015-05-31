package edu.chl.proximity.Models.Map.Paths.ConcretePaths;

import edu.chl.proximity.Models.Map.Paths.Path;
import edu.chl.proximity.Utilities.ProximityVector;

/**
 * @author Johan
 * @date 2015-05-22
 *
 * A class for handling a concrete path.
 */
public class SnakePath extends Path{



    @Override
    public void initiatePoints() {

        if(waypoints != null) {
            if (waypoints.isEmpty()) {
                waypoints.add(new ProximityVector(14, 40));
                waypoints.add(new ProximityVector(276, 38));
                waypoints.add(new ProximityVector(541, 36));
                waypoints.add(new ProximityVector(814, 76));
                waypoints.add(new ProximityVector(890, 150));
                waypoints.add(new ProximityVector(916, 288));
                waypoints.add(new ProximityVector(895, 441));
                waypoints.add(new ProximityVector(835, 543));
                waypoints.add(new ProximityVector(684, 558));
                waypoints.add(new ProximityVector(657, 487));
                waypoints.add(new ProximityVector(683, 408));
                waypoints.add(new ProximityVector(694, 291));
                waypoints.add(new ProximityVector(612, 203));
                waypoints.add(new ProximityVector(445, 168));
                waypoints.add(new ProximityVector(349, 197));
                waypoints.add(new ProximityVector(404, 262));
                waypoints.add(new ProximityVector(493, 316));
                waypoints.add(new ProximityVector(488, 417));
                waypoints.add(new ProximityVector(412, 474));
                waypoints.add(new ProximityVector(310, 415));
                waypoints.add(new ProximityVector(263, 332));
                waypoints.add(new ProximityVector(210, 294));
                waypoints.add(new ProximityVector(145, 290));
                waypoints.add(new ProximityVector(110, 318));
                waypoints.add(new ProximityVector(105, 375));
                waypoints.add(new ProximityVector(134, 492));

            }

        }
    }

}
