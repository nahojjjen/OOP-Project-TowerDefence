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
    private Image filled;
    private Image empty;
    private int level;

    public SoundBar(Vector2 position, int level){
        super(position,null,0);
        filled=new Image(Constants.filePath + "Buttons/PlayButton.png");
        empty = new Image(Constants.filePath + "Buttons/PauseButton.png");
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
