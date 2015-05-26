package edu.chl.proximity.Models.MenuModels.FactionChooser;

import edu.chl.proximity.Models.BoardObject;
import edu.chl.proximity.Models.Utils.Image;
import edu.chl.proximity.Utilities.ProximityVector;

/**
 * @author Hanna Romer
 * @date 2015-05-01
 */
public class FactionImage extends BoardObject{
    private static Image image= null;

    public FactionImage(ProximityVector position){
        super(position,image,180);
    }

}
