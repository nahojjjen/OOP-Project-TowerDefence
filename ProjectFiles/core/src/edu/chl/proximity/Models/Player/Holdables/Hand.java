package edu.chl.proximity.Models.Player.Holdables;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import edu.chl.proximity.Models.Map.Towers.Tower;
import edu.chl.proximity.Models.Player.Spells.Spell;
import edu.chl.proximity.Models.Utils.GameData;
import edu.chl.proximity.Models.Utils.Image;

/**
 * @author Linda Evaldsson
 * @date 2015-04-22
 *
 * This is a class for handling what is currently picked upp, "in the hand".
 *
 * 03-05-2015 Modified by Simon Gislen. Introducing: *Towers are not free*
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
            if(img != null) {
                img.render(batch, new Vector2(position.x - img.getTexture().getWidth() / 2, position.y - img.getTexture().getHeight() / 2), 0);
            }
        }
    }
    public Color getRangeIndicatorColor() {
        if (currentItem instanceof Tower) {
            if (canPlayerAffordTheHand()) {
                return new Color(0.4f, 0.2f, 0.9f, 0.2f);
            }
            else {
                return new Color(0.9f, 0.1f, 0.1f, 0.2f);
            }
        }

        return new Color(0.2f, 0.9f, 0.2f, 0.2f);
    }

    //Helper to check whether the player affords what is in his hand
    public boolean canPlayerAffordTheHand() {
        if (currentItem instanceof Tower) {
            Tower tower = (Tower) currentItem;
            return GameData.getInstance().getPlayer().canPlayerAfford(tower.getCost());
        }
        else if (currentItem instanceof Spell) {
            //TODO: check energy costs
        }
        return true;
    }
}
