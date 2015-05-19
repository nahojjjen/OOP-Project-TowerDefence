package edu.chl.proximity.Models.Map.Projectiles;

import edu.chl.proximity.Models.Map.Creeps.ConcreteCreeps.Line1;
import edu.chl.proximity.Models.Map.Creeps.Creep;
import edu.chl.proximity.Models.Map.Particles.ParticleManager;
import edu.chl.proximity.Models.Map.Paths.ConcretePaths.FirstPath;
import edu.chl.proximity.Utilities.ProximityVector;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Johan
 * @date 2015-05-19.
 */
public class MissileTest {

    @Test (expected = IllegalStateException.class)
    public void testReAngle() throws Exception {
        Creep creep = new Line1(1,new ParticleManager(),new FirstPath());
        creep.setPosition(new ProximityVector(200,200));
        Missile missile = new Missile(new ProximityVector(100,100), 0 , creep,new ParticleManager());
        assertTrue(missile.getAngle() == 0);
        missile.reAngle();
        assertTrue(missile.getAngle() ==45); //the missile should have re-angled to face its target

        Missile missile2 = new Missile(new ProximityVector(100,100), 0 , null,new ParticleManager());
        missile2.reAngle(); // tests re-angling to a null position
    }

    @Test
    public void testAttack() throws Exception {
        Creep creep = new Line1(1,new ParticleManager(),new FirstPath());
        //Missile missile = new Missile(new ProximityVector(100,100), 0 , creep,new ParticleManager());
        //assertTrue(missile.getAngle() == 0);
        //missile.reAngle();
        //missile.attack(creep);
        //assertTrue(creep.isRemoved());
    }
}