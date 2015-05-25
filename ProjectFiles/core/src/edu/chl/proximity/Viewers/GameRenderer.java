package edu.chl.proximity.Viewers;

import com.badlogic.gdx.graphics.Color;
import edu.chl.proximity.Models.BoardObject;
import edu.chl.proximity.Models.Map.Bases.Base;
import edu.chl.proximity.Models.Holdables.Hand;
import edu.chl.proximity.Models.Holdables.Holdable;
import edu.chl.proximity.Models.Map.Maps.Map;
import edu.chl.proximity.Models.Map.Particles.ParticleManager;
import edu.chl.proximity.Models.Utils.InformationCollector;
import edu.chl.proximity.Models.Utils.ProximityBatch;
import edu.chl.proximity.Utilities.ProximityShapeRenderer;
import edu.chl.proximity.Utilities.ProximityVector;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Johan Swanberg
 * @date 2015-04-02
 *
 * ---
 *
 * 07/04 Modified by Johan Swanberg. Updated so it doesn't crash the program on run.
 * 08/04 Modified by Johan Swanberg. Switch to ScreenType from GameState.
 * 08/04 modified by Linda Evaldsson. Made methods non-static.
 * Unknown date modified by Linda Evaldsson
 * 23/04 Modified by Hanna Romer. Added ButtonPanel and PropertiesPanel + necessary methods for them
 * 24/04 Modified by Johan Swanberg - Added creep debug view and fixed path render to not be missaligned
 * 29/04 modified by Hanna Romer. Removed PropertiesPanel instance and setter since it's a singleton.
 * 07/05 modified by Linda Evaldsson. Removed all the setters and getters for ControlPanels, replaced with setControlPanel.
 * 08/05 modified by Hanna Romer. Added renderChoosenTowerRange
 * 24/05 modified by Linda Evaldsson. Moved rendering Path-functionality to Path. Removed unused methods, changed order of rendering.
 */
public class GameRenderer {

    private Map map;
    private ParticleManager particleManager ;
    List<BoardObject> controlPanels = new ArrayList<BoardObject>();

    /**
     * create a new renderer that can show everything in a game instance
     */
    public GameRenderer(Map map) {
        this.map = map;
        this.particleManager = map.getParticleManager();

    }

    /**
     * render everything in the current game
     * @param batch what object should draw on the screen
     */
    public void render(ProximityBatch batch, ProximityShapeRenderer shapeRenderer) {


        //Renders the map and everything that is on it
        map.render(batch, shapeRenderer);

        renderControlPanels(batch);
        renderParticles(batch);

        renderHand(batch, shapeRenderer);

        renderInformation(batch);

    }

    /**
     * Render the hand and its range
     * @param batch The ProximityBatch to use for rendering
     * @param shapeRenderer The ProximityShapeRenderer to use for rendering
     */
    private void renderHand(ProximityBatch batch, ProximityShapeRenderer shapeRenderer) {
         Hand hand = map.getHand();
         Holdable handItem = hand.getItem();
         if (handItem != null) {
             hand.render(batch);
             batch.end();
             shapeRenderer.begin(ProximityShapeRenderer.Shape.Filled);
             hand.render(shapeRenderer);
             shapeRenderer.end();
             batch.begin();
         }
    }

    private void renderInformation(ProximityBatch batch) {
        InformationCollector.render(batch);
    }

    public void setControlPanels(List<BoardObject> controlPanels) { this.controlPanels.addAll(controlPanels); }


    public void addControlPanel(BoardObject controlPanel) { controlPanels.add(controlPanel); }

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
     * render all particles that are on the map
     * @param batch what graphics batch object that should draw on the creen
     */
    private void renderParticles(ProximityBatch batch)   {
        particleManager.renderAllParticles(batch);
    }




}
