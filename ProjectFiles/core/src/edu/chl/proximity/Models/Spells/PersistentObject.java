package edu.chl.proximity.Models.Spells;

import com.badlogic.gdx.math.Vector2;
import edu.chl.proximity.Models.BoardObject;
import edu.chl.proximity.Models.GameData;
import edu.chl.proximity.Models.Image;

/**
 * @author simongislen
 * @date 23/04/15.
 * Defines a marker that effects nearby objects
 *
 * 04-24 Modified by Johan, added position & makes the object add itself to map on creation
 */

public abstract class PersistentObject extends BoardObject {

    private int counter;

    public PersistentObject(Vector2 position, int counter) {
        super(position, null, 0);
        this.counter = counter;

        if (position != null){
            //GameData.getInstance().getMap().getAddstack.add(this); this needs to be made for concurrent modificaiton
            GameData.getInstance().getMap().getPersistentObjects().add(this);
        }
    }

    /**
     * Method is called every frame, calling the +performEffect method.
     * When the counter reaches zero, the object is destroyed.
     */
    public void tick() {

        if (counter <= 0) {
            GameData.getInstance().getMap().getRemoveStack().add(this);
            return;
        }
        performEffect(counter);
        counter--;
    }

    public abstract void performEffect(int counter);
}
