package edu.chl.proximity.Models.ButtonsPanel;

import com.badlogic.gdx.math.Vector2;
import edu.chl.proximity.Models.BoardObject;
import edu.chl.proximity.Models.Image;
import edu.chl.proximity.Utilities.Constants;

/**
 * @author Hanna Römer
 * @date 2015-05-23
 * Represents the visuals for a play/pause button.
 */
public class PlayPauseButton extends BoardObject {
    private static Image pauseImage= new Image(Constants.filePath + "Buttons/PauseButton.png");
    private static Image playImage= new Image(Constants.filePath + "Buttons/PlayButton.png");
    private static int height=50;
    private static int width=50;

    /**
     * Create a new Play/Pause button
     * @param position What position the button shall have
     */
    public PlayPauseButton(Vector2 position){
        super(position,pauseImage,0,width,height);
    }

    /**
     * Toggles the image of the button between a pause and a play image.
     */
    public void toggle(){
        if(super.getImage().equals(pauseImage)){
            setImage(playImage);
        }else{
            setImage(pauseImage);
        }
    }

}
