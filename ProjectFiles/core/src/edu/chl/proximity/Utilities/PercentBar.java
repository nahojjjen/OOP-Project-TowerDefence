package edu.chl.proximity.Utilities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

/**
 * @author Linda Evaldsson
 * @date 2015-05-07
 */
public class PercentBar {

    private Vector2 position;
    int width;
    int height;
    private Color borderColor;
    private Color foregroundColor;
    private Color backgroundColor;
    private int border = 0;
    private int percent = 100;

    public PercentBar(Vector2 position, int width, int height, Color borderColor, Color foregroundColor, Color backgroundColor) {
        this.position = position;
        this.width = width;
        this.height = height;
        this.borderColor = borderColor;
        this.foregroundColor = foregroundColor;
        this.backgroundColor = backgroundColor;

    }

    public void setPercent(int newPercent) {
        if(newPercent >= 0) {
            percent = newPercent;
        } else {
            percent = 0;
        }
    }



    public void render(ShapeRenderer renderer) {

        renderer.begin(ShapeRenderer.ShapeType.Filled);
        renderer.setColor(borderColor);
        renderer.rect(position.x, position.y, position.x, position.y, width, height, 1, 1, 0);

        renderer.setColor(backgroundColor);
        renderer.rect(position.x + border, position.y + border, position.x + border, position.y + border, width - border * 2, height - border * 2, 1, 1, 0);

        int foregroundWidth = (int)((width/100.0)*percent);
        renderer.setColor(foregroundColor);
        renderer.rect(position.x + border, position.y + border, position.x + border, position.y + border, foregroundWidth - border * 2, height - border * 2, 1, 1, 0);

        renderer.end();

    }
}
