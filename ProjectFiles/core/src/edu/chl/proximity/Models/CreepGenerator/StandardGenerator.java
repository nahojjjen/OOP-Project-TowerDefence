package edu.chl.proximity.Models.CreepGenerator;

import com.badlogic.gdx.Game;
import edu.chl.proximity.Models.Creeps.ConcreteCreeps.Circle;
import edu.chl.proximity.Models.Creeps.ConcreteCreeps.Line1;
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
 * Unknown date modified by Johan Swanberg
 *
 * ---
 * 16/04 modified by Simon Gislén. Modified purpose of class to handle creep spawning logic.
 * 21/04 modified by Simon Gislén.
 */
public class StandardGenerator {
    private Map map;

    public StandardGenerator() {
        this.map = GameData.getInstance().getMap();

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
        double cooldown = 4;
        ArrayList<Creep> creeps = new ArrayList<Creep>();

        //First 5 waves are standard, after this spawning follows an algorithm
        if (waveIndex <= 5) {

            for (int i = 0; i < 5; i++) {
                creeps.add(new Line1(1));
            }
            //creeps.add(new Triangle());

            switch (waveIndex) {
                case 2: {
                    spawnInterval = 0.75;
                }
                break;
                case 3: {
                    for (int i = 0; i < 5; i++) {
                        creeps.add(new Line1(2));
                    }
                }
                break;
                case 4: {
                    for (int i = 0; i < 10; i++) {
                        creeps.add(new Line1(3));
                    }
                    spawnInterval = 0.5;
                }
                break;
                case 5: {
                    for (int i = 0; i < 10; i++) {
                        creeps.add(new Triangle());
                    }
                    spawnInterval = 1;
                }
                break;

            }

        } else {
            //Some endless algorithm
            for (int i = 0; i < (int)(waveIndex/2); i++) {
                creeps.add(new Circle());
            }
            for (int i = 0; i < (int)(waveIndex/5); i++) {
                creeps.add(new Line1(4));
            }

            spawnInterval = Math.max(3 / waveIndex, 0.2);

        }
        return new Wave(creeps, spawnInterval, cooldown);
    }
}