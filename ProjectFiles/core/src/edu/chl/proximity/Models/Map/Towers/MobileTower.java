package edu.chl.proximity.Models.Map.Towers;

import edu.chl.proximity.Models.Map.Creeps.Creep;
import edu.chl.proximity.Models.Map.Particles.ParticleManager;
import edu.chl.proximity.Models.Map.Projectiles.Projectile;
import edu.chl.proximity.Models.Map.Towers.TargetingMethods.TargetingMethod;
import edu.chl.proximity.Models.ResourceSystem.Resources;
import edu.chl.proximity.Models.Utils.Image;
import edu.chl.proximity.Models.Utils.ProximityBatch;
import edu.chl.proximity.Utilities.Constants;
import edu.chl.proximity.Utilities.PointCalculations;
import edu.chl.proximity.Utilities.ProximityRandom;
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

    private int speed=4;
    private Creep currentTarget =null;
    private int counter=20;
    private boolean counterTicking=false;
    private float blade1Rotation = 0.4f;

    private static final Image blades=new Image(Constants.FILE_PATH + "Towers/Mobile/blade.png");
    private static final Image image=new Image(Constants.FILE_PATH + "Towers/Mobile/1.png");

    public MobileTower(ProximityVector pos, TargetingMethod targetingMethod, ParticleManager particleManager){
        super(pos,image,100,targetingMethod,1,new Resources(0,0,1),"Tank Tower");
        this.targetingMethod = targetingMethod;
        this.particleManager=particleManager;
        //make the blade spin a random direction
        if (ProximityRandom.getRandomDoubleBetween(-1,1) > 0){
            blade1Rotation = -0.4f;
        }
    }

    @Override
    public void render(ProximityBatch batch){
        super.render(batch);
        if(blades != null) {
            batch.render(blades, new ProximityVector(getPosition().x-1, getPosition().y-4), blade1Rotation);
            if (this.isPlaced()){
                blade1Rotation += 4;
            }

        }

    }
    public void update(List<Creep> creeps){
        if(creeps!=null) {
            if(counterTicking){
                counter--;
                if(counter==0){
                    counter=60;
                    counterTicking=false;
                }
            }
            target(creeps);
            move();
            checkIfCollision();
        }

    }

    public void target(List<Creep> creeps){
        currentTarget =targetingMethod.getTarget(creeps, getPosition(),range);
        if (currentTarget != null && !counterTicking) {
            this.setAngle(PointCalculations.getVectorAngle(this.getCenter(), currentTarget.getCenter()));
        }else{
            this.setAngle(PointCalculations.getVectorAngle(this.getCenter(), origPos));
        }
    }

    public void move(){
        if(currentTarget!=null && !counterTicking) {
            ProximityVector newPosition;
            float xLenght = (float) ((Math.cos(Math.toRadians(getAngle())) * speed));
            float yLenght = (float) ((Math.sin(Math.toRadians(getAngle())) * speed));
            newPosition = new ProximityVector(getPosition().x + xLenght, getPosition().y + yLenght);
            setPosition(newPosition);
        }else if(Math.abs(this.getCenter().x-origPos.x) > 1 || Math.abs(this.getCenter().y-origPos.y) > 1){
            ProximityVector newPosition;
            float xLenght = (float) ((Math.cos(Math.toRadians(getAngle())) * speed));
            float yLenght = (float) ((Math.sin(Math.toRadians(getAngle())) * speed));
            newPosition = new ProximityVector(getPosition().x + xLenght, getPosition().y + yLenght);
            setPosition(newPosition);
        }
    }

    public void checkIfCollision(){
        if(currentTarget!=null && !counterTicking) {
            if (this.containsPoint(currentTarget.getCenter())){
                currentTarget.devolve();
                counterTicking=true;
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
