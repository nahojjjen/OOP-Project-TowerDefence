package edu.chl.proximity.Models.Map.Towers;

import edu.chl.proximity.Models.Map.Particles.ParticleManager;
import edu.chl.proximity.Models.Map.Projectiles.Projectile;
import edu.chl.proximity.Models.Map.Projectiles.SniperBullet;
import edu.chl.proximity.Models.Map.Towers.TargetingMethods.TargetingMethod;
import edu.chl.proximity.Models.ResourceSystem.Resources;
import edu.chl.proximity.Models.Utils.Image;
import edu.chl.proximity.Utilities.Constants;
import edu.chl.proximity.Utilities.PointCalculations;
import edu.chl.proximity.Utilities.ProximityVector;

/**
 * @author Simon Gislen
 * @date 2015-05-18
 *
 * A class representing a tower that has long range and snipes creeps
 */
public class SniperTower2 extends ShootingTower {

    //Tower stats
    private static Resources resources = new Resources(100, 100, 0);
    private static double range = 9999f;
    private static int reloadTime = 150;

    private static Image img = new Image(Constants.FILE_PATH + "Towers/Sniper/2.png");

    /**
     * @param pos
     *  double range, TargetingMethod targetingMethod, int reloadTime
     */
    public SniperTower2(ProximityVector pos, TargetingMethod targetingMethod, ParticleManager particleManager) {
        super(pos, img, range, targetingMethod, reloadTime, resources, "Needle Tower");
        setParticleManager(particleManager);
    }


    @Override
    public Projectile createProjectile() {
        SniperBullet bullet= new SniperBullet(getCenter(),  PointCalculations.getVectorAngle(getCenter(),getTarget().getCenter()), getParticleManager());
        bullet.setHealth(3);
        return bullet;
      }

    @Override
    public Object clone() throws CloneNotSupportedException{
        return super.clone();
    }

    public String getDescription() {
        return super.getDescription() + "\n " +
                "Makes the sniper bullets pierce its initial target and hit 2 additional targets behind.";
    }

    public Tower getNewUpgrade() {
        return new SniperTower3(this.getPosition(), this.getTargetingMethod(), getParticleManager());
        //return new SniperTower(this.getPosition(), this.getTargetingMethod(), getParticleManager());
    }
}
