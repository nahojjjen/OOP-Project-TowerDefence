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
 * 24/04 edited by Hanna Römer. Added SoundButton, MainMenuButton and Sound-bars
 */
public class PropertiesPanel extends BoardObject{
    private static Image background = new Image(Constants.filePath + "Backgrounds/TempPropPanel.png");
    private static Vector2 position=new Vector2(300,200);
    private Vector2 resumePos=new Vector2(position.x+95,position.y+20);
    private Vector2 mainMenuPos = new Vector2(position.x+95,resumePos.y+100);
    private Vector2 soundPos = new Vector2(position.x + 100, mainMenuPos.y+100);

    private ResumeButton resumeButton=new ResumeButton(resumePos);
    private MainMenuButton mainMenuButton=new MainMenuButton(mainMenuPos);
    private SoundButton soundButton=new SoundButton(soundPos);

    private ArrayList<SoundBar> bars=new ArrayList<SoundBar>();

    private boolean isVisible=false;

    /**
     * Create a new properies panel
     */
    public PropertiesPanel(){
        super(position, background, 0);
        setBars();
    }

    private void setBars(){
        Vector2 pos=new Vector2(soundPos.x+35,soundPos.y);
        for (int n=1; n<9; n++) {
            bars.add(new SoundBar(new Vector2(pos.x+n*15,pos.y),n));
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
        GameData.VOLUME=level*0.05f;
    }

    /**
     * Get which button is on given position
     * @param position position to be checked for button
     * @return Button on given position, null if none are
     */
    public BoardObject getButtonOnPosition(Vector2 position){
        for(SoundBar bar:bars){
            System.out.println("Hello?" + PointCalculations.isPointInObject(position,bar));
            if(PointCalculations.isPointInObject(position,bar)){
                System.out.println("Why aren't you returning?");
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
        System.out.print("Pressed bar at level " + level);
    }

    /**
     * Called if the Resume-button is pressed. Resumes game
     */
    public void pressedResumeButton(){
        setVisability(false);
        GameData.getInstance().getButtonPanel().startGame();
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
            GameData.VOLUME=0f;
            soundButton.setSoundOff();
        }else{
            GameData.VOLUME=0.1f;
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
