package edu.chl.proximity.Controllers.SubControllers;

import edu.chl.proximity.Models.Map.Creeps.Creep;
import edu.chl.proximity.Models.Map.Maps.Map;
import edu.chl.proximity.Models.Utils.GameData;

import java.util.List;

/**
 * Created by Johan on 2015-04-11. Group work with Linda
 */
public class CreepController {
    private List<Creep> creeps;
    private Map map;

    public CreepController(Map map){
        creeps = map.getCreeps();
    }


    public void update() {
        for (Creep creep : creeps) {
            creep.rotate();
            creep.move();
        }
    }

}
