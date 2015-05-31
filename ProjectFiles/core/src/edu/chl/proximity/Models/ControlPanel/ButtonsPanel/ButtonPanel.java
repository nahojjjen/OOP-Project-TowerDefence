package edu.chl.proximity.Models.ControlPanel.ButtonsPanel;

import edu.chl.proximity.Models.BoardObject;
import edu.chl.proximity.Models.ControlPanel.PropertiesPanel.PropertiesPanel;
import edu.chl.proximity.Models.Player.Players.GameData;
import edu.chl.proximity.Models.Utils.Image;
import edu.chl.proximity.Models.Utils.ProximityBatch;
import edu.chl.proximity.Utilities.Constants;
import edu.chl.proximity.Utilities.ProximityVector;
import edu.chl.proximity.Utilities.TestChecker;

/**
 * @author Hanna Romer
 * @date 2015-04-22
 *
 * Class used for managing the buttons to the far bottom right that can change
 * speed and open the properties panel
 *
 * ---
 * 29/04 modified by Hanna Romer. Buttons themselves now keeps track if what image they should have. Replaced PLayPauseButton with a play and a pause button.
 * 28/05 modified by Hanna Romer. Added comments.
 */
public class ButtonPanel extends BoardObject {
    private static Image background = null;
    private static int width=300;
    private static int height=70;

    private static ProximityVector position= new ProximityVector(Constants.GAME_WIDTH-width, Constants.GAME_HEIGHT-height);
    private static ProximityVector pausePos=new ProximityVector(position.x+20, position.y);
    private static ProximityVector playPos=new ProximityVector(pausePos.x+60, position.y);
    private static ProximityVector speedPos=new ProximityVector(playPos.x+60, position.y);
    private static ProximityVector propPos=new ProximityVector(speedPos.x+70, position.y);

    private int speed=1;

    private PlayButton playButton;
    private PauseButton pauseButton;
    private SpeedButton speedButton;
    private PropertiesButton prButton;
    private PropertiesPanel propertiesPanel;

    /**
     * Create a new instance of ButtonPanel
     */
    public ButtonPanel(PropertiesPanel propertiesPanel) {
        super(position, background, 0, width, height);
        GameData.getInstance().getPlayer().getSettings().setGameSpeed(speed);
        this.propertiesPanel = propertiesPanel;
        playButton=new PlayButton(playPos);
        pauseButton=new PauseButton(pausePos);
        speedButton=new SpeedButton(speedPos);
        prButton=new PropertiesButton(propPos);
    }

    /**
     * Get which button is on speciefies position, if any are
     * @param position position to be checked for buttons
     * @return button on specified position. If there is no button there, null is returned
     */
    public BoardObject getButtonOnPosition(ProximityVector position){
        if(playButton.containsPoint(position)) {
            return playButton;
        }else if(pauseButton.containsPoint(position)){
            return pauseButton;
        }else if(speedButton.containsPoint(position)){
            return speedButton;
        }else if(prButton.containsPoint(position)){
            return prButton;
        }
        return null;
    }

    /**
     * Method only for testing
     * @return the play button
     */
    public PlayButton getPlayButton() {
        if(TestChecker.isJUnitTest()) {
            return playButton;
        }
        return null;
    }

    /**
     * Method only for testing
     * @return the pause button
     */
    public PauseButton getPauseButton() {
        if(TestChecker.isJUnitTest()) {
            return pauseButton;
        }
        return null;
    }

    /**
     * Method only for testing
     * @return the fast-forward button
     */
    public SpeedButton getSpeedButton() {
        if(TestChecker.isJUnitTest()) {
            return speedButton;
        }
        return null;
    }

    /**
     * Method only for testing
     * @return the properties button
     */
    public PropertiesButton getPropertiesButton() {
        if(TestChecker.isJUnitTest()) {
            return prButton;
        }
        return null;
    }

    /**
     * Called if the play-button is pressed.
     * Speed is set to 1.
     */
    public void pressedPlay(){
        GameData.getInstance().getPlayer().getSettings().setGameSpeed(1);
    }

    public void pressedPause(){
        if(GameData.getInstance().getPlayer().getSettings().getGameSpeed() != 0) {
            GameData.getInstance().getPlayer().getSettings().setGameSpeed(0);
            playButton.setRightImage();
            speedButton.setRightImage();
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
        propertiesPanel.setVisibility(true);
    }


    /**
     * Render the buttonPanel
     * @param batch What batch to render the buttonPanel
     */
    public void render(ProximityBatch batch){
        super.render(batch);
        playButton.render(batch);
        pauseButton.render(batch);
        speedButton.render(batch);
        prButton.render(batch);
    }

}
