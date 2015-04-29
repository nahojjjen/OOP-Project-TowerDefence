package edu.chl.proximity.Controllers;

import com.badlogic.gdx.math.Vector2;
import edu.chl.proximity.Models.BoardObject;

import java.util.List;

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

    /**
     * Method that checks wheter this controllers models are clicked
     * @param clickedPoint The point that was clicked
     * @return true if the models are clicked, false otherwise
     */
    public boolean isModelClicked(Vector2 clickedPoint);

    /**
     * Method called when the mouse is moved
     * @param newPosition The position the mouse was moved to
     */
    public void mouseMoved (Vector2 newPosition);

}
