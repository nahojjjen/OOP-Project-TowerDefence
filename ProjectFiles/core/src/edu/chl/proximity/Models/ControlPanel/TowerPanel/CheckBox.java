package edu.chl.proximity.Models.ControlPanel.TowerPanel;

import edu.chl.proximity.Models.BoardObject;
import edu.chl.proximity.Models.Utils.Image;
import edu.chl.proximity.Models.Utils.ProximityBatch;
import edu.chl.proximity.Models.Utils.ProximityFont;
import edu.chl.proximity.Utilities.Constants;
import edu.chl.proximity.Utilities.ProximityVector;

/**
 * @author Hanna Romer
 * @date 2015-05-08
 *
 * Represents a checkbox -has an checked and unckecked image that can be toggled
 *
 * 28/05 modified by Hanna Romer. Added comments + removed unused map in signature.
 */
public class CheckBox extends BoardObject{
    private static Image checked= new Image(Constants.FILE_PATH + "Buttons/RadioBoxS.png");
    private static Image unChecked=new Image(Constants.FILE_PATH + "Buttons/RadioBoxNS.png");
    private ProximityFont text;

    /**
     * Create a new CheckBox with given position and text to display
     * @param pos Position Checkbox will be placed at
     * @param text Text to be displayed next to the checkbox
     */
    public CheckBox(ProximityVector pos, String text){
        super(pos, unChecked, 0, 16, 16);
        this.text=new ProximityFont(new ProximityVector(pos.x + 20, pos.y + 2), text,12, 1,1,1);
    }

    /**
     * Check wether or not the checkbox is currently checked in.
     * @return if the checkbox is checked in.
     */
    public boolean isChecked() {
        return getImage() == checked;
    }

    /**
     * Set checkbox as checked in.
     */
    public void setAsChecked(){
        super.setImage(checked);
    }

    /**
     * Set checkbox as unchecked
     */
    public void setAsUnchecked(){
        super.setImage(unChecked);
    }
    public void render(ProximityBatch batch){
        super.render(batch);
        text.draw(batch);
    }
}
