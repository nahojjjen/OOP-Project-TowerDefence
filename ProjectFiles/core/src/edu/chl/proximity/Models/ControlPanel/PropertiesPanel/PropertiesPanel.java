package edu.chl.proximity.Models.ControlPanel.PropertiesPanel;

import edu.chl.proximity.Models.Utils.ProximityBatch;
import edu.chl.proximity.Models.Utils.ProximityFont;
import edu.chl.proximity.Utilities.ProximityVector;
import edu.chl.proximity.Models.BoardObject;
import edu.chl.proximity.Models.Player.Players.GameData;
import edu.chl.proximity.Models.Utils.Image;
import edu.chl.proximity.Models.Utils.Settings;
import edu.chl.proximity.Utilities.Constants;

import java.util.ArrayList;

/**
 * @author Hanna Romer
 * @date 2015-04-23
 *
 * 24/04 modified by Hanna Romer. Added SoundButton, MainMenuButton and Sound-bars
 * 29/04 modified by Hanna Romer. Made into singleton.
 * 01/05 modified by Hanna Romer. MainMenuButton now takes you to the mainMenu
 * 24/05 modified by Linda Evaldsson. Fixed the design of this panel.
 */
public class PropertiesPanel extends BoardObject{

    private static Image background = new Image(Constants.FILE_PATH + "Backgrounds/square.png");
    private static int width = 300;
    private static int height = 400;
    private static ProximityVector position=new ProximityVector((Constants.GAME_WIDTH-300-width)/2, (Constants.GAME_HEIGHT-100-height)/2); //350, 200

    private ProximityFont headline = new ProximityFont(new ProximityVector(position.x + 45, position.y + 20), "Options");
    private ProximityVector resumePos=new ProximityVector(position.x+40,position.y + 70);
    private ProximityVector mainMenuPos = new ProximityVector(resumePos.x, resumePos.y+80);
    private ProximityVector soundPos = new ProximityVector(position.x + 45, mainMenuPos.y+80);

    private PropertiesPanelButton resumeButton;
    private PropertiesPanelButton mainMenuButton;
    private SoundButton soundButton;

    private ArrayList<SoundBar> bars=new ArrayList<SoundBar>();

    private int backUpLevel;
    private boolean isVisible=false;
    private Settings settings;

    /**
     * Create a new properies panel
     */
    public PropertiesPanel(Settings settings){
        super(position, background, 0, width, height);
        headline.setSize(40);
        this.settings = settings;
        resumeButton = new PropertiesPanelButton(resumePos, "Resume");
        mainMenuButton = new PropertiesPanelButton(mainMenuPos, "Main menu");
        soundButton = new SoundButton(soundPos);
        initBars();
        setBarsAt((int)settings.getGameVolume());
        setSoundAt((int)settings.getGameVolume());
    }


    private void initBars(){
        ProximityVector pos=new ProximityVector(soundPos.x+50,soundPos.y+15);
        for (int n=1; n<9; n++) {
            bars.add(new SoundBar(new ProximityVector(pos.x + n * 17, pos.y), n));
        }
    }


    private void setBarsAt(int level){
        setBarsEmpty();
        for(int n=0;n<level;n++){
            bars.get(n).setFilled();
        }
    }
    private void setBarsEmpty(){
        for(SoundBar bar:bars){
            bar.setEmpty();
        }
    }

    /**
     * Set whether or not the panel should be visible
     * @param isVisible true if it should be visible, flase otherwise
     */
    public void setVisibility(boolean isVisible){
        this.isVisible=isVisible;
    }

    /**
     * Get whether or not the panel is currently visible
     * @return true is it is visible, flase otherwise
     */
    public boolean isVisible(){
        return isVisible;
    }

    /**
     * Set the sound at a specified level from 0 to 4
     * @param level What level the sound is to be set at
     */
    public void setSoundAt(int level){
        settings.setGameVolume(level);
    }

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

    public PropertiesPanelButton getMainMenuButton() {
        return mainMenuButton;
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

    public void pressedBar(int level){
        setBarsAt(level);
        setSoundAt(level);
    }

    /**
     * Called if the Resume-button is pressed. Resumes game
     */
    public void pressedResumeButton(){
        setVisibility(false);
        GameData.getInstance().getPlayer().getSettings().setGameSpeed(1);
    }

    /**
     * Called if MainMenu-button is pressed
     */
    public void pressedMainMenuButton(){
        setVisibility(false);
    }

    /**
     * Called if Sound on/off button is pressed. Mutes/turns on sound
     */
    public void pressedSoundButton(){
        if(GameData.getInstance().getPlayer().getSettings().getGameVolume()>0){
            setSoundAt(0);
            setBarsAt(0);
            soundButton.setSoundOff();
        }else{
            setSoundAt(backUpLevel);
            setBarsAt(backUpLevel);
            soundButton.setSoundOn();
        }

    }

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
