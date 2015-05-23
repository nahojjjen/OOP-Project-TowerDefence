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
    private ProximityVector origPos;

    private int speed=2;
    private Creep currentTarget =null;

    private static final Image image=new Image(Constants.FILE_PATH + "Towers/Mobile/1.png");

    public MobileTower(ProximityVector pos, TargetingMethod targetingMethod, ParticleManager particleManager){
        super(pos,image,100,targetingMethod,1,new Resources(100,100,0),"Tank Tower");
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
            this.setAngle(PointCalculations.getVectorAngle(this.getCenter(), currentTarget.getCenter()));
        }else{
            this.setAngle(PointCalculations.getVectorAngle(this.getCenter(), origPos));
        }
    }

    public void move(){
        if(currentTarget!=null) {
            ProximityVector newPosition;
            float xLenght = (float) ((Math.cos(Math.toRadians(getAngle())) * speed));
            float yLenght = (float) ((Math.sin(Math.toRadians(getAngle())) * speed));
            newPosition = new ProximityVector(getPosition().x + xLenght, getPosition().y + yLenght);
            setPosition(newPosition);
        }else if(Math.abs(this.getCenter().x-origPos.x) > 2 || Math.abs(this.getCenter().y-origPos.y) > 2){
            ProximityVector newPosition;
            float xLenght = (float) ((Math.cos(Math.toRadians(getAngle())) * speed));
            float yLenght = (float) ((Math.sin(Math.toRadians(getAngle())) * speed));
            newPosition = new ProximityVector(getPosition().x + xLenght, getPosition().y + yLenght);
            setPosition(newPosition);
        }
    }

    public void checkIfCollision(){
        if(currentTarget!=null) {
            if (this.containsPoint(currentTarget.getCenter())){
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

    @Override
    public void preparePlacing(ProximityVector position) {
        if (position == null) throw new IllegalArgumentException();
        this.setCenter(position);
        origPos=position;
        super.setAsPlaced(true);
    }
}
