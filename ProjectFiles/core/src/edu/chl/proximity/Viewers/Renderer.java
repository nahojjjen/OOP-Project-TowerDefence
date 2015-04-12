package edu.chl.proximity.Viewers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import edu.chl.proximity.Models.Creeps.Creep;
import edu.chl.proximity.Models.GameData;
import edu.chl.proximity.Models.Maps.Map;
import edu.chl.proximity.Models.Particles.ParticleManager;
import edu.chl.proximity.Models.Projectiles.Projectile;
import edu.chl.proximity.Models.Towers.Tower;

import java.util.List;

/**
 * Created by Johan on 2015-04-02.
 *
 * Should be split into
 * towerRenderer
 * projectileRenderer
 * Creeprenderer
 * DebugRenderer
 * etc
 */
public class Renderer {

    private Map map;
    private ParticleManager particleManager ;

    public Renderer() {
        this.map = GameData.getInstance().getMap();
        this.particleManager = map.getParticleManager();
    }

    public void render(SpriteBatch batch) {

        renderBackground(batch);
        renderPath(batch);
        renderBase(batch);
        renderTowers(batch);
        renderCreeps(batch);
        renderProjectiles(batch);
        renderParticles(batch);

    }

    private void renderPath(Batch batch){
        //   batch.draw(Map.getInstance().getPath().getImage(), 0, Gdx.graphics.getHeight());
    }
    private void renderBackground(SpriteBatch batch) {
        map.getBackground().render(batch);
    }

    private void renderBase(Batch batch) {
        //Base.getImage().draw(600, 200);
    }

    private void renderTowers(SpriteBatch batch)  {
        List<Tower> towers = map.getTowers();
           if (towers != null){
               for (Tower tower : towers) {
                   //tower.getAnimation().draw(tower.getPoint().getX()-20, tower.getPoint().getY()-20);
                   tower.getImage().render(batch, tower.getPosition(), tower.getAngle());

               }
        }

    }

    private void renderProjectiles(SpriteBatch batch)  {

        List<Projectile> projectiles = map.getProjectiles();
        if (projectiles != null){
            for (Projectile projectile : projectiles) {
                projectile.getImage().render(batch, projectile.getPosition(), projectile.getAngle());
            }
        }

    }

    private void renderCreeps(SpriteBatch batch)   {

        List<Creep> creeps = map.getCreeps();
        if (creeps != null){
            for (Creep creep : creeps) {
                creep.getImage().render(batch, creep.getPosition(), creep.getAngle());
            }
        }

    }



    private  void renderParticles(SpriteBatch batch)   {
        particleManager.renderAllParticles(batch);
    }

}
