package edu.chl.proximity.Models.Holdables;

import com.badlogic.gdx.graphics.Color;
import edu.chl.proximity.Models.Map.Paths.Path;
import edu.chl.proximity.Models.Map.Towers.Tower;
import edu.chl.proximity.Models.ResourceSystem.Resources;
import edu.chl.proximity.Models.Utils.ProximityBatch;
import edu.chl.proximity.Utilities.ProximityShapeRenderer;
import edu.chl.proximity.Utilities.ProximityVector;
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
    private boolean handAfforded = false;

    private Path path;

    public Hand(){
        currentItem = null;
    }

    public void setPath(Path path){
        this.path=path;
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
            if(!currentItem.isPlaced()) {
                batchRendering(batch);
            }
        }
    }
    private void batchRendering(ProximityBatch batch){
        if (currentItem != null){
            Image img = currentItem.getImage();
            if(img != null) {
                batch.render(img, new ProximityVector(position.x - img.getTexture().getWidth() / 2f, position.y - img.getTexture().getHeight() / 2f), 0);
            }
        }

    }

    public Color getRangeIndicatorColor() {
        if(currentItem instanceof Tower && path!=null) {
            if(path.isPointInHitbox(position)){
                return new Color(0.9f, 0.7f, 0.1f, 0.2f);
            }
        }
        if (canPlayerAffordTheHand()) {
            return currentItem.getColor();
        }
        else {
            return new Color(0.9f, 0.1f, 0.1f, 0.2f);
        }
    }

    public void setIfHandAfforded(Resources playerResources) {
        if(currentItem == null)
            return;

        Resources itemCost = currentItem.getCost();
        handAfforded = playerResources.getPoints() >= itemCost.getPoints() && playerResources.getPolygons() >= itemCost.getPolygons() && playerResources.getLines() >= itemCost.getLines();
    }

    //Helper to check whether the player affords what is in his hand
    public boolean canPlayerAffordTheHand() {
        return handAfforded;
    }

    public void render(ProximityShapeRenderer shapeRenderer) {
        if(!(currentItem.isPlaced())) {
            shapeRenderer.renderRangeIndicator(getPosition(), getItem().getRange(), getRangeIndicatorColor());
        } else {
            shapeRenderer.renderRangeIndicator(currentItem.getCenter(), getItem().getRange(), new Color(0.4f, 0.2f, 0.9f, 0.2f));
        }
    }
}
