package edu.chl.proximity.Models.Map.Towers;

import edu.chl.proximity.Models.Map.Particles.ParticleManager;
import edu.chl.proximity.Models.Map.Projectiles.Projectile;
import edu.chl.proximity.Models.Map.Projectiles.SlowDownBullet;
import edu.chl.proximity.Models.Map.Towers.TargetingMethods.TargetingMethod;
import edu.chl.proximity.Models.ResourceSystem.Resources;
import edu.chl.proximity.Models.Utils.Image;
import edu.chl.proximity.Utilities.Constants;
import edu.chl.proximity.Utilities.ProximityVector;

/**
 * @author Simon Gislen
 * @date 2015-05-13
 *
 * A class representing a concrete tower.
 */
public class SlowTower2 extends ShootingTower {

    //Tower stats
    protected static final Resources resources = new Resources(700, 700, 0);
    protected static final double range = 140f;
    protected static final int reloadTime = 35;

    //Bullet stats
    protected static final double slowDownPercent = 60;
    protected static final int slowDownTime = 120;

    protected static final Image img = new Image(Constants.FILE_PATH + "Towers/Hairbrush/2.png");

    /**
     * Create a new SlowTower
     * @param pos Position of tower
     */
    public SlowTower2(ProximityVector pos, TargetingMethod targetingMethod, ParticleManager particleManager){
        super(pos, img, range, targetingMethod, reloadTime, resources, "Permafrost Tower");
        setParticleManager(particleManager);
    }

    @Override
    public Projectile createProjectile() {
        SlowDownBullet projectile = new SlowDownBullet(getCenter(), getAngle(), getTarget(), slowDownPercent, slowDownTime, getParticleManager());
        projectile.setAsSlow2();
        return projectile;
    }
    public String getDescription() {
        return super.getDescription() + "\n " +
                "Makes the freezing bullets freeze a large area on impact. Also increases slow effect to 60% for 2 seconds.";
    }
    public Tower getNewUpgrade(){
        return null;
    }
}
