package edu.chl.proximity.Viewers;

import com.badlogic.gdx.graphics.Color;
import edu.chl.proximity.Models.BoardObject;
import edu.chl.proximity.Models.Map.Bases.Base;
import edu.chl.proximity.Models.Player.Holdables.Hand;
import edu.chl.proximity.Models.Player.Holdables.Holdable;
import edu.chl.proximity.Models.Map.Maps.Map;
import edu.chl.proximity.Models.Map.Particles.ParticleManager;
import edu.chl.proximity.Models.Utils.ProximityBatch;
import edu.chl.proximity.Utilities.ProximityShapeRenderer;
import edu.chl.proximity.Utilities.ProximityVector;

import java.util.List;

/**
 * @author Johan Swanberg
 * @date 2015-04-02
 *
 * ---
 *
 * 07/04 Modified by Johan Swanberg. Updated so it doesn't crash the program on run.
 * 08/04 Modified by Johan Swanberg. Switch to Screen from GameState.
 * 08/04 modified by Linda Evaldsson. Made methods non-static.
 * Unknown date modified by Linda Evaldsson
 * 23/04 Modified by Hanna Romer. Added ButtonPanel and PropertiesPanel + necessary methods for them
 * 24/04 Modified by Johan Swanberg - Added creep debug view and fixed path render to not be missaligned
 * 29/04 modified by Hanna Romer. Removed PropertiesPanel instance and setter since it's a singleton.
 * 07/05 modified by Linda Evaldsson. Removed all the setters and getters for ControlPanels, replaced with setControlPanel.
 * 08/05 modified by Hanna Romer. Added renderChoosenTowerRange
 */
public class Renderer {

    private Map map;
    private ParticleManager particleManager ;
    List<BoardObject> controlPanels;

    /**
     * create a new renderer that can show everything in a game instance
     */
    public Renderer(Map map) {
        this.map = map;
        this.particleManager = map.getParticleManager();

    }

    /**
     * render everything in the current game
     * @param batch what object should draw on the screen
     */
    public void render(ProximityBatch batch, ProximityShapeRenderer shapeRenderer) {

        renderBackground(batch);

        batch.end(); //spritebatch needs to end  for shaperenderer to work
        shapeRenderer.begin();
        renderPath(shapeRenderer);
        shapeRenderer.end();

        shapeRenderer.begin(ProximityShapeRenderer.Shape.Filled);
        //renderChosenTowerRange(shapeRenderer);
        //renderAllTowerRanges(shapeRenderer);
        //debugRenderAllCentersAndUpperLeftCorners(shapeRenderer);
        shapeRenderer.end();


        batch.begin();
        renderBase(batch);
        renderBase(batch);
        renderParticles(batch);
        renderControlPanels(batch);
        map.render(batch);

        //Render the hand and its range.
        Hand hand = map.getHand();
        Holdable handItem = hand.getItem();
        if (handItem != null) {
            hand.render(batch);
            batch.end();
            shapeRenderer.begin(ProximityShapeRenderer.Shape.Filled);
            hand.render(shapeRenderer);
            //renderRangeIndicator(shapeRenderer, hand.getRangeIndicatorColor(), hand.getPosition(), handItem.getRange());
            shapeRenderer.end();
            batch.begin();
        }


    }

    public void setControlPanels(List<BoardObject> controlPanels) { this.controlPanels = controlPanels; }

    /**
     * Draws out the control panel
     * @param batch what graphics batch object that should draw on the screen
     */
    private void renderControlPanels(ProximityBatch batch) {
        for(BoardObject panel : controlPanels) {
            panel.render(batch);
        }

    }


    /**
     * automatically renders the lines between the waypoints of the current path
     * Uses the shaperenderer, so you need to stop the SpriteBatch before calling this method,
     * or you get a completely white blank screen.
     * @param shapeRenderer what shaperenderer to use to draw the lines
     */
    private void renderPath(ProximityShapeRenderer shapeRenderer){
        List<ProximityVector> waypoints = map.getPath().getWaypoints();

        shapeRenderer.setColor(new Color(0.4f, 0.6f, 0.9f, 0));

        for (int i = 1; i<waypoints.size(); i++){
            shapeRenderer.renderLine(waypoints.get(i-1).x  ,waypoints.get(i-1).y, waypoints.get(i).x,waypoints.get(i).y);
        }
    }

    private void renderBackground(ProximityBatch batch) {
        map.getBackground().render(batch);
    }

    /**
     * render the current base
     * @param batch what graphics batch object that should draw on the screen
     */
    private void renderBase(ProximityBatch batch) {
        Base base = map.getBase();
        if (base.getImage() != null && base != null){
            base.render(batch);
        } else{
            System.out.println("In Renderer: There was no base to be found");
        }

    }


    /**
     * render all particles that are on the map
     * @param batch what graphics batch object that should draw on the creen
     */
    private void renderParticles(ProximityBatch batch)   {
        particleManager.renderAllParticles(batch);
    }




}
