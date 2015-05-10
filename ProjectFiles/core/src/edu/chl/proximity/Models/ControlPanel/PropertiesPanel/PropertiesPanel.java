package edu.chl.proximity.Models.ControlPanel.PropertiesPanel;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import edu.chl.proximity.Utilities.ProximityVector;
import com.badlogic.gdx.utils.viewport.Viewport;
import edu.chl.proximity.Models.BoardObject;
import edu.chl.proximity.Models.Map.Maps.Map;
import edu.chl.proximity.Models.Utils.GameData;
import edu.chl.proximity.Models.Utils.Image;
import edu.chl.proximity.Models.Utils.Settings;
import edu.chl.proximity.Proximity;
import edu.chl.proximity.Utilities.Constants;
import edu.chl.proximity.Utilities.PointCalculations;

import java.util.ArrayList;

/**
 * @author Hanna R�mer
 * @date 2015-04-23
 *
 * 24/04 modified by Hanna R�mer. Added SoundButton, MainMenuButton and Sound-bars
 * 29/04 modified by Hanna R�mer. Made into singleton.
 * 01/05 modified by Hanna Römer. MainMenuButton now takes you to the mainMenu
 */
public class PropertiesPanel extends BoardObject{
    private static PropertiesPanel propertiesPanel;

    private static Image background = new Image(Constants.FILE_PATH + "Backgrounds/TempPropPanel.png");
    private static ProximityVector position=new ProximityVector(300,200);
    private ProximityVector resumePos=new ProximityVector(position.x+95,position.y+20);
    private ProximityVector mainMenuPos = new ProximityVector(position.x+95,resumePos.y+100);
    private ProximityVector soundPos = new ProximityVector(position.x + 100, mainMenuPos.y+100);

    private ResumeButton resumeButton;
    private MainMenuButton mainMenuButton;
    private SoundButton soundButton;

    private ArrayList<SoundBar> bars=new ArrayList<SoundBar>();

    private int backUpLevel;
    private boolean isVisible=false;
    private Proximity game;
    private Viewport viewport;

    /**
     * Create a new properies panel
     */
    public PropertiesPanel(Proximity game, Viewport viewport){
        super(null, position, background, 0);
        this.game = game;
        this.viewport=viewport;
        resumeButton = new ResumeButton(resumePos);
        mainMenuButton = new MainMenuButton(mainMenuPos);
        soundButton = new SoundButton(soundPos);
        initBars();
        setBarsAt(4);
        setSoundAt(4);
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
     * Set wether or not the panel should be visible
     * @param isVisible true if it should be visible, flase otherwise
     */
    public void setVisability(boolean isVisible){
        this.isVisible=isVisible;



    }

    /**
     * Get wether or not the panel is currently visible
     * @return true is it is visible, flase otherwise
     */
    public boolean getIfVisible(){
        return isVisible;
    }

    /**
     * Set the sound at a specified level from 0 to 4
     * @param level What level the sound is to be set at
     */
    public void setSoundAt(int level){
        Settings settings = GameData.getInstance().getPlayer().getSettings();
        switch (level){
            case 0: settings.setGameVolume(0f);
                    break;
            case 1: settings.setGameVolume(0.0125f);
                    backUpLevel=1;
                    break;
            case 2: settings.setGameVolume(0.025f);
                    backUpLevel=2;
                    break;
            case 3: settings.setGameVolume(0.05f);
                    backUpLevel=3;
                    break;
            case 4: settings.setGameVolume(0.1f);
                    backUpLevel=4;
                    break;
            case 5: settings.setGameVolume(0.2f);
                    backUpLevel=5;
                    break;
            case 6: settings.setGameVolume(0.4f);
                    backUpLevel=6;
                    break;
            case 7: settings.setGameVolume(0.8f);
                    backUpLevel=7;
                    break;
            case 8: settings.setGameVolume(1.6f);
                    backUpLevel=8;
                    break;
        }

    }

    /**
     * Get which button is on given position
     * @param position position to be checked for button
     * @return Button on given position, null if none are
     */
    public BoardObject getButtonOnPosition(ProximityVector position){
        for(SoundBar bar:bars){
            if(PointCalculations.isPointInObject(position,bar)){
                return bar;
            }
        }
        if(PointCalculations.isPointInObject(position, resumeButton)) {
            return resumeButton;
        }else if(PointCalculations.isPointInObject(position,mainMenuButton)){
            return mainMenuButton;
        }else if(PointCalculations.isPointInObject(position,soundButton)){
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
        setVisability(false);
        GameData.getInstance().getPlayer().getSettings().setGameSpeed(1);
    }

    /**
     * Called if MainMenu-button is pressed
     */
    public void pressedMainMenuButton(){
        setVisability(false);

        game.changeScreen(Proximity.State.MAIN_MENU,getMap(), GameData.getInstance().getPlayer(),viewport);
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
    public void render(SpriteBatch batch){

        if(isVisible) {
            super.render(batch);
            resumeButton.render(batch);
            mainMenuButton.render(batch);
            soundButton.render(batch);
            for (SoundBar bar : bars) {
                bar.render(batch);
            }
        }
    }


}
