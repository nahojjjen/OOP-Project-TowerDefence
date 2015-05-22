package edu.chl.proximity.Models.Map.Towers;

import edu.chl.proximity.Models.Map.Particles.ParticleManager;
import edu.chl.proximity.Utilities.ProximityVector;
import edu.chl.proximity.Models.Map.Towers.TargetingMethods.TargetingMethod;
import edu.chl.proximity.Models.Utils.Image;
import edu.chl.proximity.Models.Map.Projectiles.Missile;
import edu.chl.proximity.Models.Map.Projectiles.Projectile;
import edu.chl.proximity.Models.ResourceSystem.Resources;
import edu.chl.proximity.Utilities.Constants;

/**
 * @author Linda Evaldsson and Johan Swanberg
 * @date 2015-04-08
 *
 * 03-05-2015 Modified by Simon Gislen. Introducing: Towers aren't free.
 * 04-05-2015 Modified by Simon Gislen. Moved projectile functionality to ShootingTower
 * 10/05 modified by Hanna Romer. Added method getUpgrade
 * 22/05 added missile tower 2
 */
public class MissileTower extends ShootingTower {

    //Tower stats
    private static Resources resources = new Resources(0, 75, 0);
    private static double range = 90f;
    private static int reloadTime = 100;

    private static Image img = new Image(Constants.FILE_PATH + "Towers/Missile/1.png");

    /**
     * @param pos
     *  double range, TargetingMethod targetingMethod, int reloadTime
     */
    public MissileTower(ProximityVector pos, TargetingMethod targetingMethod, ParticleManager particleManager) {
        super(pos, img, range, targetingMethod, reloadTime, resources, "Missile Tower");
        setParticleManager(particleManager);

    }

    public Projectile createProjectile() {

        return new Missile(getCenter(), getAngle(), getTarget(), getParticleManager());

    }

    public Tower getNewUpgrade() {
        return new MissileTower2(this.getPosition(), this.getTargetingMethod(), getParticleManager());
    }


}
