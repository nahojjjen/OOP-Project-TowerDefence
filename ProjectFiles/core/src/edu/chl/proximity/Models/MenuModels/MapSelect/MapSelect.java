package edu.chl.proximity.Models.MenuModels.MapSelect;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import edu.chl.proximity.Models.BoardObject;
import edu.chl.proximity.Models.Map.Maps.Map;
import edu.chl.proximity.Models.Map.Maps.StandardMap;
import edu.chl.proximity.Utilities.PointCalculations;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Hanna Römer
 * @date 2015-05-03
 */
public class MapSelect extends BoardObject{
    private static Vector2 pos=new Vector2(0,0);
    private List<MapSelectIcon> maps = new ArrayList<MapSelectIcon>();
    private int selected=0;

    public MapSelect(){
        super(pos, null, 0);
        maps.add(new MapSelectIcon(new StandardMap(), new Vector2(200, 200)));

        //TODO: this for-loop is just for debug. remove later
        for(MapSelectIcon m:maps){
            m.setAsSelectable();
        }

        maps.get(selected).setAsSelected();
    }
    public Map getSelected(){
        return maps.get(selected).getMap();
    }

    public void pressed(Vector2 pos){
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

    public void render(SpriteBatch batch){
        for(MapSelectIcon map:maps){
            map.render(batch);
        }
    }
}
