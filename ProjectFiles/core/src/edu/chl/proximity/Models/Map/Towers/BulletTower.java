package edu.chl.proximity.Models.Map.Towers;

import edu.chl.proximity.Models.Map.Particles.ParticleManager;
import edu.chl.proximity.Utilities.ProximityVector;
import edu.chl.proximity.Models.Map.Towers.TargetingMethods.TargetingMethod;
import edu.chl.proximity.Models.Utils.Image;
import edu.chl.proximity.Models.Map.Projectiles.Bullet;
import edu.chl.proximity.Models.Map.Projectiles.Projectile;
import edu.chl.proximity.Models.ResourceSystem.Resources;
import edu.chl.proximity.Utilities.Constants;
import edu.chl.proximity.Utilities.PointCalculations;

/**
 * @author Johan Swanberg and Linda Evaldsson
 *
 * @date 2015-04-09
 *
 * 03-05-2015 Modified by Simon Gislen. Introducing: Towers aren't free.
 * 04-05-2015 Modified by Simon Gislen. Moved projectile functionality to ShootingTower
 * 10/05 modified by Hanna Romer. Added method getUpgrade
 */
public class BulletTower extends ShootingTower implements Cloneable {

    //Tower stats
    private static Resources resources = new Resources(75, 0, 0);
    private static double range = 75f;
    private static int reloadTime = 60;

    private static Image img = new Image(Constants.FILE_PATH + "Towers/Bullet/1.png");

    /**
     * @param pos
     *  double range, TargetingMethod targetingMethod, int reloadTime
     */
    public BulletTower(ProximityVector pos, TargetingMethod targetingMethod, ParticleManager particleManager) {
        super(pos, img, range, targetingMethod, reloadTime, resources, "Bullet Tower");
        setParticleManager(particleManager);
    }


    @Override
    public Projectile createProjectile() {
        return new Bullet(getCenter(), PointCalculations.getVectorAngle(getPosition(),getTarget().getPosition()), getTarget(), getParticleManager());
    }

    @Override
    public Object clone() throws CloneNotSupportedException{
        return super.clone();
    }
    public Tower getNewUpgrade() {
        return new BulletTower2(this.getPosition(), this.getTargetingMethod(), getParticleManager());
    }


}
