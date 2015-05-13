package edu.chl.proximity.Models.MenuModels;

import edu.chl.proximity.Utilities.ProximityVector;
import edu.chl.proximity.Models.BoardObject;
import edu.chl.proximity.Models.Map.Maps.Map;
import edu.chl.proximity.Models.Utils.Image;
import edu.chl.proximity.Utilities.Constants;

/**
 * @author Hanna R�mer
 * @date 2015-04-25
 */
public class StartButton extends BoardObject{
    private static Image image=new Image(Constants.FILE_PATH + "Buttons/startButton.png");

    public StartButton(Map map, ProximityVector pos){
        super(pos,image,0);
    }
}
