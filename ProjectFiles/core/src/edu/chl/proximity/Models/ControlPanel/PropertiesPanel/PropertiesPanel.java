package edu.chl.proximity.Models.ControlPanel.PropertiesPanel;

import edu.chl.proximity.Models.BoardObject;
import edu.chl.proximity.Models.ControlPanel.ButtonsPanel.PropertiesButton;
import edu.chl.proximity.Models.Player.Players.GameData;
import edu.chl.proximity.Models.Utils.Image;
import edu.chl.proximity.Models.Utils.ProximityBatch;
import edu.chl.proximity.Models.Utils.ProximityFont;
import edu.chl.proximity.Models.Utils.Settings;
import edu.chl.proximity.Utilities.Constants;
import edu.chl.proximity.Utilities.ProximityVector;
import edu.chl.proximity.Utilities.TestChecker;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Hanna Romer
 * @date 2015-04-23
 *
 * 24/04 modified by Hanna Romer. Added SoundButton, MainMenuButton and Sound-bars
 * 29/04 modified by Hanna Romer. Made into singleton.
 * 01/05 modified by Hanna Romer. MainMenuButton now takes you to the mainMenu
 * 24/05 modified by Linda Evaldsson. Fixed the design of this panel. Also fixed toggling music issue (it wasnt working properly).
 *
 */
public class PropertiesPanel extends BoardObject{

    //The PropertiesPanel data
    private static Image background = new Image(Constants.FILE_PATH + "Backgrounds/square.png");
    private static int width = 300;
    private static int height = 400;
    private static ProximityVector position=new ProximityVector((Constants.GAME_WIDTH-300-width)/2f, (Constants.GAME_HEIGHT-100-height)/2f);
    private boolean isVisible=false;
    private Settings settings;

    //Headline font
    private ProximityFont headline = new ProximityFont(new ProximityVector(position.x + 45, position.y + 20), "Options",40, 1,1,1);

    //Button positions
    private ProximityVector resumePos=new ProximityVector(position.x+40,position.y + 70);
    private ProximityVector mainMenuPos = new ProximityVector(resumePos.x, resumePos.y+80);
    private ProximityVector soundPos = new ProximityVector(mainMenuPos.x + 5, mainMenuPos.y+80);

    //Buttons
    private PropertiesPanelButton resumeButton = new PropertiesPanelButton(resumePos, "Resume");
    private PropertiesPanelButton mainMenuButton = new PropertiesPanelButton(mainMenuPos, "Main menu");
    private SoundButton soundButton = new SoundButton(soundPos);
    private ArrayList<SoundBar> bars=new ArrayList<SoundBar>();

    /**
     * Create a new properies panel
     */
    public PropertiesPanel(Settings settings){
        super(position, background, 0, width, height);
        this.settings = settings;
        initBars();
        updateSoundDisplay(settings.getGameVolume());
    }

    /**
     * Initiates the sound bars for selecting music volume
     */
    private void initBars(){
        ProximityVector pos=new ProximityVector(soundPos.x+50,soundPos.y+15);
        for (int n=1; n<9; n++) {
            bars.add(new SoundBar(new ProximityVector(pos.x + n * 17, pos.y), n));
        }
    }

    /**
     * Visually sets the bars at the correct volume level taken as parameter
     * @param level The new volume level
     */
    private void setBarsAt(int level){
        setBarsEmpty();
        for(int n=0;n<level;n++){
            bars.get(n).setFilled();
        }
    }

    /**
     * Clear all bars
     */
    private void setBarsEmpty(){
        for(SoundBar bar:bars){
            bar.setEmpty();
        }
    }

    /**
     * Set whether or not the panel should be visible
     * @param isVisible true if it should be visible, false otherwise
     */
    public void setVisibility(boolean isVisible){ this.isVisible=isVisible;
        if(isVisible) {
            settings.setGameSpeed(0);
        }
        if(!isVisible) {
            settings.togglePause();
        }
    }

    /**
     * Method only for testing. Returns the current bar level set
     * @return
     */
    public int getBarLevel() {
        if(TestChecker.isJUnitTest()) {
            for (int i = 0; i < bars.size(); i++) {
                if (!bars.get(i).isFilled())
                    return i;
            }
            return bars.size();
        }
        return 0;
    }

    /**
     * Get whether or not the panel is currently visible
     * @return true is it is visible, flase otherwise
     */
    public boolean isVisible(){
        return isVisible;
    }

    /**
     * Set the sound at a specified level
     * @param level What volume level the sound is to be set at
     */
    public void setSoundAt(int level){
        settings.setGameVolume(level);
    }

    /**
     * Is called when given button is pressed
     * @param button The pressed button
     */
    public void pressButton(BoardObject button) {
        if(button == resumeButton) {
            pressedResumeButton();
        }
        else if(button == mainMenuButton) {
            pressedMainMenuButton();
        }
        else if(button instanceof SoundButton) {
            pressedSoundButton();
        }
        else if(button instanceof SoundBar) {
            pressedBar(((SoundBar)button).getLevel());
        }

    }

    /**
     * Get which button is on given position
     * @param position position to be checked for button
     * @return Button on given position, null if none are
     */
    public BoardObject getButtonOnPosition(ProximityVector position){
        for(SoundBar bar:bars){
            if(bar.containsPoint(position)){
                return bar;
            }
        }
        if(resumeButton.containsPoint(position)) {
            return resumeButton;
        }else if(mainMenuButton.containsPoint(position)){
            return mainMenuButton;
        }else if(soundButton.containsPoint(position)){
            return soundButton;
        }
        return null;
    }

    /**
     * Method to get the main menu button
     * @return the MainMenu button
     */
    public PropertiesPanelButton getMainMenuButton() {
        return mainMenuButton;
    }

    /**
     * Method only for testing
     * @return the Resume button
     */
    public PropertiesPanelButton getResumeButton() {
        if(TestChecker.isJUnitTest())
            return resumeButton;
        return null;
    }

    /**
     * Method only for testing
     * @return the toggle sound button
     */
    public SoundButton getSoundButton() {
        if(TestChecker.isJUnitTest())
            return soundButton;
        return null;
    }

    public List<SoundBar> getBars() {
        if(TestChecker.isJUnitTest()) {
            return bars;
        }
        return null;
    }

    /**
     * Called when one of the sound-bars is pressed
     * @param level what level the pressed sound-bar represents.
     */
    private void pressedBar(int level){
        setSoundAt(level);
        updateSoundDisplay(level);
    }

    /**
     * Called if the Resume-button is pressed. Resumes game
     */
    private void pressedResumeButton(){
        setVisibility(false);
    }

    /**
     * Called if MainMenu-button is pressed
     */
    private void pressedMainMenuButton(){
        setVisibility(false);
    }

    /**
     * Called if Sound on/off button is pressed. Mutes/turns on sound
     */
    private void pressedSoundButton(){
        settings.toggleSound();
        updateSoundDisplay(settings.getGameVolume());
    }

    /**
     * Visually updates the soundbars and the SoundButton to a volume
     * @param volume the new music volume
     */
    private void updateSoundDisplay(int volume) {
        setBarsAt(volume);
        if(volume > 0) {
            soundButton.setSoundOn();
        } else {
            soundButton.setSoundOff();
        }
    }

    /**
     * Returns whether the PropertiesPanel contains the point and is visible
     * @param point The point that is to be checked
     * @return Whether the point is within the PropertiesPanel and the panel is visible
     */
    public boolean containsPoint(ProximityVector point) {
        return isVisible && super.containsPoint(point);
    }

    /**
     * Render the panel
     * @param batch What batch to render
     */
    public void render(ProximityBatch batch){

        if(isVisible) {
            batch.renderRepeatedly(background, getPosition(), width, height);
            resumeButton.render(batch);
            mainMenuButton.render(batch);
            soundButton.render(batch);
            headline.draw(batch);
            for (SoundBar bar : bars) {
                bar.render(batch);
            }
        }
    }


}
