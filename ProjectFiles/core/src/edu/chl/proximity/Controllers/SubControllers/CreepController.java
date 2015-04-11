package edu.chl.proximity.Controllers.SubControllers;

import com.badlogic.gdx.math.Vector2;
import edu.chl.proximity.Models.Creeps.Creep;
import edu.chl.proximity.Models.GameData;
import edu.chl.proximity.Utilities.PointCalculations;

import java.awt.*;
import java.util.List;

/**
 * Created by Johan on 2015-04-11. Group work with Linda
 */
public class CreepController {
    private List<Creep> creeps;

    public CreepController(){
        creeps = GameData.getInstance().getMap().getCreeps();
    }


    public void update() {
        for (Creep creep : creeps) {
            creep.rotate(); //TODO: rotate creep angle every turn
            creep.move();
            //TODO: fix so creeps that intersects base decrease its health
        }
    }




    /**
     * get the creep that was added first in the list
     *
     * @return the creep with position 0  in the creep list
     */
    public Creep getFirstCreepInList(Point p) { //gör så den kollar på p istället för list.get(0)
        if (creeps.size() != 0) {
            return creeps.get(0);
        }
        return null;
    }

}
