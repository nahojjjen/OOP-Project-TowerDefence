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
            creep.rotate();
            creep.move();
        }
    }

}
