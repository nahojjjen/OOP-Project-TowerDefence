package edu.chl.proximity.Models.ControlPanel.TowerPanel;

import edu.chl.proximity.Models.BoardObject;
import edu.chl.proximity.Models.Map.Maps.Map;
import edu.chl.proximity.Models.Utils.Image;
import edu.chl.proximity.Utilities.Constants;
import edu.chl.proximity.Utilities.ProximityVector;

/**
 * @author Hanna Romer
 * @date 2015-05-18
 *
 * A simple button for selling towers.
 */
public class SellButton extends BoardObject{

    private static Image image=new Image(Constants.FILE_PATH + "Resources/trash.png");

    public SellButton(ProximityVector pos, Map map){
        super(pos,image,0);
    }

}
