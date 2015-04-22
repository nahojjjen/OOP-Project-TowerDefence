package edu.chl.proximity.Controllers.SubControllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import edu.chl.proximity.Controllers.ClickHandler;
import edu.chl.proximity.Models.Backgrounds.Background;
import edu.chl.proximity.Models.BoardObject;
import edu.chl.proximity.Models.GameData;

/**
 * @author Linda Evaldsson
 * @date 2015-04-2
 *
 * A class for updating the hand
 *
 */
public class HandController implements ClickHandler {

    private Background model = new Background(null);

    public HandController() {
        //The model is the area where this HandController should be listening for changes. In this case its the whole window.
        model.setPosition(new Vector2(0,0));
        model.setWidth(Gdx.graphics.getWidth());
        model.setHeight(Gdx.graphics.getHeight());
    }


    @Override
    public void touchDown(Vector2 clickedPoint, int pointer, int button) {

    }

    @Override
    public BoardObject getModel() {
        return model;
    }

    @Override
    public void mouseMoved(Vector2 newPosition) {

        //Updates the position of the hand to be the position of the mouse
        GameData.getInstance().getHand().setPosition(newPosition);
    }
}
