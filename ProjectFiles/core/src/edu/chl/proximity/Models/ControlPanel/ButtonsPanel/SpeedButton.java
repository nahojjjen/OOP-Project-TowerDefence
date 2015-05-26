package edu.chl.proximity.Models.ControlPanel.ButtonsPanel;

import edu.chl.proximity.Models.BoardObject;
import edu.chl.proximity.Models.Player.Players.GameData;
import edu.chl.proximity.Models.Utils.Image;
import edu.chl.proximity.Models.Utils.ProximityBatch;
import edu.chl.proximity.Utilities.Constants;
import edu.chl.proximity.Utilities.ProximityVector;

/**
 * @author Hanna Romer
 * @date 2015-04-23
 * Represents the visuals for a speed button
 *
 * 29/04 modified Hanna Romer. Button keeps track of what image it should have
 */
public class SpeedButton extends BoardObject{
    private static Image upImage=new Image(Constants.FILE_PATH + "Buttons/FastPlayButton.png");
    private static Image downImage=new Image(Constants.FILE_PATH + "Buttons/DownFastPlayButton.png");
    private static int height=50;
    private static int width=50;

    /**
     * Create a new speed button
     * @param position What position the button shall have
     */
    public SpeedButton(ProximityVector position){
        super(position,upImage,0,width,height);
    }

    /**
     * Sets image depending on what the current game-speed is
     */
    public void setRightImage(){
        if(GameData.getInstance().getPlayer().getSettings().getGameSpeed()==2){
            super.setImage(downImage);
        }else{
            super.setImage(upImage);
        }
    }

    /**
     * Renders the button with what image it should currently have
     * @param batch what batch to render the SpeedButton
     */
    public void render(ProximityBatch batch){
        setRightImage();
        super.render(batch);
    }
}
