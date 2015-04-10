package edu.chl.proximity.Viewers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import edu.chl.proximity.Models.Creeps.Creep;
import edu.chl.proximity.Models.Maps.Map;
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

    public Renderer(Map map) {
        this.map = map;
    }

    public void render(SpriteBatch batch) {

        //renderBackground(batch);
        renderPath(batch);
        renderBase(batch);
        renderTowers(batch);
        renderCreeps(batch);
        renderProjectiles(batch);
        renderParticles(batch);


        //debuggRenderWaypoints();
        //debuggRenderCreepAngle();

    }


    /*
    public static void debuggRenderPathHitbox(Graphics g){
        Path path = new Path();
        List<Rectangle> hitboxes = path.getHitbox();
        ShapeRenderer renderer =  new ShapeRenderer();
        for(Rectangle rectangle : hitboxes){
            renderer.begin();
            renderer.setColor(1,1,1,1);
            renderer.rect(rectangle.getX(),rectangle.getY(),rectangle.getWidth(),rectangle.getHeight());
           }
    }
    */

    /*
    private static void debuggRenderCreepAngle(){
        List<Creep> creeps = Map.getInstance().getCreeps();
        Image line = Debug.getLine();
        for (Creep creep : creeps) {
            line.setRotation((float) creep.getAngle());
            line.draw(creep.getx()-20,creep.gety()-20);
        }
    }
    */
    /*
    private static void debuggRenderWaypoints(){
        Path path = Map.getPath();
        List<Point> wayPoints = path.getWaypoints();
        Image image = Debug.getWaypoint();
        for (Point point: wayPoints){
            image.draw(point.getX(),point.getY());
        }
    }
    */

    private void renderPath(Batch batch){
//         batch.draw(Map.getInstance().getPath().getImage(), 0, Gdx.graphics.getHeight());

    }
    private void renderBackground(Batch batch) {
        //batch.draw(Map.getInstance().);
        //Map.getBackground().drawCentered(0, 0);
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
        List<ParticleEffectPool.PooledEffect> explosions = map.getExplosions();

        for (int i = explosions.size() - 1; i >= 0; i--) {
            ParticleEffectPool.PooledEffect explosion = explosions.get(i);
            explosion.draw(batch, Gdx.graphics.getDeltaTime());
            if(explosion.isComplete()){
                System.out.println("In renderer: Explosion effect is marked complete and removed");
                explosion.free();
                explosions.remove(explosion);
            }
        }
    }

}
