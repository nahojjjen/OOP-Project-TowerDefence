package edu.chl.proximity.Controllers.SubControllers;

import com.badlogic.gdx.Gdx;
import edu.chl.proximity.Controllers.ClickHandler;
import edu.chl.proximity.Models.Map.Maps.Map;
import edu.chl.proximity.Models.Map.Towers.Tower;
import edu.chl.proximity.Models.Utils.Background;
import edu.chl.proximity.Models.BoardObject;
import edu.chl.proximity.Models.Player.Holdables.Holdable;
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


    public void update() {
        map.update();
    }

    @Override
    public void touchDown(ProximityVector clickedPoint, int pointer, int button) {
        //Checks if the click is within the map
        if (model.containsPoint(clickedPoint)) {

            //Checks what item is currently picked up
            Holdable item = map.getHand().getItem();

            BoardObject object = map.getObjectOnPosition(clickedPoint);
            if(object instanceof Tower) {
                //map.getHand().setItem((Tower) object);
                map.setChoosenTower((Tower) object);
            }

            if (item != null) {
                if(item instanceof Tower) {
                    if (!((Tower) item).getIfPlaced()) {
                        placeHandObject(item, clickedPoint);
                    }
                }else {
                    placeHandObject(item, clickedPoint);
                }
            }
        }
    }

    private void placeHandObject(Holdable item, ProximityVector clickedPoint){
        if (map.getHand().canPlayerAffordTheHand()) {
            item.preparePlacing(clickedPoint);
            map.add((BoardObject)item);
            map.getHand().setItem(null);
            if(item instanceof Tower){
                ((Tower) item).setAsPlaced(true);
                map.setChoosenTower((Tower) item);

            }
        } else {
            //TODO: display error?
        }
    }


    @Override
    public void mouseMoved(ProximityVector newPosition) {

    }
}
