package edu.chl.proximity.Models.WonLostModels;

import edu.chl.proximity.Models.BoardObject;
import edu.chl.proximity.Models.Utils.Image;
import edu.chl.proximity.Utilities.ProximityVector;

/**
 * @author Hanna Romer
 * @date 2015-05-17
 *
 * A Button for the Game Over Screen
 */
public class Button extends BoardObject{
    public Button(ProximityVector pos, Image image){
        super(pos, image, 0);
    }
}
