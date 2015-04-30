package edu.chl.proximity.Models.Map.Towers;

import com.badlogic.gdx.math.Vector2;
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
 */
public class MissileTower extends Tower{
    private static Image img = new Image(Constants.filePath + "Towers/Missile/1.png");


    /**
     * @param pos
     *  double range, TargetingMethod targetingMethod, int reloadTime
     */
    public MissileTower(Vector2 pos) {
        super(pos, img, 200, new TargetClosest(), 100, new Resources(300, 200, 0));
    }

    public Projectile createProjectile() {
        return new Missile(getCenter(), getAngle(), getTarget());

    }
}
