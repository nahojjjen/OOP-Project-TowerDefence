package test.edu.chl.proximity.Models.Map.Towers;

import edu.chl.proximity.Models.Map.Particles.ParticleManager;
import edu.chl.proximity.Models.Map.Projectiles.Projectile;
import edu.chl.proximity.Models.Map.Towers.SlowTower;
import edu.chl.proximity.Models.Map.Towers.TargetingMethods.TargetClosest;
import edu.chl.proximity.Models.Map.Towers.Tower;
import edu.chl.proximity.Models.Utils.Settings;
import edu.chl.proximity.Utilities.ProximityVector;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Johan
 * @date 2015-05-19.
 *
 * pretty much tests setter / getter, but why not
 */
public class SlowTowerTest {

    @Test// (expected = java.lang.ExceptionInInitializerError.class)
    public void testCreateProjectile() throws Exception {
        SlowTower test = new SlowTower( new ProximityVector(0,0), new TargetClosest(), new ParticleManager(new Settings()));
        Projectile projectile = test.createProjectile();
        assertTrue(projectile != null);
    }

    @Test
    public void testGetNewUpgrade() throws Exception {

        SlowTower test = new SlowTower( new ProximityVector(0,0), new TargetClosest(), new ParticleManager(new Settings()));
        Tower tower =test.getNewUpgrade();
        assertTrue(tower != null);
    }
}