package edu.chl.proximity.Models.Towers;

import com.badlogic.gdx.math.Vector2;
import edu.chl.proximity.Models.Image;
import edu.chl.proximity.Models.Projectiles.Bullet;
import edu.chl.proximity.Models.Projectiles.Projectile;
import edu.chl.proximity.Models.Towers.TargetingMethods.TargetClosest;
import edu.chl.proximity.Utilities.Constants;

import java.awt.*;

/**
 * Created by Floompa on 2015-04-08.
 */
public class ShootingTower extends Tower{
    private static Image img = new Image(Constants.filePath + "Towers/ShootingTower/Level1/level1.png");


    /**
     * @param pos
     *  double range, TargetingMethod targetingMethod, int reloadTime
     */
    public ShootingTower(Vector2 pos) {
        super(pos, img, 1000, new TargetClosest(), 100);
    }

    public Projectile createProjectile() {
        return new Bullet(this.getPosition(), 0);

    }
}
