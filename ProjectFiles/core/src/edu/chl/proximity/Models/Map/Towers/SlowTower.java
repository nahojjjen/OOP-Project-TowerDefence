package edu.chl.proximity.Models.Map.Towers;

import com.badlogic.gdx.math.Vector2;
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
        return new SlowDownBullet(getCenter(), getAngle(), getTarget(), 50, 200);
    }

}
