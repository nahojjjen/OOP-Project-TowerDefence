package edu.chl.proximity.Models.Map.Towers.TargetingMethods;

import edu.chl.proximity.Utilities.ProximityVector;
import edu.chl.proximity.Models.Map.Creeps.Creep;
import edu.chl.proximity.Models.Utils.GameData;
import edu.chl.proximity.Models.Map.Maps.Map;
import edu.chl.proximity.Utilities.PointCalculations;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Johan Swanberg and Linda Evaldsson (group work)
 * @date 2015-04-11
 *
 */
public class TargetClosest extends TargetingMethod{


    public TargetClosest(Map map) {
        super(map);
    }

    public Creep getTarget(ProximityVector position, double range){
        double rangeToClosest;
        Creep closestCreep;

        if(getMap().getCreeps().size() > 0){
            List<Creep> inRange = new ArrayList<Creep>();
            for(Creep c: getMap().getCreeps()){
                if(isWithinRange(c,position,range)){
                    inRange.add(c);
                }
            }
            if(inRange.size() <= 0){
                return null;
            }

            closestCreep = inRange.get(0);
            rangeToClosest = PointCalculations.distanceBetweenNoSqrt(closestCreep.getPosition(), position);

            for (Creep creep:inRange){
                double rangeToCurrent = PointCalculations.distanceBetweenNoSqrt(position, creep.getPosition());
                if (rangeToCurrent < rangeToClosest){
                    rangeToClosest = rangeToCurrent;
                    closestCreep = creep;
                }
            }

            return closestCreep;
        }
        return null;
    }

}
