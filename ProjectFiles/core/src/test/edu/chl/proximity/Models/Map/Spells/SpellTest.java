package test.edu.chl.proximity.Models.Map.Spells;

import edu.chl.proximity.Models.Map.Creeps.ConcreteCreeps.Line1;
import edu.chl.proximity.Models.Map.Creeps.Creep;
import edu.chl.proximity.Models.Map.Particles.ParticleManager;
import edu.chl.proximity.Models.Map.Paths.ConcretePaths.FirstPath;
import edu.chl.proximity.Models.Map.Paths.Path;
import edu.chl.proximity.Models.Map.Spells.ConcreteSpells.*;
import edu.chl.proximity.Models.Map.Spells.Spell;
import edu.chl.proximity.Models.Map.Towers.BulletTower;
import edu.chl.proximity.Models.Map.Towers.Tower;
import edu.chl.proximity.Utilities.ProximityVector;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author Simon Gislen.
 * @date 21/05/15.
 *
 *
 * A Test class for Spell.
 * Blood Carnage is used for testing here. Not optimal since the bug could be in subclass
 */
public class SpellTest {

    private Path path = new FirstPath();

    @Test
    public void testAllSpells() throws Exception {

        testSpell(new BloodCarnage(null));
        testSpell(new BloodPool(null));
        testSpell(new BloodSipper(null));
        testSpell(new ChainLightning(null));
        testSpell(new CoinFlip(null));
        testSpell(new FireField(null));
        testSpell(new FrostField(null));
        testSpell(new LifeGamble(null));
        testSpell(new PickACard(null));
        testSpell(new Sacrifice(null));
        testSpell(new SpeedGamble(null));
        testSpell(new WallOfStone(null));
    }

    private void testSpell(Spell spell) throws Exception{
        testSetHealthChange(spell);
        testIsReadyToCast(spell);
        testPreparePlacing(spell);
        testGetCreepsWithinDistance(spell);
        testGetTowersWithinDistance(spell);


        testGetRange(spell);
        testGetCreeps(spell);
        testGetTowers(spell);

        for (int i=30; i>0; i--){
            spell.preparePlacing(new ProximityVector(100,100));
            spell.start();
            spell.performEffect(i);
        }
    }

    public void testSetHealthChange(Spell spell) throws Exception {
        spell.setHealthChange(1);
        assertTrue(spell.getHealthChange() == 1);

        //Cant go lower than zero
        spell.setHealthChange(-1);
        assertTrue(spell.getHealthChange() == -1);

        spell.setHealthChange(10000);
        assertTrue(spell.getHealthChange() == 10000);
    }


    public void testPreparePlacing(Spell spell) {
        spell.preparePlacing(new ProximityVector(100, 100));
        assertTrue(spell.getPosition().x == 100);
        assertTrue(spell.isPlaced());
    }

    public void testGetCreepsWithinDistance(Spell spell) throws Exception {

        List<Creep> creeps = new ArrayList<Creep>();
        Creep creep = new Line1(1, null, path);
        creep.setPosition(new ProximityVector(10, 10));
        creeps.add(creep);
        spell.setCreeps(creeps);

        ProximityVector pos = new ProximityVector(5,5);
        int range = 50;

        //Expected 1
        List<Creep> creepList = spell.getCreepsWithinDistance(pos, range);
        assertTrue(creepList.size() == 1);

        creep = new Line1(1, null, path);
        creep.setPosition(new ProximityVector(100, 100));
        creeps.add(creep);
        spell.setCreeps(creeps);
        //Still expected one.
        creepList = spell.getCreepsWithinDistance(pos, range);
        assertTrue(creepList.size() == 1);

        //Expected 0.
        creepList = spell.getCreepsWithinDistance(null, range);
        assertTrue(creepList.size() == 0);

    }

    public void testGetTowersWithinDistance(Spell spell) throws Exception {

        List<Tower> towers = new ArrayList<Tower>();
        Tower tower = new BulletTower(new ProximityVector(10, 10), null, null);
        towers.add(tower);
        spell.setTowers(towers);

        ProximityVector pos = new ProximityVector(5,5);
        int range = 50;

        //Expected 1
        List<Tower> towerList = spell.getTowersWithinDistance(pos, range);
        assertTrue(towerList.size() == 1);

        tower = new BulletTower(new ProximityVector(100, 100), null, null);
        towers.add(tower);
        spell.setTowers(towers);
        //Still expected one.
        towerList = spell.getTowersWithinDistance(pos, range);
        assertTrue(towerList.size() == 1);

        //Expected 0.
        towerList = spell.getTowersWithinDistance(null, range);
        assertTrue(towerList.size() == 0);
    }

    public void testIsReadyToCast(Spell spell) throws Exception {
        boolean ready = spell.isReadyToCast();
        assertTrue(ready);

    }


    public void testGetRange(Spell spell) throws Exception {
        assertTrue(spell.getRange() > 0);

    }


    public void testGetCreeps(Spell spell) throws Exception {
        spell.setCreeps(null);
        List<Creep> creeps = spell.getCreeps();
        assertNull(creeps);
        creeps = new ArrayList<Creep>();
        Creep creep = new Line1(1, null, null);
        creeps.add(creep);
        spell.setCreeps(creeps);
        assertEquals(creep, spell.getCreeps().get(0));

    }

    public void testGetTowers(Spell spell) throws Exception {
        spell.setTowers(null);
        List<Tower> towers = spell.getTowers();
        assertNull(towers);
        towers = new ArrayList<Tower>();
        Tower tower = new BulletTower(null, null, null);
        towers.add(tower);
        spell.setTowers(towers);
        assertEquals(tower, spell.getTowers().get(0));
    }

}