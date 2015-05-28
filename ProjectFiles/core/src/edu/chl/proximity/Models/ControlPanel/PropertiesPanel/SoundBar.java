package edu.chl.proximity.Models.ControlPanel.PropertiesPanel;

import edu.chl.proximity.Models.BoardObject;
import edu.chl.proximity.Models.Utils.Image;
import edu.chl.proximity.Utilities.Constants;
import edu.chl.proximity.Utilities.ProximityVector;

/**
 * @author Hanna Romer
 * @date 2015-04-24
 *
 * 28/05 modified by Hanna Romer. Added comments.
 */
public class SoundBar extends BoardObject{
    private Image filled;
    private Image empty;
    private int level;

    /**
     * Create a new Sound-bar with given position and level to prepresent
     * @param position Position sound bar is to be placed at
     * @param level What level of volume to represent.
     */
    public SoundBar(ProximityVector position, int level){
        super(position,null,0,16,32);
        filled=new Image(Constants.FILE_PATH + "Buttons/SoundBarFilled.png");
        empty = new Image(Constants.FILE_PATH + "Buttons/SoundBarEmpty.png");
        super.setImage(filled);
        this.level = level;
    }

    /**
     *
     * @return
     */
    public int getLevel() {
        return level;
    }

    public void setFilled(){
        super.setImage(filled);
    }

    public void setEmpty(){
        super.setImage(empty);
    }

    public boolean isFilled() {
        return getImage() == filled;
    }
}
