package edu.chl.proximity.Controllers;

import com.badlogic.gdx.math.Vector2;
import edu.chl.proximity.Models.BoardObject;

/**
 * @author Linda Evaldsson
 * @date 2015-04-22
 *
 * This interface is for the Controllers that handle clicks, they should implement this interface
 */
public interface ClickHandler {

    /**
     * Method called when the mouse is clicked
     * @param clickedPoint the point that was clicked by the mouse
     * @param pointer the pointer for the event
     * @param button the button that was clicked
     */
    public void touchDown (Vector2 clickedPoint, int pointer, int button);

    //Todo: Possibly change from "BoardObject" to another type of object (some type of rectangle or Area-object)

    /**
     * Returns a model that reflects the area that the ClickHandler should be listening in to
     * @return a BoardObject that has the size and position for the area
     */
    public BoardObject getModel();

    /**
     * Method called when the mouse is moved
     * @param newPosition The position the mouse was moved to
     */
    public void mouseMoved (Vector2 newPosition);

}
