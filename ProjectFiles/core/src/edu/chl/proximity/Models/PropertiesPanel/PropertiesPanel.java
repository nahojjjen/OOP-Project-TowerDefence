package edu.chl.proximity.Models.PropertiesPanel;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import edu.chl.proximity.Models.BoardObject;
import edu.chl.proximity.Models.GameData;
import edu.chl.proximity.Models.Image;
import edu.chl.proximity.Utilities.Constants;
import edu.chl.proximity.Utilities.PointCalculations;

/**
 * @author Hanna Römer
 * @date 2015-04-23
 */
public class PropertiesPanel extends BoardObject{
    private static Image background = new Image(Constants.filePath + "Backgrounds/TempPropPanel.png");
    private static Vector2 position=new Vector2(300,200);
    private Vector2 resumePos=new Vector2(position.x+95,position.y+50);

    private ResumeButton resumeButton=new ResumeButton(resumePos);

    private boolean isVisible=false;

    /**
     * Create a new properies panel
     */
    public PropertiesPanel(){
        super(position, background, 0);
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
        //TODO make button(s) display current soundlevel
    }

    /**
     * Get which button is on given position
     * @param position position to be checked for button
     * @return Button on given position, null if none are
     */
    public BoardObject getButtonOnPosition(Vector2 position){
        if(PointCalculations.isPointInObject(position, resumeButton)) {
            return resumeButton;
        }
        return null;
    }

    /**
     * Called if the Resume-button is pressed
     */
    public void pressedResumeButton(){
        setVisability(false);
        GameData.getInstance().getButtonPanel().startGame();
    }

    /**
     * Render the panel
     * @param batch What batch to render
     */
    public void render(SpriteBatch batch){
        super.render(batch);
        resumeButton.render(batch);
    }


}
