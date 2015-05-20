package test.edu.chl.proximity.Models.Map.Towers;

import edu.chl.proximity.Models.Map.Creeps.ConcreteCreeps.Line1;
import edu.chl.proximity.Models.Map.Creeps.Creep;
import edu.chl.proximity.Models.Map.Particles.ParticleManager;
import edu.chl.proximity.Models.Map.Paths.ConcretePaths.FirstPath;
import edu.chl.proximity.Models.Map.Projectiles.Projectile;
import edu.chl.proximity.Models.Map.Towers.SniperTower;
import edu.chl.proximity.Models.Map.Towers.TargetingMethods.TargetClosest;
import edu.chl.proximity.Models.Map.Towers.Tower;
import edu.chl.proximity.Models.Utils.Settings;
import edu.chl.proximity.Utilities.ProximityVector;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Johan on 2015-05-19.
 */
public class SniperTowerTest {

    @Test //(expected = java.lang.ExceptionInInitializerError.class)
    public void testCreateProjectile() throws Exception {
        SniperTower tower = new SniperTower(new ProximityVector(0,0), new TargetClosest(),new ParticleManager(new Settings()));
        List<Creep> list = new ArrayList<Creep>();
        list.add(new Line1(4, new ParticleManager(new Settings()), new FirstPath()));
        tower.update(list);
        Projectile projectile = tower.createProjectile();
        assertTrue(projectile != null);
    }

    @Test
    public void testClone() throws Exception {

        SniperTower tower = new SniperTower(new ProximityVector(0,0), new TargetClosest(),new ParticleManager(new Settings()));
        SniperTower towerCopy = (SniperTower)tower.clone();
        assertTrue(tower.getPosition().equals(towerCopy.getPosition()));
        tower.setPosition(new ProximityVector(1010,0100));
        assertFalse(tower.getPosition().equals(towerCopy.getPosition()));
    }

    @Test
    public void testGetNewUpgrade() throws Exception {

        SniperTower tower = new SniperTower(new ProximityVector(0,0), new TargetClosest(),new ParticleManager(new Settings()));
        Tower tower2 = tower.getNewUpgrade();
        assertTrue(tower2 == null);
    }
}