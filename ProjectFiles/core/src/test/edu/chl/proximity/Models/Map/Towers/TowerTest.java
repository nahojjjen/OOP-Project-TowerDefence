package test.edu.chl.proximity.Models.Map.Towers;

import com.badlogic.gdx.graphics.Color;
import edu.chl.proximity.Models.Map.Creeps.ConcreteCreeps.Line1;
import edu.chl.proximity.Models.Map.Creeps.Creep;
import edu.chl.proximity.Models.Map.Particles.ParticleManager;
import edu.chl.proximity.Models.Map.Paths.ConcretePaths.FirstPath;
import edu.chl.proximity.Models.Map.Towers.*;
import edu.chl.proximity.Models.Map.Towers.TargetingMethods.TargetClosest;
import edu.chl.proximity.Models.Player.Factions.ConcreteFactions.Filler;
import edu.chl.proximity.Models.Player.Players.GameData;
import edu.chl.proximity.Models.Player.Players.Player;
import edu.chl.proximity.Models.Utils.Settings;
import edu.chl.proximity.Utilities.ProximityVector;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * @author Johan
 * @date 2015-05-19.
 *
 * This test class is mostly redundant since it tests methods the same way the  test classes for concrete towers preform the tests
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
        GameData.getInstance().setPlayer(new Player(new Filler()));
        tower.setPosition(new ProximityVector(100, 100));

        tower.update(null); //should do nothing, no creep list -> no action

        List<Creep> testCreepList = new ArrayList<Creep>();
        tower.update(testCreepList); // should not fire, no creeps nearby; list empty

        Creep creep = new Line1(1, new ParticleManager(new Settings()), new FirstPath());
        creep.setPosition(new ProximityVector(100,100));
        testCreepList.add(creep);   //adds a creep near the tower

        tower.update(testCreepList); // should fire a projectile at the creep and start the cooldown
        assertTrue(tower.getAddList().size() == 1); //make sure that the projectile has been marked for adding
        tower.update(testCreepList);
        assertTrue(tower.getAddList().size() == 1); //since the tower has a cooldown, the previous update should not have fired a bullet
    }

    @Test
    public void testGetUpgradeCost() throws Exception {
        Tower  tower = new BulletTower(new ProximityVector(0,0), new TargetClosest(), new ParticleManager(new Settings()));
        assertTrue(tower.getUpgradeCost().getLines() + tower.getUpgradeCost().getPoints() > 0);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testPreparePlacing() throws Exception {

        //preparing placing should mark the tower as placed and place the center where the curos was
        Tower tower = new MissileTower(new ProximityVector(0,0), new TargetClosest(),new ParticleManager(new Settings()));
        tower.preparePlacing(new ProximityVector(100, 100));
        assertTrue(tower.getCenter().x == 100);
        assertTrue(tower.isPlaced());
        tower.preparePlacing(null); //should throw illegal argument exception

    }


    @Test
    public void testGetColor() throws Exception {
        Tower  tower = new BulletTower(new ProximityVector(0,0), new TargetClosest(), new ParticleManager(new Settings()));
        assertTrue(tower.getColor() != null);
    }

    @Test
    public void testSetAsPlaced() throws Exception {
        Tower  tower = new BulletTower(new ProximityVector(0,0), new TargetClosest(), new ParticleManager(new Settings()));
        tower.setAsPlaced(true);
        assertTrue(tower.isPlaced());
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
        //tests a couple of child classes, these tests are duplicate since they also exist in the towers test class.
        Tower tower = new MissileTower(new ProximityVector(0,0), new TargetClosest(),new ParticleManager(new Settings()));
        assertTrue(tower.getUpgrade() instanceof  MissileTower2);
        tower = new BulletTower(new ProximityVector(0,0), new TargetClosest(),new ParticleManager(new Settings()));
        assertTrue(tower.getUpgrade() instanceof BulletTower2);
        tower = tower.getUpgrade();
        assertTrue(tower.getUpgrade() instanceof BulletTower3);
        tower = tower.getUpgrade();
        assertTrue(tower.getUpgrade() == null);

    }

}