package edu.chl.proximity.Models.Towers.TargetingMethods;

import com.badlogic.gdx.math.Vector2;
import edu.chl.proximity.Models.Creeps.Creep;
import edu.chl.proximity.Utilities.PointCalculations;

/**
 * Created by Johan on 2015-04-11. Group work with Linda
 */
public abstract class TargetingMethod {

    public abstract Creep getTarget(Vector2 position, double range);

    public boolean isWithinRange(Creep creep, Vector2 towerPosition, double range){
            return PointCalculations.distanceBetweenNoSqrt(towerPosition, creep.getPosition()) < range*range;
        }
}
