package edu.chl.proximity.Models.Map.Holdables;

import com.badlogic.gdx.graphics.Color;
import edu.chl.proximity.Models.ResourceSystem.Resources;
import edu.chl.proximity.Models.Utils.Image;
import edu.chl.proximity.Models.Utils.ProximityBatch;
import edu.chl.proximity.Utilities.ProximityVector;

/**
 * @author Linda Evaldsson
 * @date 2015-04-22
 *
 * Classes that implement the Holdable interface are objects that the user is about to place on the map.
 * For example, towers and spells.
 *
 * ---
 * 03/05 modified by Simon Gislen. Introducing: *Towers are not free*
 *
 */
public interface Holdable {

    /**
     * Puts the object down on the map
     */
    public void preparePlacing(ProximityVector position);

    public void render(ProximityBatch batch);

    public Image getImage();

    public int getWidth();

    public int getHeight();

    public boolean isPlaced();

    public Color getColor();

    public double getRange();

    public Resources getCost();

    public ProximityVector getCenter();

    public boolean canBePlacedOnPath();

}
