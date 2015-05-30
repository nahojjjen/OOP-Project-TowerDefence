package edu.chl.proximity.Models.ControlPanel.PropertiesPanel;

import edu.chl.proximity.Models.BoardObject;
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
 * 24/04 modified by Hanna Romer. Added SoundButton, MainMenuButton and Sound-musicBars
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
    private int storedSpeed;

    //Headline font
    private ProximityFont headline = new ProximityFont(new ProximityVector(position.x + 45, position.y + 20), "Options",40, 1,1,1);

    //Button positions
    private ProximityVector resumePos=new ProximityVector(position.x+40,position.y + 70);
    private ProximityVector mainMenuPos = new ProximityVector(resumePos.x, resumePos.y+80);
    private ProximityVector musicSoundPos = new ProximityVector(mainMenuPos.x + 5, mainMenuPos.y+80);
    private ProximityVector effectSoundPos=new ProximityVector(musicSoundPos.x,musicSoundPos.y + 70);

    //Buttons
    private PropertiesPanelButton resumeButton = new PropertiesPanelButton(resumePos, "Resume");
    private PropertiesPanelButton mainMenuButton = new PropertiesPanelButton(mainMenuPos, "Main menu");

    private Image onMusic=new Image(Constants.FILE_PATH + "Buttons/musicOn.png");
    private Image offMusic= new Image(Constants.FILE_PATH + "Buttons/musicOff.png");
    private SoundButton musicSoundButton = new SoundButton(musicSoundPos,onMusic,offMusic);
    private ArrayList<SoundBar> musicBars =new ArrayList<>();
    private ProximityFont musicFont = new ProximityFont(new ProximityVector(470,342), "Music volume", 10, 1,1,1 );

    private Image onEffects=new Image(Constants.FILE_PATH + "Buttons/soundEffectsOn.png");
    private Image offEffects= new Image(Constants.FILE_PATH + "Buttons/soundEffectsOff.png");
    private SoundButton effectSoundButton=new SoundButton(effectSoundPos,onEffects,offEffects);
    private ArrayList<SoundBar> effectBars=new ArrayList<>();
    private ProximityFont effectsFont = new ProximityFont(new ProximityVector(470,412), "Effects volume", 10, 1,1,1 );

    /**
     * Create a new properies panel
     */
    public PropertiesPanel(Settings settings){
        super(position, background, 0, width, height);
        this.settings = settings;
        initMusicBars();
        updateMusicSoundDisplay(settings.getMusicVolume());

        initEffectBars();
        updateEffectSoundDisplay(settings.getMusicVolume());
    }

    /**
     * Initiates the sound musicBars for selecting music volume
     */
    private void initMusicBars(){
        ProximityVector pos=new ProximityVector(musicSoundPos.x+50, musicSoundPos.y+15);
        for (int n=1; n<9; n++) {
            musicBars.add(new SoundBar(new ProximityVector(pos.x + n * 17, pos.y), n));
        }
    }

    /**
     * Initiates the effect music bars for selecting effects volume
     */
    private void initEffectBars(){
        ProximityVector pos=new ProximityVector(effectSoundPos.x+50,effectSoundPos.y+15);
        for(int n=1;n<9;n++){
            effectBars.add(new SoundBar(new ProximityVector(pos.x + n * 17, pos.y), n));
        }
    }

    /**
     * Visually sets the musicBars at the correct volume level taken as parameter
     * @param level The new volume level
     */
    private void setMusicBarsAt(int level){
        setMusicBarsEmpty();
        for(int n=0;n<level;n++){
            musicBars.get(n).setFilled();
        }
    }

    /**
     * Clear all musicBars
     */
    private void setMusicBarsEmpty(){
        for(SoundBar bar: musicBars){
            bar.setEmpty();
        }
    }

    /**
     * Visually sets the effectBars at the correct volume level taken as parameter.
     * @param level The new volume level
     */
    private void setEffctBarsAt(int level){
        setEffectBarsEmpty();
        for(int n=0;n<level;n++){
            effectBars.get(n).setFilled();
        }
    }

    /**
     * Clear all effectBars
     */
    private void setEffectBarsEmpty(){
        for(SoundBar bar: effectBars){
            bar.setEmpty();
        }
    }

    /**
     * Set whether or not the panel should be visible
     * @param isVisible true if it should be visible, false otherwise
     */
    public void setVisibility(boolean isVisible){ this.isVisible=isVisible;
        if(isVisible) {
            storedSpeed = settings.getGameSpeed();
            settings.setGameSpeed(0);
        }
        if(!isVisible) {
            settings.setGameSpeed(storedSpeed);
        }
    }

    /**
     * Method only for testing. Returns the current bar level set
     * @return
     */
    public int getMusicBarLevel() {
        if(TestChecker.isJUnitTest()) {
            for (int i = 0; i < musicBars.size(); i++) {
                if (!musicBars.get(i).isFilled())
                    return i;
            }
            return musicBars.size();
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
     * Set the music sound at a specified level
     * @param level What volume level the music sound is to be set at
     */
    public void setMusicSoundAt(int level){
        settings.setMusicVolume(level);
    }

    /**
     * Set the effect sound ar a specified level
     * @param level What volume level the meffect sound is to be set at
     */
    public void setEffectSoundAt(int level){
        settings.setEffectsVolume(level);
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
            if(button==effectSoundButton){
                pressedEffectSoundButton();
            }else {
                pressedMusicSoundButton();
            }
        }
        else if(button instanceof SoundBar) {
            if(musicBars.contains(button)) {
                pressedMusicBar(((SoundBar) button).getLevel());
            }else if(effectBars.contains(button)){
                pressedEffectBar(((SoundBar) button).getLevel());
            }
        }

    }

    /**
     * Get which button is on given position
     * @param position position to be checked for button
     * @return Button on given position, null if none are
     */
    public BoardObject getButtonOnPosition(ProximityVector position){
        for(SoundBar bar: musicBars){
            if(bar.containsPoint(position)){
                return bar;
            }
        }
        for(SoundBar eBar:effectBars){
            if(eBar.containsPoint(position)){
                return eBar;
            }
        }
        if(resumeButton.containsPoint(position)) {
            return resumeButton;
        }else if(mainMenuButton.containsPoint(position)){
            return mainMenuButton;
        }else if(musicSoundButton.containsPoint(position)){
            return musicSoundButton;
        }else if(effectSoundButton.containsPoint(position)){
            return effectSoundButton;
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
    public SoundButton getMusicSoundButton() {
        if(TestChecker.isJUnitTest())
            return musicSoundButton;
        return null;
    }

    public List<SoundBar> getMusicBars() {
        if(TestChecker.isJUnitTest()) {
            return musicBars;
        }
        return null;
    }

    /**
     * Called when one of the sound-musicBars is pressed
     * @param level what level the pressed sound-bar represents.
     */
    private void pressedMusicBar(int level){
        setMusicSoundAt(level);
        updateMusicSoundDisplay(level);
    }

    private void pressedEffectBar(int level){
        setEffectSoundAt(level);
        updateEffectSoundDisplay(level);
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
    private void pressedMusicSoundButton(){
        settings.toggleMusicVolume();
        updateMusicSoundDisplay(settings.getMusicVolume());
    }

    private void pressedEffectSoundButton(){
        settings.toggleEffectVolume();
        updateEffectSoundDisplay(settings.getEffectsVolume());
    }

    /**
     * Visually updates the soundbars and the SoundButton to a volume
     * @param volume the new music volume
     */
    private void updateMusicSoundDisplay(int volume) {
        setMusicBarsAt(volume);
        if(volume > 0) {
            musicSoundButton.setSoundOn();
        } else {
            musicSoundButton.setSoundOff();
        }
    }

    private void updateEffectSoundDisplay(int volume){
        setEffctBarsAt(volume);
        if(volume > 0){
            effectSoundButton.setSoundOn();
        }else{
            effectSoundButton.setSoundOff();
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
            musicSoundButton.render(batch);
            headline.draw(batch);
            musicFont.draw(batch);
            effectsFont.draw(batch);
            for (SoundBar bar : musicBars) {
                bar.render(batch);
            }
            effectSoundButton.render(batch);
            for(SoundBar eBar: effectBars){
                eBar.render(batch);
            }
        }
    }


}
