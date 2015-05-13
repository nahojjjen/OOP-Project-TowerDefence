package edu.chl.proximity.Models.ControlPanel;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import edu.chl.proximity.Utilities.PercentBar;
import edu.chl.proximity.Utilities.ProximityVector;
import edu.chl.proximity.Models.BoardObject;
import edu.chl.proximity.Models.Player.Factions.Faction;
import edu.chl.proximity.Models.Utils.Image;
import edu.chl.proximity.Models.Utils.ProximityFont;
import edu.chl.proximity.Utilities.Constants;
import edu.chl.proximity.Utilities.PointCalculations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Linda Evaldsson
 * @date 2015-05-07
 *
 * 13/05 Added spell cooldown indicator
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



    public SpellPanel(Faction faction) {
        super(position, background, 0, width, height);
        initiateSpells(faction);
    }

    private void initiateSpells(Faction faction) {
        for(int i = 0; i < 4; i++) {
            ControlPanelSpell tmpSpell = new ControlPanelSpell(new ProximityVector(position.x + 10 + (64+10)*i, position.y + 10), faction.getSpell(i));
            controlPanelSpellList.add(tmpSpell);
            shortCuts.add(new ProximityFont(new ProximityVector(position.x + 14 + (64+10)*i, position.y + 14), Constants.SPELL_SHORTS[i]));
            spellCooldownBars.put(tmpSpell, new PercentBar(new ProximityVector(tmpSpell.getPosition().x, tmpSpell.getPosition().y + 64), 64, 10, spellCooldownForeground, spellCooldownBackground));

        }


    }

    public void updateCooldowns() {
        for(ControlPanelSpell cpSpell : controlPanelSpellList) {
            spellCooldownBars.get(cpSpell).setPercent((int) cpSpell.getSpell().getCooldownPercent());
        }
    }

    public void render(SpriteBatch batch) {


        background.getTexture().setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
        batch.draw(background.getTexture(), position.x, position.y, background.getTexture().getWidth(), background.getTexture().getHeight(), width, height);

        for(ControlPanelSpell cpSpell : controlPanelSpellList) {

            cpSpell.render(batch);
            spellCooldownBars.get(cpSpell).render(batch);
        }
        for(ProximityFont font : shortCuts) {
            font.draw(batch);
        }

    }

    public ControlPanelSpell getSpellOnPosition(ProximityVector position) {
        for(ControlPanelSpell cpSpell : controlPanelSpellList) {
            if(PointCalculations.isPointInObject(position, cpSpell))
                return cpSpell;
        }
        return null;
    }
}
