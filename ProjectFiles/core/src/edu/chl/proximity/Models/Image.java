package edu.chl.proximity.Models;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.awt.*;

/**
 * @author Linda Evaldsson
 * @date 2015-04-02
 *
 */
public class Image {

    private Texture texture;

    public Image(String s) {
        texture = new Texture(s);
        texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
    }

    public Image(Texture t) {
        texture = t;

        texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Nearest);
    }

    public void render(SpriteBatch batch, Vector2 p, double angle) {
        batch.draw(texture, (int)p.x, (int)p.y, texture.getWidth()/2,
                texture.getHeight()/2, texture.getWidth(), texture.getHeight(),
                1, 1, (int)angle, 0, 0, texture.getWidth(), texture.getHeight(), false, false);
    }

    public Texture getTexture(){ return texture; }
}
