package edu.chl.proximity.Models.ControlPanel.ButtonsPanel;

import edu.chl.proximity.Models.BoardObject;
import edu.chl.proximity.Models.Player.Players.GameData;
import edu.chl.proximity.Models.Utils.Image;
import edu.chl.proximity.Models.Utils.ProximityBatch;
import edu.chl.proximity.Utilities.Constants;
import edu.chl.proximity.Utilities.ProximityVector;

/**
 * @author Hanna Romer
 * @date 2015-04-29
 *
 * Represents a Play-button. Keeps track itself what it's image should be depending on the current game-speed.
 *
 * ---
 * 28/05 modified by Hanna Romer. Added comments.
 */
public class PlayButton extends BoardObject{
    public static final Image upImage = new Image(Constants.FILE_PATH + "Buttons/PlayButton.png");
    public static final Image downImage = new Image(Constants.FILE_PATH + "Buttons/DownPlayButton.png");
    private static int height=50;
    private static int width=50;

    /**
     * Create a new Play button
     * @param position What position the button shall have
     */
    public PlayButton(ProximityVector position){
        super(position,upImage,0,width,height);
    }

    /**
     * Sets image depending on what the current game-speed is
     */
    public void setRightImage(){
        if(GameData.getInstance().getPlayer().getSettings().getGameSpeed()==1){
            super.setImage(downImage);
        }else{
            super.setImage(upImage);
        }
    }

    /**
     * Renders the button with what image it should currently have
     * @param batch what batch to render the PauseButton
     */
    public void render(ProximityBatch batch){
        setRightImage();
        super.render(batch);
    }

}
