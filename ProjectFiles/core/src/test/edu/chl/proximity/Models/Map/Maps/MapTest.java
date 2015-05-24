package test.edu.chl.proximity.Models.Map.Maps;

import edu.chl.proximity.Models.Map.Bases.Base;
import edu.chl.proximity.Models.Map.Bases.ShardBase;
import edu.chl.proximity.Models.Map.Creeps.ConcreteCreeps.Triangle;
import edu.chl.proximity.Models.Map.Creeps.Creep;
import edu.chl.proximity.Models.Map.Maps.Map;
import edu.chl.proximity.Models.Map.Maps.StandardMap;
import edu.chl.proximity.Models.Map.Particles.ParticleManager;
import edu.chl.proximity.Models.Map.Paths.ConcretePaths.TestPath;
import edu.chl.proximity.Models.Map.Paths.Path;
import edu.chl.proximity.Models.Map.Projectiles.Bullet;
import edu.chl.proximity.Models.Map.Projectiles.Projectile;
import edu.chl.proximity.Models.Map.Towers.BulletTower;
import edu.chl.proximity.Models.Map.Towers.TargetingMethods.TargetClosest;
import edu.chl.proximity.Models.Map.Towers.TargetingMethods.TargetingMethod;
import edu.chl.proximity.Models.Map.Towers.Tower;
import edu.chl.proximity.Models.Holdables.Hand;
import edu.chl.proximity.Models.Map.Spells.ConcreteSpells.WallOfStone;
import edu.chl.proximity.Models.Map.Spells.Spell;
import edu.chl.proximity.Models.Map.Background;
import edu.chl.proximity.Models.Utils.Settings;
import edu.chl.proximity.Utilities.Constants;
import edu.chl.proximity.Utilities.ProximityRandom;
import edu.chl.proximity.Utilities.ProximityVector;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.List;


/**
 * @author Linda Evaldsson
 * @date 2015-05-19
 */
public class MapTest {
    @Test
    public void testAdd() throws Exception {
        Map map = new StandardMap(new ParticleManager(new Settings()));
        Creep creep = new Triangle(map.getParticleManager(), map.getPath());
        Tower tower = new BulletTower(new ProximityVector(0, 0), new TargetClosest(), map.getParticleManager());
        Spell spell = new WallOfStone(map.getParticleManager());
        Projectile projectile = new Bullet(new ProximityVector(0, 0), 0, null, map.getParticleManager());

        int i = map.getNumberOfObjectsOnMap();
        map.add(creep);
        assertTrue(map.getNumberOfObjectsOnMap() == i + 1);
        map.add(tower);
        assertTrue(map.getNumberOfObjectsOnMap() == i + 2);
        map.add(spell);
        assertTrue(map.getNumberOfObjectsOnMap() == i + 3);
        map.add(projectile);
        assertTrue(map.getNumberOfObjectsOnMap() == i + 4);


    }
    @Test
    public void testGetName() throws Exception {

        Map map = new StandardMap(new ParticleManager(new Settings()));
        assertEquals("Standard", map.getName());

    }
    @Test
    public void testGetHand() throws Exception {
        Map map = new StandardMap(new ParticleManager(new Settings()));
        assertTrue(map.getHand().getClass() == Hand.class);

    }
    @Test
    public void testGetParticleManager() throws Exception {
        Map map = new StandardMap(new ParticleManager(new Settings()));
        assertTrue(map.getParticleManager().getClass() == ParticleManager.class);
    }
    @Test
    public void testGetCreeps() throws Exception {
        Map map = new StandardMap(new ParticleManager(new Settings()));
        Creep creep = new Triangle(map.getParticleManager(), map.getPath());
        map.add(creep);
        assertTrue(map.getCreeps().size() == 1);
        assertEquals(map.getCreeps().get(0), creep);

    }
    @Test
    public void testGetObjectOnPosition() throws Exception {
        Map map = new StandardMap(new ParticleManager(new Settings()));

        Creep creep = new Triangle(map.getParticleManager(), map.getPath());

        assertTrue(map.getObjectOnPosition(new ProximityVector(0, 0)) == null);
        TargetingMethod targetingMethod = new TargetClosest();

        ProximityVector vector;
        for(int i = 0; i < 1000; i++) {
            ProximityRandom random = new ProximityRandom();
            vector = new ProximityVector((float)random.getRandomDoubleBetween(0, Constants.GAME_WIDTH), (float)random.getRandomDoubleBetween(0, Constants.GAME_HEIGHT));
            Tower tower = new BulletTower(vector, targetingMethod, map.getParticleManager());
            map.add(tower);
            assertTrue(map.getObjectOnPosition(tower.getCenter()).equals(tower) || map.getObjectOnPosition(tower.getCenter()).containsPoint(tower.getCenter()));
        }

        map = new StandardMap(new ParticleManager(new Settings()));
        map.add(creep);
        assertEquals(creep, map.getObjectOnPosition(creep.getCenter()));
        assertTrue(null == map.getObjectOnPosition(new ProximityVector(100000, 10000)));

    }
    @Test
    public void testGetCreepsWithinDistance() throws Exception {
        Map map = new StandardMap(new ParticleManager(new Settings()));
        Creep creep1 = new Triangle(map.getParticleManager(), map.getPath());
        Creep creep2 = new Triangle(map.getParticleManager(), map.getPath());
        Creep creep3 = new Triangle(map.getParticleManager(), map.getPath());
        Creep creep4 = new Triangle(map.getParticleManager(), map.getPath());
        Creep creep5 = new Triangle(map.getParticleManager(), map.getPath());
        creep1.setPosition(new ProximityVector(50, 50));
        creep2.setPosition(new ProximityVector(60, 50));
        creep3.setPosition(new ProximityVector(40, 50));
        creep4.setPosition(new ProximityVector(50, 60));
        creep5.setPosition(new ProximityVector(200, 200));

        map.add(creep1);
        map.add(creep2);
        map.add(creep3);
        map.add(creep4);
        map.add(creep5);

        List<Creep> creeps = map.getCreepsWithinDistance(new ProximityVector(50, 50), 50);

        assertTrue(creeps.contains(creep1));
        assertTrue(creeps.contains(creep2));
        assertTrue(creeps.contains(creep3));
        assertTrue(creeps.contains(creep4));
        assertFalse(creeps.contains(creep5));
    }

    @Test
    public void testGetPath() throws Exception {
        Map map = new StandardMap(new ParticleManager(new Settings()));
        assertTrue(map.getPath() instanceof Path);
    }
    @Test
    public void testSetPath() throws Exception {
        Map map = new StandardMap(new ParticleManager(new Settings()));
        Path path = new TestPath();
        map.setPath(path);
        assertEquals(path, map.getPath());
        try {
            map.setPath(null);
            fail();
        } catch(IllegalArgumentException e) {
            assertTrue(true);
        }

    }
    @Test
    public void testGetBackground() throws Exception {
        Map map = new StandardMap(new ParticleManager(new Settings()));
        assertTrue(map.getBackground() instanceof Background);

    }

    @Test
    public void testSetAndGetBase() throws Exception {
        Map map = new StandardMap(new ParticleManager(new Settings()));

        Base base = new ShardBase(map.getPath(), map.getParticleManager());
        map.setBase(base);
        assertEquals(base, map.getBase());

        map.setBase(null);
        assertTrue(map.getBase() == null);

    }
    @Test
    public void testSetAndGetChoosenTower() throws Exception {
        Map map = new StandardMap(new ParticleManager(new Settings()));
        Tower tower1 = new BulletTower(new ProximityVector(0, 0), new TargetClosest(), map.getParticleManager());
        map.setChosenTower(tower1);
        assertTrue(map.getChosenTower() == null);

        map.add(tower1);
        map.setChosenTower(tower1);
        assertEquals(map.getChosenTower(), tower1);

        map.setChosenTower(null);
        assertTrue(map.getChosenTower() == null);

    }
    @Test
    public void testClearRemoveStack() throws Exception {
        Map map = new StandardMap(new ParticleManager(new Settings()));
        Creep creep1 = new Triangle(map.getParticleManager(), map.getPath());
        Creep creep2 = new Triangle(map.getParticleManager(), map.getPath());
        Creep creep3 = new Triangle(map.getParticleManager(), map.getPath());
        Creep creep4 = new Triangle(map.getParticleManager(), map.getPath());
        Creep creep5 = new Triangle(map.getParticleManager(), map.getPath());

        map.add(creep1);
        map.add(creep2);
        map.add(creep3);
        map.add(creep4);
        map.add(creep5);

        assertTrue(map.getNumberOfObjectsOnMap() == 5);

        creep1.remove();
        creep2.remove();

        assertTrue(map.getNumberOfObjectsOnMap() == 5);

        map.clearRemoveStack();

        assertTrue(map.getNumberOfObjectsOnMap() == 3);

        Tower tower = new BulletTower(new ProximityVector(0, 0), new TargetClosest(), map.getParticleManager());
        Spell spell = new WallOfStone(map.getParticleManager());
        Projectile projectile = new Bullet(new ProximityVector(0, 0), 0, null, map.getParticleManager());

        map.add(tower);
        map.add(spell);
        map.add(projectile);

        assertTrue(map.getNumberOfObjectsOnMap() == 6);

        tower.remove();
        spell.remove();
        projectile.remove();

        map.clearRemoveStack();

        assertTrue(map.getNumberOfObjectsOnMap() == 3);


    }
    @Test
    public void testClearAddStack() throws Exception {
        Map map = new StandardMap(new ParticleManager(new Settings()));
        Creep creep1 = new Triangle(map.getParticleManager(), map.getPath());
        Creep creep2 = new Triangle(map.getParticleManager(), map.getPath());
        Creep creep3 = new Triangle(map.getParticleManager(), map.getPath());
        Creep creep4 = new Triangle(map.getParticleManager(), map.getPath());
        Creep creep5 = new Triangle(map.getParticleManager(), map.getPath());
        Projectile projectile = new Bullet(new ProximityVector(0, 0), 0, null, map.getParticleManager());
        Tower tower = new BulletTower(new ProximityVector(0, 0), new TargetClosest(), map.getParticleManager());
        Spell spell = new WallOfStone(map.getParticleManager());

        map.add(creep1);
        map.add(creep2);

        creep1.getAddList().add(creep3);
        creep1.getAddList().add(creep4);
        creep1.getAddList().add(creep5);
        creep1.getAddList().add(projectile);
        creep1.getAddList().add(tower);
        creep1.getAddList().add(spell);
        creep1.getAddList().add(null);


        assertTrue(creep1.getAddList().size() == 7);
        assertTrue(map.getNumberOfObjectsOnMap() == 2);

        map.clearAddStack();
        assertTrue(map.getNumberOfObjectsOnMap() == 8);
    }
    @Test
    public void testGetNew() throws Exception {
        Map map = new StandardMap(new ParticleManager(new Settings()));
        Map anotherMap = map.getNew();

        assertNotSame(map, anotherMap);
        assertTrue(map.getClass() == anotherMap.getClass());
    }
}