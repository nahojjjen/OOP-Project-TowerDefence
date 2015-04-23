package edu.chl.proximity.Models.ButtonsPanel;

import com.badlogic.gdx.math.Vector2;
import edu.chl.proximity.Models.BoardObject;
import edu.chl.proximity.Models.Image;
import edu.chl.proximity.Utilities.Constants;

/**
 * Created by Hanna on 2015-04-22.
 */
public class PlayPauseButton extends BoardObject {
    private static Image pauseImage= new Image(Constants.filePath + "Buttons/PauseButton.png");
    private static Image playImage= new Image(Constants.filePath + "Buttons/PauseButton.png");
    private static int height=50;
    private static int width=50;

    public PlayPauseButton(Vector2 position){
        super(position,pauseImage,0,width,height);
    }

}
