package test.edu.chl.proximity.Models;

import edu.chl.proximity.Models.BoardObject;
import edu.chl.proximity.Utilities.ProximityVector;

/**
 * @author Johan on 2015-05-02.
 * this is a class that simply enables you to create instances of the abstract class boardObject, for junit testing
 * this class should never be created in non-testing scenarios.
 *
 * 13/05 Modified by Simon Gislen to update BoardObject constructor
 */
public class BoardObjectConcreteTest extends BoardObject {
    public BoardObjectConcreteTest(ProximityVector position, edu.chl.proximity.Models.Utils.Image img, double angle, int width, int height){
        super(position,img,angle, width, height);
    }

}
