package edu.chl.proximity.Controllers.SubControllers;

import edu.chl.proximity.Models.Map.Maps.Map;
import edu.chl.proximity.Models.Utils.GameData;
import edu.chl.proximity.Models.Player.Spells.PersistentObject;

import java.util.ArrayList;

/**
 * @author simongislen
 * @date 23/04/15
 *
 * This class updates persistant objects on the map.
 * ---
 *
 */
public class PersistentObjectController {

    private Map map;

    public PersistentObjectController(Map map) {
        this.map = map;
    }

    public void update() {
        map.update();
    }
}
