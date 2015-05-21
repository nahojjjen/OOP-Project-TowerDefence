package edu.chl.proximity.Models.ControlPanel;

import edu.chl.proximity.Models.ResourceSystem.Resources;
import edu.chl.proximity.Models.Utils.Image;
import edu.chl.proximity.Models.Utils.ProximityBatch;
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

    public enum Face {
        Horizontal, Vertical
    }
    private ResourceDisplayer lineDisplayer;
    private ResourceDisplayer pointDisplayer;
    private ResourceDisplayer polygonDisplayer;

    private List<ResourceDisplayer> displayerList = new ArrayList<ResourceDisplayer>();

    public ResourceDisplayerCollection(ProximityVector position, float spaceBetweenNumbers, int fontSize, Face d) {

        if(d == Face.Horizontal) {
            lineDisplayer = new ResourceDisplayer(new Image(Constants.FILE_PATH + "Resources/lines.png"), new ProximityVector(position.x, position.y));
            pointDisplayer = new ResourceDisplayer(new Image(Constants.FILE_PATH + "Resources/points.png"), new ProximityVector(position.x + spaceBetweenNumbers, position.y));
            polygonDisplayer = new ResourceDisplayer(new Image(Constants.FILE_PATH + "Resources/polygons.png"), new ProximityVector(position.x + spaceBetweenNumbers*2, position.y));
        }
        else {
            lineDisplayer = new ResourceDisplayer(new Image(Constants.FILE_PATH + "Resources/lines.png"), new ProximityVector(position.x, position.y));
            pointDisplayer = new ResourceDisplayer(new Image(Constants.FILE_PATH + "Resources/points.png"), new ProximityVector(position.x, position.y + spaceBetweenNumbers));
            polygonDisplayer = new ResourceDisplayer(new Image(Constants.FILE_PATH + "Resources/polygons.png"), new ProximityVector(position.x, position.y + spaceBetweenNumbers*2));
        }

        displayerList.add(lineDisplayer);
        displayerList.add(pointDisplayer);
        displayerList.add(polygonDisplayer);

        setFontSize(fontSize);
    }

    public void updateResources(Resources resources) {
        if(resources != null) {
            lineDisplayer.getFont().setText("" + resources.getLines());
            pointDisplayer.getFont().setText("" + resources.getPoints());
            polygonDisplayer.getFont().setText("" + resources.getPolygons());
        }
    }

    private void setFontSize(int size) {
        for(ResourceDisplayer displayer : displayerList) {
            displayer.getFont().setSize(size);
        }
    }

    /**
     * This method is only for testing.
     * @return a new instance of Resources that represents the amount of resources currently displayed.
     */
    public Resources getResources() {
        return new Resources(Integer.parseInt(pointDisplayer.getFont().getText()), Integer.parseInt(lineDisplayer.getFont().getText()), Integer.parseInt(polygonDisplayer.getFont().getText()));
    }

    public void render(ProximityBatch batch) {
        for(ResourceDisplayer displayer : displayerList) {
            displayer.render(batch);
        }
    }
}
