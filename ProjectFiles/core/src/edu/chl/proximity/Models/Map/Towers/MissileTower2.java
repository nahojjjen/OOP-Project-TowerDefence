package edu.chl.proximity.Models.Map.Towers;

import edu.chl.proximity.Models.Map.Particles.ParticleManager;
import edu.chl.proximity.Models.Map.Projectiles.Missile;
import edu.chl.proximity.Models.Map.Projectiles.Projectile;
import edu.chl.proximity.Models.Map.Towers.TargetingMethods.TargetingMethod;
import edu.chl.proximity.Models.ResourceSystem.Resources;
import edu.chl.proximity.Models.Utils.Image;
import edu.chl.proximity.Utilities.Constants;
import edu.chl.proximity.Utilities.ProximityVector;

/**
 * @author Simon Gislen
 * @date 2015-05-22
 * The second upgrade to MissileTower
 */
public class MissileTower2 extends ShootingTower {

    //Tower stats
    private static Resources resources = new Resources(50, 200, 0);
    private static double range = 100f;
    private static int reloadTime = 50;

    private static Image img = new Image(Constants.FILE_PATH + "Towers/Missile/2.png");

    /**
     * @param pos
     *  double range, TargetingMethod targetingMethod, int reloadTime
     */
    public MissileTower2(ProximityVector pos, TargetingMethod targetingMethod, ParticleManager particleManager) {
        super(pos, img, range, targetingMethod, reloadTime, resources, "Missile Tower");
        setParticleManager(particleManager);

    }

    public Projectile createProjectile() {

        return new Missile(getCenter(), getAngle(), getTarget(), getParticleManager());

    }

    public Tower getNewUpgrade() {
        return null;
        //return new MissileTower(this.getPosition(), this.getTargetingMethod(), getParticleManager());
    }


}
