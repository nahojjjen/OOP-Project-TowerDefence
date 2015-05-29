package edu.chl.proximity.Models.Map.Towers;

import edu.chl.proximity.Models.Map.Creeps.Creep;
import edu.chl.proximity.Models.Map.Particles.ParticleManager;
import edu.chl.proximity.Models.Map.Particles.ProximityEffect;
import edu.chl.proximity.Models.Map.Projectiles.Projectile;
import edu.chl.proximity.Models.Map.Towers.TargetingMethods.TargetingMethod;
import edu.chl.proximity.Models.ResourceSystem.Resources;
import edu.chl.proximity.Models.Utils.Image;
import edu.chl.proximity.Models.Utils.ProximityBatch;
import edu.chl.proximity.Utilities.Constants;
import edu.chl.proximity.Utilities.PointCalculations;
import edu.chl.proximity.Models.Utils.ProximityRandom;
import edu.chl.proximity.Utilities.ProximityVector;

import java.util.List;

/**
 * @author Hanna Romer
 * @date 2015-05-23
 *
 * Respresents a mobile Tower that chases the enemies and drives over them to damage them.
 *
 * 28/05 modified by Hanna Romer. Added comments.
 */
public class MobileTower extends ShootingTower{
    private TargetingMethod targetingMethod;
    private ProximityVector origPos;

    private int speed=4;
    private Creep currentTarget =null;
    private int counter=20;
    private boolean counterTicking=false;
    private float blade1Rotation = 0;
    private boolean reverseBlade = false;

    private static final Image blades=new Image(Constants.FILE_PATH + "Towers/Mobile/blade.png");
    private static final Image image=new Image(Constants.FILE_PATH + "Towers/Mobile/1.png");

    /**
     * Create a new mobile tower
     * @param pos Starting position of mobile-tower
     * @param targetingMethod what targeting method the tower should employ
     * @param particleManager what particle manager the tower should use.
     */
    public MobileTower(ProximityVector pos, TargetingMethod targetingMethod, ParticleManager particleManager){
        super(pos,image,100,targetingMethod,1,new Resources(0,0,1),"Tank Tower");
        this.targetingMethod = targetingMethod;
        setParticleManager(particleManager);

    }

    @Override
    public void render(ProximityBatch batch){
        super.render(batch);
        if(blades != null) {
            batch.render(blades, new ProximityVector(getPosition().x-1, getPosition().y-4), blade1Rotation);
            if (this.isPlaced()){
                if (reverseBlade){
                    blade1Rotation += 3;
                }else{
                    blade1Rotation-=3;
                }

            }

        }
    }

    /**
     * Updates the tower
     * @param creeps list of creeps currently on map during this update
     */
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

    /**
     * Targets a creep using the tower's targeting method
     * @param creeps list of creeps to choose a taget from
     */
    public void target(List<Creep> creeps){
        currentTarget = targetingMethod.getTarget(creeps, getPosition(),range);
        if (currentTarget != null && !counterTicking) {
            this.setAngle(PointCalculations.getVectorAngle(this.getCenter(), currentTarget.getCenter()));
        }else{
            this.setAngle(PointCalculations.getVectorAngle(this.getCenter(), origPos));
        }
    }

    /**
     * Moves the tower towards its target.
     * If target==null or the tower has killed a target within its "reload time" then its target is its original position.
     */
    public void move(){
        if(currentTarget!=null && !counterTicking) {
            ProximityVector newPosition;
            float xLenght = (float) ((Math.cos(Math.toRadians(getAngle())) * speed));
            float yLenght = (float) ((Math.sin(Math.toRadians(getAngle())) * speed));
            newPosition = new ProximityVector(getPosition().x + xLenght, getPosition().y + yLenght);
            setPosition(newPosition);

        }
        if(this.getPosition() != null && origPos != null){
            if((Math.abs(this.getCenter().x-origPos.x) > 2 || Math.abs(this.getCenter().y-origPos.y) > 2)){
                ProximityVector newPosition;
                float xLenght = (float) ((Math.cos(Math.toRadians(getAngle())) * speed));
                float yLenght = (float) ((Math.sin(Math.toRadians(getAngle())) * speed));
                newPosition = new ProximityVector(getPosition().x + xLenght, getPosition().y + yLenght);
                setPosition(newPosition);
            }
        }
    }

    /**
     * Checks if the tower has collided with a creep.
     * If its "reload time" is not done yet nothing happens
     * Otherwise the creep is devolved and the counter for the towers "reload time" has started.
     */
    public void checkIfCollision(){
        if (currentTarget != null) {
            if (!counterTicking) {
                if (this.containsPoint(currentTarget.getCenter())) {
                    currentTarget.devolve();
                    counterTicking = true;
                    ProximityEffect effect = getParticleManager().getBloodPoolCreepEffect();
                    if (effect != null) {
                        effect.createEffect(currentTarget.getCenter());
                    }
                }
            }
        }
    }
    public Projectile createProjectile(){
        return null;
    }

    public Tower getNewUpgrade(){
        return new MobileTower2(this.getPosition(),this.targetingMethod,getParticleManager());

    }
    public String getDescription() {
        return super.getDescription() + "\n " +
                "A tower which kills creeps by running them over.";
    }
    @Override
    public void preparePlacing(ProximityVector position) {
        if (position == null) throw new IllegalArgumentException();
        this.setCenter(position);
        origPos=position;
        super.setAsPlaced(true);

        //make the blade spin a random direction
        if (ProximityRandom.getRandomDouble() > 0.5){
            reverseBlade = true;
        }
    }
}
