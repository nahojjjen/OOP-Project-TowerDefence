package edu.chl.proximity.Models.Map.Towers;

import com.badlogic.gdx.math.Vector2;
import edu.chl.proximity.Models.Utils.Image;
import edu.chl.proximity.Models.Map.Projectiles.Bullet;
import edu.chl.proximity.Models.Map.Projectiles.Projectile;
import edu.chl.proximity.Models.Player.ResourceSystem.Resources;
import edu.chl.proximity.Models.Map.Towers.TargetingMethods.TargetFirst;
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
