package edu.chl.proximity.Models.Utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

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
    private Vector2 position;

    /**
     * create a new text on the given position, and the given message
     * @param position where the top left corner of the text should start
     * @param s what the text should say
     */
    public ProximityFont(Vector2 position, String s) {
        str = s;
        this.position = position;
        font = new BitmapFont(true);

    }

    public void scale(float scale) {

        font.scale(scale);
    }

    /**
     * draw out the text on the screen
     * @param batch what batch to use when drawing the text
     */
    public void draw(SpriteBatch batch) {
        font.draw(batch, str, position.x, position.y);
    }

    /**
     * change the text to a new string
     * @param s what string the text should display
     */
    public void setText(String s) {
        str = s;
    }

}
