package edu.chl.proximity.Models.Utils;

import edu.chl.proximity.Utilities.ProximityVector;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Linda Evaldsson
 * @date 2015-05-25
 *
 * A class for collecting all MouseOverBoxes that should be rendered
 */
public class InformationCollector {

    private static List<MouseOverBox> mouseOverBoxList = new ArrayList<MouseOverBox>();

    public static void addBox(MouseOverBox newBox) {
            mouseOverBoxList.add(newBox);
    }

    public static void mouseMoved(ProximityVector newPosition) {
        for(MouseOverBox box : mouseOverBoxList) {
            if(box.isEnabled()) {
                if(!box.containsPoint(newPosition)) {
                    box.disable();
                }
            }
        }
    }
    public static void render(ProximityBatch batch) {
        for(MouseOverBox box : mouseOverBoxList) {
            box.render(batch);
        }
    }
}
