package edu.chl.proximity.Models.MenuModels;

import edu.chl.proximity.Models.BoardObject;
import edu.chl.proximity.Models.Utils.Image;
import edu.chl.proximity.Utilities.Constants;
import edu.chl.proximity.Utilities.ProximityVector;

/**
 * @author Hanna Romer
 * @date 2015-04-25
 *
 * 21/05 modified by Hanna Romer. Removed map in constructor.
 */
public class StartButton extends BoardObject{
    private static Image image=new Image(Constants.FILE_PATH + "Buttons/startButton.png");

    public StartButton(ProximityVector pos){
        super(pos,image,0);
    }
}
