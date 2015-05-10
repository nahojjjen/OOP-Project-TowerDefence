package edu.chl.proximity.Controllers.SubControllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import edu.chl.proximity.Controllers.ClickHandler;
import edu.chl.proximity.Models.Map.Maps.Map;
import edu.chl.proximity.Models.Utils.Background;
import edu.chl.proximity.Models.Utils.GameData;
import edu.chl.proximity.Utilities.ProximityVector;

/**
 * @author Linda Evaldsson
 * @date 2015-04-2
 *
 * A class for updating the hand
 *
 */
public class HandController implements ClickHandler {
    private Background model;
    private Map map;

    public HandController(Map map) {
        this.map = map;
        model = new Background(map, null);

        //The model is the area where this HandController should be listening for changes. In this case its the whole window.
        model.setPosition(new ProximityVector(0,0));
        model.setWidth(Gdx.graphics.getWidth());
        model.setHeight(Gdx.graphics.getHeight());
    }


    @Override
    public void touchDown(ProximityVector clickedPoint, int pointer, int button) {

    }

    @Override
    public void mouseMoved(ProximityVector newPosition) {

        //Updates the position of the hand to be the position of the mouse
        map.getHand().setPosition(newPosition);
    }
}
