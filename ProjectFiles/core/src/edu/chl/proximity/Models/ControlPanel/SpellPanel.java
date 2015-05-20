package edu.chl.proximity.Models.ControlPanel;

import com.badlogic.gdx.graphics.Texture;
import edu.chl.proximity.Models.Player.Spells.Spell;
import edu.chl.proximity.Models.Utils.PercentBar;
import edu.chl.proximity.Models.Utils.ProximityBatch;
import edu.chl.proximity.Utilities.*;
import edu.chl.proximity.Models.BoardObject;
import edu.chl.proximity.Models.Player.Factions.Faction;
import edu.chl.proximity.Models.Utils.Image;
import edu.chl.proximity.Models.Utils.ProximityFont;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Linda Evaldsson
 * @date 2015-05-07
 *
 * 13/05 Added spell cooldown indicator
 * 15/5 modified by johan, added javadoc
 */
public class SpellPanel extends BoardObject {

    private static int width = 306;
    private static int height = 90;

    private static List<ProximityFont> shortCuts = new ArrayList<ProximityFont>();
    private static ProximityFont w = new ProximityFont(new ProximityVector(0, 0), "Q");
    private static ProximityFont e = new ProximityFont(new ProximityVector(0, 0), "Q");
    private static ProximityFont r = new ProximityFont(new ProximityVector(0, 0), "Q");

    private static Image spellCooldownForeground = new Image(Constants.FILE_PATH + "Backgrounds/health.png");
    private static Image spellCooldownBackground = new Image(Constants.FILE_PATH + "Backgrounds/square.png");

    private static ProximityVector position = new ProximityVector(340, Constants.GAME_HEIGHT - height);

    //The background of the ControlPanel
    private static Image background = new Image(Constants.FILE_PATH + "Backgrounds/tweed.png");

    private List<ControlPanelSpell> controlPanelSpellList = new ArrayList<ControlPanelSpell>();
    private Map<ControlPanelSpell, PercentBar> spellCooldownBars = new HashMap<ControlPanelSpell, PercentBar>();


    /**
     * creates an instance of the spellPanel
     * @param faction
     */
    public SpellPanel(Faction faction) {
        super(position, background, 0, width, height);
        addSpellsToControlPanel(faction);
    }


    /**
     * Reads the current factions spells, and creates representing images for them
     * @param faction
     */
    private void addSpellsToControlPanel(Faction faction) {
        for(int i = 0; i < 4; i++) {
            ControlPanelSpell tmpSpell = new ControlPanelSpell(new ProximityVector(position.x + 10 + (64+10)*i, position.y + 10), faction.getSpell(i));
            controlPanelSpellList.add(tmpSpell);
            shortCuts.add(new ProximityFont(new ProximityVector(position.x + 14 + (64+10)*i, position.y + 14), Constants.SPELL_SHORTS.get(i)));
            spellCooldownBars.put(tmpSpell, new PercentBar(new ProximityVector(tmpSpell.getPosition().x, tmpSpell.getPosition().y + 64), 64, 10, spellCooldownForeground, spellCooldownBackground));
        }
    }

    /**
     * Reads the current spell cooldowns and updates the cooldown representers model accordingly
     */
    public void updateCooldowns() {
       updateCooldownIndicators();
        updateSpellCooldowns();
    }

    /**
     * update the spell cooldown indicator models
     */
    private void updateCooldownIndicators(){
        for(ControlPanelSpell cpSpell : controlPanelSpellList) {
            spellCooldownBars.get(cpSpell).setPercent((int) cpSpell.getSpell().getCooldownPercent());
        }
    }

    /**
     * updates the model spell cooldowns
     */
    private void updateSpellCooldowns(){
        for(ControlPanelSpell cpSpell : controlPanelSpellList) {
            cpSpell.getSpell().updateCooldown();
        }
    }

    /**
     * Draws out the spell-panel background, spell images and shortcut text
     * @param batch
     */
    public void render(ProximityBatch batch) {
        background.getTexture().setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
        batch.renderRepeatedly(background, position, width, height);
        for(ControlPanelSpell cpSpell : controlPanelSpellList) {

            cpSpell.render(batch);
            spellCooldownBars.get(cpSpell).render(batch);
        }
        for(ProximityFont font : shortCuts) {
            font.draw(batch);
        }

    }

    /**
     * Get which spell Icon is on the given position
     * @param position what position to search for
     * @return A controlpanelSpell, if one was clicked.
     */
    public ControlPanelSpell getSpellOnPosition(ProximityVector position) {
        for(ControlPanelSpell cpSpell : controlPanelSpellList) {
            if(cpSpell.containsPoint(position))
                return cpSpell;
        }
        return null;
    }

    /**
     * get the spell connceted to a keybinding
     * @param input what spell input should be checked
     * @return a spell bound to that key, if a spell exists
     * */
    public Spell getSpellBoundTo(String input){
        switch (input.charAt(0)){
            case 'q': return controlPanelSpellList.get(0).getSpell();
            case 'w': return controlPanelSpellList.get(1).getSpell();
            case 'e': return controlPanelSpellList.get(2).getSpell();
            case 'r': return controlPanelSpellList.get(3).getSpell();
        }
        return null;
    }
}
