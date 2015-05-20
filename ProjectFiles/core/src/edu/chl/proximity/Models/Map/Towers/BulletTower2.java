package edu.chl.proximity.Models.Map.Towers;

import edu.chl.proximity.Models.Map.Creeps.Creep;
import edu.chl.proximity.Models.Map.Particles.ParticleManager;
import edu.chl.proximity.Models.Map.Projectiles.Bullet;
import edu.chl.proximity.Models.Map.Projectiles.Projectile;
import edu.chl.proximity.Models.Map.Towers.TargetingMethods.TargetingMethod;
import edu.chl.proximity.Models.ResourceSystem.Resources;
import edu.chl.proximity.Models.Utils.Image;
import edu.chl.proximity.Utilities.Constants;
import edu.chl.proximity.Utilities.PointCalculations;
import edu.chl.proximity.Utilities.ProximityVector;

import java.util.List;

/**
 * @author simon gislen
 *
 * @date 13/05/15.
 */
public class BulletTower2 extends Tower implements Cloneable {


    //Tower stats
    private static Resources resources = new Resources(150, 0, 0);
    private static double range = 160f;
    private static int reloadTime = 10;
    private static int overHeatTime = 50;
    private static int ammo = 3;

    private int currentReload = 0;
    private int ammoCounter = 0;


    private TargetingMethod targetingMethod;
    private Creep currentTarget;

    private static Image img = new Image(Constants.FILE_PATH + "Towers/Bullet/level2.png");

    public BulletTower2(ProximityVector pos, TargetingMethod targetingMethod, ParticleManager particleManager) {
        super(pos, img, 0, "Rapid Fire Tower");
        setParticleManager(particleManager);
        this.cost = resources;
        this.targetingMethod = targetingMethod;
    }

//    public BulletTower2(ProximityVector pos, TargetingMethod targetingMethod, ParticleManager particleManager) {
//        super(pos, img, range, targetingMethod, reloadTime, resources, "Bullet Tower 2");
//    }

    /**
     * Targets the closest creep, if one is in range.
     */
    public void target(List<Creep> creeps){
        if (creeps != null){
            currentTarget = targetingMethod.getTarget(creeps, getPosition(), range);
            if (currentTarget != null) {
                this.setAngle(PointCalculations.getVectorAngle(this.getPosition(), currentTarget.getPosition()));
            }
        }

    }

    public void shoot(List<Creep> creeps){

        target(creeps);

        if (creeps != null){
            if (ammoCounter <= ammo) {
                if (currentReload < 1 && currentTarget != null) {
                    add(createProjectile(getCenter()));
                    currentReload = reloadTime;
                    ammoCounter++;
                } else {
                    currentReload--;
                }
            }
            else {
                currentReload = overHeatTime;
                ammoCounter = 0;
            }
        }

    }

    public Creep getTarget(){
        return currentTarget;
    }

    public Projectile createProjectile(ProximityVector pos) {
        return new Bullet(pos, PointCalculations.getVectorAngle(getPosition(), getTarget().getPosition()), getTarget(), getParticleManager());
    }

    @Override
    public void update(List<Creep> creeps) {
        shoot(creeps);
    }

    @Override
    public Tower getNewUpgrade() {
        return null;
        //return new BulletTower2(this.getPosition(), targetingMethod, getParticleManager());
    }

    @Override
    public double getRange(){
        return range;
    }

}
