package edu.chl.proximity.Models.ControlPanel.TowerPanel;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import edu.chl.proximity.Models.BoardObject;
import edu.chl.proximity.Models.Map.Maps.Map;
import edu.chl.proximity.Models.Utils.Image;
import edu.chl.proximity.Utilities.Constants;

/**
 * @author Hanna Römer
 * @date 2015-05-08
 */
public class UpgradeButton extends BoardObject{
    private static Image image=new Image(Constants.FILE_PATH + "Towers/Missile/1.png");
    public UpgradeButton(Vector2 pos, Map map){
        super(map, pos, image,0);
    }
    public void render(SpriteBatch batch){
        super.render(batch);
    }
}
