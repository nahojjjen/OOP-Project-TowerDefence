package edu.chl.proximity.Models.CreepGenerator;

import com.badlogic.gdx.Game;
import edu.chl.proximity.Models.Creeps.ConcreteCreeps.Circle;
import edu.chl.proximity.Models.Creeps.ConcreteCreeps.Triangle;
import edu.chl.proximity.Models.Creeps.Creep;
import edu.chl.proximity.Models.GameData;
import edu.chl.proximity.Models.Maps.Map;
import edu.chl.proximity.Models.Waves.Wave;

import java.util.ArrayList;

/**
 * @author Linda Evaldsson
 * @author Johan Swanberg (revised)
 * @author Simon Gislen
 * @date 2015-04-08
 *
 * Revised Simon Gislen 16/04: Modified purpose of class to handle creep spawning logic.
 */
public class StandardGenerator {
    private Map map;

    public StandardGenerator() {
        this.map = GameData.getInstance().getMap();

    }



}