package edu.chl.proximity.Controllers.SubControllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import edu.chl.proximity.Controllers.ClickHandler;
import edu.chl.proximity.Models.Utils.Background;
import edu.chl.proximity.Models.BoardObject;
import edu.chl.proximity.Models.Utils.GameData;
import edu.chl.proximity.Models.Player.Holdables.Holdable;

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

    private Background model = new Background(null);
    private int tempCounter = 0;
    private List<BoardObject> models = new ArrayList<BoardObject>();

    public MapController() {
        model.setPosition(new Vector2(0,0));
        model.setWidth(Gdx.graphics.getWidth() - 300);
        model.setHeight(Gdx.graphics.getHeight());
        models.add(model);
    }


    @Override
    public void touchDown(Vector2 clickedPoint, int pointer, int button) {

        //Checks if the click is within the map
        if (model.containsPoint(clickedPoint)) {
            //Checks what item is currently picked up
            Holdable item = GameData.getInstance().getHand().getItem();

            if (item != null) {
                //Places the currently picked up item on the map if the player can afford it.
                if (GameData.getInstance().getHand().canPlayerAffordTheHand()) {
                    item.placeObject(clickedPoint);
                } else {
                    //TODO: display error?
                }
            }

        }
    }


    @Override
    public void mouseMoved(Vector2 newPosition) {

    }
}
