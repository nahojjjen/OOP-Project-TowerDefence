package edu.chl.proximity.Models.ControlPanel.ButtonsPanel;

import edu.chl.proximity.Models.BoardObject;
import edu.chl.proximity.Models.Utils.Image;
import edu.chl.proximity.Utilities.Constants;
import edu.chl.proximity.Utilities.ProximityVector;

/**
 * @author Hanna Romer
 * @date 2015-04-23
 * Represents the  visuals for a properties button
 */
public class PropertiesButton extends BoardObject{
    private static Image image=new Image(Constants.FILE_PATH + "Buttons/PropButton.png");
    private static int height=50;
    private static int width=50;

    public PropertiesButton(ProximityVector position){ super(position,image,0,width,height);}

}
