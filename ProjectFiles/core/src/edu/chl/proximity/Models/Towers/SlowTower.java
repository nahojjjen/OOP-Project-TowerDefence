package edu.chl.proximity.Models.Towers;

import com.badlogic.gdx.math.Vector2;
import edu.chl.proximity.Models.Image;
import edu.chl.proximity.Models.Projectiles.Projectile;
import edu.chl.proximity.Models.Projectiles.SlowDownBullet;
import edu.chl.proximity.Models.Towers.TargetingMethods.TargetClosest;
import edu.chl.proximity.Models.Towers.TargetingMethods.TargetFirst;
import edu.chl.proximity.Utilities.Constants;
import edu.chl.proximity.Utilities.PointCalculations;

/**
 * Created by Hanna on 2015-04-15.
 */
public class SlowTower extends Tower {
    private static Image img = new Image(Constants.filePath + "Towers/Hairbrush/1.png");


    public SlowTower(Vector2 pos){
        super(pos, img, 1000, new TargetFirst(), 100);
    }

    @Override
    public Projectile createProjectile() {
        return new SlowDownBullet(getCenter(), PointCalculations.getVectorAngle(getPosition(),getTarget().getPosition()), getTarget(), 20, 5000);
    }

}
