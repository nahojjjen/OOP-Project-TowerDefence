package edu.chl.proximity.Models.ButtonsPanel;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import edu.chl.proximity.Models.BoardObject;
import edu.chl.proximity.Models.GameData;
import edu.chl.proximity.Models.Image;
import edu.chl.proximity.Models.PropertiesPanel.PropertiesPanel;
import edu.chl.proximity.Utilities.PointCalculations;

/**
 * @author Hanna Römer
 * @date 2015-04-22
 *
 * Class used for managing the buttons to the far bottom right. Keeps track of the game's speed
 *
 * 29/04 modified by Hanna. Buttons themselves now keeps track if what image they should have.
 */
public class ButtonPanel extends BoardObject {
    private static Image background = null;
    private static int width=300;
    private static int height=70;

    private static Vector2 position= new Vector2(Gdx.graphics.getWidth()-width, Gdx.graphics.getHeight()-height);
    private static Vector2 ppPos=new Vector2(position.x+20, position.y);
    private static Vector2 sPos=new Vector2(position.x+80, position.y);
    private static Vector2 prPos=new Vector2(position.x+150, position.y);

    private boolean pause=false;
    private int speed=1;

    private PlayPauseButton ppButton=new PlayPauseButton(ppPos);
    private SpeedButton speedButton=new SpeedButton(sPos);
    private PropertiesButton prButton=new PropertiesButton(prPos);

    private PropertiesPanel pPanel = new PropertiesPanel();


    /**
     * Create a new instance of ButtonPanel
     */
    public ButtonPanel() {
        super(position, background, 0, width, height);
        GameData.getInstance().setGameSpeed(speed);
    }

    /**
     * Get wether or not the game is paused
     * @return true if game is paused, false otherwise
     */
    public boolean isPaused(){
        return pause;
    }

    /**
     * Get which button is on speciefies position, if any are
     * @param position position to be checked for buttons
     * @return button on specified position. If there is no button there, null is returned
     */
    public BoardObject getButtonOnPosition(Vector2 position){
        if(PointCalculations.isPointInObject(position, ppButton)){
            return ppButton;
        }else if(PointCalculations.isPointInObject(position, speedButton)){
            return speedButton;
        }else if(PointCalculations.isPointInObject(position, prButton)){
            return prButton;
        }
        return null;
    }

    /**
     * Called if the pause/play button is pressed. Toggles the button and pauses/playes the game.
     */
    public void pressedPausePlay(){
        if(pause){
            pause=false;
            GameData.getInstance().setGameSpeed(speed);
        }else{
            pause=true;
            GameData.getInstance().setGameSpeed(0);

        }

    }

    /**
     * Pauses game
     */
    public void pauseGame(){
        if(!pause){
            pause=true;
            GameData.getInstance().setGameSpeed(0);
        }
    }

    /**
     * Starts the game again if it's paused
     */
    public void startGame(){
        if(pause){
            pause=false;
            GameData.getInstance().setGameSpeed(speed);
        }
    }

    /**
     * Called if the Speed button is pressed. Increases speed and toggles button.
     * If speed is greater than 3 it is set to 1.
     */
    public void pressedSpeedButton(){
        if(speed==2){
            speed=1;
        }else{
            speed=2;
        }
        if(pause){
            pressedPausePlay();
        }
        GameData.getInstance().setGameSpeed(speed);
    }

    /**
     * Called if the propertiesButton is pressed
     * Pauses game and tells the properiespanel to be visible
     */
    public void pressedPropertiesButton(){
        pauseGame();
        GameData.getInstance().getPropertiesPanel().setVisability(true);
    }


    /**
     * Render the buttonPanel
     * @param batch What batch to render the buttonPanel
     */
    public void render(SpriteBatch batch){
        super.render(batch);
        ppButton.render(batch);
        speedButton.render(batch);
        prButton.render(batch);
    }

}
