package edu.chl.proximity.Models.Map.Towers.TargetingMethods;

import edu.chl.proximity.Models.Map.Creeps.Creep;
import edu.chl.proximity.Utilities.ProximityVector;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Johan
 * @date 2015-04-11.
 *
 * 18/05 modified by Linda Evaldsson. Removed Map.
 */


public class TargetFirst extends TargetingMethod{

    public TargetFirst() {

    }


    /**
     * Get the creep which has traveled furthest on the path
     * @param towerPosition
     * @param towerRange
     * @return
     */
    @Override
    public Creep getTarget(List<Creep> creeps, ProximityVector towerPosition, double towerRange) {
        if (creeps.size() > 0){ //can only get a target if there are enemies
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
            double distanceToWaypoint = 9999999999.9; //the first creep does not have to be checked, more efficient to hard-code in dummy data
            int waypointNumberOfFirstCreep = 0;

            //cycle through all creeps, get what waypoints they're on, remember the one with the highest waypoints & shortest distance to waypoints
            for (Creep creep:inRange){
                    if ((creep.getDistanceToNextWayPoint() < distanceToWaypoint && creep.getNextWayPointID() == waypointNumberOfFirstCreep)|| creep.getNextWayPointID() >= waypointNumberOfFirstCreep){
                        target = creep;
                        distanceToWaypoint = creep.getDistanceToNextWayPoint();
                        waypointNumberOfFirstCreep = creep.getNextWayPointID();
                    }


            }
            return target;
        }
        return null;
    }

}
