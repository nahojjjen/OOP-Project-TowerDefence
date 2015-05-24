package edu.chl.proximity.Controllers.SubControllers;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.utils.viewport.Viewport;
import edu.chl.proximity.Models.Map.Maps.Map;
import edu.chl.proximity.Models.Map.Towers.Tower;
import edu.chl.proximity.Models.Map.Background;
import edu.chl.proximity.Models.BoardObject;
import edu.chl.proximity.Models.Holdables.Holdable;
import edu.chl.proximity.Models.Player.Players.GameData;
import edu.chl.proximity.Controllers.ScreenChanger.ScreenChanger;
import edu.chl.proximity.Utilities.Constants;
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
    private List<BoardObject> models = new ArrayList<BoardObject>();
    private Map map;

    public MapController(Map map) {
        this.map = map;
        model = new Background(null);
        model.setPosition(new ProximityVector(0,0));
        model.setWidth(Constants.GAME_WIDTH - 300);
        model.setHeight(Constants.GAME_HEIGHT);
        models.add(model);
    }


    public void update() {
        map.update();
        GameData.getInstance().getPlayer().addResources(map.getCollectedResources());
        GameData.getInstance().getPlayer().addExperiencePoints(map.getCollectedExperience());
        map.clearCollectedResources();
        map.clearCollectedExperience();
        if(map.getBase().getLife() <= 0)
            ScreenChanger.changeScreen(ScreenChanger.ScreenType.GameOver);
    }

    @Override
    public void touchDown(ProximityVector clickedPoint, int pointer, int button) {
        //Checks if the click is within the map
        if (model.containsPoint(clickedPoint)) {

            //Checks what item is currently picked up
            Holdable item = map.getHand().getItem();
            //Checks if anything was clicked on the board (Ex towers)
            BoardObject object = map.getObjectOnPosition(clickedPoint);
            if(object instanceof Tower) {
                map.setChosenTower((Tower) object);
            }else if(object==null && map.getChosenTower() != null){
                map.getHand().setItem(null);
            }

            if (item != null) {
                if(item instanceof Tower) {
                    if (!((Tower) item).isPlaced()) {
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
            GameData.getInstance().getPlayer().getResources().removeResources(item.getCost());
            item.preparePlacing(clickedPoint);


            map.add((BoardObject)item);

            map.getHand().setItem(null);
            if(item instanceof Tower){
                ((Tower) item).setAsPlaced(true);
                map.setChosenTower((Tower) item);

            }
        } else {
            //TODO: display error?
        }
    }


    @Override
    public void mouseMoved(ProximityVector newPosition) {

    }
}
