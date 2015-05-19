package edu.chl.proximity.Models.Map.Towers;

import edu.chl.proximity.Models.Map.Particles.ParticleManager;
import edu.chl.proximity.Utilities.ProximityVector;
import edu.chl.proximity.Models.Map.Towers.TargetingMethods.TargetingMethod;
import edu.chl.proximity.Models.Utils.Image;
import edu.chl.proximity.Models.Map.Projectiles.Projectile;
import edu.chl.proximity.Models.Map.Projectiles.SlowDownBullet;
import edu.chl.proximity.Models.Player.ResourceSystem.Resources;
import edu.chl.proximity.Utilities.Constants;

/**
 * @author Hanna Romer
 * @date 2015-04-15
 * A class representing a tower that shoots bullets that slows down the creeps
 *
 * 03-05-2015 Modified by Simon Gislen. Introducing: Towers aren't free.
 * 04-05-2015 Modified by Simon Gislen. Moved projectile functionality to ShootingTower
 * 10/05 modified by Hanna Romer. Added method getUpgrade
 */
public class SlowTower extends ShootingTower {

    //Tower stats
    protected static final Resources resources = new Resources(100, 100, 0);
    protected static final double range = 140f;
    protected static final int reloadTime = 40;

    //Bullet stats
    protected static final double slowDownPercent = 50;
    protected static final int slowDownTime = 200;

    protected static final Image img = new Image(Constants.FILE_PATH + "Towers/Hairbrush/1.png");

    /**
     * Create a new SlowTower
     * @param pos Position of tower
     */
    public SlowTower(ProximityVector pos, TargetingMethod targetingMethod, ParticleManager particleManager){
        super(pos, img, range, targetingMethod, reloadTime, resources, "Freeze Tower");
        setParticleManager(particleManager);
    }

    @Override
    public Projectile createProjectile() {

        return new SlowDownBullet(getCenter(), getAngle(), getTarget(), slowDownPercent, slowDownTime, getParticleManager());
    }

    public Tower getNewUpgrade(){
        return new SlowTower2(this.getPosition(),this.getTargetingMethod(), getParticleManager());
    }

}
