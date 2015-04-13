package edu.chl.proximity.Models.CreepGenerator;

import com.badlogic.gdx.Game;
import edu.chl.proximity.Models.Creeps.ConcreteCreeps.Triangle;
import edu.chl.proximity.Models.GameData;
import edu.chl.proximity.Models.Maps.Map;

/**
 * Created by Floompa on 2015-04-08.
 * A class that adds a creep every 50th frame
 */
public class StandardGenerator {

    private int counter = 0;
    private Map map;


    public StandardGenerator() {
        this.map = GameData.getInstance().getMap();
    }

    public void tick() {
        counter++;
        if(counter > 4) {
            counter = 0;
            map.addCreep(new Triangle());
        }
    }
}
