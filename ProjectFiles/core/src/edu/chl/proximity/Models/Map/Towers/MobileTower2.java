package edu.chl.proximity.Models.Map.Towers;

import edu.chl.proximity.Models.Map.Creeps.Creep;
import edu.chl.proximity.Models.Map.Particles.ParticleManager;
import edu.chl.proximity.Models.Map.Projectiles.Missile;
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
 * @date 2015-05-26.
 *
 * Represents an upgraded version of mobileTower, that chases the enemies and drives over them and shoots missiles at them to damage them.
 *
 * 28/05 modified by Hanna Romer. Added comments.
 */
public class MobileTower2 extends ShootingTower{

    //Tower stats
    private static Resources resources = new Resources(100, 100, 5);
    private static double range = 180f;
    private static int reloadTime = 60;

    private int counter=20;
    private boolean counterTicking=false;
    private float blade1Rotation = 0;
    private boolean reverseBlade = false;

    private int speed=5;

    private ProximityVector origPos;

    private static Image img = new Image(Constants.FILE_PATH + "Towers/Mobile/1.png");
    private static Image blades = new Image(Constants.FILE_PATH+"Towers/Mobile/blade2.png");

    /**
     * Create a new upgraded mobile tower.
     * @param pos Position for tower to start at
     * @param targetingMethod Targeting method the tower should employ
     * @param particleManager Particle manager the ower should use.
     */
    public MobileTower2(ProximityVector pos, TargetingMethod targetingMethod, ParticleManager particleManager) {
        super(pos, img, range, targetingMethod, reloadTime, resources, "Warfare Tank");
        setParticleManager(particleManager);
        origPos=this.getCenter();
        if (ProximityRandom.getRandomDouble() > 0.5){
            reverseBlade = true;
        }

    }

    /**
     * Updates the tower
     * @param creeps list of creeps currently on map during this update
     */
    @Override
    public void update(List<Creep> creeps){
        super.update(creeps);
        if (counterTicking) {
            counter--;
            if (counter == 0) {
                counter = 60;
                counterTicking = false;
            }
        }
        target(creeps);
        move();
        checkIfCollision();

    }


    @Override
    public Projectile createProjectile() {
        if (getTarget() != null){
            return new Missile(getCenter(), PointCalculations.getVectorAngle(getPosition(),getTarget().getPosition()), getTarget(), getParticleManager());
        }
        return null;
    }

    @Override
    public Object clone() throws CloneNotSupportedException{
        return super.clone();
    }
    public Tower getNewUpgrade() {
        return null;
    }

    /**
     * Targets a creep using the tower's targeting method
     * @param creeps list of creeps to choose a taget from
     */
    public void target(List<Creep> creeps) {

        super.setTarget(super.getTargetingMethod().getTarget(creeps, getPosition(),range));
        if (super.getTarget() != null && !counterTicking) {
            this.setAngle(PointCalculations.getVectorAngle(this.getCenter(), super.getTarget().getCenter()));
        }else{
            this.setAngle(PointCalculations.getVectorAngle(this.getCenter(), origPos));
        }
    }

    /**
     * Moves the tower towards its target.
     * If target==null or the tower has killed a target within its "reload time" then its target is its original position.
     */
    public void move(){
        if(super.getTarget()!=null && !counterTicking) {
            ProximityVector newPosition;
            float xLenght = (float) ((Math.cos(Math.toRadians(getAngle())) * speed));
            float yLenght = (float) ((Math.sin(Math.toRadians(getAngle())) * speed));
            newPosition = new ProximityVector(getPosition().x + xLenght, getPosition().y + yLenght);
            setPosition(newPosition);
        }else if(Math.abs(this.getCenter().x-origPos.x) > 3 || Math.abs(this.getCenter().y-origPos.y) > 3){
            ProximityVector newPosition;
            float xLenght = (float) ((Math.cos(Math.toRadians(getAngle())) * speed));
            float yLenght = (float) ((Math.sin(Math.toRadians(getAngle())) * speed));
            newPosition = new ProximityVector(getPosition().x + xLenght, getPosition().y + yLenght);
            setPosition(newPosition);
        }
    }

    /**
     * Checks if the tower has collided with a creep.
     * If its "reload time" is not done yet nothing happens
     * Otherwise the creep is devolved and the counter for the towers "reload time" has started.
     */
    public void checkIfCollision(){
        if(super.getTarget()!=null && !counterTicking) {
            if (this.containsPoint(super.getTarget().getCenter())) {
                super.getTarget().devolve();
                counterTicking=true;
                super.getParticleManager().getBloodPoolCreepEffect().createEffect(super.getTarget().getCenter());
            }
        }
    }
    public String getDescription() {
        return super.getDescription() + "\n " +
                "Gives the tank a missile launcher, because why not?.";
    }
    @Override
    public void render(ProximityBatch batch){
        super.render(batch);
        if(blades != null) {
            batch.render(blades, new ProximityVector(getPosition().x-1, getPosition().y-4), blade1Rotation);
            if (this.isPlaced()){
                if (reverseBlade){
                    blade1Rotation += 6;
                }else{
                    blade1Rotation-=6;
                }

            }

        }

    }
}
