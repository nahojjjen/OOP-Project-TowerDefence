package edu.chl.proximity.Models.Utils.CreepGenerator;

import edu.chl.proximity.Models.Map.Creeps.ConcreteCreeps.Circle;
import edu.chl.proximity.Models.Map.Creeps.ConcreteCreeps.Line1;
import edu.chl.proximity.Models.Map.Creeps.ConcreteCreeps.Triangle;
import edu.chl.proximity.Models.Map.Creeps.Creep;
import edu.chl.proximity.Models.Utils.GameData;
import edu.chl.proximity.Models.Map.Maps.Map;
import edu.chl.proximity.Models.Map.Waves.Wave;

import java.util.ArrayList;

/**
 * @author Linda Evaldsson
 * @author Johan Swanberg (revised)
 * @author Simon Gislen
 * @date 2015-04-08
 *
 * Unknown date modified by Johan Swanberg
 *
 * ---
 * 16/04 modified by Simon Gislén. Modified purpose of class to handle creep spawning logic.
 * 21/04 modified by Simon Gislén.
 */
public class StandardGenerator {
    private Map map;

    public StandardGenerator(Map map) {
        this.map = map;

    }

    /**
     * @author Simon Gislen
     *
     * Method that generates creep waves
     *
     * @param waveIndex the wave number that the player is on
     * @return Wave instance describing creeps, cooldown and spawn intervals
     */
    public Wave generateWaveForWaveIndex(int waveIndex) {

        //Defaults

        double spawnInterval = 1;
        double cooldown = 10;
        ArrayList<Creep> creeps = new ArrayList<Creep>();

        //First 5 waves are standard, after this spawning follows an algorithm
        if (waveIndex <= 5) {

            for (int i = 0; i < 5; i++) {
                creeps.add(new Line1(map, 1));
            }
            //creeps.add(new Triangle());

            switch (waveIndex) {
                case 2: {
                    spawnInterval = 0.75;
                }
                break;
                case 3: {
                    for (int i = 0; i < 5; i++) {
                        creeps.add(new Line1(map, 2));
                    }
                }
                break;
                case 4: {
                    for (int i = 0; i < 10; i++) {
                        creeps.add(new Line1(map, 3));
                    }
                    spawnInterval = 0.5;
                }
                break;
                case 5: {
                    for (int i = 0; i < 10; i++) {
                        creeps.add(new Triangle(map));
                    }
                    spawnInterval = 1;
                }
                break;

            }

        } else {
            //Some endless algorithm
            double r = Math.random()*5;
            for (int i = 0; i < Math.max(0, (int)(waveIndex/2) - r); i++) {
                creeps.add(new Circle(map));
            }
            for (int i = 0; i < (int)(waveIndex/5); i++) {
                creeps.add(new Line1(map, 4));
            }

            spawnInterval = Math.max(3 / Math.max(1, waveIndex - 5), 0.2);

        }
        return new Wave(creeps, spawnInterval, cooldown);
    }
}