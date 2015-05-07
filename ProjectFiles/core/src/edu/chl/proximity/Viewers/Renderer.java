package edu.chl.proximity.Viewers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import edu.chl.proximity.Models.BoardObject;
import edu.chl.proximity.Models.Map.Bases.Base;
import edu.chl.proximity.Models.Map.Creeps.Creep;
import edu.chl.proximity.Models.Player.Holdables.Hand;
import edu.chl.proximity.Models.Player.Holdables.Holdable;
import edu.chl.proximity.Models.Map.Maps.Map;
import edu.chl.proximity.Models.Map.Particles.ParticleManager;
import edu.chl.proximity.Models.Map.Projectiles.Projectile;
import edu.chl.proximity.Models.Map.Towers.Tower;

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
 * 23/04 Modified by Hanna R�mer. Added ButtonPanel and PropertiesPanel + necessary methods for them
 * 24/04 Modified by Johan Swanberg - Added creep debug view and fixed path render to not be missaligned
 * 29/04 modified by Hanna R�mer. Removed PropertiesPanel instance and setter since it's a singleton.
 * 07/05 modified by Linda Evaldsson. Removed all the setters and getters for ControlPanels, replaced with setControlPanel.
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
    public void render(SpriteBatch batch, ShapeRenderer shapeRenderer) {

        renderBackground(batch);

        batch.end(); //spritebatch needs to end  for shaperenderer to work
        shapeRenderer.begin();
        renderPath(shapeRenderer);
        shapeRenderer.end();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        renderAllTowerRanges(shapeRenderer);
        //debugRenderAllCentersAndUpperLeftCorners(shapeRenderer);
        shapeRenderer.end();


        batch.begin();
        renderBase(batch);
        renderTowers(batch);
        renderCreeps(batch);
        renderProjectiles(batch);
        renderBase(batch);
        renderParticles(batch);
        renderControlPanels(batch, shapeRenderer);

        //Render the hand and its range.
        Hand hand = map.getHand();
        Holdable handItem = hand.getItem();
        if (handItem != null) {
            hand.render(batch);
            batch.end();
            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            renderRangeIndicator(shapeRenderer, hand.getRangeIndicatorColor(), hand.getPosition(), handItem.getRange());
            shapeRenderer.end();
            batch.begin();
        }


    }

    public void setControlPanels(List<BoardObject> controlPanels) { this.controlPanels = controlPanels; }


    private void debugRenderAllCentersAndUpperLeftCorners(ShapeRenderer shapeRenderer){
        Color red = new Color(1,0,0,0.8f);
        Color blue = new Color(0, 0, 1, 0.8f);

        for (Creep creep:map.getCreeps()){

            shapeRenderer.setColor(blue); //hitbox
            shapeRenderer.circle(creep.getCenter().x, creep.getCenter().y, 20, 20);

            shapeRenderer.setColor(red); //center
            shapeRenderer.circle(creep.getCenter().x, creep.getCenter().y, 4);


        }
    }


    /**
     * Draws out the control panel
     * @param batch what graphics batch object that should draw on the screen
     */
    private void renderControlPanels(SpriteBatch batch, ShapeRenderer shapeRenderer) {
        for(BoardObject panel : controlPanels) {
            panel.render(batch);
            batch.end();
            panel.renderShapes(shapeRenderer);
            batch.begin();
        }
        /*
        controlPanel.render(batch);
        buttonPanel.render(batch);
        profilePanel.render(batch);
        spellPanel.render(batch);
        if(map.getPropertiesPanel().getIfVisible()){
            map.getPropertiesPanel().render(batch);
        }
        */

    }


    /**
     * automatically renders the lines between the waypoints of the current path
     * Uses the shaperenderer, so you need to stop the SpriteBatch before calling this method,
     * or you get a completely white blank screen.
     * @param shapeRenderer what shaperenderer to use to draw the lines
     */
    private void renderPath( ShapeRenderer shapeRenderer){
        List<Vector2> waypoints = map.getPath().getWaypoints();

        shapeRenderer.setColor(new Color(0.4f, 0.6f, 0.9f, 0));

        for (int i = 1; i<waypoints.size(); i++){
            shapeRenderer.line(waypoints.get(i-1).x  ,waypoints.get(i-1).y, waypoints.get(i).x,waypoints.get(i).y);
        }
    }

    /**
     * render a circle around all towers that show their range
     * @param shapeRenderer what shaperenderer to use to show the graphics
     */
    private void renderAllTowerRanges(ShapeRenderer shapeRenderer){
        for (Tower tower : map.getTowers()) {
            renderRangeIndicator(shapeRenderer, new Color(0.4f, 0.2f, 0.9f, 0.2f), tower.getCenter(), tower.getRange());
        }
    }
    private void renderBackground(SpriteBatch batch) {
        map.getBackground().render(batch);
    }

    /**
     * render the current base
     * @param batch what graphics batch object that should draw on the screen
     */
    private void renderBase(SpriteBatch batch) {
        Base base = map.getBase();
        if (base.getImage() != null && base != null){
            base.render(batch);
        } else{
            System.out.println("In Renderer: There was no base to be found");
        }

    }

    /**
     * render out all towers on the map
     * @param batch what graphics batch object that should draw on the creen
     */
    private void renderTowers(SpriteBatch batch)  {
        List<Tower> towers = map.getTowers();

           if (towers != null){
               for (Tower tower : towers) {
                   tower.render(batch);

               }
        }

    }

    /**
     * render all projectiles that are on the map
     * @param batch what graphics batch object that should draw on the creen
     */
    private void renderProjectiles(SpriteBatch batch)  {

        List<Projectile> projectiles = map.getProjectiles();
        if (projectiles != null){
            for (Projectile projectile : projectiles) {
                projectile.render(batch);
            }
        }

    }

    /**
     * render all creeps that are on the map
     * @param batch what graphics batch object that should draw on the creen
     */
    private void renderCreeps(SpriteBatch batch)   {

        List<Creep> creeps = map.getCreeps();
        if (creeps != null){
            for (Creep creep : creeps) {
                creep.render(batch);
            }
        }

    }

    /**
     * render all particles that are on the map
     * @param batch what graphics batch object that should draw on the creen
     */
    private void renderParticles(SpriteBatch batch)   {
        particleManager.renderAllParticles(batch);
    }


    /**
     * Helper method to draw a circular range
     * @param renderer shape renderer that does the rendering
     * @param color range colour
     * @param position position to draw
     * @param range radius to draw
     */
    private void renderRangeIndicator(ShapeRenderer renderer, Color color, Vector2 position, double range) {
        Gdx.gl.glEnable(GL20.GL_BLEND); //enables transparency
        renderer.setColor(color);
        renderer.circle(position.x, position.y, (float) range);
    }

}
