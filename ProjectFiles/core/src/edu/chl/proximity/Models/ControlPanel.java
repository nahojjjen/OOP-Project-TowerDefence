package edu.chl.proximity.Models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import edu.chl.proximity.Models.ResourceSystem.Resources;
import edu.chl.proximity.Utilities.Constants;


/**
 * @author Linda Evaldsson
 * @date 2015-04-17
 */
public class ControlPanel {



    private ProximityFont healthText;
    private ProximityFont linesText;
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
        healthText = createFont(0, 0, "null");
        linesText = createFont(30, 30, "null");
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

    public void setHealth(int percent){
        healthText.setText("Liv: " + percent + "%");
    }
    public void setResources(Resources resources){
        linesText.setText("Lines: " + resources.getLines());

    }

    public void setWidth(int width) {
        this.width = width;
    }

    private ProximityFont createFont(float x, float y, String s){
        return new ProximityFont(new Vector2(Gdx.graphics.getWidth()-width + x, y), s);
    }
    /*public void render(ShapeRenderer shapeRenderer) {
        shapeRenderer.rect(position.x, position.y, width, height);

    }*/

    public void render(SpriteBatch batch) {
        background.render(batch, position, 0);
        /*
        batch.end();
        SpriteBatch newBatch = new SpriteBatch();
        newBatch.begin();
        healthText.draw(newBatch);
        newBatch.end();*/
        healthText.draw(batch);
    }
    public void renderText(SpriteBatch batch) {

    }


}
