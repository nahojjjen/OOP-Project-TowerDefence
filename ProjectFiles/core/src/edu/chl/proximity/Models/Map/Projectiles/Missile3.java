package edu.chl.proximity.Models.Map.Projectiles;

import edu.chl.proximity.Models.Map.Creeps.Creep;
import edu.chl.proximity.Models.Map.Particles.ParticleManager;
import edu.chl.proximity.Models.Utils.Image;
import edu.chl.proximity.Models.Utils.ProximitySound;
import edu.chl.proximity.Utilities.Constants;
import edu.chl.proximity.Utilities.ProximityVector;

/**
 * @author Linda Evaldsson
 * @date 2015-04-08
 *
 * Unknown date modified by Johan Swanberg
 * 12/05 modified by Linda Evaldsson. Removed Map from constructor.
 *
 * A missile is a target-following projectile that kills all creeps in a radius.
 */
public class Missile3 extends Projectile {

    private static Image img = new Image(Constants.FILE_PATH + "Projectiles/missile2.png");
    private static ProximitySound sound = new ProximitySound(Constants.FILE_PATH + "Sounds/explosion.ogg");
    private static double range = 100f;
    private Creep target;

    /**
     * create a new bullet projectile
     * @param position where to create the bullet projectile
     * @param angle what angle the image & movement should start at
     * @param target what creep the projectile should see it it hits etc.
     */
    public Missile3(ProximityVector position, double angle, Creep target, ParticleManager particleManager) {
        //Arguments: ProximityEffect particleEffect, int health, int speed, Sound sound, Image image, ProximityVector position, double angle, Creep target
        super(particleManager.getExplosionBigEffect(), 1, 8, sound, img, position, angle);
        this.target = target;
        setAreaOfEffectRange(range);
    }

    @Override
    public void reAngle() {
            if (target != null) {

                if(!target.isRemoved()) {
                    faceTarget(target.getCenter());
                }
                else {
                    //Keep angle
                }

            } else {
                throw new IllegalStateException("Missile: Trying to reangle but target is null");
            }
    }

    @Override
    public void attack(Creep creep) {
        if (creep != null){
            creep.devolve();
        }

    }



}
