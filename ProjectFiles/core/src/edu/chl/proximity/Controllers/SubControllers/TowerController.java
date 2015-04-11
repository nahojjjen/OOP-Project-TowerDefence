package edu.chl.proximity.Controllers.SubControllers;

import com.badlogic.gdx.math.Vector2;
import edu.chl.proximity.Models.Creeps.Creep;
import edu.chl.proximity.Models.GameData;
import edu.chl.proximity.Models.Maps.Map;
import edu.chl.proximity.Models.Towers.Tower;

import java.util.List;

/**
 * Created by Johan on 2015-04-11. Group work with Linda
 */
public class TowerController {


    private List<Tower> towers;


    public TowerController(){
        Map map = GameData.getInstance().getMap();
        towers = map.getTowers();
    }
    /**
     * Target the closest creep and attempt to fire
     */
    public void update() {
        for (Tower tower : towers) {
            tower.target();
            tower.shoot();
            tower.reload();
        }
    }
}
