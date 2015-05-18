package edu.chl.proximity.Models.Map.Towers.TargetingMethods;

import edu.chl.proximity.Utilities.ProximityVector;
import edu.chl.proximity.Models.Map.Creeps.Creep;
import edu.chl.proximity.Utilities.PointCalculations;

import java.util.List;

/**
 * Created by Johan on 2015-04-11. Group work with Linda
 *
 * 18/05 modified by Linda Evaldsson. Removed Map.
 */
public abstract class TargetingMethod {

    public TargetingMethod() {
    }
    public abstract Creep getTarget(List<Creep> creeps, ProximityVector position, double range);

    public boolean isWithinRange(Creep creep, ProximityVector towerPosition, double range){
        return PointCalculations.distanceBetweenNoSqrt(towerPosition, creep.getPosition()) < range*range;
        }

}
