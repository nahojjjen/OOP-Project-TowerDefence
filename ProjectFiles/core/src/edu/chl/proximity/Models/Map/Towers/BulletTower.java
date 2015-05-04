package edu.chl.proximity.Models.Map.Towers;

import com.badlogic.gdx.math.Vector2;
import edu.chl.proximity.Models.Map.Towers.TargetingMethods.TargetingMethod;
import edu.chl.proximity.Models.Utils.Image;
import edu.chl.proximity.Models.Map.Projectiles.Bullet;
import edu.chl.proximity.Models.Map.Projectiles.Projectile;
import edu.chl.proximity.Models.Player.ResourceSystem.Resources;
import edu.chl.proximity.Models.Map.Towers.TargetingMethods.TargetFirst;
import edu.chl.proximity.Utilities.Constants;
import edu.chl.proximity.Utilities.PointCalculations;

/**
 * @author Johan Swanberg and Linda Evaldsson
 *
 * @date 2015-04-09
 *
 * 03-05-2015 Modified by Simon Gislen. Introducing: Towers aren't free.
 * 04-05-2015 Modified by Simon Gislen. Moved projectile functionality to ShootingTower
 */
public class BulletTower extends ShootingTower implements Cloneable {

    //Tower stats
    private static Resources resources = new Resources(150, 0, 0);
    private static double range = 150f;
    private static TargetingMethod targetingMethod = new TargetFirst();
    private static int reloadTime = 60;

    private static Image img = new Image(Constants.filePath + "Towers/Bullet/1.png");

    /**
     * @param pos
     *  double range, TargetingMethod targetingMethod, int reloadTime
     */
    public BulletTower(Vector2 pos) {
        super(pos, img, range, targetingMethod, reloadTime, resources);
    }


    @Override
    public Projectile createProjectile() {
        return new Bullet(getCenter(), PointCalculations.getVectorAngle(getPosition(),getTarget().getPosition()), getTarget());
    }

    @Override
    public Object clone() throws CloneNotSupportedException{
        return super.clone();
    }
}
