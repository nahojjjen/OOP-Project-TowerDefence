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
 */
public class BulletTest {

    @Test
    public void testReAngle() throws Exception {

        Projectile projectile = new Bullet(new ProximityVector(100,100), 0f, null, new ParticleManager(new Settings()));
        double angle = projectile.getAngle();
        projectile.reAngle();
        assertTrue(projectile.getAngle() == angle);
    }

    @Test
    public void testAttack() throws Exception {

        Projectile projectile = new Bullet(new ProximityVector(100,100), 0f, null, new ParticleManager(new Settings()));
        Creep creep = new Line1(1,new ParticleManager(new Settings()),new FirstPath());
        projectile.attack(creep);
        assertTrue(creep.isRemoved());
        projectile.attack(null);
    }
}