package test.edu.chl.proximity.Models.Map.Projectiles;

import edu.chl.proximity.Models.Map.Creeps.ConcreteCreeps.Line1;
import edu.chl.proximity.Models.Map.Creeps.Creep;
import edu.chl.proximity.Models.Map.Particles.ParticleManager;
import edu.chl.proximity.Models.Map.Paths.ConcretePaths.FirstPath;
import edu.chl.proximity.Models.Map.Projectiles.Bullet;
import edu.chl.proximity.Models.Map.Projectiles.Projectile;
import edu.chl.proximity.Models.Utils.Settings;
import edu.chl.proximity.Utilities.ProximityVector;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Johan
 * @date 2015-05-24.
 * A test class for the abstract class Projectile
 *
 */
public class ProjectileTest {

    @Test
    public void testSetHealthAndCollide() throws Exception {

        Projectile projectile = new Bullet(new ProximityVector(100,100), 0f, null,  new ParticleManager(new Settings()));
        projectile.setHealth(2);
        assertFalse(projectile.isRemoved());
        projectile.collide(null); //causes bullet to lose 1 health
        assertFalse(projectile.isRemoved());
        projectile.collide(null);
        assertTrue(projectile.isRemoved());
    }

    @Test
    public void testSetAndGetRange() throws Exception {

        Projectile projectile = new Bullet(new ProximityVector(100,100), 0f, null,  new ParticleManager(new Settings()));
        projectile.setRange(5);
        assertTrue(projectile.getRange() == 5);
    }
    @Test
    public void testCollidesWith() throws Exception {

        Projectile projectile = new Bullet(new ProximityVector(100,100), 0f, null, new ParticleManager(new Settings()));
        Creep creep = new Line1(1,new ParticleManager(new Settings()),new FirstPath());
        ProximityVector position = new ProximityVector(100,100);
        projectile.setPosition(position);
        creep.setPosition(position);
        assertTrue(projectile.collidesWith(creep));
        creep.setPosition(new ProximityVector(80, 100));
        assertTrue(projectile.collidesWith(creep));
        creep.setPosition(new ProximityVector(79, 100));
        assertFalse(projectile.collidesWith(creep));
        creep.setPosition(null);
        assertFalse(projectile.collidesWith(creep));
    }



    @Test
    public void testPlayParticleEffect() throws Exception {

        Projectile projectile = new Bullet(new ProximityVector(100,100), 0f, null, new ParticleManager(new Settings()));
        projectile.playParticleEffect();
    }

    @Test
    public void testPlaySound() throws Exception {

        Projectile projectile = new Bullet(new ProximityVector(100,100), 0f, null, new ParticleManager(new Settings()));
        projectile.playSound();
    }

    @Test
    public void testMove() throws Exception {

        Projectile projectile = new Bullet(new ProximityVector(100,100), 0f, null, new ParticleManager(new Settings()));
        ProximityVector firstPosition = projectile.getPosition();
        projectile.move();
        ProximityVector secondPosition = projectile.getPosition();
        assertFalse(firstPosition.equals(secondPosition));
    }
}