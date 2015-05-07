package test.edu.chl.proximity.Models;

import com.badlogic.gdx.math.Vector2;
import edu.chl.proximity.Models.BoardObject;

/**
 * @author Johan on 2015-05-02.
 * this is a class that simply enables you to create instances of the abstract class boardObject, for junit testing
 * this class should never be created in non-testing scenarios.
 */
public class BoardObjectConcreteTest extends BoardObject {
    public BoardObjectConcreteTest(Vector2 position, edu.chl.proximity.Models.Utils.Image img, double angle){
        super(position,img,angle);
    }

}
