package edu.chl.proximity.Models.Map.Paths.ConcretePaths;

import edu.chl.proximity.Utilities.ProximityVector;
import edu.chl.proximity.Models.Map.Paths.Path;

/**
 *  @author Johan Swanberg
 *  @date 2015-04-02
 */
public class FirstPath extends Path {




    public void initiatePoints(){
        if(waypoints != null) {
            if (waypoints.isEmpty()) {
                waypoints.add(new ProximityVector(2, 716));
                waypoints.add(new ProximityVector(435, 728));
                waypoints.add(new ProximityVector(821, 730));
                waypoints.add(new ProximityVector(1027, 724));
                waypoints.add(new ProximityVector(1016, 552));
                waypoints.add(new ProximityVector(957, 550));
                waypoints.add(new ProximityVector(949, 638));
                waypoints.add(new ProximityVector(674, 646));
                waypoints.add(new ProximityVector(451, 628));
                waypoints.add(new ProximityVector(457, 543));
                waypoints.add(new ProximityVector(472, 459));
                waypoints.add(new ProximityVector(265, 466));
                waypoints.add(new ProximityVector(109, 462));
                waypoints.add(new ProximityVector(104, 284));
                waypoints.add(new ProximityVector(117, 101));
                waypoints.add(new ProximityVector(313, 99));
                waypoints.add(new ProximityVector(322, 229));
                waypoints.add(new ProximityVector(304, 376));
                waypoints.add(new ProximityVector(592, 406));
                waypoints.add(new ProximityVector(886, 415));
                waypoints.add(new ProximityVector(1033, 401));
                waypoints.add(new ProximityVector(1047, 228));
                waypoints.add(new ProximityVector(1033, 120));
                waypoints.add(new ProximityVector(933, 98));
                waypoints.add(new ProximityVector(845, 106));
                waypoints.add(new ProximityVector(848, 272));
                waypoints.add(new ProximityVector(672, 260));

            }
        }
    }
}
