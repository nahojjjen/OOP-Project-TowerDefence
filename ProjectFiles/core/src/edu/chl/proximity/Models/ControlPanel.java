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
 * @revised by Johan
 * @date 2015-04-17
 * The class managing the information to the right of the screen
 */
public class ControlPanel {



    private ProximityFont healthText;
    private ProximityFont linesText;
    private int width = Gdx.graphics.getWidth() - 300; //where the left corner of the controllpanel should start in x- coordinate
    private int height;
    private Vector2 position;
    private Image background = new Image(Constants.filePath + "Backgrounds/temporaryControlPanelBackground.png");

    /**
     * Create a new instance of the controll panel
     */
    public ControlPanel() {
        this.position = new Vector2(width, 0); //set the top left corner of the control panel;
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

    /**
     * create a new text within the controlpanel
     * @param x x coordinate within the control panel
     * @param y y coordinate within the control panel
     * @param s what the string should say
     * @return a font corresponding to this information
     */
    private ProximityFont createFont(float x, float y, String s){
        //return new ProximityFont(new Vector2(Gdx.graphics.getWidth()-width + x, y), s);
        return new ProximityFont(new Vector2(width + x, y), s);
    }

    /**
     * Show the Controlpanel
     * @param batch what batch to render the controlpanel
     */
    public void render(SpriteBatch batch) {
        background.render(batch, position, 0);
        healthText.draw(batch);
    }


}
