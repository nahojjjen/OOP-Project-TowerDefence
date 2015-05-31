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
import edu.chl.proximity.Models.Utils.ProximityRandom;
import edu.chl.proximity.Utilities.ProximityVector;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

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
        ProximityVector vector;

        int spellCounter = 0;
        int nullCounter = 0;

        for(int x = 0; x < spellPanel.getWidth(); x++) {
            for(int y = 0; y < spellPanel.getHeight(); y++) {
                vector = new ProximityVector(spellPanel.getPosition().x + x, spellPanel.getPosition().y + y);
                ControlPanelSpell spell = spellPanel.getSpellOnPosition(vector);
                if(spell == null) {
                    nullCounter++; }
                else {
                    spellCounter++; }
            }
        }
        assertTrue(spellCounter > 0);
        assertTrue(nullCounter > spellCounter);

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