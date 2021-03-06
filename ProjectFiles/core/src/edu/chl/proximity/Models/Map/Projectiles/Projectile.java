package edu.chl.proximity.Models.Map.Projectiles;

import edu.chl.proximity.Models.BoardObject;
import edu.chl.proximity.Models.Map.Creeps.Creep;
import edu.chl.proximity.Models.Map.Particles.ProximityEffect;
import edu.chl.proximity.Models.Utils.Image;
import edu.chl.proximity.Models.Utils.ProximityRandom;
import edu.chl.proximity.Models.Utils.ProximitySound;
import edu.chl.proximity.Utilities.PointCalculations;
import edu.chl.proximity.Utilities.ProximityVector;

/**
 * @author Hanna Romer
 * @date 2015-04-02
 *
 * An abstract class for projectiles. All projectiles extend this class.
 *
 * ---
 * 08/04 modified by Linda Evaldsson. Refactoring to Projectile instead of AbstractProjectile.
 * Unknown date modified by Linda Evaldsson
 * Unknown date modified by Johan Swanberg
 * 04-24 modified by Johan Swanberg, fixed projectile hitbox origin to center
 * 12/05 modified by Linda Evaldsson. Removed Map from constructor.
 *
 *
 */
public abstract class Projectile extends BoardObject implements Cloneable{
    private ProximityEffect effect;
    private int health;
    private int speed;
    private ProximitySound sound;
    private double areaOfEffectRange = 0;
    /**
     * Create a new projectile type
     * @param particleEffect what effect should be played when the bullet hits
     * @param health how many hits can the bullet take before dissapearing
     * @param speed how fast does the bullet travel
     * @param sound what sound does the bullet make on collision
     * @param image what image does the bullet have
     * @param position where should the projectile be created
     * @param angle what angle should the image of the projectile have
     */
    public Projectile(ProximityEffect particleEffect, int health, int speed, ProximitySound sound, Image image, ProximityVector position, double angle){
        super(position, image, angle);
        this.setCenter(position);
        this.effect=particleEffect;
        this.health=health;
        this.speed=speed;
        this.sound=sound;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setAreaOfEffectRange(double areaOfEffectRange) {
        this.areaOfEffectRange = areaOfEffectRange;
    }

    public double getAreaOfEffectSize() {
        return areaOfEffectRange;
    }


    /**
     * readjust the angle so the projectile is facing the current target
     */
    public abstract void reAngle();

    /**
     * Get whether the projectile point intersects another area
     *
     * @param creep what other creep position should be compared
     * @return true if the projectile intersects the given area
     */
    public boolean collidesWith(Creep creep) {
        if (creep != null){
            if (creep.getCenter() != null){
                return (PointCalculations.distanceBetweenNoSqrt(getCenter(), creep.getCenter()) <= 20*20); //radious 10
            }
        }
       return false; // if the creep has no position, it doesnt collide.
    }

    /**
     *  Play all the logic the bullet does on collision.
     */
    public void collide(Creep creep){
        playParticleEffect();
        playSound();
        reAngle();
        decreaseProjectileHealth();
    }

    /**
     * the logic that happens that is specific to this projectile, most often creep.devolve
     */
    public abstract void attack(Creep creep);
    /**
     * play the effect connected to this projectile (example, the explosion effect of the missile, smoke & circles))
     */
    public void playParticleEffect(){
        if (effect != null){
            effect.createEffect((int) getCenter().x, (int) getCenter().y); //display the effect this projectile has
        }
    }

    /**
     * play the sound connected to this projectile, example explosion sound.
     * Plays the sound relative to the creep position and gives a slight random putch
     */
    public void playSound(){
        if (sound != null){
            float xSoundPosition = getXSoundPosition();
            float rndPitch = (float)(ProximityRandom.getRandomDoubleBetween(0.75,1.25));

            sound.play(rndPitch, xSoundPosition);
        }
    }

    /**
     * get where the projectile is x-wise
     * @return a value between -1 and 1, where -1 is furthest to the left, 0 is in the middle of the map and 1 is to the right.
     */
    private float getXSoundPosition(){
        return ((getCenter().x / 800)-0.5f)*2;
        //getCenter/800 returns a value between 0 and 1, 0 if to left and 1 if to right.
        //-0.5 makes the value between -0.5,+0,5, *2 makes it -1,1 -> between left and right ear
    }
    /**
     * decrease the projectiles health by one, remove it if its 0
     */
    private void decreaseProjectileHealth(){
        health--;
        if (health <= 0) {
            this.remove();
        }
    }

    /**
     * Moves the projectile a step forward based on the current angle and speed
     */
    public void move() {
        ProximityVector newPosition;
        float xLenght = (float) ((Math.cos(Math.toRadians(getAngle())) * speed));
        float yLenght = (float) ((Math.sin(Math.toRadians(getAngle())) * speed));
        newPosition = new ProximityVector(getPosition().x + xLenght, getPosition().y + yLenght);
        setPosition(newPosition);

    }

}
