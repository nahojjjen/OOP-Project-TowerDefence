package test.edu.chl.proximity.Models.Map.Towers;

import edu.chl.proximity.Models.Map.Creeps.ConcreteCreeps.Line1;
import edu.chl.proximity.Models.Map.Creeps.Creep;
import edu.chl.proximity.Models.Map.Particles.ParticleManager;
import edu.chl.proximity.Models.Map.Paths.ConcretePaths.FirstPath;
import edu.chl.proximity.Models.Map.Towers.BulletTower;
import edu.chl.proximity.Models.Map.Towers.BulletTower2;
import edu.chl.proximity.Models.Map.Towers.MissileTower;
import edu.chl.proximity.Models.Map.Towers.TargetingMethods.TargetClosest;
import edu.chl.proximity.Models.Map.Towers.Tower;
import edu.chl.proximity.Models.Player.Factions.ConcreteFactions.Filler;
import edu.chl.proximity.Models.Player.Players.Player;
import edu.chl.proximity.Models.Utils.GameData;
import edu.chl.proximity.Utilities.ProximityVector;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @authoor Johan
 * @date 2015-05-19.
 */
public class TowerTest {

    Tower tower;
    @Before public void initialize(){
        tower = new BulletTower(new ProximityVector(0,0), new TargetClosest(), new ParticleManager());
    }

    @Test
    public void testGetName() throws Exception {
    assertTrue(tower.getName().equals("Bullet Tower"));
    }

    @Test(expected=java.lang.ExceptionInInitializerError.class) // will attempt to create a projectile, but will fail
    public void testUpdate() throws Exception {
        //update will do target, shoot, reload

        GameData.getInstance();
        GameData.getInstance().setPlayer(new Player(new Filler()));

        tower.update(null);
        List<Creep> testCreepList = new ArrayList<Creep>();
        tower.update(testCreepList); // should not fire, no creeps nearby
        tower.setPosition(new FirstPath().getWaypoint(0)); // place the test tower on top of where the creep will spawn
        testCreepList.add(new Line1(1, new ParticleManager(), new FirstPath()));
        tower.update(testCreepList); // should fire a projectile and start the cooldown
        tower.setAsPlaced(true);
        testCreepList.add(new Line1(1, new ParticleManager(), new FirstPath()));
        tower.update(testCreepList);
        assertTrue(tower.getIfPlaced());
        assertTrue(tower.getRange() > 0);

    }

    @Test
    public void testGetUpgradeCost() throws Exception {
        assertTrue(tower.getUpgradeCost().getLines() + tower.getUpgradeCost().getPoints() > 0);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testPreparePlacing() throws Exception {
        //preparing placing should mark the tower as placed and place the center where the curos was
        tower = new MissileTower(new ProximityVector(0,0), new TargetClosest(),new ParticleManager());
        tower.preparePlacing(new ProximityVector(100, 100));
        assertTrue(tower.getCenter().x == 100);
        assertTrue(tower.getIfPlaced());
        tower.preparePlacing(null); //should throw illegal argument exception

    }

    @Test
    public void testGetRange() throws Exception {
        assertTrue(tower.getRange() > 0);
    }

    @Test
    public void testGetCost() throws Exception {
       assertTrue(tower.getCost().getLines() + tower.getCost().getPoints() > 0);
    }

    @Test
    public void testGetNewUpgrade() throws Exception {
        tower = new MissileTower(new ProximityVector(0,0), new TargetClosest(),new ParticleManager());
        assertTrue(tower.getUpgrade() == null);
        tower = new BulletTower(new ProximityVector(0,0), new TargetClosest(),new ParticleManager());
        assertTrue(tower.getUpgrade() instanceof BulletTower2);
        tower = tower.getUpgrade();
        assertTrue(tower.getUpgrade() == null);

    }

    @Test
    public void testGetUpgrade() throws Exception {

    }

    @Test
    public void testGetIfPlaced() throws Exception {

    }

    @Test
    public void testSetAsPlaced() throws Exception {

    }

    @Test
    public void testGetParticleManager() throws Exception {

    }

    @Test
    public void testSetParticleManager() throws Exception {

    }
}