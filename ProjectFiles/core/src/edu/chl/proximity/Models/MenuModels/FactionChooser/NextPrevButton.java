package edu.chl.proximity.Models.MenuModels.FactionChooser;

import edu.chl.proximity.Models.BoardObject;
import edu.chl.proximity.Models.Utils.Image;
import edu.chl.proximity.Utilities.ProximityVector;

/**
 * @author Hanna Romer
 * @date 2015-05-01
 *
 * A class for handling a next or previous button
 */
public class NextPrevButton extends BoardObject{
    public NextPrevButton(ProximityVector pos, Image image){
        super(pos,image,0);
    }
}
