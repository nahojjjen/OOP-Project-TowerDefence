package edu.chl.proximity.Models.Map.Projectiles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.math.Vector2;
import edu.chl.proximity.Models.Map.Creeps.Creep;
import edu.chl.proximity.Models.Map.Maps.Map;
import edu.chl.proximity.Models.Utils.GameData;
import edu.chl.proximity.Models.Utils.Image;
import edu.chl.proximity.Utilities.Constants;

/**
 * @author Linda Evaldsson and Johan Swanberg
 * @date 2015-04-13
 *
 * A bullet is a simple bullet that has a static angle and kills one creep.
 */
public class Bullet extends Projectile {

    private static Image img = new Image(Constants.filePath + "Projectiles/bullet.png");
    private static Sound sound = Gdx.audio.newSound(new FileHandle(Constants.filePath + "Sounds/poof.ogg"));

    /**
     * create a new bullet projectile
     *
     * @param position where to create the bullet projectile
     * @param angle    what angle the image & movement should start at
     * @param target   what creep the projectile should see it it hits etc.
     */
    public Bullet(Map map, Vector2 position, double angle, Creep target) {
        //Arguments: ProximityEffect particleEffect, int health, int speed, Sound sound, Image image, Vector2 position, double angle, Creep target
        super(map, map.getParticleManager().getBulletEffect(), 1, 20, sound, img, position, angle);
    }

    @Override
    public void reAngle() {//bullets keep their angle    }
    }

    @Override
    public void attack(Creep creep) {
        creep.devolve();
    }

}
