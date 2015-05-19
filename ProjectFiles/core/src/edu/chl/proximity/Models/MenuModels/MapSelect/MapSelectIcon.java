package edu.chl.proximity.Models.MenuModels.MapSelect;

import edu.chl.proximity.Utilities.ProximityBatch;
import edu.chl.proximity.Utilities.ProximityVector;
import edu.chl.proximity.Models.BoardObject;
import edu.chl.proximity.Models.Map.Maps.Map;
import edu.chl.proximity.Models.Utils.Image;
import edu.chl.proximity.Models.Utils.ProximityFont;
import edu.chl.proximity.Utilities.Constants;

/**
 * @author Hanna Romer
 * @date 2015-05-03
 *
 * 08/05 modified by Hanna Romer. Added proximityFont name.
 */
public class MapSelectIcon extends BoardObject{
    private Map map;
    private ProximityVector pos;
    private static Image selectable = new Image(Constants.FILE_PATH + "Buttons/MapSelectable.png");
    private static Image notSelectable = new Image(Constants.FILE_PATH + "Buttons/MapNotSelectable.png");
    private static Image selected= new Image(Constants.FILE_PATH + "Buttons/MapSelected.png");
    private ProximityFont name;

    public MapSelectIcon(Map map, ProximityVector pos){
        super(pos,notSelectable,0);
        this.map=map;
        this.pos=pos;
        name= new ProximityFont(new ProximityVector(pos.x, pos.y-20), map.getName());
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

    public void render(ProximityBatch batch){
        super.render(batch);
        name.draw(batch);
    }
}
