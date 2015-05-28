package test.edu.chl.proximity.Models.Map.Towers;

import edu.chl.proximity.Models.Map.Creeps.ConcreteCreeps.Line1;
import edu.chl.proximity.Models.Map.Creeps.Creep;
import edu.chl.proximity.Models.Map.Particles.ParticleManager;
import edu.chl.proximity.Models.Map.Paths.ConcretePaths.FirstPath;
import edu.chl.proximity.Models.Map.Projectiles.Projectile;
import edu.chl.proximity.Models.Map.Towers.*;
import edu.chl.proximity.Models.Map.Towers.TargetingMethods.TargetClosest;
import edu.chl.proximity.Models.Utils.Settings;
import edu.chl.proximity.Utilities.ProximityVector;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author Johan
 * @date 2015-05-24.
 */
public class ShootingTowerTest {

    @Test
    public void testShootingTowers()throws Exception {
        testTower(new BulletTower(new ProximityVector(100,100), new TargetClosest(), new ParticleManager(new Settings())));
        testTower(new BulletTower2(new ProximityVector(100,100), new TargetClosest(), new ParticleManager(new Settings())));
        testTower(new SniperTower(new ProximityVector(100,100), new TargetClosest(), new ParticleManager(new Settings())));
        testTower(new SniperTower2(new ProximityVector(100,100), new TargetClosest(), new ParticleManager(new Settings())));
        testTower(new SniperTower3(new ProximityVector(100,100), new TargetClosest(), new ParticleManager(new Settings())));
        testTower(new MissileTower(new ProximityVector(100, 100), new TargetClosest(), new ParticleManager(new Settings())));
        testTower(new MissileTower2(new ProximityVector(100,100), new TargetClosest(), new ParticleManager(new Settings())));
        testTower(new MissileTower3(new ProximityVector(100,100), new TargetClosest(), new ParticleManager(new Settings())));
        testTower(new SlowTower(new ProximityVector(100,100), new TargetClosest(), new ParticleManager(new Settings())));
        testTower(new SlowTower2(new ProximityVector(100,100), new TargetClosest(), new ParticleManager(new Settings())));


    }

    /**
     * runns all tests on the tower
     * @param tower which tower to test
     * @throws Exception
     */
    private void testTower(ShootingTower tower) throws Exception{
        testCreateProjectile(tower);
        testClone(tower);
        testGetNewUpgrade(tower);
    }

    /**
     * places a test tower on top of a creep, and ansures that something is created from the create projectile method
     * @param tower what tower to test
     * @throws Exception
     */
    private void testCreateProjectile(ShootingTower tower) throws Exception {
        Creep creep = new Line1(10,new ParticleManager(new Settings()), new FirstPath());
        tower.setPosition(new ProximityVector(100,100));
        creep.setPosition(new ProximityVector(100,100));
        List<Creep> creepList = new ArrayList();
        creepList.add(creep);
        tower.target(creepList);
        Projectile projectile = tower.createProjectile();
        assertTrue(projectile != null);
    }

    /**
     * creates a clone, modifies its properties and makes sure that the properties changing in the clone did not
     * affect the original tower
     * @param tower what tower to clone and test
     * @throws Exception
     */
    private void testClone(ShootingTower tower) throws Exception {
        ShootingTower towerCopy = (ShootingTower)tower.clone();

        assertTrue(tower.getPosition().equals(towerCopy.getPosition()));
        tower.setPosition(new ProximityVector(1010, 100));
        assertFalse(tower.getPosition().equals(towerCopy.getPosition()));

        assertTrue(tower.getAngle() == towerCopy.getAngle());
        tower.setAngle(123123);
        assertFalse(tower.getAngle() == towerCopy.getAngle());

        assertTrue(tower.getAddList().equals(towerCopy.getAddList()));
        tower.getAddList().add(new Line1(100, new ParticleManager(new Settings()),new FirstPath()));
        assertFalse(tower.getAddList().size() == towerCopy.getAddList().size());

    }


    /**
     * makes sure the upgrade returns a new type of tower
     * @param tower
     * @throws Exception
     */
    private void testGetNewUpgrade(ShootingTower tower) throws Exception {
        assertTrue(tower.getUpgrade() instanceof Tower || tower.getNewUpgrade() == null); //Findbugs gives a warning here, but we want this functionality

        if (tower.getNewUpgrade() != null){
            assertFalse(tower.getClass().equals(tower.getUpgrade().getClass()));
        }

    }
}