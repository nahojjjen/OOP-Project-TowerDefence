package test.edu.chl.proximity.Models.Player.Spells;

import edu.chl.proximity.Models.Map.Creeps.ConcreteCreeps.Line1;
import edu.chl.proximity.Models.Map.Creeps.Creep;
import edu.chl.proximity.Models.Map.Particles.ParticleManager;
import edu.chl.proximity.Models.Map.Paths.ConcretePaths.FirstPath;
import edu.chl.proximity.Models.Map.Paths.Path;
import edu.chl.proximity.Models.Player.Spells.ConcreteSpells.BloodCarnage;
import edu.chl.proximity.Models.Player.Spells.Spell;
import edu.chl.proximity.Utilities.ProximityVector;
import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

/**
 * @author simongislen.
 * @date 21/05/15.
 *
 *
 * A Test class for Spell.
 * Blood Carnage is used for testing here. Not optimal since the bug could be in subclass
 */
public class SpellTest extends TestCase {

    private Path path = new FirstPath();
    private ParticleManager particleManager = null

    @Override
    public void setUp() throws Exception {
        super.setUp();
    }

    public void testSetHealthChange() throws Exception {

        Spell spell = new BloodCarnage(null);
        spell.setHealthChange(1);
        assertTrue(spell.getHealthChange() == 1);

        //Cant go lower than zero
        //spell.setHealthChange(-1);
        //assertTrue(spell.getHealthChange() == 0);

        spell.setHealthChange(10000);
        assertTrue(spell.getHealthChange() == 10000);
    }

    public void testGetHealthChange() throws Exception {
        //NA. Tested in method above
    }

    public void testPreparePlacing() throws Exception {
        Spell spell = new BloodCarnage(particleManager);
        ProximityVector pos = new ProximityVector(5,5);
        spell.preparePlacing(pos);

        assertTrue(spell.getPosition().equals(pos));
    }

    public void testGetCreepsWithinDistance() throws Exception {

        List<Creep> creeps = new ArrayList<Creep>();
        Creep creep = new Line1(1, null, path);
        creeps.add(creep);
        creep = new Line1(1, null, path);
        creeps.add(creep);
        creep = new Line1(1, null, path);
        creeps.add(creep);

        Spell spell = new BloodCarnage(null);
        spell.setCreeps(creeps);

        ProximityVector pos = new ProximityVector(5,5);
        int range = 60;

        List<Creep> creepList = spell.getCreepsWithinDistance(pos, range);
        //assertTrue(creepList.size() > 0);
    }

    public void testGetTowersWithinDistance() throws Exception {

    }

    public void testResetCooldown() throws Exception {

    }

    public void testUpdateCooldown() throws Exception {

    }

    public void testGetCooldownPercent() throws Exception {

    }

    public void testIsReadyToCast() throws Exception {

    }

    public void testStartCooldown() throws Exception {

    }

    public void testPlayParticleEffect() throws Exception {

    }

    public void testGetRange() throws Exception {

    }

    public void testGetControlPanelImage() throws Exception {

    }

    public void testGetCreeps() throws Exception {

    }

    public void testSetCreeps() throws Exception {

    }

    public void testGetParticleManager() throws Exception {

    }

    public void testGetTowers() throws Exception {

    }

    public void testSetTowers() throws Exception {

    }
}