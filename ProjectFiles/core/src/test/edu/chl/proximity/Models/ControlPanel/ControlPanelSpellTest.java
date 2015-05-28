package test.edu.chl.proximity.Models.ControlPanel;

import edu.chl.proximity.Models.ControlPanel.ControlPanelSpell;
import edu.chl.proximity.Models.Map.Particles.ParticleManager;
import edu.chl.proximity.Models.Map.Spells.ConcreteSpells.FireField;
import edu.chl.proximity.Models.Map.Spells.Spell;
import edu.chl.proximity.Models.Utils.Settings;
import edu.chl.proximity.Utilities.ProximityVector;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Linda Evaldsson
 * @date 2015-05-23
 *
 * A class for testing the class ControlPanelSpell
 */
public class ControlPanelSpellTest {

    @Test
    public void testGetSpell() throws Exception {
        Spell spell = new FireField(new ParticleManager(new Settings()));
        ControlPanelSpell cpSpell1 = new ControlPanelSpell(new ProximityVector(0, 0), spell);

        assertFalse(spell == cpSpell1.getSpell());
        assertTrue(cpSpell1.getSpell() != null);

        try {
            ControlPanelSpell cpSpell2 = new ControlPanelSpell(new ProximityVector(0, 0), null);
            fail("It should not be possible to create a ControlPanelSpell with spell null");
        } catch (NullPointerException e){
            assertTrue(true);
        }

    }


}