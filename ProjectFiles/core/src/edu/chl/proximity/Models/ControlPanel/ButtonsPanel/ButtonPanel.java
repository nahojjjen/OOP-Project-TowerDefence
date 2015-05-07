package edu.chl.proximity.Models.ControlPanel.ButtonsPanel;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import edu.chl.proximity.Models.BoardObject;
import edu.chl.proximity.Models.Map.Maps.Map;
import edu.chl.proximity.Models.Utils.GameData;
import edu.chl.proximity.Models.Utils.Image;
import edu.chl.proximity.Models.ControlPanel.PropertiesPanel.PropertiesPanel;
import edu.chl.proximity.Utilities.PointCalculations;

/**
 * @author Hanna R�mer
 * @date 2015-04-22
 *
 * Class used for managing the buttons to the far bottom right. Keeps track of the game's speed
 *
 * 29/04 modified by Hanna Römer. Buttons themselves now keeps track if what image they should have. Replaced PLayPauseButton with a play and a pause button.
 */
public class ButtonPanel extends BoardObject {
    private static Image background = null;
    private static int width=300;
    private static int height=70;

    private static Vector2 position= new Vector2(Gdx.graphics.getWidth()-width, Gdx.graphics.getHeight()-height);
    private static Vector2 pausePos=new Vector2(position.x+20, position.y);
    private static Vector2 playPos=new Vector2(pausePos.x+60, position.y);
    private static Vector2 speedPos=new Vector2(playPos.x+60, position.y);
    private static Vector2 propPos=new Vector2(speedPos.x+60, position.y);

    private boolean pause=false;
    private int speed=1;

    private PlayButton playButton;
    private PauseButton pauseButton;
    private SpeedButton speedButton;
    private PropertiesButton prButton;
    private PropertiesPanel propertiesPanel;

    /**
     * Create a new instance of ButtonPanel
     */
    public ButtonPanel(Map map, PropertiesPanel propertiesPanel) {
        super(map, position, background, 0, width, height);
        GameData.getInstance().getPlayer().getSettings().setGameSpeed(speed);
        this.propertiesPanel = propertiesPanel;
        playButton=new PlayButton(map, playPos);
        pauseButton=new PauseButton(map, pausePos);
        speedButton=new SpeedButton(map, speedPos);
        prButton=new PropertiesButton(map, propPos);
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
        if(PointCalculations.isPointInObject(position, playButton)) {
            return playButton;
        }else if(PointCalculations.isPointInObject(position,pauseButton)){
            return pauseButton;
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
            GameData.getInstance().getPlayer().getSettings().setGameSpeed(speed);
        }else{
            pause=true;
            GameData.getInstance().getPlayer().getSettings().setGameSpeed(0);

        }
    }
    public void pressedPlay(){
        GameData.getInstance().getPlayer().getSettings().setGameSpeed(1);
    }
    public void pressedPause(){
        GameData.getInstance().getPlayer().getSettings().setGameSpeed(0);
        playButton.setRightImage();
        speedButton.setRightImage();
    }

    /**
     * Pauses game
     */
    public void pauseGame(){
        if(!pause){
            pause=true;
            GameData.getInstance().getPlayer().getSettings().setGameSpeed(0);
        }
    }

    /**
     * Starts the game again if it's paused
     */
    public void startGame(){
        if(pause){
            pause=false;
            GameData.getInstance().getPlayer().getSettings().setGameSpeed(speed);
        }
    }

    /**
     * Called if the Speed button is pressed. Increases speed and toggles button.
     * If speed is greater than 2 it is set to 1.
     */
    public void pressedSpeedButton(){
        GameData.getInstance().getPlayer().getSettings().setGameSpeed(2);
    }

    /**
     * Called if the propertiesButton is pressed
     * Pauses game and tells the properiespanel to be visible
     */
    public void pressedPropertiesButton(){

        pressedPause();
        propertiesPanel.setVisability(true);
    }


    /**
     * Render the buttonPanel
     * @param batch What batch to render the buttonPanel
     */
    public void render(SpriteBatch batch){
        super.render(batch);
        playButton.render(batch);
        pauseButton.render(batch);
        speedButton.render(batch);
        prButton.render(batch);
    }

}
