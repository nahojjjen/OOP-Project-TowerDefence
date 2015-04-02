package edu.chl.proximity.Controllers;

import edu.chl.proximity.Models.Creeps.AbstractCreep;
import edu.chl.proximity.Models.Maps.Map;
import edu.chl.proximity.Models.Projectiles.AbstractProjectile;
import edu.chl.proximity.Models.Towers.AbstractTower;

import java.util.List;

/**
 * Created by Johan on 2015-04-02.
 */
public class GodController {
    private static List<AbstractTower> towers = Map.getTowers();
    private static List<AbstractProjectile> projectiles = Map.getProjectiles();
    private static List<AbstractCreep> creeps = Map.getCreeps();
    private static List<Particle> particles = Map.getParticles();

    /**
     * update the animation & positions of the particle effects
     *
     * @param delta
     */
    public static void updateParticles(int delta) {
        for (Particle particle : particles) {
            particle.getParticleSystem().update(delta);
        }
    }

    /**
     * get the creep listed in the model that is closest to the input point
     *
     * @param p what point should search around
     * @return the creep with the closest position
     */
    public static Creep getClosestCreep(Point p) { //gör så den kollar på p istället för list.get(0)
        if (creeps.size() > 0) { //make sure there's a creep that can be found
            Creep closest = creeps.get(0); //starts with first creep to avoid null error
            double distanceToClosest = 9999999; //dummy startvalue to avoid null comparison

            for (Creep creep : creeps) {
                double distanceToThisCreep = Util.distanceBetween(creep.getPoint(), p);
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
     * get the creep listed in the model that is closest to the input point
     *
     * @param p what point should search around
     * @return the creep with the closest position
     */
    public static Creep getFirstCreep(Point p) { //gör så den kollar på p istället för list.get(0)
        if (creeps.size() != 0) {
            return creeps.get(0);
        }
        return null;
    }

    /**
     * Target the closest creep and attempt to fire
     *
     * @throws SlickException
     */
    public static void updateTowers() throws SlickException {
        for (Tower tower : towers) {
            Creep closestCreep = getClosestCreep(tower.getPoint());

            if (closestCreep != null) {
                Point closestCreepPosition = closestCreep.getPoint();
                tower.faceTarget(closestCreepPosition);
                tower.shoot();
            }
            tower.reload();
            tower.getAnimation().update(16); //16 should be delta in update, add as taketurn argument?

        }
    }

    private static void playPoofSound() throws SlickException {
        CreepDestroyedSound sound = new CreepDestroyedSound();
        sound.playCreepDestroyedSound();

    }

    public static void updateCreeps() throws SlickException {
        for (Creep creep : creeps) {
            creep.getImage().rotate(5);
            creep.move();
            if (Base.intersects(creep.getPoint(), 50)) {
                creeps.iterator().remove();
                Base.decreaseHealth(10);
                System.out.println("base health = " + Base.health);
            }
        }
    }

    public static void updateProjectiles() throws SlickException {
        Iterator projectileIterator = projectiles.iterator();

        while (projectileIterator.hasNext()) {
            Object projectileObject = projectileIterator.next();
            Projectile projectile = (Projectile) projectileObject;
            Creep closestVictim = getClosestCreep(projectile.getPoint());
            if (closestVictim != null) {
                projectile.faceTarget(closestVictim.getPoint());
                if (projectile.collidesWith(closestVictim.getPoint(), 40)) {
                    Controller.addParticle(new Particle(closestVictim.getPoint()));
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

    public static void updateBackground() throws SlickException {
        Background.getBackground().rotate((float) 0.05);
    }

}
