package test.edu.chl.proximity.Models.Map.Towers;

import edu.chl.proximity.Models.Map.Creeps.ConcreteCreeps.Line1;
import edu.chl.proximity.Models.Map.Creeps.Creep;
import edu.chl.proximity.Models.Map.Particles.ParticleManager;
import edu.chl.proximity.Models.Map.Paths.ConcretePaths.FirstPath;
import edu.chl.proximity.Models.Map.Towers.MobileTower;
import edu.chl.proximity.Models.Map.Towers.TargetingMethods.TargetClosest;
import edu.chl.proximity.Models.Map.Towers.Tower;
import edu.chl.proximity.Models.Utils.Settings;
import edu.chl.proximity.Utilities.ProximityVector;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author simongislen.
 * @date 28/05/15.
 *
 * A test class for MobileTower
 */
public class MobileTowerTest {

    @Test
    public void testUpdate() throws Exception {
        MobileTower tower = new MobileTower(new ProximityVector(100,100),new TargetClosest(), new ParticleManager(new Settings()));

        List<Creep> list = new ArrayList<>();
        Creep creep = new Line1(5, new ParticleManager(new Settings()), new FirstPath());
        creep.setPosition(new ProximityVector(100,100));
        tower.update(list);
        list.add(creep);
        tower.setPosition(new ProximityVector(110, 100));
        tower.update(list);
        assertFalse(tower.getPosition().equals(new ProximityVector(110, 100)));
        for (int i = 0; i<100; i++){
            tower.update(list);
        }
        assertTrue(creep.isRemoved() == true);

    }

    @Test
    public void testTarget() throws Exception {
        MobileTower tower = new MobileTower(new ProximityVector(100,100),new TargetClosest(), new ParticleManager(new Settings()));

        List<Creep> list = new ArrayList<>();
        Creep creep = new Line1(5, new ParticleManager(new Settings()), new FirstPath());
        creep.setPosition(new ProximityVector(100,100));
        tower.target(list);
        double firstAngle = tower.getAngle();
        list.add(creep);

        tower.setPosition(new ProximityVector(110, 100));
        tower.target(list);
       assertTrue(firstAngle != tower.getAngle());

    }

    @Test
    public void testMove() throws Exception {
        MobileTower tower = new MobileTower(new ProximityVector(100,100),new TargetClosest(), new ParticleManager(new Settings()));

        List<Creep> list = new ArrayList<>();
        Creep creep = new Line1(5, new ParticleManager(new Settings()), new FirstPath());
        creep.setPosition(new ProximityVector(100, 100));
        tower.update(list);
        list.add(creep);
        ProximityVector pos1 =  tower.getPosition();
        tower.move();
        tower.preparePlacing(new ProximityVector(new ProximityVector(101,101)));
        assertFalse(pos1.equals(tower.getPosition()));

    }


    @Test
    public void testCreateProjectile() throws Exception {

        MobileTower tower = new MobileTower(new ProximityVector(100,100),new TargetClosest(), new ParticleManager(new Settings()));
        assertTrue(tower.createProjectile() == null);
    }

    @Test
    public void testGetNewUpgrade() throws Exception {
        MobileTower tower = new MobileTower(new ProximityVector(100,100),new TargetClosest(), new ParticleManager(new Settings()));
        assertTrue(tower.getUpgrade() != null);
    }

    @Test
    public void testPreparePlacing() throws Exception {
        MobileTower tower = new MobileTower(new ProximityVector(100,100),new TargetClosest(), new ParticleManager(new Settings()));
        tower.preparePlacing(new ProximityVector(100,100));
        assertTrue(tower.isPlaced());
        assertTrue(tower.getCenter().equals(new ProximityVector(100,100)));
    }
}