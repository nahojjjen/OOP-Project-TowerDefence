package edu.chl.proximity.Models.ControlPanel;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import edu.chl.proximity.Models.BoardObject;
import edu.chl.proximity.Models.Utils.Image;
import edu.chl.proximity.Utilities.Constants;

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


    public SpellPanel() {
        super(null, position, background, 0, width, height);
    }

    public void render(SpriteBatch batch) {
        super.render(batch);

    }
}
