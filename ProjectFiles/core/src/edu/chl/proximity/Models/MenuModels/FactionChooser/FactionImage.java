package edu.chl.proximity.Models.MenuModels.FactionChooser;

import edu.chl.proximity.Utilities.ProximityVector;
import edu.chl.proximity.Models.BoardObject;
import edu.chl.proximity.Models.Map.Maps.Map;
import edu.chl.proximity.Models.Utils.Image;

/**
 * @author Hanna Römer
 * @date 2015-05-01
 */
public class FactionImage extends BoardObject{
    private static Image image= null;

    public FactionImage(ProximityVector position){
        super(position,image,180);
    }

}
