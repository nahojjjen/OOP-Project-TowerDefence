package edu.chl.proximity.Models.ControlPanel.PropertiesPanel;

import com.badlogic.gdx.math.Vector2;
import edu.chl.proximity.Models.BoardObject;
import edu.chl.proximity.Models.Map.Maps.Map;
import edu.chl.proximity.Models.Utils.Image;
import edu.chl.proximity.Utilities.Constants;

/**
 * @author Hanna R�mer
 * @date 2015-04-24
 */
public class SoundBar extends BoardObject{
    private Image filled;
    private Image empty;
    private int level;

    public SoundBar(Vector2 position, int level){
        super(null, position,null,0,16,32);
        filled=new Image(Constants.FILE_PATH + "Buttons/SoundBarFilled.png");
        empty = new Image(Constants.FILE_PATH + "Buttons/SoundBarEmpty.png");
        super.setImage(filled);
        this.level=level;
    }

    public int getLevel(){
        return level;
    }

    public void setFilled(){
        super.setImage(filled);
    }

    public void setEmpty(){
        super.setImage(empty);
    }
}
