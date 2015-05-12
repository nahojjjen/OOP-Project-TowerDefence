package edu.chl.proximity.Controllers.SubControllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import edu.chl.proximity.Controllers.ClickHandler;
import edu.chl.proximity.Models.Map.Maps.Map;
import edu.chl.proximity.Models.Map.Towers.Tower;
import edu.chl.proximity.Models.Utils.Background;
import edu.chl.proximity.Models.BoardObject;
import edu.chl.proximity.Models.Utils.GameData;
import edu.chl.proximity.Models.Player.Holdables.Holdable;
import edu.chl.proximity.Utilities.PointCalculations;
import edu.chl.proximity.Utilities.ProximityVector;

import java.util.ArrayList;
import java.util.List;


/**
 * @author Linda Evaldsson
 * @date 2015-04-22
 *
 * A class for controlling clicks on the Map.
 *
 * 03-05-2015 Modified by Simon Gislen. Introducing: *Towers are not free*
 *
 */

//Todo: Possibly add update-method and add CreepController, ProjectileController, WaveController and TowerController to this Controller
public class MapController implements ClickHandler {

    private Background model;
    private int tempCounter = 0;
    private List<BoardObject> models = new ArrayList<BoardObject>();
    private Map map;

    public MapController(Map map) {
        this.map = map;
        model = new Background(null);
        model.setPosition(new ProximityVector(0,0));
        model.setWidth(Gdx.graphics.getWidth() - 300);
        model.setHeight(Gdx.graphics.getHeight());
        models.add(model);
    }


    @Override
    public void touchDown(ProximityVector clickedPoint, int pointer, int button) {
        //Checks if the click is within the map
        if (model.containsPoint(clickedPoint)) {
            //Checks what item is currently picked up
            Holdable item = map.getHand().getItem();

            BoardObject object = map.getObjectOnPosition(clickedPoint);
            if(object instanceof Tower)
                map.setChoosenTower((Tower)object);


            if (item != null) {
                //Places the currently picked up item on the map if the player can afford it.
                if (map.getHand().canPlayerAffordTheHand()) {
                    item.placeObject(clickedPoint);
                    if(item instanceof Tower){
                        map.setChoosenTower((Tower) item);
                    }
                } else {
                    //TODO: display error?
                }
            }

        }else{
            map.getHand().setItem(null);
        }
    }


    @Override
    public void mouseMoved(ProximityVector newPosition) {

    }
}
