package edu.chl.proximity.Controllers;

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

    private static List<Tower> towers = Map.getInstance().getTowers();
    private static List<Projectile> projectiles = Map.getInstance().getProjectiles();
    private static List<Creep> creeps = Map.getInstance().getCreeps();
    //private static List<Particle> particles = Map.getParticles();

    /**
     * update the animation & positions of the particle effects
     *
     * @param delta
     */
   // public static void updateParticles(int delta) {
   //     for (Particle particle : particles) {
   //         particle.getParticleSystem().update(delta);
   //     }
   // }

    /**
     * get the creep listed in the model (entire map)that is closest to the input point
     *
     * @param p what point should search around
     * @return the creep with the closest position
     */
    public static Creep getClosestCreep(Point p) { //gör så den kollar på p istället för list.get(0)
        return getClosestCreepInRange(Map.getInstance().getCreeps(), p);
    }

    /**
     * Get the creep in a list that is closest to the tower.
     * @param creepsInRange A list of creeps to search among
     * @param p what point should it find the closest creep to
     * @return the creep in the list that is closest to the point.
     */
    public static Creep getClosestCreepInRange(List<Creep> creepsInRange, Point p){
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
    public static Creep getFirstCreepInList(Point p) { //gör så den kollar på p istället för list.get(0)
        if (creeps.size() != 0) {
            return creeps.get(0);
        }
        return null;
    }

    /**
     * Target the closest creep and attempt to fire
     */
    public static void updateTowers() {
        for (Tower tower : towers) {
            Creep closestCreep = getClosestCreep(tower.getPosition());

            if (closestCreep != null) {
                Point closestCreepPosition = closestCreep.getPosition();
                tower.faceTarget(closestCreepPosition);
                tower.shoot();
            }
            tower.reload();

        }
    }

    /**
     * TODO: Fix this method for libGDX
     */
    private static void playPoofSound() {
       // Sound sound = new Sound() {
        //};
        //sound.playCreepDestroyedSound();
        System.out.println("In Godcontroller: Sound is trying to play, but method is not yet fixed for new library");

    }


    public static void updateCreeps() {
        for (Creep creep : creeps) {
            //creep.getTexture().rotate(5); //TODO: rotate creep angle every turn
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

    public static void updateProjectiles() {
        Iterator projectileIterator = projectiles.iterator();

        while (projectileIterator.hasNext()) {
            Object projectileObject = projectileIterator.next();
            Projectile projectile = (Projectile) projectileObject;
            Creep closestVictim = getClosestCreep(projectile.getPosition());
            if (closestVictim != null) {
                projectile.faceTarget(closestVictim.getPosition());
                if (projectile.collidesWith(closestVictim.getPosition(), 40)) {
                    System.out.println("In GodController, a projectile has collided and is trying to add particles, and play a sound, but can not.");
                    //todo: fix particles!
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
    public static void updateBackground() {

        //Background.getBackground().rotate((float) 0.05);
    }

}
