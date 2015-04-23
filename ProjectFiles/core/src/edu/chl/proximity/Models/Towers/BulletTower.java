package edu.chl.proximity.Models.Towers;

import com.badlogic.gdx.math.Vector2;
import edu.chl.proximity.Models.Image;
import edu.chl.proximity.Models.Projectiles.Bullet;
import edu.chl.proximity.Models.Projectiles.Projectile;
import edu.chl.proximity.Models.ResourceSystem.Resources;
import edu.chl.proximity.Models.Towers.TargetingMethods.TargetClosest;
import edu.chl.proximity.Models.Towers.TargetingMethods.TargetFirst;
import edu.chl.proximity.Utilities.Constants;
import edu.chl.proximity.Utilities.PointCalculations;

/**
 * @author Johan Swanberg and Linda Evaldsson
 * @date 2015-04-09
 */
public class BulletTower extends Tower implements Cloneable {


    private static Image img = new Image(Constants.filePath + "Towers/Bullet/1.png");

    /**
     * @param pos
     *  double range, TargetingMethod targetingMethod, int reloadTime
     */
    public BulletTower(Vector2 pos) {
        super(pos, img, 150, new TargetFirst(), 60, new Resources(150,0,0));
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
