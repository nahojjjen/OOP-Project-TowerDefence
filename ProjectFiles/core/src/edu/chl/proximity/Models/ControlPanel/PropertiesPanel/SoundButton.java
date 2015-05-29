package edu.chl.proximity.Models.ControlPanel.PropertiesPanel;

import edu.chl.proximity.Models.BoardObject;
import edu.chl.proximity.Models.Utils.Image;
import edu.chl.proximity.Utilities.Constants;
import edu.chl.proximity.Utilities.ProximityVector;

/**
 * @author Hanna Romer
 * @date 2015-04-23
 *
 * Represent the sound button that toggles sound on/off
 */
public class SoundButton extends BoardObject{
    private static Image onImage;   //= new Image(Constants.FILE_PATH + "Buttons/SoundOnButton.png");
    private static Image offImage;   //= new Image(Constants.FILE_PATH + "Buttons/SoundOffButton.png");
    private static int height=50;
    private static int width=50;

    /**
     * Create a new sound button
     * @param position At what position the button is to be placed
     */
    public SoundButton(ProximityVector position, Image onImage, Image offImage){
        super(position,onImage,0,width,height);
        this.onImage=onImage;
        this.offImage=offImage;
    }

    public boolean isSoundOn() {
        return onImage == getImage();
    }
    /**
     * Set the button's image to the one for sound on
     */
    public void setSoundOn(){
        setImage(onImage);
    }

    /**
     * Set the button's image to the one for sound off
     */
    public void setSoundOff(){
        setImage(offImage);
    }

}
