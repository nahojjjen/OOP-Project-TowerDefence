package edu.chl.proximity.Viewers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import edu.chl.proximity.Models.Creeps.AbstractCreep;
import edu.chl.proximity.Models.Maps.Map;
import edu.chl.proximity.Models.Paths.Path;
import edu.chl.proximity.Models.Projectiles.AbstractProjectile;
import edu.chl.proximity.Models.Towers.AbstractTower;

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

    public static void render() {
        SpriteBatch batch = new SpriteBatch(); //TODO: The batch could not be created every turn!!!
        batch.begin();
        //renderBackground(batch);
        renderPath(batch);
        renderBase(batch);
        renderTowers(batch);
        renderCreeps(batch);
        renderProjectiles(batch);
        //renderParticles(batch);


        //debuggRenderWaypoints();
        //debuggRenderCreepAngle();
        batch.end();

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
        List<AbstractCreep> creeps = Map.getInstance().getCreeps();
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

    private static void renderPath(Batch batch){
//         batch.draw(Map.getInstance().getPath().getImage(), 0, Gdx.graphics.getHeight());

    }
    private static void renderBackground(Batch batch) {
        //batch.draw(Map.getInstance().);
        //Map.getBackground().drawCentered(0, 0);
    }

    private static void renderBase(Batch batch) {
        //Base.getImage().draw(600, 200);
    }

    private static void renderTowers(SpriteBatch batch)  {
        List<AbstractTower> towers = Map.getInstance().getTowers();
   if (towers != null){
       for (AbstractTower tower : towers) {
           //tower.getAnimation().draw(tower.getPoint().getX()-20, tower.getPoint().getY()-20);
           tower.getTexture().render(batch, tower.getPosition(), tower.getAngle());

       }
   }

    }

    private static void renderProjectiles(SpriteBatch batch)  {

        List<AbstractProjectile> projectiles = Map.getInstance().getProjectiles();
        if (projectiles != null){
            for (AbstractProjectile projectile : projectiles) {
                projectile.getTexture().render(batch, projectile.getPosition(), projectile.getAngle());
            }
        }

    }

    private static void renderCreeps(SpriteBatch batch)   {

        List<AbstractCreep> creeps = Map.getInstance().getCreeps();
        if (creeps != null){
            for (AbstractCreep creep : creeps) {
                creep.getTexture().render(batch, creep.getPosition(), creep.getAngle());
            }
        }

    }


    /*
    private static void renderParticles(SpriteBatch batch)   {
        List<Particle> particles = Map.getParticles();
        for (Particle particle : particles) {
            particle.getParticleSystem().render();
        }
    }
    */
}
