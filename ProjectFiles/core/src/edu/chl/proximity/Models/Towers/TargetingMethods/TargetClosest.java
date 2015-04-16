package edu.chl.proximity.Models.Towers.TargetingMethods;

import com.badlogic.gdx.math.Vector2;
import edu.chl.proximity.Models.Creeps.Creep;
import edu.chl.proximity.Models.GameData;
import edu.chl.proximity.Models.Maps.Map;
import edu.chl.proximity.Utilities.PointCalculations;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Johan Swanberg and Linda Evaldsson (group work)
 * @date 2015-04-11
 *
 */
public class TargetClosest extends TargetingMethod{
    public Map map = GameData.getInstance().getMap();


    public Creep getTarget(Vector2 position, double range){
        double rangeToClosest;
        Creep closestCreep;
        List<Creep> creeps = map.getCreeps();

        if(map.getCreeps().size() > 0){
            List<Creep> inRange = new ArrayList<Creep>();
            for (Creep c : creeps) {
                if (isWithinRange(c, position, range)) {
                    System.out.println(isWithinRange(c, position, range));
                    inRange.add(c);
                }
            }
            if (inRange.size() <= 0) {
                return null;
            }

            closestCreep = inRange.get(0);
            rangeToClosest = PointCalculations.distanceBetweenNoSqrt(closestCreep.getPosition(), position);

            for (Creep creep:inRange){
                double rangeToCurrent = PointCalculations.distanceBetweenNoSqrt(position, creep.getPosition());
                //if (isWithinRange(creep, position, range)){
                    if (rangeToCurrent < rangeToClosest){
                        rangeToClosest = rangeToCurrent;
                        closestCreep = creep;
                    }
                //}
            }

            return closestCreep;
        }
        return null;
    }

}
