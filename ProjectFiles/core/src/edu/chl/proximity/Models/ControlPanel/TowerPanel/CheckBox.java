package edu.chl.proximity.Models.ControlPanel.TowerPanel;

import edu.chl.proximity.Models.Utils.ProximityBatch;
import edu.chl.proximity.Utilities.ProximityVector;
import edu.chl.proximity.Models.BoardObject;
import edu.chl.proximity.Models.Map.Maps.Map;
import edu.chl.proximity.Models.Utils.Image;
import edu.chl.proximity.Models.Utils.ProximityFont;
import edu.chl.proximity.Utilities.Constants;

/**
 * @author Hanna Romer
 * @date 2015-05-08
 */
public class CheckBox extends BoardObject{
    private static Image checked= new Image(Constants.FILE_PATH + "Buttons/RadioBoxS.png");
    private static Image unChecked=new Image(Constants.FILE_PATH + "Buttons/RadioBoxNS.png");
    private ProximityFont text;

    public CheckBox(ProximityVector pos, Map map, String text){
        super(pos, unChecked,0);
        this.text=new ProximityFont(new ProximityVector(pos.x + 20, pos.y + 2), text);
        this.text.setSize(12);
    }

    public void setAsChecked(){
        super.setImage(checked);
    }
    public void setAsUnchecked(){
        super.setImage(unChecked);
    }
    public void render(ProximityBatch batch){
        super.render(batch);
        text.draw(batch);
    }
}
