package edu.chl.proximity.Controllers.SubControllers;

import edu.chl.proximity.Models.Map.Maps.Map;
import edu.chl.proximity.Models.Player.Players.GameData;
import edu.chl.proximity.Models.Map.MouseOver.InformationCollector;
import edu.chl.proximity.Utilities.ProximityVector;

/**
 * @author Linda Evaldsson
 * @date 2015-04-2
 *
 * A controller for updating the hand
 *
 */
public class HandController implements ClickHandler, UpdateHandler {

    private Map map;

    public HandController(Map map) {
        this.map = map;
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

        InformationCollector.mouseMoved(newPosition);
    }
}
