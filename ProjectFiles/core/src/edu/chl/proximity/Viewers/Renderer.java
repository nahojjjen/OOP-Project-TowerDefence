package edu.chl.proximity.Viewers;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import edu.chl.proximity.Models.Bases.Base;
import edu.chl.proximity.Models.ButtonsPanel.ButtonPanel;
import edu.chl.proximity.Models.ControlPanel.ControlPanel;
import edu.chl.proximity.Models.Creeps.Creep;
import edu.chl.proximity.Models.GameData;
import edu.chl.proximity.Models.Maps.Map;
import edu.chl.proximity.Models.Particles.ParticleManager;
import edu.chl.proximity.Models.Projectiles.Projectile;
import edu.chl.proximity.Models.Towers.Tower;

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
 */
public class Renderer {

    private Map map;
    private ParticleManager particleManager ;
    private ControlPanel controlPanel;
    private ButtonPanel buttonPanel;

    /**
     * create a new renderer that can show everything in a game instance
     */
    public Renderer() {
        this.map = GameData.getInstance().getMap();
        this.particleManager = map.getParticleManager();

    }

    public void setControlPanel(ControlPanel controlPanel) {
        this.controlPanel = controlPanel;
    }
    public void setButtonPanel(ButtonPanel buttonPanel){ this.buttonPanel=buttonPanel;}

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
        shapeRenderer.end();


        batch.begin();
        renderBase(batch);
        renderTowers(batch);
        renderCreeps(batch);
        renderProjectiles(batch);
        renderBase(batch);
        renderParticles(batch);
        renderControlPanel(batch);
        GameData.getInstance().getHand().render(batch);

    }

    /**
     * Draws out the control panel
     * @param batch what graphics batch object that should draw on the screen
     */
    private void renderControlPanel(SpriteBatch batch) {
        /*batch.end();
        shapeRenderer.begin();
        shapeRenderer.setColor(Color.RED);
        controlPanel.render(shapeRenderer);
        shapeRenderer.end();
        batch.begin();*/
        controlPanel.render(batch);
        buttonPanel.render(batch);
    }

    /**
     * automatically renders the lines between the waypoints of the current path
     * Uses the shaperenderer, so you need to stop the SpriteBatch before calling this method,
     * or you get a completely white blank screen.
     * @param shapeRenderer what shaperenderer to use to draw the lines
     */
    private void renderPath( ShapeRenderer shapeRenderer){
        List<Vector2> waypoints = GameData.getInstance().getMap().getPath().getWaypoints();

        shapeRenderer.setColor(new Color(0.4f, 0.6f, 0.9f, 0));

        for (int i = 1; i<waypoints.size(); i++){
            shapeRenderer.line(waypoints.get(i-1).x +20 ,waypoints.get(i-1).y+20, waypoints.get(i).x+20,waypoints.get(i).y+20);
        }
    }

    /**
     * render a circle around all towers that show their range
     * @param shapeRenderer what shaperenderer to use to show the graphics
     */
    private void renderAllTowerRanges(ShapeRenderer shapeRenderer){
        for (Tower tower:GameData.getInstance().getMap().getTowers()){
            Vector2 towerCenter = tower.getCenter();
            Gdx.gl.glEnable(GL20.GL_BLEND); //enables transparency
            shapeRenderer.setColor(0.5f,0.5f,1f,0.2f);
            shapeRenderer.circle(towerCenter.x,towerCenter.y,(float)tower.getRange());
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
        Base base = GameData.getInstance().getMap().getBase();
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
                   //tower.getAnimation().draw(tower.getPoint().getX()-20, tower.getPoint().getY()-20);
                   tower.getImage().render(batch, tower.getPosition(), tower.getAngle());

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
                projectile.getImage().render(batch, projectile.getPosition(), projectile.getAngle());
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
                creep.getImage().render(batch, creep.getPosition(), creep.getAngle());
            }
        }

    }

    /**
     * render all particles that are on the map
     * @param batch what graphics batch object that should draw on the creen
     */
    private  void renderParticles(SpriteBatch batch)   {
        particleManager.renderAllParticles(batch);
    }

}
