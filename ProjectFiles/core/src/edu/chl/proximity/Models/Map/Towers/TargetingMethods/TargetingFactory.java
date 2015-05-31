package edu.chl.proximity.Models.Map.Towers.TargetingMethods;


/**
 * @author Linda Evaldsson
 * @date 2015-05-06
 *
 * A targeting factory that can handle all the targeting methods
 *
 * ---
 * 18/05 modified by Linda Evaldsson. Removed Map.
 */
public class TargetingFactory {

    private TargetFirst targetFirst;
    private TargetLast targetLast;
    private TargetClosest targetClosest;


    public TargetingFactory() {
        targetFirst = new TargetFirst();
        targetLast = new TargetLast();
        targetClosest = new TargetClosest();
    }

    public TargetingMethod getTargetFirst() {
        return targetFirst;
    }
    public TargetingMethod getTargetLast() {
        return targetLast;
    }
    public TargetingMethod getTargetClosest() {
        return targetClosest;
    }
}
