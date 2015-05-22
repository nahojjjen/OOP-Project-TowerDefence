package test.edu.chl.proximity.Models.Map.Towers;

import edu.chl.proximity.Models.Map.Creeps.ConcreteCreeps.Line1;
import edu.chl.proximity.Models.Map.Creeps.Creep;
import edu.chl.proximity.Models.Map.Particles.ParticleManager;
import edu.chl.proximity.Models.Map.Paths.ConcretePaths.FirstPath;
import edu.chl.proximity.Models.Map.Towers.*;
import edu.chl.proximity.Models.Map.Towers.TargetingMethods.TargetClosest;
import edu.chl.proximity.Models.Player.Factions.ConcreteFactions.Filler;
import edu.chl.proximity.Models.Player.Players.Player;
import edu.chl.proximity.Models.Player.Players.GameData;
import edu.chl.proximity.Models.Utils.Settings;
import edu.chl.proximity.Utilities.ProximityVector;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @authoor Johan
 * @date 2015-05-19.
 */
public class TowerTest {



    @Test
    public void testGetName() throws Exception {
     Tower  tower = new BulletTower(new ProximityVector(0,0), new TargetClosest(), new ParticleManager(new Settings()));
    assertTrue(tower.getName().equals("Bullet Tower"));
    }

    @Test//(expected=java.lang.NoClassDefFoundError.class) // will attempt to create a projectile, but will fail
    public void testUpdate() throws Exception {
        //update will do target, shoot, reload

        Tower  tower = new BulletTower(new ProximityVector(0,0), new TargetClosest(), new ParticleManager(new Settings()));
        GameData.getInstance();
        GameData.getInstance().setPlayer(new Player(new Filler()));

        tower.update(null);
        List<Creep> testCreepList = new ArrayList<Creep>();
        tower.update(testCreepList); // should not fire, no creeps nearby
        tower.setPosition(new FirstPath().getWaypoint(0)); // place the test tower on top of where the creep will spawn
        testCreepList.add(new Line1(1, new ParticleManager(new Settings()), new FirstPath()));
        tower.update(testCreepList); // should fire a projectile and start the cooldown
        assertTrue(tower.getAddList().size() == 1); //make sure that the prjectiile has been marked for adding

        testCreepList.add(new Line1(1, new ParticleManager(new Settings()), new FirstPath()));
        tower.update(testCreepList);
        tower.preparePlacing(new ProximityVector(100,100));
        assertTrue(tower.isPlaced());
        assertTrue(tower.getRange() > 0);

    }

    @Test
    public void testGetUpgradeCost() throws Exception {
        Tower  tower = new BulletTower(new ProximityVector(0,0), new TargetClosest(), new ParticleManager(new Settings()));
        assertTrue(tower.getUpgradeCost().getLines() + tower.getUpgradeCost().getPoints() > 0);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testPreparePlacing() throws Exception {

        Tower  tower = new BulletTower(new ProximityVector(0,0), new TargetClosest(), new ParticleManager(new Settings()));
        //preparing placing should mark the tower as placed and place the center where the curos was
        tower = new MissileTower(new ProximityVector(0,0), new TargetClosest(),new ParticleManager(new Settings()));
        tower.preparePlacing(new ProximityVector(100, 100));
        assertTrue(tower.getCenter().x == 100);
        assertTrue(tower.isPlaced());
        tower.preparePlacing(null); //should throw illegal argument exception

    }

    @Test
    public void testGetRange() throws Exception {
        Tower  tower = new BulletTower(new ProximityVector(0,0), new TargetClosest(), new ParticleManager(new Settings()));
        assertTrue(tower.getRange() > 0);
    }

    @Test
    public void testGetCost() throws Exception {
        Tower  tower = new BulletTower(new ProximityVector(0,0), new TargetClosest(), new ParticleManager(new Settings()));
       assertTrue(tower.getCost().getLines() + tower.getCost().getPoints() > 0);
    }

    @Test
    public void testGetNewUpgrade() throws Exception {
        Tower  tower = new BulletTower(new ProximityVector(0,0), new TargetClosest(), new ParticleManager(new Settings()));
        tower = new MissileTower(new ProximityVector(0,0), new TargetClosest(),new ParticleManager(new Settings()));
        assertTrue(tower.getUpgrade() == null);
        tower = new BulletTower(new ProximityVector(0,0), new TargetClosest(),new ParticleManager(new Settings()));
        assertTrue(tower.getUpgrade() instanceof BulletTower2);
        tower = tower.getUpgrade();
        assertTrue(tower.getUpgrade() instanceof BulletTower3);
        tower = tower.getUpgrade();
        assertTrue(tower.getUpgrade() == null);

    }

}