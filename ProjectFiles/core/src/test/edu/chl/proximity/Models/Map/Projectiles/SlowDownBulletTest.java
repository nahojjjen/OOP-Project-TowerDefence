package test.edu.chl.proximity.Models.Map.Projectiles;

import edu.chl.proximity.Models.Map.Creeps.ConcreteCreeps.Line1;
import edu.chl.proximity.Models.Map.Creeps.Creep;
import edu.chl.proximity.Models.Map.Particles.ParticleManager;
import edu.chl.proximity.Models.Map.Paths.ConcretePaths.FirstPath;
import edu.chl.proximity.Models.Map.Projectiles.Projectile;
import edu.chl.proximity.Models.Map.Projectiles.SlowDownBullet;
import edu.chl.proximity.Models.Utils.Settings;
import edu.chl.proximity.Utilities.ProximityVector;
import org.junit.Test;

import static org.junit.Assert.*;


/**
 * @author Johan
 * @date 2015-05-24.
 */
public class SlowDownBulletTest {


    @Test (expected = IllegalStateException.class)
    public void testReAngle() throws Exception {
        Creep creep = new Line1(1,new ParticleManager(new Settings()),new FirstPath());
        Projectile projectile = new SlowDownBullet(new ProximityVector(100,100), 0f, creep,50,50, new ParticleManager(new Settings()));
        double angle = projectile.getAngle();
        projectile.reAngle();
        assertTrue(projectile.getAngle() != angle);

        Projectile projectile2 = new SlowDownBullet(new ProximityVector(100,100), 0f, null,50,50, new ParticleManager(new Settings()));

        projectile2.reAngle();
    }

    @Test
    public void testAttack() throws Exception {

        Projectile projectile = new SlowDownBullet(new ProximityVector(100,100), 0f, null,50,50, new ParticleManager(new Settings()));
        Creep creep = new Line1(1,new ParticleManager(new Settings()),new FirstPath());
        double creepSpeed = creep.getSpeed();
        projectile.attack(creep);

        assertFalse(creep.isRemoved());
        assertTrue(creep.getSpeed()< creepSpeed);
        projectile.attack(null);
    }
}