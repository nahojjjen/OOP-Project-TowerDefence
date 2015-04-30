package edu.chl.proximity.Models.Player.Holdables;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import edu.chl.proximity.Models.Utils.Image;

/**
 * @author Linda Evaldsson
 * @date 2015-04-22
 *
 * This is a class for handling what is currently picked upp, "in the hand".
 */
public class Hand {


    private Vector2 position;
    private Holdable currentItem;

    public Hand(){
        currentItem = null;
    }

    public void setItem(Holdable item) {
        currentItem = item;
    }

    public Holdable getItem() {
        return currentItem;
    }
    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }
    public void render(SpriteBatch batch) {
        if(currentItem != null) {
            Image img = currentItem.getImage();
            img.render(batch, new Vector2(position.x - img.getTexture().getWidth() / 2, position.y - img.getTexture().getHeight() / 2), 0);
        }
    }
}
