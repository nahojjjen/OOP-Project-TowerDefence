package edu.chl.proximity.Models.MenuModels.MapSelect;

import com.badlogic.gdx.math.Vector2;
import edu.chl.proximity.Models.BoardObject;
import edu.chl.proximity.Models.Map.Maps.Map;
import edu.chl.proximity.Models.Utils.Image;
import edu.chl.proximity.Utilities.Constants;

/**
 * @author Hanna Römer
 * @date 2015-05-03
 */
public class MapSelectIcon extends BoardObject{
    private Map map;
    private Vector2 pos;
    private static Image selectable = new Image(Constants.FILE_PATH + "Buttons/MapSelectable.png");
    private static Image notSelectable = new Image(Constants.FILE_PATH + "Buttons/MapNotSelectable.png");
    private static Image selected= new Image(Constants.FILE_PATH + "Buttons/MapSelected.png");

    public MapSelectIcon(Map map, Vector2 pos){
        super(map, pos,notSelectable,0);
        this.map=map;
        this.pos=pos;
    }

    public Map getMap(){
        return map;
    }

    public void setAsSelectable(){
        setImage(selectable);
    }
    public void setAsNotSelectable(){
        setImage(notSelectable);
    }
    public void setAsSelected(){
        setImage(selected);
    }
}
