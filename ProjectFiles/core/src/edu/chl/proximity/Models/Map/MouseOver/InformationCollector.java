package edu.chl.proximity.Models.Map.MouseOver;

import edu.chl.proximity.Models.Utils.ProximityBatch;
import edu.chl.proximity.Utilities.ProximityVector;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Linda Evaldsson
 * @date 2015-05-25
 *
 * A class for collecting all MouseOverBoxes that should be rendered
 *
 * 26/05 modified by Linda Evaldsson. Changed function so only one MouseOverBox can be visible at any one time.
 */
public class InformationCollector {

    private static MouseOverBox currentBox;

    public static void displayBox(MouseOverBox newBox) {

        if(currentBox != null) {
            currentBox.disable();
        }
        currentBox = newBox;
    }

    public static void mouseMoved(ProximityVector newPosition) {

        if(currentBox != null) {
            if(!currentBox.containsPoint(newPosition)) {
                currentBox.disable();
            }
        }

    }
    public static void render(ProximityBatch batch) {
        if(currentBox != null) {
            currentBox.render(batch);
        }
    }
}
