package edu.chl.proximity.Models.Map.Towers;

import edu.chl.proximity.Models.Map.Particles.ParticleManager;
import edu.chl.proximity.Models.Map.Projectiles.Bullet;
import edu.chl.proximity.Models.Map.Projectiles.Projectile;
import edu.chl.proximity.Models.Map.Towers.TargetingMethods.TargetingMethod;
import edu.chl.proximity.Models.ResourceSystem.Resources;
import edu.chl.proximity.Models.Utils.Image;
import edu.chl.proximity.Utilities.Constants;
import edu.chl.proximity.Utilities.PointCalculations;
import edu.chl.proximity.Utilities.ProximityVector;

/**
 * @author Linda Evaldsson
 * @date 2015-05-22
 */
public class BulletTower2 extends ShootingTower implements Cloneable {

    //Tower stats
    private static Resources resources = new Resources(150, 0, 0);
    private static double range = 125f;
    private static int reloadTime = 40;

    private static Image img = new Image(Constants.FILE_PATH + "Towers/Bullet/level2.png");

    /**
     * @param pos
     *  double range, TargetingMethod targetingMethod, int reloadTime
     */
    public BulletTower2(ProximityVector pos, TargetingMethod targetingMethod, ParticleManager particleManager) {
        super(pos, img, range, targetingMethod, reloadTime, resources, "Bullet Tower");
        setParticleManager(particleManager);
    }


    @Override
    public Projectile createProjectile() {
        return new Bullet(getCenter(), PointCalculations.getVectorAngle(getPosition(), getTarget().getPosition()), getTarget(), getParticleManager());
    }

    @Override
    public Object clone() throws CloneNotSupportedException{
        return super.clone();
    }
    public Tower getNewUpgrade() {
        return new BulletTower3(this.getPosition(), this.getTargetingMethod(), getParticleManager());
    }


}
