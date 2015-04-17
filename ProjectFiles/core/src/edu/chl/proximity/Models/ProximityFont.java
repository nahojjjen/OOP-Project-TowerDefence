package edu.chl.proximity.Models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

/**
 * @author Linda Evaldsson
 * @date 2015-04-17
 */
public class ProximityFont {


    private BitmapFont font;
    private String str;
    private Vector2 position;

    public ProximityFont(Vector2 position, String s) {
        str = s;
        this.position = position;
        font = new BitmapFont();

    }

    public void draw(SpriteBatch batch) {
        font.draw(batch, str, position.x, Gdx.graphics.getHeight() - position.y);
    }
    public void setText(String s) {
        str = s;
    }

}
