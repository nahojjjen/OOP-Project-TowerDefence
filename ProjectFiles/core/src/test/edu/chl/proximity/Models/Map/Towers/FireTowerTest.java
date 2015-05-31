package test.edu.chl.proximity.Models.Map.Towers;

import edu.chl.proximity.Models.Map.Creeps.ConcreteCreeps.Line2;
import edu.chl.proximity.Models.Map.Creeps.Creep;
import edu.chl.proximity.Models.Map.Particles.ParticleManager;
import edu.chl.proximity.Models.Map.Paths.ConcretePaths.FirstPath;
import edu.chl.proximity.Models.Map.Towers.FireTower;
import edu.chl.proximity.Models.Map.Towers.TargetingMethods.TargetClosest;
import edu.chl.proximity.Models.Map.Towers.Tower;
import edu.chl.proximity.Models.Utils.Settings;
import edu.chl.proximity.Utilities.ProximityVector;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author Johan
 * @date 2015-05-26.
 */
public class FireTowerTest {


    @Test
    public void testUpdateAndShoot() throws Exception {
        FireTower tower = new FireTower(new ProximityVector(0,0), new TargetClosest(), new ParticleManager(new Settings()));
        tower.setPosition(new ProximityVector(100, 100));
        List<Creep> creepList = null;
        tower.update(creepList); //a method that just calls the shoot method
        creepList = new ArrayList<Creep>();
        tower.update(creepList);
        Creep creep = new Line2(6,new ParticleManager(new Settings()), new FirstPath());
        creep.setPosition(new ProximityVector(1000,1000)); //creep is out of range, should not have caused it to fire
        creepList.add(creep);
        tower.update(creepList);
        assertTrue(!creep.isRemoved());
        creep.setPosition(tower.getPosition());
        tower.update(creepList);
        assertTrue(creep.isRemoved()); //now that the tower has fired close to the creep, the creep should be marked for deletion
    }

    @Test
    public void testGetNewUpgrade() throws Exception {
        FireTower tower = new FireTower(new ProximityVector(0,0), new TargetClosest(), new ParticleManager(new Settings()));
        assertTrue(tower.getNewUpgrade() == null);
    }

    @Test
    public void testGetRange() throws Exception {
        FireTower tower = new FireTower(new ProximityVector(0,0), new TargetClosest(), new ParticleManager(new Settings()));
        assertTrue(tower.getRange() > 0);
    }
}