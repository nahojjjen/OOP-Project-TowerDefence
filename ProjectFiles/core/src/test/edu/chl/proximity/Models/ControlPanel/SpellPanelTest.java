package test.edu.chl.proximity.Models.ControlPanel;

import edu.chl.proximity.Models.ControlPanel.ControlPanelSpell;
import edu.chl.proximity.Models.ControlPanel.SpellPanel;
import edu.chl.proximity.Models.Map.Particles.ParticleManager;
import edu.chl.proximity.Models.Map.Spells.ConcreteSpells.BloodSipper;
import edu.chl.proximity.Models.Map.Spells.Spell;
import edu.chl.proximity.Models.Player.Factions.ConcreteFactions.Filler;
import edu.chl.proximity.Models.Player.Factions.Faction;
import edu.chl.proximity.Models.Utils.Settings;
import edu.chl.proximity.Utilities.Constants;
import edu.chl.proximity.Utilities.ProximityRandom;
import edu.chl.proximity.Utilities.ProximityVector;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Linda Evaldsson
 * @date 2015-05-23
 *
 * A class for testing the class SpellPanel
 *
 */
public class SpellPanelTest {

    @Test
    public void testUpdateCooldowns() throws Exception {
        Faction faction = new Filler();
        faction.configureSpells(new ParticleManager(new Settings()));
        SpellPanel spellPanel = new SpellPanel(faction);
        Spell spell = spellPanel.getSpellBoundTo("E");
        int cooldownBefore = spell.getCooldown().getCooldownPercent();
        spellPanel.updateCooldowns();
        int cooldown = spell.getCooldown().getCooldownPercent();
        assertTrue(cooldown == spellPanel.getCooldownBarForSpellBoundTo("E").getPercent());
        assertTrue(cooldownBefore == cooldown);

        spell.preparePlacing(new ProximityVector(0, 0));
        cooldownBefore = spell.getCooldown().getCooldownPercent();
        spellPanel.updateCooldowns();
        cooldown = spell.getCooldown().getCooldownPercent();
        assertTrue(cooldown > cooldownBefore);
    }

    @Test
    public void testGetSpellOnPosition() throws Exception {
        Faction faction = new Filler();
        faction.configureSpells(new ParticleManager(new Settings()));
        SpellPanel spellPanel = new SpellPanel(faction);

        ProximityRandom random = new ProximityRandom();
        ProximityVector vector;
        for(int i = 0; i < 1000; i++) {
            vector = new ProximityVector((float)random.getRandomDoubleBetween(0, Constants.GAME_WIDTH), (float)random.getRandomDoubleBetween(0, Constants.GAME_HEIGHT));
            ControlPanelSpell spell = spellPanel.getSpellOnPosition(vector);
            assertTrue(spell == null || spell instanceof ControlPanelSpell);
        }
    }

    @Test
    public void testGetSpellBoundTo() throws Exception {
        Faction faction = new Filler();
        faction.configureSpells(new ParticleManager(new Settings()));
        SpellPanel spellPanel = new SpellPanel(faction);
        Spell spell = spellPanel.getSpellBoundTo("E");
        assertTrue(spell instanceof BloodSipper);
    }
}