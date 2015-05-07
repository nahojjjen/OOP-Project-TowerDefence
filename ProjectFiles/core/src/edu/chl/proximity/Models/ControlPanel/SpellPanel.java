package edu.chl.proximity.Models.ControlPanel;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import edu.chl.proximity.Models.BoardObject;
import edu.chl.proximity.Models.Player.Factions.Faction;
import edu.chl.proximity.Models.Utils.Image;
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

    private static Vector2 position = new Vector2(340, Constants.GAME_HEIGHT - height);

    //The background of the ControlPanel
    private static Image background = new Image(Constants.FILE_PATH + "Backgrounds/temporaryControlPanelBackground.png");

    private List<ControlPanelSpell> controlPanelSpellList = new ArrayList<ControlPanelSpell>();


    public SpellPanel(Faction faction) {
        super(null, position, background, 0, width, height);
        initiateSpells(faction);
    }

    private void initiateSpells(Faction faction) {

        for(int i = 0; i < 4; i++) {
            controlPanelSpellList.add(new ControlPanelSpell(new Vector2(position.x + 2 + (64+2)*i, position.y + 2), faction.getSpell(i)));
        }


    }

    public void render(SpriteBatch batch) {
        super.render(batch);
        for(ControlPanelSpell cpSpell : controlPanelSpellList) {
            cpSpell.render(batch);
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
