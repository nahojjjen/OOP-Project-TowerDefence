package edu.chl.proximity.Models.ControlPanel.TowerPanel;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import edu.chl.proximity.Utilities.ProximityVector;
import edu.chl.proximity.Models.BoardObject;
import edu.chl.proximity.Models.Map.Maps.Map;
import edu.chl.proximity.Models.Utils.Image;
import edu.chl.proximity.Models.Utils.ProximityFont;
import edu.chl.proximity.Utilities.Constants;
import edu.chl.proximity.Viewers.Renderer;

/**
 * @author Hanna Römer
 * @date 2015-05-08
 */
public class CheckBox extends BoardObject{
    private static Image checked= new Image(Constants.FILE_PATH + "Buttons/MapSelected.png");
    private static Image unChecked=new Image(Constants.FILE_PATH + "Buttons/MapSelectable.png");
    private ProximityFont text;

    public CheckBox(ProximityVector pos, Map map, String text){
        super(pos, unChecked,0);
        this.text=new ProximityFont(new ProximityVector(pos.x + 40, pos.y), text);
    }

    public void setAsChecked(){
        super.setImage(checked);
    }
    public void setAsUnchecked(){
        super.setImage(unChecked);
    }
    public void render(SpriteBatch batch){
        super.render(batch);
        text.draw(batch);
    }
}
