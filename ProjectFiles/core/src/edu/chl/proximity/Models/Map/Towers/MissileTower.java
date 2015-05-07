package edu.chl.proximity.Models.Map.Towers;

import com.badlogic.gdx.math.Vector2;
import edu.chl.proximity.Models.Map.Maps.Map;
import edu.chl.proximity.Models.Map.Towers.TargetingMethods.TargetFirst;
import edu.chl.proximity.Models.Map.Towers.TargetingMethods.TargetingMethod;
import edu.chl.proximity.Models.Utils.Image;
import edu.chl.proximity.Models.Map.Projectiles.Missile;
import edu.chl.proximity.Models.Map.Projectiles.Projectile;
import edu.chl.proximity.Models.Player.ResourceSystem.Resources;
import edu.chl.proximity.Models.Map.Towers.TargetingMethods.TargetClosest;
import edu.chl.proximity.Utilities.Constants;

/**
 * @author Linda Evaldsson and Johan Swanberg
 * @date 2015-04-08
 *
 * 03-05-2015 Modified by Simon Gislen. Introducing: Towers aren't free.
 * 04-05-2015 Modified by Simon Gislen. Moved projectile functionality to ShootingTower
 */
public class MissileTower extends ShootingTower {

    //Tower stats
    private static Resources resources = new Resources(300, 200, 0);
    private static double range = 200f;
    private static int reloadTime = 100;

    private static Image img = new Image(Constants.filePath + "Towers/Missile/1.png");

    /**
     * @param pos
     *  double range, TargetingMethod targetingMethod, int reloadTime
     */
    public MissileTower(Map map, Vector2 pos, TargetingMethod targetingMethod) {
        super(map, pos, img, range, targetingMethod, reloadTime, resources);
    }

    public Projectile createProjectile() {

        return new Missile(getMap(), getCenter(), getAngle(), getTarget());

    }
}
