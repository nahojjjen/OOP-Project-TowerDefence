package edu.chl.proximity.Models.Player.Spells;

import com.badlogic.gdx.math.Vector2;
import edu.chl.proximity.Models.BoardObject;
import edu.chl.proximity.Models.Map.Maps.Map;
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
    private int backupCounter = 0;

    public PersistentObject(Map map, Vector2 position, Image icon, int counter) {
        super(map, position, icon, 0);
        this.counter = counter;
        this.backupCounter = counter;

        if (position != null){
            getMap().add(this);// this needs to be made for concurrent modificaiton
        }
    }

    public void resetPersistentObject(){
        counter = backupCounter;
        started = false;
    }
    /**
     * Method is called every frame, calling the +performEffect method.
     * When the counter reaches zero, the object is destroyed.
     */
    public void tick() {
    if (started){
            if (counter <= 0) {
                getMap().remove(this);
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
