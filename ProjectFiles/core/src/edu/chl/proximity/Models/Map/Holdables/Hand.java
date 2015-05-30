package edu.chl.proximity.Models.Map.Holdables;

import com.badlogic.gdx.graphics.Color;
import edu.chl.proximity.Models.Map.Paths.Path;
import edu.chl.proximity.Models.ResourceSystem.Resources;
import edu.chl.proximity.Models.Utils.Image;
import edu.chl.proximity.Models.Utils.ProximityBatch;
import edu.chl.proximity.Models.Utils.ProximityShapeRenderer;
import edu.chl.proximity.Utilities.ProximityVector;

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

    /**
     * Gets the color of the hand items indicator
     * @return
     */
    public Color getRangeIndicatorColor() {
        if(!currentItem.canBePlacedOnPath() && path!=null) {
            if(isObjectOnLine()){
                return new Color(0.9f, 0.7f, 0.1f, 0.2f); //if & hand on path
            }
        }
        if (canPlayerAffordTheHand()) {
            return currentItem.getColor();
        }
        else {
            return new Color(0.9f, 0.1f, 0.1f, 0.2f);
        }
    }


    /**
     * Check if a tower intersects any part of the current path
     * @return true if the tower is on the path
     */
    private boolean isObjectOnLine(){

        ProximityVector pos=new ProximityVector(getPosition().x-currentItem.getWidth()/2f, getPosition().y-currentItem.getHeight()/2f);
        for(int x=0; x<currentItem.getWidth();x++){
            if(path.isPointOnPath(new ProximityVector(pos.x+x,pos.y))){
                return true;
            }
            if(path.isPointOnPath(new ProximityVector(pos.x+x,pos.y+currentItem.getHeight()))){
                return true;
            }
        }for(int y=0;y<currentItem.getHeight();y++){
            if(path.isPointOnPath(new ProximityVector(pos.x,pos.y+y))){
                return true;
            }
            if(path.isPointOnPath(new ProximityVector(pos.x+currentItem.getWidth(),pos.y+y))){
                return true;
            }
        }
        return false;
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
            if (currentItem.getRange() < 9999){ //render normal tower ranges
                shapeRenderer.renderRangeIndicator(getPosition(), getItem().getRange(), getRangeIndicatorColor());
            }else{ //render sniper-like ranges
                shapeRenderer.renderRangeIndicator(getPosition(), 34, getRangeIndicatorColor());
            }
            if (!currentItem.canBePlacedOnPath()){ //render out build-hitbox if item can't be placed on a path
                ProximityVector pos=new ProximityVector(getPosition().x-currentItem.getWidth()/2f, getPosition().y-currentItem.getHeight()/2f);
                shapeRenderer.renderRectangle(pos, getItem().getImage().getTexture().getWidth(), getItem().getImage().getTexture().getHeight(), new Color(0.5f, 0.5f, 0.5f, 0.3f));
            }
        } else { //if the thing is placed, render from where its positioned instead of from the cursor position
            shapeRenderer.renderRangeIndicator(currentItem.getCenter(), getItem().getRange(), new Color(0.4f, 0.2f, 0.9f, 0.2f));
        }
    }
}
