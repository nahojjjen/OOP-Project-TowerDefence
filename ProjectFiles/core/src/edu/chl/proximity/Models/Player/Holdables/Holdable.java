package edu.chl.proximity.Models.Player.Holdables;

import edu.chl.proximity.Models.Player.ResourceSystem.Resources;
import edu.chl.proximity.Models.Utils.ProximityBatch;
import edu.chl.proximity.Utilities.ProximityVector;
import edu.chl.proximity.Models.Utils.Image;

/**
 * @author Linda Evaldsson
 * @date 2015-04-22
 *
 * Classes that implement the Holdable interface are objects that the user is about to place on the map.
 * For example, towers and spells.
 *
 * 03-05-2015 Modified by Simon Gislen. Introducing: *Towers are not free*
 *
 */
public interface Holdable {

    /**
     * Puts the object down on the map
     */
    public void preparePlacing(ProximityVector position);

    public void render(ProximityBatch batch);

    public Image getImage();

    public double getRange();

    public Resources getCost();

}
