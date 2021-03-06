package edu.chl.proximity.Models.ControlPanel.TowerPanel;

import edu.chl.proximity.Models.BoardObject;
import edu.chl.proximity.Models.Utils.Image;
import edu.chl.proximity.Models.Utils.ProximityBatch;
import edu.chl.proximity.Utilities.Constants;
import edu.chl.proximity.Utilities.ProximityVector;

/**
 * @author Hanna Romer
 * @date 2015-05-08
 *
 * A class that represents the Upgrade button pressed to upgrade a tower
 */
public class UpgradeButton extends BoardObject{
    private static Image image=new Image(Constants.FILE_PATH + "Towers/Missile/1.png");
    public UpgradeButton(ProximityVector pos){
        super(pos, image,0);
    }
    public void render(ProximityBatch batch){
        super.render(batch);
    }
}
