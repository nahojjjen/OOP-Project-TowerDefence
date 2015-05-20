package edu.chl.proximity.Models.ControlPanel.TowerPanel;

import edu.chl.proximity.Models.BoardObject;
import edu.chl.proximity.Models.Map.Maps.Map;
import edu.chl.proximity.Models.ResourceSystem.Resources;
import edu.chl.proximity.Models.Utils.Image;
import edu.chl.proximity.Models.Utils.ProximityFont;
import edu.chl.proximity.Utilities.Constants;
import edu.chl.proximity.Models.Utils.ProximityBatch;
import edu.chl.proximity.Utilities.ProximityVector;

/**
 * @author Hanna Romer
 * @date 2015-05-18
 *
 * A button for selling towers. Displays what resouces the choosen tower will be sold for
 */
public class SellButton extends BoardObject{
    private Map map;

    private static Image image=new Image(Constants.FILE_PATH + "Buttons/SellButton.png");
    private ProximityFont points;
    private ProximityFont lines;
    private ProximityFont polygons;

    public SellButton(ProximityVector pos, Map map){
        super(pos,image,0);
        this.map=map;
        points=new ProximityFont(new ProximityVector(pos.x+85,pos.y+10), null);
        lines=new ProximityFont(new ProximityVector(pos.x+85, pos.y+25),null);
        polygons=new ProximityFont(new ProximityVector(pos.x+85,pos.y+40),null);
        setInfo();
    }

    public void setInfo(){
        if(map.getChosenTower()!=null){
            Resources cost=map.getChosenTower().getCost();
            Double p= new Double(cost.getPoints()/2);
            Double l=new Double(cost.getLines()/2);
            Double poly=new Double(cost.getPolygons()/2);
            points.setText("Points " + p.intValue());
            lines.setText("Lines "+ l.intValue());
            polygons.setText("Polygons "+ poly.intValue());
        }
    }

    public void render(ProximityBatch batch){
        setInfo();
        if(map.getChosenTower()!=null){
            super.render(batch);
            points.draw(batch);
            lines.draw(batch);
            polygons.draw(batch);
        }
    }

}
