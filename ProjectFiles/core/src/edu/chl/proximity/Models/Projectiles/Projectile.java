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
import edu.chl.proximity.Utilities.PointCalculations;

import java.awt.Point;
import java.util.Iterator;

/**
 * Created by Hanna Römer on 2015-04-02, edited by Johan and Linda
 */
public abstract class Projectile extends BoardObject{
    private ParticleEffect effect;
    private int health;
    private int speed;
    private Sound sound;
    private Creep target;

    public Projectile(ParticleEffect particleEffect, int health, int speed, Sound sound, Image image, Vector2 position, double angle, Creep target){
        //position, texture, angle
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
        faceTarget(target.getPosition());
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
        if (creeppos.y+20 - hitbox < getPosition().y && getPosition().y < creeppos.y+20 + hitbox) {
            if (creeppos.x+20 - hitbox < getPosition().x && getPosition().x < creeppos.x+20 + hitbox) {
                return true;
            }
        }
        return false;
    }

    public void checkCollision(Iterator projectileIterator){
        Map map = GameData.getInstance().getMap();
        if(collidesWith(target.getPosition(), 20)){
            map.getParticleManager().getExplosionEffect().createEffect((int) getPosition().x, (int) getPosition().y);

            map.getCreeps().remove(target);

            map.getParticleManager().getCreepDiesEffect().createEffect(target.getPosition().x, target.getPosition().y);
            projectileIterator.remove();
            //map.getProjectiles().remove(this);
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
