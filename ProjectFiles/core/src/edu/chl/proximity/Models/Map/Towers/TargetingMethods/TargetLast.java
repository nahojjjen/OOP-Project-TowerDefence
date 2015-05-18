package edu.chl.proximity.Models.Map.Towers.TargetingMethods;

import edu.chl.proximity.Utilities.ProximityVector;
import edu.chl.proximity.Models.Map.Creeps.Creep;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Johan Swanberg
 * @date 2015-04-11
 *
 * 18/05 modified by Linda Evaldsson. Removed Map.
 */
public class TargetLast extends TargetingMethod {
    public TargetLast() {

    }


    /**
     * Get the creep which has traveled furthest on the path
     *
     * @param towerPosition
     * @param towerRange
     * @return
     */
    @Override
    public Creep getTarget(List<Creep> creeps, ProximityVector towerPosition, double towerRange) {
        if (creeps.size() > 0) { //can only get a target if there are enemies
            List<Creep> inRange = new ArrayList<Creep>();
            for(Creep c: creeps){
                if(isWithinRange(c,towerPosition,towerRange)){
                    inRange.add(c);
                }
            }
            if(inRange.size() <= 0){
                return null;
            }


            Creep target = inRange.get(0); //start with a creep so a comparison can be made
            double distanceToWaypoint = 0; //the first creep does not have to be checked, more efficient to hard-code in dummy data
            int waypointNumber = 99999;

            //cycle through all creeps, check if they're within range, get what waypoint they're on, remember the one with the highest waypoint & shortest distance to waypoint
            for (Creep creep : inRange) {
                if ((creep.getDistanceToNextWayPoint() > distanceToWaypoint && creep.getNextWayPointID() == waypointNumber) || creep.getNextWayPointID() < waypointNumber) {
                    target = creep;
                    distanceToWaypoint = creep.getDistanceToNextWayPoint();
                    waypointNumber = creep.getNextWayPointID();
                }


            }
            return target;
        }
        return null;
    }
}

