package edu.chl.proximity.Models.Map.Paths.ConcretePaths;

import edu.chl.proximity.Models.Map.Paths.Path;
import edu.chl.proximity.Utilities.ProximityVector;

/**
 * @author Johan
 * @date 5/22/2015.
 */
public class SnakePath extends Path{



    @Override
    public void initiatePoints() {

        if(waypoint != null) {
            if (waypoint.isEmpty()) {
                waypoint.add(new ProximityVector(14, 40));
                waypoint.add(new ProximityVector(276, 38));
                waypoint.add(new ProximityVector(541, 36));
                waypoint.add(new ProximityVector(814, 76));
                waypoint.add(new ProximityVector(890, 150));
                waypoint.add(new ProximityVector(916, 288));
                waypoint.add(new ProximityVector(895, 441));
                waypoint.add(new ProximityVector(835, 543));
                waypoint.add(new ProximityVector(684, 558));
                waypoint.add(new ProximityVector(657, 487));
                waypoint.add(new ProximityVector(683, 408));
                waypoint.add(new ProximityVector(694, 291));
                waypoint.add(new ProximityVector(612, 203));
                waypoint.add(new ProximityVector(445, 168));
                waypoint.add(new ProximityVector(349, 197));
                waypoint.add(new ProximityVector(404, 262));
                waypoint.add(new ProximityVector(493, 316));
                waypoint.add(new ProximityVector(488, 417));
                waypoint.add(new ProximityVector(412, 474));
                waypoint.add(new ProximityVector(310, 415));
                waypoint.add(new ProximityVector(263, 332));
                waypoint.add(new ProximityVector(210, 294));
                waypoint.add(new ProximityVector(145, 290));
                waypoint.add(new ProximityVector(110, 318));
                waypoint.add(new ProximityVector(105, 375));
                waypoint.add(new ProximityVector(134, 492));

            }

        }
    }

}
