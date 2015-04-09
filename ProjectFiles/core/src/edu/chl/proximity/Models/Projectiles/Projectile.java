package edu.chl.proximity.Models.Projectiles;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.math.Vector2;
import edu.chl.proximity.Models.BoardObject;
import edu.chl.proximity.Models.Image;
import edu.chl.proximity.Utilities.PointCalculations;

import java.awt.Point;

/**
 * Created by Hanna R�mer on 2015-04-02, edited by Johan and Linda
 */
public abstract class Projectile extends BoardObject{
    private ParticleEffect effect;
    private int health;
    private int speed;
    private Sound sound;

    public Projectile(ParticleEffect particleEffect, int health, int speed, Sound sound, Image image, Vector2 position, double angle){
        //position, texture, angle
        super(position, image, angle);
        this.effect=particleEffect;
        this.health=health;
        this.speed=speed;
        this.sound=sound;
    }


    /**
     * Get whether the projectile point intersects another point
     *
     * @param creeppos what other position should be compared
     * @param hitbox how bix the hitbox is (10 wide hitbox would be 5, because)
     * the 5 goes in both directions.
     * @return true if the projectile intersects the given area
     */
    public boolean collidesWith(Vector2 creeppos, int hitbox) {
        if (creeppos.y - hitbox < getPosition().y && getPosition().y < creeppos.y + hitbox) {
            if (creeppos.x - hitbox < getPosition().x && getPosition().x < creeppos.x + hitbox) {
                return true;
            }
        }
        return false;
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
        float xLenght = (float) ((Math.cos(Math.toRadians(getAngle())) * speed)+0.5); //+0.5 to round to correct int aka 0.9 is 1
        float yLenght = (float) ((Math.sin(Math.toRadians(getAngle())) * speed)+0.5);


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
