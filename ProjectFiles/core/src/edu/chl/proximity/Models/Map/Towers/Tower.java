package edu.chl.proximity.Models.Map.Towers;

import com.badlogic.gdx.math.Vector2;
import edu.chl.proximity.Models.BoardObject;
import edu.chl.proximity.Models.Map.Creeps.Creep;
import edu.chl.proximity.Models.Utils.GameData;
import edu.chl.proximity.Models.Player.Holdables.Holdable;
import edu.chl.proximity.Models.Utils.Image;
import edu.chl.proximity.Models.Map.Projectiles.Projectile;
import edu.chl.proximity.Models.Player.ResourceSystem.Resources;
import edu.chl.proximity.Models.Map.Towers.TargetingMethods.TargetingMethod;
import edu.chl.proximity.Utilities.PointCalculations;


/**
 * @author Johan Swanberg
 * @date 2015-04-02
 *
 * ---
 * 08/04 modified by Linda Evaldsson. Refactoring to Tower instead of AbstracTower. Removed Projectile as parameter for the constructor.
 * 03-05-2015 Modified by Simon Gislen. Tiny spell check.
 * 04-05-2015 Modified by Simon Gislen. Moved projectile functionality to ShootingTower
 *
 */
public abstract class Tower extends BoardObject implements Holdable, Cloneable{

    protected Resources cost;
    protected double range;

    public Tower(Vector2 pos, Image image, int angle) {
        super(pos, image, angle);
    }

    public abstract void update();

    @Override
    public void placeObject(Vector2 position) {
        this.setCenter(position);
        GameData.getInstance().getMap().addTower(this);
        GameData.getInstance().getHand().setItem(null);
        GameData.getInstance().getPlayer().getResources().removeResources(getCost());
    }

    @Override
    public double getRange(){
        return range;
    }

    public Resources getCost() {
        return cost;
    }
}
