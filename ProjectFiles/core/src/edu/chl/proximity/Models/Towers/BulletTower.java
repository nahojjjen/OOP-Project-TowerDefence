package edu.chl.proximity.Models.Towers;

import com.badlogic.gdx.math.Vector2;
import edu.chl.proximity.Models.Image;
import edu.chl.proximity.Models.Projectiles.Bullet;
import edu.chl.proximity.Models.Projectiles.Projectile;
import edu.chl.proximity.Models.Towers.TargetingMethods.TargetClosest;
import edu.chl.proximity.Models.Towers.TargetingMethods.TargetFirst;
import edu.chl.proximity.Utilities.Constants;
import edu.chl.proximity.Utilities.PointCalculations;

/**
 * Created by Johan on 2015-04-13. group work with linda
 */
public class BulletTower extends Tower {


    private static Image img = new Image(Constants.filePath + "Towers/Bullet/1.png");

    /**
     * @param pos
     *  double range, TargetingMethod targetingMethod, int reloadTime
     */
    public BulletTower(Vector2 pos) {
        super(pos, img, 5, new TargetFirst(), 1);
    }


    @Override
    public Projectile createProjectile() {
        return new Bullet(getPosition(), PointCalculations.getVectorAngle(getPosition(),getTarget().getPosition()), getTarget());
    }
}
