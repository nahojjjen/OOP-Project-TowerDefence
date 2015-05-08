package edu.chl.proximity.Models.ControlPanel.TowerPanel;

import com.badlogic.gdx.math.Vector2;
import edu.chl.proximity.Models.BoardObject;
import edu.chl.proximity.Models.Map.Maps.Map;
import edu.chl.proximity.Models.Utils.Image;
import edu.chl.proximity.Models.Utils.ProximityFont;
import edu.chl.proximity.Utilities.Constants;

/**
 * @author Hanna Römer
 * @date 2015-05-08
 */
public class CheckBox extends BoardObject{
    private static Image checked= new Image(Constants.FILE_PATH + "Buttons/MapSelected.png");
    private static Image unChecked=new Image(Constants.FILE_PATH + "Buttons/MapSelectable.png");
    private ProximityFont text;

    public CheckBox(Vector2 pos, Map map, String text){
        super(map,pos, unChecked,0);
        this.text=new ProximityFont(new Vector2(pos.x + 20, pos.y), text);
    }

    public void setAsChecked(){
        super.setImage(checked);
    }
    public void setAsUnchecked(){
        super.setImage(unChecked);
    }
}
