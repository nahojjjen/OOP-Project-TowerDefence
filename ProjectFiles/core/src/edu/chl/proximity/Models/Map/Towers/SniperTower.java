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
public class SniperTower extends ShootingTower {

    //Tower stats
    private static Resources resources = new Resources(0, 100, 0);
    private static double range = 9999f;
    private static int reloadTime = 150;

    private static Image img = new Image(Constants.FILE_PATH + "Towers/Sniper/1.png");

    /**
     * @param pos
     *  double range, TargetingMethod targetingMethod, int reloadTime
     */
    public SniperTower(ProximityVector pos, TargetingMethod targetingMethod, ParticleManager particleManager) {
        super(pos, img, range, targetingMethod, reloadTime, resources, "Sniper Tower");
        setParticleManager(particleManager);
    }


    @Override
    public Projectile createProjectile() {
        return new SniperBullet(getCenter(),  PointCalculations.getVectorAngle(getCenter(),getTarget().getCenter()), getParticleManager());
    }

    @Override
    public Object clone() throws CloneNotSupportedException{
        return super.clone();
    }

    public String getDescription() {
        return super.getDescription() + "\n " +
                "A tower which has global range, fires slowly.";
    }

    public Tower getNewUpgrade() {
        return new SniperTower2(this.getPosition(), this.getTargetingMethod(), getParticleManager());
        //return new SniperTower(this.getPosition(), this.getTargetingMethod(), getParticleManager());
    }
}
