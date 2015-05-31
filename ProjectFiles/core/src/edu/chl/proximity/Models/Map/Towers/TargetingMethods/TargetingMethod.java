package edu.chl.proximity.Models.Map.Towers.TargetingMethods;

import edu.chl.proximity.Models.Map.Creeps.Creep;
import edu.chl.proximity.Utilities.PointCalculations;
import edu.chl.proximity.Utilities.ProximityVector;

import java.util.List;

/**
 * Created by Johan on 2015-04-11. Group work with Linda
 *
 * An abstract targeting class used by towers for handling targeting logic that all concrete
 * targeting methods extends.
 *
 * ---
 * 18/05 modified by Linda Evaldsson. Removed Map.
 */
public abstract class TargetingMethod {

    public TargetingMethod() {
    }
    public abstract Creep getTarget(List<Creep> creeps, ProximityVector position, double range);

    public boolean isWithinRange(Creep creep, ProximityVector towerPosition, double range){
        return PointCalculations.distanceBetweenNoSqrt(towerPosition, creep.getCenter()) < range*range;
        }

}
