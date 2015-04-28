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
 * 24/04 Modified by Johan, added position & makes the object add itself to map on creation
 * 27/04 Modified by Johan, persistentobject now uses addStack
 * 28/4 Modified by Johan, persistant objects now have to be started
 */

public abstract class PersistentObject extends BoardObject {

    private int counter;
    private boolean started= false;

    public PersistentObject(Vector2 position, Image icon, int counter) {
        super(position, icon, 0);
        this.counter = counter;

        if (position != null){
            GameData.getInstance().getMap().getAddStack().add(this);// this needs to be made for concurrent modificaiton
        }
    }

    /**
     * Method is called every frame, calling the +performEffect method.
     * When the counter reaches zero, the object is destroyed.
     */
    public void tick() {
    if (started){
            if (counter <= 0) {
                GameData.getInstance().getMap().getRemoveStack().add(this);
                return;
            }
            performEffect(counter);
            counter--;
        }
    }

    public void start(){
        started = true;
    }
    public abstract void performEffect(int counter);
}
