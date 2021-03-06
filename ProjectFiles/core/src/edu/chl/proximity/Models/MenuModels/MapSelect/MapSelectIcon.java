package edu.chl.proximity.Models.MenuModels.MapSelect;

import edu.chl.proximity.Models.BoardObject;
import edu.chl.proximity.Models.Map.Maps.Map;
import edu.chl.proximity.Models.Utils.Image;
import edu.chl.proximity.Models.Utils.ProximityBatch;
import edu.chl.proximity.Models.Utils.ProximityFont;
import edu.chl.proximity.Utilities.Constants;
import edu.chl.proximity.Utilities.ProximityVector;

/**
 * @author Hanna Romer
 * @date 2015-05-03
 *
 * 08/05 modified by Hanna Romer. Added proximityFont name.
 */
public class MapSelectIcon extends BoardObject{
    private Map map;
    private static Image selectable = new Image(Constants.FILE_PATH + "Buttons/MapSelectable.png");
    private static Image notSelectable = new Image(Constants.FILE_PATH + "Buttons/MapNotSelectable.png");
    private static Image selected= new Image(Constants.FILE_PATH + "Buttons/MapSelected.png");
    private ProximityFont name;
    private ProximityFont completion;

    public MapSelectIcon(Map map, ProximityVector pos){
        super(pos,notSelectable,0);
        this.map=map;
        this.setPosition(pos);
        name= new ProximityFont(new ProximityVector(pos.x, pos.y-20), map.getName(),14, 1,1,1);
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
    public void setCompletionText(ProximityFont text){completion = text;}

    public void render(ProximityBatch batch){
        super.render(batch);
        name.draw(batch);
        completion.draw(batch);
    }
}
