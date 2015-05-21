package edu.chl.proximity.Models.ControlPanel;

import edu.chl.proximity.Models.Utils.Image;
import edu.chl.proximity.Models.Utils.ProximityBatch;
import edu.chl.proximity.Models.Utils.ProximityFont;
import edu.chl.proximity.Utilities.Constants;
import edu.chl.proximity.Utilities.ProximityVector;
import edu.chl.proximity.Models.BoardObject;
import edu.chl.proximity.Models.Map.Maps.Map;
import edu.chl.proximity.Models.Map.Towers.Tower;

/**
 * @author Linda Evaldsson
 * @date 2015-04-18
 *
 * ControlPanelTower is a class that can display Towers
 *
 * 21/05 modified by Linda Evaldsson. Added a separate background image and a font.
 */
public class ControlPanelTower extends BoardObject{

    Tower tower;
    private static Image bgImage = new Image(Constants.FILE_PATH + "Towers/controlpanelbackground.png");
    private ProximityFont headline;

    public ControlPanelTower(ProximityVector position, Tower tower) {
        super(position, bgImage, 0);
        this.tower = tower;
        headline = new ProximityFont(position, tower.getName());
        headline.setSize(11);
        setPosition(position);
    }

    /**
     * Returns a copy of the tower in this ControlPanelTower
     * @return a copy of tower
     */
    public Tower getTower(){
        try {
            return (Tower)tower.clone();
        } catch (CloneNotSupportedException e){
            System.out.println("ControlPanelTower: Error, Clone not supported");
        }
        return null;
    }

    public void setPosition(ProximityVector vector) {
        super.setPosition(vector);
        tower.setPosition(new ProximityVector(vector.x + 8, vector.y + 8));
        headline.setPosition(new ProximityVector(vector.x + 4, vector.y - 4));

    }

    public void render(ProximityBatch batch) {
        super.render(batch);
        tower.render(batch);
        headline.draw(batch);
    }
}
