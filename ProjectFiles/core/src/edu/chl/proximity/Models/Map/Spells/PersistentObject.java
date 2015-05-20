package edu.chl.proximity.Models.Map.Spells;

import edu.chl.proximity.Utilities.ProximityVector;
import edu.chl.proximity.Models.BoardObject;
import edu.chl.proximity.Models.Utils.Image;

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

    public PersistentObject(ProximityVector position, Image icon, int counter) {
        super(position, icon, 0);
        this.counter = counter;

        if (position != null){
            add(this);
        }
    }


    /**
     * Method is called every frame, calling the +performEffect method.
     * When the counter reaches zero, the object is destroyed.
     */
    public void tick() {
    if (started){
            if (counter <= 0) {
                this.remove();
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
