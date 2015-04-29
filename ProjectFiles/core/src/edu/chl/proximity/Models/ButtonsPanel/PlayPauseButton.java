package edu.chl.proximity.Models.ButtonsPanel;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import edu.chl.proximity.Models.BoardObject;
import edu.chl.proximity.Models.GameData;
import edu.chl.proximity.Models.Image;
import edu.chl.proximity.Utilities.Constants;

/**
 * @author Hanna Römer
 * @date 2015-05-23
 * Represents the visuals for a play/pause button.
 *
 * 29/04 modified by Hanna. The button now keeps track of what image it should have by itself
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
     * Sets image depending on what the current game-speed is
     */
    public void setRightImage(){
        if(GameData.getInstance().getGameSpeed()==0){
            setImage(playImage);
        }else{
            setImage(pauseImage);
        }
    }

    /**
     * Renders the button with what image it should currently have
     * @param batch what batch to render the PlayPauseButton
     */
    public void render(SpriteBatch batch){
        setRightImage();
        super.render(batch);
    }

}
