package edu.chl.proximity.Controllers.SubControllers;

import edu.chl.proximity.Models.Utils.GameData;
import edu.chl.proximity.Models.Map.Maps.Map;
import edu.chl.proximity.Models.Map.Towers.Tower;

import java.util.List;

/**
 * Created by Johan on 2015-04-11. Group work with Linda
 */
public class TowerController {


    private Map map;


    public TowerController(Map map){
        this.map = map;
    }
    /**
     * Target the closest creep and attempt to fire
     */
    public void update() {
        map.update();
    }
}
