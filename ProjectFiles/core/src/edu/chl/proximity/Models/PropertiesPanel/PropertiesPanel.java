package edu.chl.proximity.Models.PropertiesPanel;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import edu.chl.proximity.Models.BoardObject;
import edu.chl.proximity.Models.GameData;
import edu.chl.proximity.Models.Image;
import edu.chl.proximity.Utilities.Constants;
import edu.chl.proximity.Utilities.PointCalculations;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Hanna Römer
 * @date 2015-04-23
 *
 * 24/04 modified by Hanna Römer. Added SoundButton, MainMenuButton and Sound-bars
 * 29/04 modified by Hanna Römer. Made into singleton.
 */
public class PropertiesPanel extends BoardObject{
    private static PropertiesPanel propertiesPanel;

    private static Image background = new Image(Constants.filePath + "Backgrounds/TempPropPanel.png");
    private static Vector2 position=new Vector2(300,200);
    private Vector2 resumePos=new Vector2(position.x+95,position.y+20);
    private Vector2 mainMenuPos = new Vector2(position.x+95,resumePos.y+100);
    private Vector2 soundPos = new Vector2(position.x + 100, mainMenuPos.y+100);

    private ResumeButton resumeButton=new ResumeButton(resumePos);
    private MainMenuButton mainMenuButton=new MainMenuButton(mainMenuPos);
    private SoundButton soundButton=new SoundButton(soundPos);

    private ArrayList<SoundBar> bars=new ArrayList<SoundBar>();

    private int backUpLevel;
    private boolean isVisible=false;

    /**
     * Create a new properies panel
     */
    public PropertiesPanel(){
        super(position, background, 0);
        initBars();
        setBarsAt(4);
        setSoundAt(4);
    }


    public static PropertiesPanel getInstance(){
        if(propertiesPanel==null){
            propertiesPanel=new PropertiesPanel();
        }

        return propertiesPanel;

    }

    private void initBars(){
        Vector2 pos=new Vector2(soundPos.x+50,soundPos.y+15);
        for (int n=1; n<9; n++) {
            bars.add(new SoundBar(new Vector2(pos.x + n * 18, pos.y), n));
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

        switch (level){
            case 0: GameData.VOLUME=0f;
                    break;
            case 1: GameData.VOLUME=0.0125f;
                    backUpLevel=1;
                    break;
            case 2: GameData.VOLUME=0.025f;
                    backUpLevel=2;
                    break;
            case 3: GameData.VOLUME=0.05f;
                    backUpLevel=3;
                    break;
            case 4: GameData.VOLUME=0.1f;
                    backUpLevel=4;
                    break;
            case 5: GameData.VOLUME=0.2f;
                    backUpLevel=5;
                    break;
            case 6: GameData.VOLUME=0.4f;
                    backUpLevel=6;
                    break;
            case 7: GameData.VOLUME=0.8f;
                    backUpLevel=7;
                    break;
            case 8: GameData.VOLUME=1.6f;
                    backUpLevel=8;
                    break;
        }

    }

    /**
     * Get which button is on given position
     * @param position position to be checked for button
     * @return Button on given position, null if none are
     */
    public BoardObject getButtonOnPosition(Vector2 position){
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
        GameData.getInstance().setGameSpeed(1);
    }

    /**
     * Called if MainMenu-button is pressed
     */
    public void pressedMainMenuButton(){

    }

    /**
     * Called if Sound on/off button is pressed. Mutes/turns on sound
     */
    public void pressedSoundButton(){
        if(GameData.VOLUME>0){
            setSoundAt(0);
            setBarsAt(0);
            soundButton.setSoundOff();
        }else{
            setSoundAt(backUpLevel);
            setBarsAt(backUpLevel);
            soundButton.setSoundOn();
        }

    }

    /**
     * Render the panel
     * @param batch What batch to render
     */
    public void render(SpriteBatch batch){
        super.render(batch);
        resumeButton.render(batch);
        mainMenuButton.render(batch);
        soundButton.render(batch);
        for(SoundBar bar:bars){
            bar.render(batch);
        }
    }


}
