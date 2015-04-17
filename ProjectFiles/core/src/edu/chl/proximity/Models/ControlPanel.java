package edu.chl.proximity.Models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import edu.chl.proximity.Utilities.Constants;


/**
 * @author Linda Evaldsson
 * @date 2015-04-17
 */
public class ControlPanel {



    private int width = 300;
    private int height;
    private Vector2 position;
    private Image background = new Image(Constants.filePath + "Backgrounds/temporaryControlPanelBackground.png");

    /**
     * create a new board object
     *
     */
    public ControlPanel() {
        this.position = new Vector2(Gdx.graphics.getWidth() - width, 0);
        height = Gdx.graphics.getHeight();
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public void setHeight(int height) {
        this.height = height;
    }
    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public void setWidth(int width) {
        this.width = width;
    }
    /*public void render(ShapeRenderer shapeRenderer) {
        shapeRenderer.rect(position.x, position.y, width, height);

    }*/

    public void render(SpriteBatch batch) {
        background.render(batch, position, 0);
        batch.end();
        SpriteBatch newBatch = new SpriteBatch();
        newBatch.begin();
        CharSequence str = "Hello World!";
        BitmapFont font = new BitmapFont();
        font.draw(newBatch, str, 10, 10);
        newBatch.end();
        batch.begin();
    }


}
