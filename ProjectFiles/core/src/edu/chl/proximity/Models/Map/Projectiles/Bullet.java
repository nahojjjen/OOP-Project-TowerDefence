package edu.chl.proximity.Models.Map.Projectiles;


import edu.chl.proximity.Models.Map.Creeps.Creep;
import edu.chl.proximity.Models.Map.Particles.ParticleManager;
import edu.chl.proximity.Models.Utils.Image;
import edu.chl.proximity.Models.Utils.ProximitySound;
import edu.chl.proximity.Utilities.Constants;
import edu.chl.proximity.Utilities.ProximityVector;

/**
 * @author Linda Evaldsson and Johan Swanberg
 * @date 2015-04-13
 *
 * 12/05 modified by Linda Evaldsson. Removed map from constructor.
 *
 * A bullet is a simple bullet that has a static angle and kills one creep.
 */
public class Bullet extends Projectile {

    private static Image img = new Image(Constants.FILE_PATH + "Projectiles/bullet.png");
    private static ProximitySound sound = new ProximitySound(Constants.FILE_PATH + "Sounds/poof.ogg");

    /**
     * create a new bullet projectile
     *
     * @param position where to create the bullet projectile
     * @param angle    what angle the image & movement should start at
     * @param target   what creep the projectile should see it it hits etc.
     */
    public Bullet(ProximityVector position, double angle, Creep target, ParticleManager particleManager) {
        //Arguments: ProximityEffect particleEffect, int health, int speed, Sound sound, Image image, ProximityVector position, double angle, Creep target
        super(particleManager.getBulletEffect(), 1, 20, sound, img, position, angle);

    }

    @Override
    public void reAngle() {//bullets keep their angle    }
    }

    @Override
    public void attack(Creep creep) {
        if (creep != null){
            creep.devolve();
        }
    }

}
