package edu.chl.proximity.Models.Map.Towers;

import edu.chl.proximity.Models.Map.Towers.TargetingMethods.TargetingMethod;

/**
 * @author Linda Evaldsson
 * @date 2015-05-22
 *
 * An interface for towers that can target creeps
 */

public interface TargetingTower {

    void setTargetingMethod(TargetingMethod targetingMethod);
    TargetingMethod getTargetingMethod();
}
