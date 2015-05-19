package test.edu.chl.proximity.Models.Map.Towers;

import edu.chl.proximity.Models.Map.Particles.ParticleManager;
import edu.chl.proximity.Models.Map.Projectiles.Projectile;
import edu.chl.proximity.Models.Map.Towers.SniperTower;
import edu.chl.proximity.Models.Map.Towers.TargetingMethods.TargetClosest;
import edu.chl.proximity.Models.Map.Towers.Tower;
import edu.chl.proximity.Utilities.ProximityVector;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Johan on 2015-05-19.
 */
public class SniperTowerTest {

    @Test (expected = java.lang.ExceptionInInitializerError.class)
    public void testCreateProjectile() throws Exception {
        SniperTower tower = new SniperTower(new ProximityVector(0,0), new TargetClosest(),new ParticleManager());
        Projectile projectile = tower.createProjectile();
        assertTrue(projectile != null);
    }

    @Test
    public void testClone() throws Exception {

        SniperTower tower = new SniperTower(new ProximityVector(0,0), new TargetClosest(),new ParticleManager());
        SniperTower towerCopy = (SniperTower)tower.clone();
        assertTrue(tower.getPosition().equals(towerCopy.getPosition()));
        tower.setPosition(new ProximityVector(1010,0100));
        assertFalse(tower.getPosition().equals(towerCopy.getPosition()));
    }

    @Test
    public void testGetNewUpgrade() throws Exception {

        SniperTower tower = new SniperTower(new ProximityVector(0,0), new TargetClosest(),new ParticleManager());
        Tower tower2 = tower.getNewUpgrade();
        assertTrue(tower2 == null);
    }
}