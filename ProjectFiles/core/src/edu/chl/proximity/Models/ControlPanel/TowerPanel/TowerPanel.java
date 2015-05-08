package edu.chl.proximity.Models.ControlPanel.TowerPanel;

import com.badlogic.gdx.math.Vector2;
import edu.chl.proximity.Models.BoardObject;
import edu.chl.proximity.Models.Map.Maps.Map;
import edu.chl.proximity.Models.Map.Towers.Tower;
import edu.chl.proximity.Models.Utils.Image;

/**
 * @author Hanna Römer
 * @date 2015-05-08
 */
public class TowerPanel extends BoardObject{
    private Tower tower;
    private Map map;
    private static Image image=null;
    private static Vector2 pos=new Vector2(0,0);

    public TowerPanel(Map map){
        this.map=map;
    }
}
