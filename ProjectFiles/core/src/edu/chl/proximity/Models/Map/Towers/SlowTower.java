package edu.chl.proximity.Models.Map.Towers;

import com.badlogic.gdx.math.Vector2;
import edu.chl.proximity.Models.Map.Towers.TargetingMethods.TargetingMethod;
import edu.chl.proximity.Models.Utils.Image;
import edu.chl.proximity.Models.Map.Projectiles.Projectile;
import edu.chl.proximity.Models.Map.Projectiles.SlowDownBullet;
import edu.chl.proximity.Models.Player.ResourceSystem.Resources;
import edu.chl.proximity.Models.Map.Towers.TargetingMethods.TargetFirst;
import edu.chl.proximity.Utilities.Constants;

/**
 * @author Hanna Römer
 * @date 2015-04-15
 * A class representing a tower that shoots bullets that slows down the creeps
 *
 * 03-05-2015 Modified by Simon Gislen. Introducing: Towers aren't free.
 * 04-05-2015 Modified by Simon Gislen. Moved projectile functionality to ShootingTower
 */
public class SlowTower extends ShootingTower {

    //Tower stats
    private static Resources resources = new Resources(100, 100, 0);
    private static double range = 140f;
    private static TargetingMethod targetingMethod = new TargetFirst();
    private static int reloadTime = 40;

    //Bullet stats
    private static double slowDownPercent = 50;
    private static int slowDownTime = 200;

    private static Image img = new Image(Constants.filePath + "Towers/Hairbrush/1.png");

    /**
     * Create a new SlowTower
     * @param pos Position of tower
     */
    public SlowTower(Vector2 pos){
        super(pos, img, range, targetingMethod, reloadTime, resources);
    }

    @Override
    public Projectile createProjectile() {
        return new SlowDownBullet(getCenter(), getAngle(), getTarget(), slowDownPercent, slowDownTime);
    }

}
