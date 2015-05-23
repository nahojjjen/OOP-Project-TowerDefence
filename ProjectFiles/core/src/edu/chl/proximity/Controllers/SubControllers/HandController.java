package edu.chl.proximity.Controllers.SubControllers;

import edu.chl.proximity.Models.Map.Maps.Map;
import edu.chl.proximity.Models.Map.Background;
import edu.chl.proximity.Models.Player.Players.GameData;
import edu.chl.proximity.Utilities.Constants;
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
        model = new Background(null);

        //The model is the area where this HandController should be listening for changes. In this case its the whole window.
        model.setPosition(new ProximityVector(0,0));
        model.setWidth(Constants.GAME_WIDTH);
        model.setHeight(Constants.GAME_HEIGHT);
    }

    public void update() {
        map.getHand().setIfHandAfforded(GameData.getInstance().getPlayer().getResources());
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
