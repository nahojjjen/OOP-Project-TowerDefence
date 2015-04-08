package edu.chl.proximity.Models.CreepGenerator;

import edu.chl.proximity.Models.Creeps.Triangle;
import edu.chl.proximity.Models.Maps.Map;

/**
 * Created by Floompa on 2015-04-08.
 */
public class StandardGenerator {

    private int counter = 0;
    private Map map;


    public StandardGenerator(Map map) {
        this.map = map;
    }
    public void tick() {
        counter++;
        if(counter > 50) {
            counter = 0;
            map.spawnCreep(new Triangle());
        }
    }
}
