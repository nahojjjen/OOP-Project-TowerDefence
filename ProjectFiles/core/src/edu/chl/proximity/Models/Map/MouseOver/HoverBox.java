package edu.chl.proximity.Models.Map.MouseOver;

import edu.chl.proximity.Models.Utils.ProximityBatch;
import edu.chl.proximity.Utilities.ProximityVector;

/**
 * @author Linda Evaldsson
 * @date 2015-05-30
 *
 * An interface for boxes that are temporarily shown
 */
public interface HoverBox {

    /**
     * A method that checks whether the object is enabled and contains the point
     * @param point the point to be checked
     * @return whether the point is within the object
     */
    boolean containsPoint(ProximityVector point);

    /**
     * Disables the object
     */
    public void disable();

    /**
     * Enables the object on the point taken as parameter
     * @param point the point where it should be displayed
     */
    public void enable(ProximityVector point);

    /**
     * Draws the object
     * @param batch The ProximityBatch to be used for rendering
     */
    public void render(ProximityBatch batch);

}
