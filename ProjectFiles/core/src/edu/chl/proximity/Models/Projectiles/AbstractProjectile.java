package edu.chl.proximity.Models.Projectiles;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import edu.chl.proximity.Models.BoardObject;

/**
 * Created by Hanna Römer on 2015-04-02.
 */
public abstract class AbstractProjectile extends BoardObject{
    private ParticleEffect effect;
    private int health;
    private int speed;
    private Sound sound;

    public AbstractProjectile(ParticleEffect particleEffect, int health, int speed, Sound sound){
        this.effect=particleEffect;
        this.health=health;
        this.speed=speed;
        this.sound=sound;
    }

}
