package edu.chl.proximity.Controllers.SubControllers;

import edu.chl.proximity.Models.Utils.GameData;
import edu.chl.proximity.Models.Map.Maps.Map;
import edu.chl.proximity.Models.Map.Towers.Tower;

import java.util.List;

/**
 * Created by Johan on 2015-04-11. Group work with Linda
 */
public class TowerController {


    private List<Tower> towers;


    public TowerController(Map map){
        towers = map.getTowers();
    }
    /**
     * Target the closest creep and attempt to fire
     */
    public void update() {
        for (Tower tower : towers) {
            tower.update();
        }
    }
}
