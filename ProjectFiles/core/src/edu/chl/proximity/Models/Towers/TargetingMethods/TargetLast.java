package edu.chl.proximity.Models.Towers.TargetingMethods;

import com.badlogic.gdx.math.Vector2;
import edu.chl.proximity.Models.Creeps.Creep;
import edu.chl.proximity.Models.GameData;
import edu.chl.proximity.Models.Maps.Map;
import edu.chl.proximity.Utilities.PointCalculations;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Johan Swanberg
 * @date 2015-04-11
 */
public class TargetLast extends TargetingMethod {

    private Map map = GameData.getInstance().getMap();
    private List<Creep> creeps = map.getCreeps();

    /**
     * Get the creep which has traveled furthest on the path
     *
     * @param towerPosition
     * @param towerRange
     * @return
     */
    @Override
    public Creep getTarget(Vector2 towerPosition, double towerRange) {
        if (creeps.size() > 0) { //can only get a target if there are enemies
            List<Creep> inRange = new ArrayList<Creep>();
            for (Creep c : creeps) {
                if (isWithinRange(c, towerPosition, towerRange)) {
                    System.out.println(isWithinRange(c, towerPosition, towerRange));
                    inRange.add(c);
                }
            }
            if (inRange.size() <= 0) {
                return null;
            }

            Creep target = inRange.get(0); //start with a creep so a comparison can be made
            double distanceToWaypoint = 0; //the first creep does not have to be checked, more efficient to hard-code in dummy data
            int waypointNumber = 99999;

            //cycle through all creeps, check if they're within range, get what waypoint they're on, remember the one with the highest waypoint & shortest distance to waypoint
            for (Creep creep : inRange) {
                if (creep.getDistanceToNextWayPoint() > distanceToWaypoint && creep.getNextWayPointID() < waypointNumber) {
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

