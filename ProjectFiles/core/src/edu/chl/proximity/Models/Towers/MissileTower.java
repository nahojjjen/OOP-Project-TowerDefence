package edu.chl.proximity.Models.Towers;

import com.badlogic.gdx.math.Vector2;
import edu.chl.proximity.Models.Image;
import edu.chl.proximity.Models.Projectiles.Missile;
import edu.chl.proximity.Models.Projectiles.Projectile;
import edu.chl.proximity.Models.Towers.TargetingMethods.TargetClosest;
import edu.chl.proximity.Utilities.Constants;

/**
 * Created by Floompa on 2015-04-08.
 */
public class MissileTower extends Tower{
    private static Image img = new Image(Constants.filePath + "Towers/Missile/1.png");


    /**
     * @param pos
     *  double range, TargetingMethod targetingMethod, int reloadTime
     */
    public MissileTower(Vector2 pos) {
        super(pos, img, 1000, new TargetClosest(), 100);
    }

    public Projectile createProjectile() {
        return new Missile(this.getPosition(), 0, getTarget());

    }
}
