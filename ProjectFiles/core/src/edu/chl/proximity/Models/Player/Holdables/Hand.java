package edu.chl.proximity.Models.Player.Holdables;

import com.badlogic.gdx.graphics.Color;
import edu.chl.proximity.Models.Utils.ProximityBatch;
import edu.chl.proximity.Utilities.ProximityShapeRenderer;
import edu.chl.proximity.Utilities.ProximityVector;
import edu.chl.proximity.Models.Map.Towers.Tower;
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


    private ProximityVector position;
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
    public ProximityVector getPosition() {
        return position;
    }

    public void setPosition(ProximityVector position) {
        this.position = position;
    }
    public void render(ProximityBatch batch) {
        if(currentItem != null) {
            if(currentItem instanceof Tower){
                if(!((Tower) currentItem).getIfPlaced()){
                    batchRendering(batch);
                }
            }else{
                batchRendering(batch);
            }
        }
    }
    private void batchRendering(ProximityBatch batch){
        Image img = currentItem.getImage();
        if(img != null) {
            batch.render(img, new ProximityVector(position.x - img.getTexture().getWidth() / 2, position.y - img.getTexture().getHeight() / 2), 0);
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
        return GameData.getInstance().getPlayer().canPlayerAfford(currentItem.getCost());
    }

    public void render(ProximityShapeRenderer shapeRenderer) {


        if(currentItem instanceof Tower){
            if(!((Tower) currentItem).getIfPlaced()){
                shapeRenderer.renderRangeIndicator(getPosition(), getItem().getRange(), getRangeIndicatorColor());
            }else{
                shapeRenderer.renderRangeIndicator(((Tower) currentItem).getCenter(), getItem().getRange(), new Color(0.4f, 0.2f, 0.9f, 0.2f));
            }
        }else {
            shapeRenderer.renderRangeIndicator(getPosition(), getItem().getRange(), getRangeIndicatorColor());
        }
    }
}
