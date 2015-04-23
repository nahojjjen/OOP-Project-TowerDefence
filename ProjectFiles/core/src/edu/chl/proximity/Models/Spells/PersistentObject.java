package edu.chl.proximity.Models.Spells;

import com.badlogic.gdx.math.Vector2;
import edu.chl.proximity.Models.BoardObject;
import edu.chl.proximity.Models.GameData;
import edu.chl.proximity.Models.Image;

/**
 * @author simongislen
 * @date 23/04/15.
 * Defines a marker that effects nearby objects
 */

public abstract class PersistentObject extends BoardObject {

    private int counter;

    public PersistentObject(int counter) {
        super();
        this.counter = counter;
    }

    /**
     * Method is called every frame, calling the +performEffect method.
     * When the counter reaches zero, the object is destroyed.
     */
    public void tick() {

        if (counter == 0) {
            GameData.getInstance().getMap().getRemoveStack().add(this);
            return;
        }
        performEffect(counter);
        counter--;
    }

    public abstract void performEffect(int counter);
}
