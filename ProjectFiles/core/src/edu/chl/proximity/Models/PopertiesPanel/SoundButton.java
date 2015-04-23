package edu.chl.proximity.Models.PopertiesPanel;

import com.badlogic.gdx.math.Vector2;
import edu.chl.proximity.Models.BoardObject;
import edu.chl.proximity.Models.Image;
import edu.chl.proximity.Utilities.Constants;

/**
 * @author Hanna Römer
 * @date 2015-04-23
 */
public class SoundButton extends BoardObject{
    private static Image onImage= new Image(Constants.filePath + "Buttons/SoundOnButton.png");
    private static Image offImage= new Image(Constants.filePath + "Buttons/SoundOffButton.png");
    private static int height=50;
    private static int width=50;

    public SoundButton(Vector2 position){
        super(position,onImage,0,width,height);
    }

    public void setSoundOn(){
        setImage(onImage);
    }

    public void setSoundOff(){
        setImage(offImage);
    }

}
