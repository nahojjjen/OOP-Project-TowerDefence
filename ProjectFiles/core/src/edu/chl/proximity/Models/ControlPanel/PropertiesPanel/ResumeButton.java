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
public class ResumeButton  extends BoardObject{
    private static Image image=new Image(Constants.FILE_PATH + "Buttons/ResumeButton.png");

    public ResumeButton(ProximityVector position){
        super(position,image,0);
    }

}
