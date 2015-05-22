package edu.chl.proximity.Models.Map.Towers;

import edu.chl.proximity.Models.Map.Creeps.Creep;
import edu.chl.proximity.Models.Map.Particles.ParticleManager;
import edu.chl.proximity.Models.Map.Projectiles.Projectile;
import edu.chl.proximity.Models.Map.Towers.TargetingMethods.TargetingMethod;
import edu.chl.proximity.Models.ResourceSystem.Resources;
import edu.chl.proximity.Models.Utils.Image;
import edu.chl.proximity.Utilities.Constants;
import edu.chl.proximity.Utilities.PointCalculations;
import edu.chl.proximity.Utilities.ProximityVector;

import java.util.List;

/**
 * @author Hanna Romer
 * @date
 */
public class MobileTower extends ShootingTower{
    private TargetingMethod targetingMethod;
    private ParticleManager particleManager;

    private int speed=2;
    private Creep currentTarget =null;

    private static final Image image=new Image(Constants.FILE_PATH + "Towers/Mobile/1.png");

    public MobileTower(ProximityVector pos, TargetingMethod targetingMethod, ParticleManager particleManager){
        super(pos,image,150,targetingMethod,1,new Resources(100,100,0),"Tank Tower");
        this.targetingMethod = targetingMethod;
        this.particleManager=particleManager;

    }
    public void update(List<Creep> creeps){
        if(creeps!=null) {
            target(creeps);
            move();
            checkIfCollision();
        }

    }

    public void target(List<Creep> creeps){
        currentTarget =targetingMethod.getTarget(creeps, getPosition(),range);
        if (currentTarget != null) {
            this.setAngle(PointCalculations.getVectorAngle(this.getCenter(), currentTarget.getPosition()));
        }
    }

    public void move(){
        if(currentTarget!=null) {
            ProximityVector newPosition;
            float xLenght = (float) ((Math.cos(Math.toRadians(getAngle())) * speed));
            float yLenght = (float) ((Math.sin(Math.toRadians(getAngle())) * speed));
            newPosition = new ProximityVector(getPosition().x + xLenght, getPosition().y + yLenght);
            setPosition(newPosition);
        }

    }

    public void checkIfCollision(){
        if(currentTarget!=null) {
            if (this.containsPoint(currentTarget.getCenter())) {
                currentTarget.devolve();
            }
        }
    }
    public Projectile createProjectile(){
        return null;
    }

    public Tower getNewUpgrade(){
        return null;
    }
}
