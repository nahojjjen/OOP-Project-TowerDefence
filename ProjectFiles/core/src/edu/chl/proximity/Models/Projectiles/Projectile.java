package edu.chl.proximity.Models.Projectiles;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.math.Vector2;
import edu.chl.proximity.Models.BoardObject;
import edu.chl.proximity.Models.Creeps.Creep;
import edu.chl.proximity.Models.GameData;
import edu.chl.proximity.Models.Image;
import edu.chl.proximity.Models.Maps.Map;
import edu.chl.proximity.Models.Particles.ProximityEffect;
import edu.chl.proximity.Utilities.PointCalculations;

import java.awt.Point;
import java.util.Iterator;

/**
 * Created by Hanna Römer on 2015-04-02, edited by Johan and Linda
 */
public abstract class Projectile extends BoardObject{
    private ProximityEffect effect;
    private int health;
    private int speed;
    private Sound sound;
    private Creep target;

    /**
     * Create a new projectile type
     * @param particleEffect what effect should be played when the bullet hits
     * @param health how many hits can the bullet take before dissapearing
     * @param speed how fast does the bullet travel
     * @param sound what sound does the bullet make on collision
     * @param image what image does the bullet have
     * @param position where should the projectile be created
     * @param angle what angle should the image of the projectile have
     * @param target what target should the projectile check for
     */
    public Projectile(ProximityEffect particleEffect, int health, int speed, Sound sound, Image image, Vector2 position, double angle, Creep target){
        super(position, image, angle);
        this.effect=particleEffect;
        this.health=health;
        this.speed=speed;
        this.sound=sound;
        this.target = target;
    }

    /**
     * readjust the angle so the projectile is facing the current target
     */
    public void reAngle(){
        if (target != null){
            faceTarget(target.getPosition());
        }else{
            System.out.println("In projectile: trying to reAngle to a target that doesnt exist");
        }

    }

    /**
     * Get whether the projectile point intersects another object
     *
     * @param creeppos what other position should be compared
     * @param hitbox how big the hitbox the projectile should check for. (the hitbox of the creep)
     * @return true if the projectile intersects the given area
     */
    public boolean collidesWith(Vector2 creeppos, int hitbox) {
        //+20 because the creep image is 40x40 pixels, and we want to check for the center of the image
        if (creeppos.y+20 - hitbox < getPosition().y && getPosition().y < creeppos.y+20 + hitbox) {
            if (creeppos.x+20 - hitbox < getPosition().x && getPosition().x < creeppos.x+20 + hitbox) {
                return true;
            }
        }
        return false;
    }

    /**
     * This method is run every frame to see if the projectile should run its collision logic.
     * @param projectileIterator the iterator so that if the projectile needs to be removed, it can be removed without
     *                           causing a concurentModificationException in the ProjectileController.
     */
    public void checkCollision(Iterator projectileIterator){
        Map map = GameData.getInstance().getMap();
        if(collidesWith(target.getPosition(), 20)){
            effect.createEffect((int) getPosition().x, (int) getPosition().y); //display the effect this projectile has
            target.devolve();//devolve the target one step on collision
            decreaseProjectileHealth(projectileIterator);
            //TODO if the target is hit, then how do we decide the new target? Depends on the type of projectile, should the projectile have a movement method?
            target = null;
            reAngle();
            //map.getProjectiles().remove(this);
        }
    }

    /**
     * decrease the projectiles health by one, remove it if its 0
     * @param iterator the iterator that should remove the projectile from the map if projectile health is 0
     */
    private void decreaseProjectileHealth(Iterator iterator){
        health--;
        if (health <= 0){
            iterator.remove();
        }

    }

    /**
     * Moves the projectile a step forward based on the current angle and speed
     */
    public void move() {
        Vector2 newPosition;
        /*
        System.out.println("real x movement:" + (Math.cos(Math.toRadians(angle)) * speed));
        System.out.println("real y movement:" + (Math.sin(Math.toRadians(angle)) * speed));
        */
        float xLenght = (float) ((Math.cos(Math.toRadians(getAngle())) * speed)); //+0.5 to round to correct int aka 0.9 is 1
        float yLenght = (float) ((Math.sin(Math.toRadians(getAngle())) * speed));


        //System.out.println("x movement= " + xLenght + " y-momement:" + yLenght);
        newPosition = new Vector2(getPosition().x + xLenght, getPosition().y + yLenght);
        setPosition(newPosition);
    }



    /**
     * /TODO make destroy summon particle effects of destruciton
     */
    public void destroy() {
        System.out.println("destroyed projectile?");
    }



}
