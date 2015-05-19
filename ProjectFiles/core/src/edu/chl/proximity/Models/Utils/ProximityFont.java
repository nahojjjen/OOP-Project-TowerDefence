package edu.chl.proximity.Models.Utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import edu.chl.proximity.Utilities.Constants;
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
 * 19/05 modified by Linda Evaldsson. Changed so this class uses FreeTypeFontGenerator to create fonts which makes changin sizes easier. Scaling removed.
 */
public class ProximityFont {

    private BitmapFont font;
    private String str;
    private ProximityVector position;
    private FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal(Constants.FILE_PATH + "Fonts/Roboto-Regular.ttf"));
    private FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();

    private Color color = Color.WHITE;
    private int size = 14;

    /**
     * create a new text on the given position, and the given message
     * @param position where the top left corner of the text should start
     * @param s what the text should say
     */
    public ProximityFont(ProximityVector position, String s) {
        str = s;
        this.position = position;
        parameter.flip = true;

        if (!TestChecker.isJUnitTest()) {
            generateFont();
        }
    }

    private void generateFont() {
        parameter.size = size;
        parameter.color = color;
        font = generator.generateFont(parameter);
    }

    public void setSize(int size) {
        this.size = size;
        generateFont();
    }

    public void setColor(Color color) {
        this.color = color;
        generateFont();
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
