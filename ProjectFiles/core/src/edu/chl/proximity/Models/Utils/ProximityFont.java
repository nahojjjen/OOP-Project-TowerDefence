package edu.chl.proximity.Models.Utils;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import edu.chl.proximity.Utilities.ProximityBatch;
import edu.chl.proximity.Utilities.ProximityVector;
import edu.chl.proximity.Utilities.TestChecker;

/**
 * @author Linda Evaldsson
 * @date 2015-04-17
 * A class defining text in the game, is has a font, position and a string
 *
 * Unknown date modified by Johan Swanberg
 * 08/05 modified by Linda Evaldsson. Added scale method.
 */
public class ProximityFont {


    private BitmapFont font;
    private String str;
    private ProximityVector position;

    /**
     * create a new text on the given position, and the given message
     * @param position where the top left corner of the text should start
     * @param s what the text should say
     */
    public ProximityFont(ProximityVector position, String s) {
        str = s;
        this.position = position;
        if (!TestChecker.isJUnitTest()) {
            font = new BitmapFont(true);
        }

    }

    public void scale(float scale) {
        if(font != null) {
            font.scale(scale);
        }
    }

    public String getText() {
        return str;
    }


    /**
     * draw out the text on the screen
     * @param batch what batch to use when drawing the text
     */
    public void draw(ProximityBatch batch) {
        batch.render(font, str, position);
    }

    /**
     * change the text to a new string
     * @param s what string the text should display
     */
    public void setText(String s) {
        str = s;
    }

}
