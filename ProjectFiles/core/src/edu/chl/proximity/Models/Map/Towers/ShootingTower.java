package edu.chl.proximity.Models.Map.Towers;

import edu.chl.proximity.Models.Map.Creeps.Creep;
import edu.chl.proximity.Models.Map.Projectiles.Projectile;
import edu.chl.proximity.Models.Map.Towers.TargetingMethods.TargetingMethod;
import edu.chl.proximity.Models.ResourceSystem.Resources;
import edu.chl.proximity.Models.Utils.Image;
import edu.chl.proximity.Utilities.PointCalculations;
import edu.chl.proximity.Utilities.ProximityVector;

import java.util.List;

/**
 * @author Simon Gislen
 * @date 2015-05-04
 *
 * Abstract tower class that handles shooting of projectiles
 *
 * ---
 * 08/05 modified by Hanna Romer. Added setter and getter for targetingMethod.
 */
public abstract class ShootingTower extends Tower implements TargetingTower{

    private int reloadTime;
    private int currentReload = 0;

    private TargetingMethod targetingMethod;
    private Creep currentTarget;
    private double reloadTimeInSeconds;

    /**
     * Create a new type of tower
     * @param pos where the tower should be placed
     * @param image what image the tower should have
     * @param range what range the tower should have
     * @param targetingMethod how the tower should decide what target to shoot
     * @param reloadTime how long it takes the tower to shoot another bullet (in frames)
     */
    public ShootingTower(ProximityVector pos, Image image, double range, TargetingMethod targetingMethod, int reloadTime, Resources cost, String name){
        //arguments: Position, texture, image rotation-angle
        super(pos, image, 0, name);
        this.range = range;
        this.targetingMethod = targetingMethod;
        this.reloadTime = reloadTime;
        this.cost = cost;
        reloadTimeInSeconds = ((int)(reloadTime/60.0*100))/100.0;
    }

    public String getDescription() {
        return "Reload time: " + reloadTimeInSeconds + "s\n";

    }

    @Override
    public void update(List<Creep> creeps) {
        target(creeps);
        shoot();
        reload();
    }

    /**
     * create a projectile at the towers location, if the tower can shoot (aka is not reloading)
     * if the tower shoots, start the reload time
     */
    public void shoot(){
        if(currentReload < 1 && currentTarget != null){
            add(createProjectile());
            currentReload = reloadTime;
        }
    }

    /**
     * Targets the closest creep, if one is in range.
     */
    public void target(List<Creep> creeps){
        if (creeps != null){
            currentTarget = targetingMethod.getTarget(creeps, getCenter(), range);
            if (currentTarget != null) {
                this.setAngle(PointCalculations.getVectorAngle(this.getCenter(), currentTarget.getCenter()));
            }
        }

    }

    public Creep getTarget(){
        return currentTarget;
    }

    protected void setTarget(Creep target){this.currentTarget=target; }

    /**
     * create the projectile-type this tower should fire
     * @return a projectile corresponding to this towers type
     */
    public abstract Projectile createProjectile();



    /**
     * progress the towers reload, tower can shoot when reload is at 0
     */
    public void reload(){
        if(currentReload > 0){
            currentReload --;
        }
    }

    public void setTargetingMethod(TargetingMethod targetingMethod){ this.targetingMethod=targetingMethod;}
    public TargetingMethod getTargetingMethod() {return this.targetingMethod;}
}
