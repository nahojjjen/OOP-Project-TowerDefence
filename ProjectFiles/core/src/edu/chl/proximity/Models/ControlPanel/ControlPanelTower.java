package edu.chl.proximity.Models.ControlPanel;

import edu.chl.proximity.Utilities.ProximityVector;
import edu.chl.proximity.Models.BoardObject;
import edu.chl.proximity.Models.Map.Maps.Map;
import edu.chl.proximity.Models.Map.Towers.Tower;

/**
 * @author Linda Evaldsson
 * @date 2015-04-18
 *
 * ControlPanelTower is a class that can display Towers
 */
public class ControlPanelTower extends BoardObject{

    Tower tower;

    public ControlPanelTower(Map map, ProximityVector position, Tower tower) {
        super(map, position, tower.getImage(), 0);
        this.tower = tower;
    }

    /**
     * Returns a copy of the tower in this ControlPanelTower
     * @return a copy of tower
     */
    public Tower getTower(){
        try {
            return (Tower)tower.clone();
        } catch (CloneNotSupportedException e){
            System.out.println("ControlPanelTower: Error, Clone not supported");
        }
        return null;
    }
}
