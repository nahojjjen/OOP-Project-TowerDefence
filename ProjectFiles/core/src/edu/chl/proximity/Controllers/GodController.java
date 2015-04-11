package edu.chl.proximity.Controllers;

import com.badlogic.gdx.graphics.g2d.ParticleEffectPool;
import com.badlogic.gdx.math.Vector2;
import edu.chl.proximity.Models.Creeps.Creep;
import edu.chl.proximity.Models.Maps.Map;
import edu.chl.proximity.Models.Projectiles.Projectile;
import edu.chl.proximity.Models.Towers.Tower;
import edu.chl.proximity.Utilities.PointCalculations;

import java.awt.*;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Johan on 2015-04-02.
 *
 * This controller is not meant to survive, but rather be split appart into
 * CreepController
 * TowerController
 * ProjectileController
 * ParticleController
 *
 * this class exists right now because in the beginning we did not have a lot of contollers,
 * so we didn't have a good reason to split them appart.
 */
public class GodController {

    private static List<Tower> towers;
    private static List<Projectile> projectiles;
    private static List<Creep> creeps;
    //private static List<Particle> particles = Map.getParticles();

    private Map map;

    public GodController(Map map) {
        this.map = map;
        creeps = map.getCreeps();
        projectiles = map.getProjectiles();
        towers = map.getTowers();
    }

    /**
     * get the creep listed in the model (entire map)that is closest to the input point
     *
     * @param p what point should search around
     * @return the creep with the closest position
     */
    public Creep getClosestCreep(Vector2 p) { //gör så den kollar på p istället för list.get(0)
        return getClosestCreepInRange(map.getCreeps(), p);
    }

    /**
     * Get the creep in a list that is closest to the tower.
     * @param creepsInRange A list of creeps to search among
     * @param p what point should it find the closest creep to
     * @return the creep in the list that is closest to the point.
     */
    public Creep getClosestCreepInRange(List<Creep> creepsInRange, Vector2 p){
        if (creeps.size() > 0) { //make sure there's a creep that can be found
            Creep closest = creeps.get(0); //starts with first creep to avoid null error
            double distanceToClosest = 9999999; //dummy startvalue to avoid null comparison

            for (Creep creep : creeps) {
                double distanceToThisCreep = PointCalculations.distanceBetween(creep.getPosition(), p);
                if (distanceToThisCreep < distanceToClosest) {
                    distanceToClosest = distanceToThisCreep;
                    closest = creep;
                }
            }
            return closest;
        }
        return null;
    }


    /**
     * get the creep that was added first in the list
     *
     * @return the creep with position 0  in the creep list
     */
    public Creep getFirstCreepInList(Point p) { //gör så den kollar på p istället för list.get(0)
        if (creeps.size() != 0) {
            return creeps.get(0);
        }
        return null;
    }

    /**
     * Target the closest creep and attempt to fire
     */
    public void updateTowers() {
        for (Tower tower : towers) {
            Creep closestCreep = getClosestCreep(tower.getPosition());

            if (closestCreep != null) {
                Vector2 closestCreepPosition = closestCreep.getPosition();
                tower.faceTarget(closestCreepPosition);
                tower.shoot();
            }
            tower.reload();

        }
    }

    /**
     * TODO: Fix this method for libGDX
     */
    private void playPoofSound() {
       // Sound sound = new Sound() {
        //};
        //sound.playCreepDestroyedSound();
        System.out.println("In Godcontroller: Sound is trying to play, but method is not yet fixed for new library");

    }


    public void updateCreeps() {
        for (Creep creep : creeps) {
            creep.rotate(); //TODO: rotate creep angle every turn
            creep.move();
          /* TODO: fix so creeps that intersects base decrease its health
            if (Base.intersects(creep.getPoint(), 50)) {
                creeps.iterator().remove();
                Base.decreaseHealth(10);
                System.out.println("base health = " + Base.health);
            }
            */
        }
    }

    public void updateProjectiles() {
        Iterator projectileIterator = projectiles.iterator();

        while (projectileIterator.hasNext()) {
            Object projectileObject = projectileIterator.next();
            Projectile projectile = (Projectile) projectileObject;
            Creep closestVictim = getClosestCreep(projectile.getPosition());
            if (closestVictim != null) {
                projectile.faceTarget(closestVictim.getPosition());
                if (projectile.collidesWith(closestVictim.getPosition(), 20)) {
                    System.out.println("In GodController, a projectile has collided and is trying to add particles, and play a sound, but can not.");
                    //todo: fix particles!
                    map.getParticleManager().getExplosionEffect().createEffect((int)projectile.getPosition().x, (int)projectile.getPosition().y);
                    //Controller.addParticle(new Particle(closestVictim.getPoint()));
                    playPoofSound();
                    creeps.remove(closestVictim);
                    projectileIterator.remove();

                }

            }
            projectile.move();

            if (projectile.isOutsideView()) {
                projectileIterator.remove();
            }
        }

    }

    /** TODO: fix this method to rotate the background image, attatch background image as instance variable in map?
     *
     */
    public void updateBackground() {

        //Background.getBackground().rotate((float) 0.05);
    }

}
