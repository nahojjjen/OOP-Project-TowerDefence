package edu.chl.proximity.Models.Projectiles;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import edu.chl.proximity.Models.BoardObject;
import edu.chl.proximity.Utilities.PointCalculations;

import java.awt.*;

/**
 * Created by Hanna Römer on 2015-04-02, edited by Johan and Linda
 */
public abstract class AbstractProjectile extends BoardObject{
    private ParticleEffect effect;
    private int health;
    private int speed;
    private Sound sound;

    public AbstractProjectile(ParticleEffect particleEffect, int health, int speed, Sound sound, Texture texture, Point position, double angle){
        //position, texture, angle
        super(position, texture, angle);
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
    public boolean collidesWith(Point creeppos, int hitbox) {
        if (creeppos.getY() - hitbox < getPosition().getY() && getPosition().getY() < creeppos.getY() + hitbox) {
            if (creeppos.getX() - hitbox < getPosition().getX() && getPosition().getX() < creeppos.getX() + hitbox) {
                return true;
            }
        }
        return false;
    }

    /**
     * Moves the projectile a step forward based on the current angle and speed
     */
    public void move() {
        Point newPosition;
        /*
        System.out.println("real x movement:" + (Math.cos(Math.toRadians(angle)) * speed));
        System.out.println("real y movement:" + (Math.sin(Math.toRadians(angle)) * speed));
        */
        int xLenght = (int) ((Math.cos(Math.toRadians(getAngle())) * speed)+0.5); //+0.5 to round to correct int aka 0.9 is 1
        int yLenght = (int) ((Math.sin(Math.toRadians(getAngle())) * speed)+0.5);


        //System.out.println("x movement= " + xLenght + " y-momement:" + yLenght);
        newPosition = new Point((int)getPosition().getX() + xLenght, (int)getPosition().getY() + yLenght);
        setPosition(newPosition);
    }



    /**
     * /TODO make destroy summon particle effects of destruciton
     */
    public void destroy() {
        System.out.println("destroyed projectile?");
    }



}
