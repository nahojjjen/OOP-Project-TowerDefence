package edu.chl.proximity.Models.PropertiesPanel;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import edu.chl.proximity.Models.BoardObject;
import edu.chl.proximity.Models.GameData;
import edu.chl.proximity.Models.Image;
import edu.chl.proximity.Utilities.Constants;

/**
 * @author Hanna Römer
 * @date 2015-04-23
 */
public class PropertiesPanel extends BoardObject{
    private static Image background = new Image(Constants.filePath + "Backgrounds/TempPropPanel.png");
    private static int height= Gdx.graphics.getHeight()-400;
    private static int width = 1000;
    private static Vector2 position=new Vector2(300,200);
    private Vector2 resumePos=new Vector2(position.x+50,position.y+50);

    private ResumeButton resumeButton=new ResumeButton(resumePos);

    private boolean isVisible=false;

    /**
     * Create a new properies panel
     */
    public PropertiesPanel(){
        super(position, background, 0);
    }

    public void setVisability(boolean isVisible){
        this.isVisible=isVisible;
    }
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

    public void render(SpriteBatch batch){
        super.render(batch);
        resumeButton.render(batch);
    }
}
