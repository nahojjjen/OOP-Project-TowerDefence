package edu.chl.proximity.Models.Projectiles;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import edu.chl.proximity.Models.Image;
import edu.chl.proximity.Utilities.Constants;

import java.awt.*;

/**
 * Created by Floompa on 2015-04-08.
 */
public class Bullet extends Projectile {
    private static Image img = new Image(Constants.filePath + "Projectiles/level1bullet.png");

    public Bullet(Point position, double angle) {
        super(null, 1, 3, null, img, position, angle);
    }
}
