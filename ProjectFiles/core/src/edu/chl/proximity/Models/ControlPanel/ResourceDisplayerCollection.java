package edu.chl.proximity.Models.ControlPanel;

import edu.chl.proximity.Models.ResourceSystem.Resources;
import edu.chl.proximity.Models.Utils.Image;
import edu.chl.proximity.Models.Utils.ProximityBatch;
import edu.chl.proximity.Models.Utils.ProximityFont;
import edu.chl.proximity.Utilities.Constants;
import edu.chl.proximity.Utilities.ProximityVector;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Linda Evaldsson
 * @date 2015-05-21
 *
 * A class for handling three ResourceDisplayers
 */
public class ResourceDisplayerCollection {

    public enum Direction {
        Horizontal, Vertical
    }
    private ResourceDisplayer lineDisplayer;
    private ResourceDisplayer pointDisplayer;
    private ResourceDisplayer polygonDisplayer;
    private ProximityFont resourcesFont;

    private List<ResourceDisplayer> displayerList = new ArrayList<ResourceDisplayer>();

    public ResourceDisplayerCollection(ProximityVector position, float spaceBetweenNumbers, Direction d) {

        if(d == Direction.Horizontal) {
            lineDisplayer = new ResourceDisplayer(new Image(Constants.FILE_PATH + "Resources/lines.png"), new ProximityVector(position.x, position.y+20));
            pointDisplayer = new ResourceDisplayer(new Image(Constants.FILE_PATH + "Resources/points.png"), new ProximityVector(position.x + spaceBetweenNumbers, position.y+20));
            polygonDisplayer = new ResourceDisplayer(new Image(Constants.FILE_PATH + "Resources/polygons.png"), new ProximityVector(position.x + spaceBetweenNumbers*2, position.y+20));
            resourcesFont =  new ProximityFont(position,"Your resources: ", 12, 0.8f, 0.8f, 0.8f);
        }
        else {
            lineDisplayer = new ResourceDisplayer(new Image(Constants.FILE_PATH + "Resources/lines.png"), new ProximityVector(position.x, position.y));
            pointDisplayer = new ResourceDisplayer(new Image(Constants.FILE_PATH + "Resources/points.png"), new ProximityVector(position.x, position.y + spaceBetweenNumbers));
            polygonDisplayer = new ResourceDisplayer(new Image(Constants.FILE_PATH + "Resources/polygons.png"), new ProximityVector(position.x, position.y + spaceBetweenNumbers*2));
        }

        displayerList.add(lineDisplayer);
        displayerList.add(pointDisplayer);
        displayerList.add(polygonDisplayer);




    }

    public void updateResources(Resources resources) {
        if(resources != null) {
            lineDisplayer.getFont().setText("" + resources.getLines());
            pointDisplayer.getFont().setText("" + resources.getPoints());
            polygonDisplayer.getFont().setText("" + resources.getPolygons());
        }
    }


    /**
     * This method is only for testing.
     * @return a new instance of Resources that represents the amount of resources currently displayed.
     */
    public Resources getResources() {
        if(pointDisplayer.getFont().getText().length() > 0 && lineDisplayer.getFont().getText().length() > 0 && polygonDisplayer.getFont().getText().length() > 0)
            return new Resources(Integer.parseInt(pointDisplayer.getFont().getText()), Integer.parseInt(lineDisplayer.getFont().getText()), Integer.parseInt(polygonDisplayer.getFont().getText()));
        return null;
    }

    public void render(ProximityBatch batch) {
        for(ResourceDisplayer displayer : displayerList) {
            displayer.render(batch);
        }
        if (resourcesFont != null){
            resourcesFont.draw(batch);
        }

    }
}
