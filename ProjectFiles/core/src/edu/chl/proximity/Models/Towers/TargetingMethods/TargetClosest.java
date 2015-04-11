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
 * Created by Johan on 2015-04-11. Group work with Linda
 */
public class TargetClosest implements TargetingMethod{
    public Map map = GameData.getInstance().getMap();


    public Creep getTarget(Vector2 position, double range){
        double rangePow = range*range;
        double rangeToClosest;
        Creep closestCreep;
        if(map.getCreeps().size() > 0){
            closestCreep = map.getCreeps().get(0);
            rangeToClosest = PointCalculations.distanceBetweenNoSqrt(closestCreep.getPosition(), position);

            for (Creep creep:map.getCreeps()){
                double rangeToCurrent = PointCalculations.distanceBetweenNoSqrt(position, creep.getPosition());
                if (rangeToCurrent < rangePow){
                    if (rangeToCurrent < rangeToClosest){
                        rangeToClosest = rangeToCurrent;
                        closestCreep = creep;
                    }
                }
            }

            return closestCreep;
        }
        return null;
    }

}
