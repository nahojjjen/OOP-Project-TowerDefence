package edu.chl.proximity.Models.Map.Spells;

import edu.chl.proximity.Models.BoardObject;
import edu.chl.proximity.Models.Utils.Image;
import edu.chl.proximity.Utilities.ProximityVector;

/**
 * @author Simon Gislen
 * @date 2015-04-23
 *
 * An object which is left on the screen for a limited amount of time, then removed.
 *
 * ---
 * 24/04 Modified by Johan Swanberg, added position & makes the object add itself to map on creation
 * 27/04 Modified by Johan Swanberg, persistentobject now uses addStack
 * 28/4 Modified by Johan Swanberg, persistant objects now have to be started
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

    public int getCounter() {
        return counter;
    }
    public boolean isStarted() {
        return started;
    }
}
