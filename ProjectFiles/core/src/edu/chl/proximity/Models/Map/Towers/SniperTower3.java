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
 * @author simongislen
 * @date 18/05/15.
 *
 * A class representing a tower that has long range and snipes creeps
 */
public class SniperTower3 extends ShootingTower {

    //Tower stats
    private static Resources resources = new Resources(300, 150, 0);
    private static double range = 9999f;
    private static int reloadTime = 150;

    private static Image img = new Image(Constants.FILE_PATH + "Towers/Sniper/3.png");

    /**
     * @param pos
     *  double range, TargetingMethod targetingMethod, int reloadTime
     */
    public SniperTower3(ProximityVector pos, TargetingMethod targetingMethod, ParticleManager particleManager) {
        super(pos, img, range, targetingMethod, reloadTime, resources, "Sniper Tower");
        setParticleManager(particleManager);
    }


    @Override
    public Projectile createProjectile() {
        SniperBullet bullet= new SniperBullet(getCenter(), PointCalculations.getVectorAngle(getPosition(), getTarget().getPosition()), getTarget(), getParticleManager());
        bullet.setToBullet3();
        return bullet;
    }

    @Override
    public Object clone() throws CloneNotSupportedException{
        return super.clone();
    }
    public Tower getNewUpgrade() {
        return null;
        //return new SniperTower(this.getPosition(), this.getTargetingMethod(), getParticleManager());
    }
}
