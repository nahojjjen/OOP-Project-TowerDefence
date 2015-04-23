package edu.chl.proximity.Models.Holdables;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import edu.chl.proximity.Models.Image;

/**
 * @author Linda Evaldsson
 * @date 2015-04-22
 */
public interface Holdable {

    /**
     * Puts the object down on the map
     */
    public void placeObject(Vector2 position);

    public void render(SpriteBatch batch);

    public Image getImage();

}
