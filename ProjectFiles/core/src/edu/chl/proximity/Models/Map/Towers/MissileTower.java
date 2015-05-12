package edu.chl.proximity.Models.Map.Towers;

import edu.chl.proximity.Utilities.ProximityVector;
import edu.chl.proximity.Models.Map.Maps.Map;
import edu.chl.proximity.Models.Map.Towers.TargetingMethods.TargetingMethod;
import edu.chl.proximity.Models.Utils.Image;
import edu.chl.proximity.Models.Map.Projectiles.Missile;
import edu.chl.proximity.Models.Map.Projectiles.Projectile;
import edu.chl.proximity.Models.Player.ResourceSystem.Resources;
import edu.chl.proximity.Utilities.Constants;

/**
 * @author Linda Evaldsson and Johan Swanberg
 * @date 2015-04-08
 *
 * 03-05-2015 Modified by Simon Gislen. Introducing: Towers aren't free.
 * 04-05-2015 Modified by Simon Gislen. Moved projectile functionality to ShootingTower
 * 10/05 modified by Hanna R�mer. Added method getUpgrade
 */
public class MissileTower extends ShootingTower {

    //Tower stats
    private static Resources resources = new Resources(300, 200, 0);
    private static double range = 200f;
    private static int reloadTime = 100;

    private static Image img = new Image(Constants.FILE_PATH + "Towers/Missile/1.png");

    /**
     * @param pos
     *  double range, TargetingMethod targetingMethod, int reloadTime
     */
    public MissileTower(Map map, ProximityVector pos, TargetingMethod targetingMethod) {
        super(map, pos, img, range, targetingMethod, reloadTime, resources, "Missile Tower");
    }

    public Projectile createProjectile() {

        return new Missile(getCenter(), getAngle(), getTarget(), getMap().getParticleManager());

    }

    public Tower getUpgrade(){
        return new MissileTower(this.getMap(), this.getPosition(), this.getTargetingMethod());
    }
}
