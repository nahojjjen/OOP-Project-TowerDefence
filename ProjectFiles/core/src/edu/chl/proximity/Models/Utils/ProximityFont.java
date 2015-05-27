package edu.chl.proximity.Models.Utils;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import edu.chl.proximity.Utilities.Constants;
import edu.chl.proximity.Utilities.ProximityVector;
import edu.chl.proximity.Utilities.TestChecker;

import java.util.HashMap;

/**
 * @author Linda Evaldsson
 * @date 2015-04-17
 * A class defining text in the game, is has a font, position and a string
 *
 * Unknown date modified by Johan Swanberg
 * 08/05 modified by Linda Evaldsson. Added scale method.
 * 19/05 modified by Linda Evaldsson. Changed so this class uses FreeTypeFontGenerator to create fonts which makes changin sizes easier. Scaling removed.
 */
public class ProximityFont implements ProximityDisposable {

    private BitmapFont font;
    private String str;
    private ProximityVector position;
    private FreeTypeFontGenerator generator;
    private FreeTypeFontGenerator.FreeTypeFontParameter parameter;

    private Color color = Color.WHITE;
    private int size = 14;


    private static HashMap<String, BitmapFont> cache = new HashMap<String, BitmapFont>();


    /**
     * create a new text on the given position, and the given message
     * @param position where the top left corner of the text should start
     * @param s what the text should say
     */
    public ProximityFont(ProximityVector position, String s) {
        str = s;
        this.position = position;

        if (!TestChecker.isJUnitTest()) {
            generator = new FreeTypeFontGenerator(Constants.getFile(Constants.FILE_PATH + "Fonts/Roboto-Regular.ttf"));
            parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
            parameter.flip = true;
            generateFont();
        }
        DisposableCollector.add(this);
    }

    private void generateFont() {

        if (cache.containsKey(str+position.hashCode())) {
            font = cache.get(str+position.hashCode());
        }
        else {
            parameter.size = size;
            parameter.color = color;
            font = generator.generateFont(parameter);
            cache.put(str+position.hashCode(), font);
        }
    }

    public void setSize(int size) {
        this.size = size;
        if(!TestChecker.isJUnitTest())
            generateFont();
    }

    public void setColor(Color color) {
        this.color = color;
        generateFont();
    }

    public void dispose() {
        generator.dispose();

    }

    public void setPosition(ProximityVector newPosition) {
        this.position = newPosition;
    }

    public ProximityVector getPosition() {
        return position;
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
