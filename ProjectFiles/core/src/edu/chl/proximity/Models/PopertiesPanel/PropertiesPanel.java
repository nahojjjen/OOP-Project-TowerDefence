package edu.chl.proximity.Models.PopertiesPanel;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import edu.chl.proximity.Models.BoardObject;
import edu.chl.proximity.Models.GameData;
import edu.chl.proximity.Models.Image;
import edu.chl.proximity.Utilities.Constants;

import javax.sound.sampled.*;

/**
 * @author Hanna Römer
 * @date 2015-04-23
 */
public class PropertiesPanel extends BoardObject{
    private static Image background = new Image(Constants.filePath + "Backgrounds/temporaryControlPanelBackground.png");
    private static int height= Gdx.graphics.getHeight()-400;
    private static int width = 300;
    private static Vector2 position=new Vector2(200,200);

    /**
     * Create a new properies panel
     */
    public PropertiesPanel(){
        super(position, background, 0, height, width);
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
    }
}
