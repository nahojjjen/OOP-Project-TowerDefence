package test.edu.chl.proximity.Models.Map.Projectiles;

import edu.chl.proximity.Models.Map.Creeps.ConcreteCreeps.Line1;
import edu.chl.proximity.Models.Map.Creeps.Creep;
import edu.chl.proximity.Models.Map.Particles.ParticleManager;
import edu.chl.proximity.Models.Map.Paths.ConcretePaths.FirstPath;
import edu.chl.proximity.Models.Map.Projectiles.Bullet;
import edu.chl.proximity.Models.Map.Projectiles.Missile3;
import edu.chl.proximity.Models.Map.Projectiles.Projectile;
import edu.chl.proximity.Models.Utils.Settings;
import edu.chl.proximity.Utilities.ProximityVector;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Johan
 * @date 2015-05-19.
 */
public class Missile3Test {

    @Test (expected = IllegalStateException.class)
    public void testReAngle() throws Exception {
        Creep creep = new Line1(1,new ParticleManager(new Settings()),new FirstPath());
        creep.setPosition(new ProximityVector(200,200));
        Missile3 Missile3 = new Missile3(new ProximityVector(100,100), 0 , creep,new ParticleManager(new Settings()));
        assertTrue(Missile3.getAngle() == 0);
        Missile3.reAngle();
        assertTrue(Missile3.getAngle() ==45); //the Missile3 should have re-angled to face its target

        Missile3 Missile32 = new Missile3(new ProximityVector(100,100), 0 , null,new ParticleManager(new Settings()));
        Missile32.reAngle(); // tests re-angling to a null position
    }

    @Test
    public void testAttack() throws Exception {
        Creep creep = new Line1(1,new ParticleManager(new Settings()),new FirstPath());
        Projectile projectile = new Missile3(new ProximityVector(100,100), 0f, creep, new ParticleManager(new Settings()));

        projectile.attack(creep);
        assertTrue(creep.isRemoved());
        projectile.attack(null);
    }
}