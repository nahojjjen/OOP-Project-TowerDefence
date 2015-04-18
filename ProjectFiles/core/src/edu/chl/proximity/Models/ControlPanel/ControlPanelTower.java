package edu.chl.proximity.Models.ControlPanel;

import com.badlogic.gdx.math.Vector2;
import edu.chl.proximity.Models.BoardObject;
import edu.chl.proximity.Models.Image;
import edu.chl.proximity.Models.Towers.Tower;

/**
 * @author Linda Evaldsson
 * @date 2015-04-18
 */
public class ControlPanelTower extends BoardObject{

    Tower tower;

    public ControlPanelTower(Vector2 position, Tower tower) {
        super(position, tower.getImage(), 0);
        this.tower = tower;
    }

    /**
     * Returns a copy of the tower in this ControlPanelTower
     * @return a copy of tower
     */
    public Tower getTower(){
        //Todo: Make it return copy of tower
        System.out.println("ControlPanelTower: Tower: " + tower.getClass());
        return null;
    }
}
