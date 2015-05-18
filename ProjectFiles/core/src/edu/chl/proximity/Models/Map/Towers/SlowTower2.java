package edu.chl.proximity.Models.Map.Towers;

import edu.chl.proximity.Models.Map.Particles.ParticleManager;
import edu.chl.proximity.Models.Map.Projectiles.Projectile;
import edu.chl.proximity.Models.Map.Projectiles.SlowDownBullet;
import edu.chl.proximity.Models.Map.Towers.TargetingMethods.TargetingMethod;
import edu.chl.proximity.Models.Player.ResourceSystem.Resources;
import edu.chl.proximity.Models.Utils.Image;
import edu.chl.proximity.Utilities.Constants;
import edu.chl.proximity.Utilities.ProximityVector;

/**
 * Created by simongislen on 13/05/15.
 */
public class SlowTower2 extends ShootingTower {

    //Tower stats
    protected static final Resources resources = new Resources(100, 100, 0);
    protected static final double range = 140f;
    protected static final int reloadTime = 40;

    //Bullet stats
    protected static final double slowDownPercent = 90;
    protected static final int slowDownTime = 220;

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
        return new SlowDownBullet(getCenter(), getAngle(), getTarget(), slowDownPercent, slowDownTime, getParticleManager());
    }

    public Tower getNewUpgrade(){
        return null;
        //return new SlowTower2(this.getPosition(),this.getTargetingMethod(), getParticleManager());
    }
}
