package edu.chl.proximity.Viewers;

import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import edu.chl.proximity.Models.Paths.Path;

import java.util.List;

/**
 * Created by Johan on 2015-04-02.
 */
public class Renderer {

    public static void render() {
        renderBackground();
        renderPath();
        renderBase();
        renderTowers();
        renderCreeps();
        renderProjectiles();
        renderParticles();


        debuggRenderWaypoints();
        debuggRenderCreepAngle();

    }


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

    private static void debuggRenderCreepAngle(){
        List<Creep> creeps = Map.getCreeps();
        Image line = Debug.getLine();
        for (Creep creep : creeps) {
            line.setRotation((float) creep.getAngle());
            line.draw(creep.getx()-20,creep.gety()-20);
        }
    }
    private static void debuggRenderWaypoints(){
        Path path = Map.getPath();
        List<Point> wayPoints = path.getWaypoints();
        Image image = Debug.getWaypoint();
        for (Point point: wayPoints){
            image.draw(point.getX(),point.getY());
        }
    }
    private static void renderPath(){
        Path.getImage().draw(0,0);
    }
    private static void renderBackground() {
        Map.getBackground().drawCentered(0, 0);
    }

    private static void renderBase() {
        Base.getImage().draw(600, 200);
    }

    private static void renderTowers()  {
        List<Tower> towers = Map.getTowers();
        for (Tower tower : towers) {
            tower.getAnimation().draw(tower.getPoint().getX()-20, tower.getPoint().getY()-20);
        }
    }

    private static void renderProjectiles()  {

        List<Projectile> projectiles = Map.getProjectiles();
        for (Projectile projectile : projectiles) {
            projectile.getImage().draw(projectile.getPoint().getX(), projectile.getPoint().getY());
        }
    }

    private static void renderCreeps()   {

        List<Creep> creeps = Map.getCreeps();
        for (Creep creep : creeps) {
            creep.getImage().draw(creep.getx()-20, creep.gety()-20);
        }
    }


    private static void renderParticles()   {
        List<Particle> particles = Map.getParticles();
        for (Particle particle : particles) {
            particle.getParticleSystem().render();
        }
    }
}
