package edu.chl.proximity.Models.PopertiesPanel;

import edu.chl.proximity.Models.BoardObject;
import edu.chl.proximity.Models.Image;
import edu.chl.proximity.Utilities.Constants;

/**
 * @author Hanna Römer
 * @date 2015-04-23
 */
public class SoundButton extends BoardObject{
    private static Image onImage= new Image(Constants.filePath + "Buttons/PauseButton.png");
    private static Image offImage= new Image(Constants.filePath + "Buttons/PlayButton.png");
    private static int height=50;
    private static int width=50;

}
