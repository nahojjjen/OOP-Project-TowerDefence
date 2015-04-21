package edu.chl.proximity.Models.Towers;

import com.badlogic.gdx.math.Vector2;
import edu.chl.proximity.Models.Image;
import edu.chl.proximity.Models.Projectiles.Projectile;
import edu.chl.proximity.Models.Projectiles.SlowDownBullet;
import edu.chl.proximity.Models.ResourceSystem.Resources;
import edu.chl.proximity.Models.Towers.TargetingMethods.TargetClosest;
import edu.chl.proximity.Models.Towers.TargetingMethods.TargetFirst;
import edu.chl.proximity.Utilities.Constants;
import edu.chl.proximity.Utilities.PointCalculations;

/**
 * @Author Hanna R�mer
 * @Date 2015-04-15
 * A class representing a tower that shoots bullets that slows down the creeps
 */
public class SlowTower extends Tower {
    private static Image img = new Image(Constants.filePath + "Towers/Hairbrush/1.png");

    /**
     * Create a new SlowTower
     * @param pos Position of tower
     */
    public SlowTower(Vector2 pos){
        super(pos, img, 100, new TargetFirst(), 50, new Resources(100,100,0));
    }

    @Override
    public Projectile createProjectile() {
        return new SlowDownBullet(getCenter(), PointCalculations.getVectorAngle(getPosition(),getTarget().getPosition()), getTarget(), 50, 200);
    }

}
