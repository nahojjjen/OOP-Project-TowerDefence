package edu.chl.proximity.Models.ControlPanel;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import edu.chl.proximity.Models.BoardObject;
import edu.chl.proximity.Models.Player.Factions.Faction;
import edu.chl.proximity.Models.Utils.Image;
import edu.chl.proximity.Models.Utils.ProximityFont;
import edu.chl.proximity.Utilities.Constants;
import edu.chl.proximity.Utilities.PointCalculations;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Linda Evaldsson
 * @date 2015-05-07
 */
public class SpellPanel extends BoardObject {

    private static int width = 500;
    private static int height = 70;
    private String[] shorts = {"Q", "W", "E", "R"};

    private static List<ProximityFont> shortCuts = new ArrayList<ProximityFont>();
    private static ProximityFont w = new ProximityFont(new Vector2(0, 0), "Q");
    private static ProximityFont e = new ProximityFont(new Vector2(0, 0), "Q");
    private static ProximityFont r = new ProximityFont(new Vector2(0, 0), "Q");

    private static Vector2 position = new Vector2(340, Constants.GAME_HEIGHT - height);

    //The background of the ControlPanel
    private static Image background = new Image(Constants.FILE_PATH + "Backgrounds/warmBackgroundSmall.png");

    private List<ControlPanelSpell> controlPanelSpellList = new ArrayList<ControlPanelSpell>();


    public SpellPanel(Faction faction) {
        super(null, position, background, 0, width, height);
        initiateSpells(faction);
    }

    private void initiateSpells(Faction faction) {
        for(int i = 0; i < 4; i++) {
            controlPanelSpellList.add(new ControlPanelSpell(new Vector2(position.x + 2 + (64+2)*i, position.y + 2), faction.getSpell(i)));
            shortCuts.add(new ProximityFont(new Vector2(position.x + 4 + (64+2)*i, position.y + 4), shorts[i]));
        }


    }

    public void render(SpriteBatch batch) {
        super.render(batch);
        for(ControlPanelSpell cpSpell : controlPanelSpellList) {
            cpSpell.render(batch);
        }
        for(ProximityFont font : shortCuts) {
            font.draw(batch);
        }

    }

    public ControlPanelSpell getSpellOnPosition(Vector2 position) {
        for(ControlPanelSpell cpSpell : controlPanelSpellList) {
            if(PointCalculations.isPointInObject(position, cpSpell))
                return cpSpell;
        }
        return null;
    }
}
