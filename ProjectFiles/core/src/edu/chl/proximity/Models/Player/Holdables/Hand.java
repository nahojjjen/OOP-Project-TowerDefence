package edu.chl.proximity.Models.Player.Holdables;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import edu.chl.proximity.Utilities.ProximityBatch;
import edu.chl.proximity.Utilities.ProximityVector;
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
        if (currentItem instanceof Tower) {
            Tower tower = (Tower) currentItem;
            return GameData.getInstance().getPlayer().canPlayerAfford(tower.getCost());
        }
        else if (currentItem instanceof Spell) {
            //TODO: check energy costs
        }
        return true;
    }

    public void render(ShapeRenderer shapeRenderer) {

        if(currentItem instanceof Tower){
            if(!((Tower) currentItem).getIfPlaced()){
                renderRangeIndicator(shapeRenderer, getRangeIndicatorColor(), getPosition(), getItem().getRange());
            }else{
                renderRangeIndicator(shapeRenderer,new Color(0.4f, 0.2f, 0.9f, 0.2f),((Tower) currentItem).getCenter(),getItem().getRange());
            }
        }else {
            renderRangeIndicator(shapeRenderer, getRangeIndicatorColor(), getPosition(), getItem().getRange());
        }
    }

    private void renderRangeIndicator(ShapeRenderer renderer, Color color, ProximityVector position, double range) {
        if(range<1500) {
            Gdx.gl.glEnable(GL20.GL_BLEND); //enables transparency
            renderer.setColor(color);
            renderer.circle(position.x, position.y, (float) range);
        }
    }
}
