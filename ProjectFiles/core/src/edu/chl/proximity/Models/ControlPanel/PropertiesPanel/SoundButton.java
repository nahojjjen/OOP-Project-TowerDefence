package edu.chl.proximity.Models.ControlPanel.PropertiesPanel;

import edu.chl.proximity.Utilities.ProximityVector;
import edu.chl.proximity.Models.BoardObject;
import edu.chl.proximity.Models.Map.Maps.Map;
import edu.chl.proximity.Models.Utils.Image;
import edu.chl.proximity.Utilities.Constants;

/**
 * @author Hanna R�mer
 * @date 2015-04-23
 */
public class SoundButton extends BoardObject{
    private static Image onImage= new Image(Constants.FILE_PATH + "Buttons/SoundOnButton.png");
    private static Image offImage= new Image(Constants.FILE_PATH + "Buttons/SoundOffButton.png");
    private static int height=50;
    private static int width=50;

    /**
     * Create a new sound button
     * @param position At what position the button is to be placed
     */
    public SoundButton(ProximityVector position){
        super(null, position,onImage,0,width,height);
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
