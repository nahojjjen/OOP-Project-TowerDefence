package edu.chl.proximity.Models;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.awt.*;

/**
 * Created by Floompa on 2015-04-02.
 */
public class Image {

    private Texture texture;

    public Image(String s) {
        texture = new Texture(s);
    }
    public Image(Texture t) {
        texture = t;
    }

    public void render(SpriteBatch batch, Point p, double angle) { //TODO: make sure that render draws with the Point as center instead of upper left corner
        batch.draw(texture, (int)p.getX(), (int)p.getY(), texture.getWidth()/2,
                texture.getHeight()/2, texture.getWidth(), texture.getHeight(),
                1, 1, (int)angle, 0, 0, texture.getWidth(), texture.getHeight(), false, false);
    }

    public Texture getTexture(){ return texture; }
}
