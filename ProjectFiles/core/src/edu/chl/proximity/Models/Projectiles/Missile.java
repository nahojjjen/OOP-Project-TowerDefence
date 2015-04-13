package edu.chl.proximity.Models.Projectiles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.math.Vector2;
import edu.chl.proximity.Models.Creeps.Creep;
import edu.chl.proximity.Models.GameData;
import edu.chl.proximity.Models.Image;
import edu.chl.proximity.Utilities.Constants;
import edu.chl.proximity.Utilities.PointCalculations;

import java.util.Iterator;
import java.util.List;

/**
 * Created by Linda on 2015-04-08. Edited by Johan
 */
public class Missile extends Projectile {

    private static Image img = new Image(Constants.filePath + "Projectiles/missile.png");
    private static Sound sound = Gdx.audio.newSound(new FileHandle(Constants.filePath + "Sounds/poof.ogg"));
    private double range = 40;
    private Creep target;

    /**
     * create a new bullet projectile
     * @param position where to create the bullet projectile
     * @param angle what angle the image & movement should start at
     * @param target what creep the projectile should see it it hits etc.
     */
    public Missile(Vector2 position, double angle, Creep target) {
        //Arguments: ProximityEffect particleEffect, int health, int speed, Sound sound, Image image, Vector2 position, double angle, Creep target
        super(GameData.getInstance().getMap().getParticleManager().getExplosionEffect(), 1, 8, sound, img, position, angle);
        this.target = target;
    }

    @Override
    public void reAngle() {
            if (target != null){
                faceTarget(target.getPosition());
            }else{
                System.out.println("In projectile: trying to reAngle to a target that doesnt exist");
            }
    }

    @Override
    public void attack(Creep unusedCreep) {
        List<Creep> creepList = GameData.getInstance().getMap().getCreeps();
        for(Creep creep: creepList) {
            if(PointCalculations.distanceBetweenNoSqrt(this.getCenter(), creep.getCenter()) < range*range){
                creep.devolve();
            }
        }


    }
}
