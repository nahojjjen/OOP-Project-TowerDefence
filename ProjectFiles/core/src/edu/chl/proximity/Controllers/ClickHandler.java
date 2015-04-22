package edu.chl.proximity.Controllers;

import com.badlogic.gdx.math.Vector2;
import edu.chl.proximity.Models.BoardObject;

/**
 * @author Linda Evaldsson
 * Created by Linda on 2015-04-22.
 */
public interface ClickHandler {

    public void touchDown (Vector2 clickedPoint, int pointer, int button);

    public BoardObject getModel();

}
