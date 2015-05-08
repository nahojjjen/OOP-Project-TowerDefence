package edu.chl.proximity.Models.Map.Towers.TargetingMethods;

import edu.chl.proximity.Models.Map.Maps.Map;

/**
 * @author Linda Evaldsson
 * @date 2015-05-06
 */
public class TargetingFactory {

    private TargetFirst targetFirst;
    private TargetLast targetLast;
    private TargetClosest targetClosest;


    public TargetingFactory(Map map) {
        targetFirst = new TargetFirst(map);
        targetLast = new TargetLast(map);
        targetClosest = new TargetClosest(map);
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
