package edu.chl.proximity.Models.Projectiles;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.math.Vector2;
import edu.chl.proximity.Models.Creeps.Creep;
import edu.chl.proximity.Models.GameData;
import edu.chl.proximity.Models.Image;
import edu.chl.proximity.Models.Particles.ParticleManager;
import edu.chl.proximity.Utilities.Constants;

import java.awt.*;

/**
 * Created by Floompa on 2015-04-08.
 */
public class Bullet extends Projectile {
    private static Image img = new Image(Constants.filePath + "Projectiles/level1bullet.png");

    /**
     * create a new bullet projectile
     * @param position where to create the bullet projectile
     * @param angle what angle the image & movement should start at
     * @param target what creep the projectile should see it it hits etc.
     */
    public Bullet(Vector2 position, double angle, Creep target) {
        //Arguments: ProximityEffect particleEffect, int health, int speed, Sound sound, Image image, Vector2 position, double angle, Creep target
        super(GameData.getInstance().getMap().getParticleManager().getExplosionEffect(), 1, 3, null, img, position, angle, target);
    }
}
