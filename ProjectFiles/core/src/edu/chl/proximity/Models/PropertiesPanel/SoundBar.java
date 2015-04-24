package edu.chl.proximity.Models.PropertiesPanel;

import com.badlogic.gdx.math.Vector2;
import edu.chl.proximity.Models.BoardObject;
import edu.chl.proximity.Models.Image;
import edu.chl.proximity.Utilities.Constants;

/**
 * @author Hanna Römer
 * @date 2015-04-24
 */
public class SoundBar extends BoardObject{
    private static Image filled=new Image(Constants.filePath + "Buttons/SoundOffButton.png");
    private static Image empty = new Image(Constants.filePath + "Buttons/SoundOnButton.png");
    private int level;

    public SoundBar(Vector2 position, int level){
        super(position,null,0);
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
