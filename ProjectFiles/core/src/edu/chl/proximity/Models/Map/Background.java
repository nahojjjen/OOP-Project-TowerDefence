package edu.chl.proximity.Models.Map;

import edu.chl.proximity.Models.Utils.Image;
import edu.chl.proximity.Utilities.ProximityVector;
import edu.chl.proximity.Models.BoardObject;
import edu.chl.proximity.Models.Map.Maps.Map;

/**
 * @author Johan Swanberg
 * @date 2015-04-12
 *
 * A background for the game
 *
 */
public class Background extends BoardObject {
    /**
     * create a new background object
     *
     * @param img      the image of the object
     */
    public Background(Image img) {
        super(new ProximityVector(0,0), img, 0);
    }


}
