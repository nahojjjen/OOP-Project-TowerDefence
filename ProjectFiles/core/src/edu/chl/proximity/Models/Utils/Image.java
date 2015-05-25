package edu.chl.proximity.Models.Utils;

import com.badlogic.gdx.graphics.Texture;
import edu.chl.proximity.Utilities.Constants;
import edu.chl.proximity.Utilities.TestChecker;

import java.io.File;
import java.util.HashMap;

/**
 * @author Linda Evaldsson
 * @date 2015-04-02
 *
 * A service for handling images in Proximity
 *
 * 08/05 modified by Linda Evaldsson. Added renderRepatedly-method.
 */
public class Image implements Cloneable {

    private Texture texture;
    private static HashMap <String, Texture> cache = new HashMap<String, Texture>();

    public Image(String s) {

        //We cannot create images under unit tests
        if (TestChecker.isJUnitTest()) {
            return;
        }

        if (cache.containsKey(s)) {
            texture = cache.get(s);
        }
        else {
            texture = new Texture(Constants.getFile(s));
            texture = new Texture(s);
            cache.put(s, texture);
        }

        texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
    }

    public Image(File f) {
        this(f.getPath());
    }

    /*
    public void render(ProximityBatch batch, ProximityVector p, double angle) {
        batch.draw(texture, (int) p.x, (int) p.y, texture.getWidth() / 2f,
                texture.getHeight() / 2f, texture.getWidth(), texture.getHeight(),
                1, 1, (int) angle, 0, 0, texture.getWidth(), texture.getHeight(), false, false);
    }


    public void renderRepeatedly(SpriteBatch batch, ProximityVector p, int width, int height) {
        texture.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
        batch.draw(texture, p.x, p.y, texture.getWidth(), texture.getHeight(), width, height);
    }*/
    public Texture getTexture(){ return texture; }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

}
