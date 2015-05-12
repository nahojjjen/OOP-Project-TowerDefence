package edu.chl.proximity.Models.Map.Projectiles;

import com.badlogic.gdx.audio.Sound;
import edu.chl.proximity.Utilities.ProximityVector;
import edu.chl.proximity.Models.BoardObject;
import edu.chl.proximity.Models.Map.Creeps.Creep;
import edu.chl.proximity.Models.Utils.GameData;
import edu.chl.proximity.Models.Utils.Image;
import edu.chl.proximity.Models.Map.Particles.ProximityEffect;
import edu.chl.proximity.Utilities.PointCalculations;
import edu.chl.proximity.Utilities.ProximityRandom;

import java.util.List;

/**
 * @author Hanna Römer
 * @date 2015-04-02
 *
 * 08/04 modified by Linda Evaldsson. Refactoring to Projectile instead of AbstractProjectile.
 * Unknown date modified by Linda Evaldsson
 * Unknown date modified by Johan Swanberg
 * 04-24 modified by Johan Swanberg, fixed projectile hitbox origin to center
 * 12/05 modified by Linda Evaldsson. Removed Map from constructor.
 *
 * An abstract class for projectiles. All projectiles extend this class.
 */
public abstract class Projectile extends BoardObject implements Cloneable{
    private ProximityEffect effect;
    private int health;
    private int speed;
    private Sound sound;
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
    public Projectile(ProximityEffect particleEffect, int health, int speed, Sound sound, Image image, ProximityVector position, double angle){
        super(position, image, angle);
        this.effect=particleEffect;
        this.health=health;
        this.speed=speed;
        this.sound=sound;
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
        return (PointCalculations.distanceBetweenNoSqrt(getCenter(), creep.getCenter()) < 20*20); //radious 10
    }

    /**
     *  Play all the logic the bullet does on collision.
     */
    public void collide(Creep creep){
        playParticleEffect();
        playSound();
        reAngle();
        decreaseProjectileHealth();
        attack(creep);
    }

    public void checkCollision() {
        //Todo: Fix so this is checked by Map instead.
        //BoardObject o = getMap().getObjectOnPosition(getPosition());
        //if(o instanceof Creep)
        //    collide((Creep)o);
                    /*
        List<Creep> creeps = getMap().getCreeps();

        for (Creep creep : creeps){
        if(collidesWith(creep)) {
            collide(creep);
        }
    }*/

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
            float rndPitch = (ProximityRandom.getRandomFloat()/2) + 0.5f; //renturns number between 0.75 and 1.25
            sound.play(GameData.getInstance().getPlayer().getSettings().getGameVolume(), rndPitch, xSoundPosition);
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
        //System.out.println("real x movement:" + (Math.cos(Math.toRadians(angle)) * speed));
        //System.out.println("real y movement:" + (Math.sin(Math.toRadians(angle)) * speed));
        float xLenght = (float) ((Math.cos(Math.toRadians(getAngle())) * speed));
        float yLenght = (float) ((Math.sin(Math.toRadians(getAngle())) * speed));
        //System.out.println("x movement= " + xLenght + " y-momement:" + yLenght);
        newPosition = new ProximityVector(getPosition().x + xLenght, getPosition().y + yLenght);
        setPosition(newPosition);

    }

}
