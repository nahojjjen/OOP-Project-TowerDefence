package edu.chl.proximity.Models.MenuModels.MapSelect;

import edu.chl.proximity.Utilities.ProximityBatch;
import edu.chl.proximity.Utilities.ProximityVector;
import edu.chl.proximity.Models.BoardObject;
import edu.chl.proximity.Models.Map.Maps.FillerMap;
import edu.chl.proximity.Models.Map.Maps.Map;
import edu.chl.proximity.Models.Map.Maps.StandardMap;
import edu.chl.proximity.Utilities.PointCalculations;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Hanna Römer
 * @date 2015-05-03
 *
 * 08/05 modified by Hanna Römer. Removed mapName.
 */
public class MapSelect extends BoardObject{
    private static ProximityVector pos=new ProximityVector(0,0);
    private List<MapSelectIcon> maps = new ArrayList<MapSelectIcon>();
    private int selected=0;

    public MapSelect(Map map){
        super(pos, null, 0);
        maps.add(new MapSelectIcon(new StandardMap(), new ProximityVector(200, 200)));
        maps.add(new MapSelectIcon(new FillerMap(), new ProximityVector(300,200)));

        //TODO: this for-loop is just for debug. remove later
        for(MapSelectIcon m:maps){
            m.setAsSelectable();
        }

        maps.get(selected).setAsSelected();
    }
    public Map getSelected(){
        return maps.get(selected).getMap();
    }

    public void pressed(ProximityVector pos){
        for(int i=0;i<maps.size();i++){
            if(PointCalculations.isPointInObject(pos,maps.get(i))){
                mapClicked(maps.get(i),i);
            }
        }
    }

    public void mapClicked(MapSelectIcon map, int index){
        maps.get(selected).setAsSelectable();
        maps.get(index).setAsSelected();
        selected=index;
    }

    public void render(ProximityBatch batch){
        for(MapSelectIcon map:maps){
            map.render(batch);
        }
    }
}
