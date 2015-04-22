package edu.chl.proximity.Models.ButtonsPanel;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import edu.chl.proximity.Models.BoardObject;
import edu.chl.proximity.Models.Image;
import edu.chl.proximity.Utilities.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hanna on 2015-04-22.
 */
public class ButtonPanel extends BoardObject {
    private static Image background = new Image(Constants.filePath + "Backgrounds/temporaryControlPanelBackground.png");
    private static int width=300;
    private static int height=200;
    private PlayPauseButton ppButton=new PlayPauseButton();

    public ButtonPanel() {
        super(new Vector2(Gdx.graphics.getWidth() - 300, 0), background, 0, width, height);
    }
}
