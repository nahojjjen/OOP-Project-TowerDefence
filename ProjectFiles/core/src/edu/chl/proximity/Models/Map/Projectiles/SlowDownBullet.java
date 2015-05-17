package edu.chl.proximity.Models.Map.Projectiles;

import edu.chl.proximity.Models.Map.Particles.ParticleManager;
import edu.chl.proximity.Models.Utils.ProximitySound;
import edu.chl.proximity.Utilities.ProximityVector;
import edu.chl.proximity.Models.Map.Creeps.Creep;
import edu.chl.proximity.Models.Utils.Image;
import edu.chl.proximity.Utilities.Constants;

/**
 * @author Hanna Römer
 * @date 2015-04-18
 *
 * 12/05 modified by Linda Evaldsson. Removed map from constructor.
 *
 * A class representing a bullet that slows down it's target a certain percentage
 *
 *
 */
public class SlowDownBullet extends Projectile{
    private static Image img = new Image(Constants.FILE_PATH + "Projectiles/frostBolt.png");;
    private static ProximitySound sound = new ProximitySound(Constants.FILE_PATH + "Sounds/snap.ogg");
    private double percent;
    private int time;
    private Creep target;

    /**
     * Create a new SlowDownBullet
     * @param position Starting position of bullet
     * @param angle Angle bullet should start with
     * @param target Creep that bullet should aim for
     * @param slowDownPercent Percentage of speed the target creep will loose
     * @param slowDownTime Amount of time the target creep will be slowed down
     */
    public SlowDownBullet(ProximityVector position, double angle, Creep target, double slowDownPercent, int slowDownTime, ParticleManager particleManager){
        super(particleManager.getFrostBlastEffect(), 1, 10, sound, img, position, angle);
        this.target=target;
        this.percent=slowDownPercent;
        this.time=slowDownTime;
    }


    public void reAngle() {
        if (target != null) {
            if(!target.isRemoved()) {
                faceTarget(target.getCenter());
            }
            else {
                //Keep angle
            }
        } else {
            throw new IllegalStateException("SlowDownBullet: Trying to reangle but target is null");
        }
    }

    public void attack(Creep creep){
        creep.slowDown(percent, time);
    }


}
