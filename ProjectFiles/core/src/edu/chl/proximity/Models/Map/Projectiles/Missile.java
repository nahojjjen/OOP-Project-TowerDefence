package edu.chl.proximity.Models.Map.Projectiles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import edu.chl.proximity.Models.Map.Particles.ParticleManager;
import edu.chl.proximity.Utilities.ProximityVector;
import edu.chl.proximity.Models.Map.Creeps.Creep;
import edu.chl.proximity.Models.Utils.Image;
import edu.chl.proximity.Utilities.Constants;

import java.util.List;

/**
 * @author Linda Evaldsson
 * @date 2015-04-08
 *
 * Unknown date modified by Johan Swanberg
 * 12/05 modified by Linda Evaldsson. Removed Map from constructor.
 *
 * A missile is a target-following projectile that kills all creeps in a radius.
 */
public class Missile extends Projectile {

    private static Image img = new Image(Constants.FILE_PATH + "Projectiles/missile.png");
    private static Sound sound = Gdx.audio.newSound(new FileHandle(Constants.FILE_PATH + "Sounds/explosion.ogg"));
    private double range = 40;
    private Creep target;

    /**
     * create a new bullet projectile
     * @param position where to create the bullet projectile
     * @param angle what angle the image & movement should start at
     * @param target what creep the projectile should see it it hits etc.
     */
    public Missile(ProximityVector position, double angle, Creep target, ParticleManager particleManager) {
        //Arguments: ProximityEffect particleEffect, int health, int speed, Sound sound, Image image, ProximityVector position, double angle, Creep target
        super(particleManager.getExplosionEffect(), 1, 8, sound, img, position, angle);
        this.target = target;
    }

    @Override
    public void reAngle() {
            if (target != null) {

                //Todo: Fix so map informs the missile that the target is gone
                /*
                //Check if the target is still on the board
                if(getMap().getCreeps().contains((target))) {
                    faceTarget(target.getCenter());
                }
                else {
                    // Keep angle
                }*/

            } else {
                throw new IllegalStateException("Missile: Trying to reangle but target is null");
            }
    }

    @Override
    public void attack(Creep unusedCreep) {
        //Todo: Fix so map calculates this instead
        /*List<Creep> creepList = getMap().getCreeps();
        for(Creep creep: creepList) {
            if(PointCalculations.distanceBetweenNoSqrt(this.getCenter(), creep.getCenter()) < range*range){
                creep.devolve();
            }
        }*/


    }
}
