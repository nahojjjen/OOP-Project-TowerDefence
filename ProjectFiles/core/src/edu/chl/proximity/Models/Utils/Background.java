package edu.chl.proximity.Models.Utils;

import com.badlogic.gdx.math.Vector2;
import edu.chl.proximity.Models.BoardObject;

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
        super(new Vector2(0,0), img, 0);
    }

    /**
     * make the map rotate slowly
     */
    public void rotate(){
        rotate(1);
    }

}
