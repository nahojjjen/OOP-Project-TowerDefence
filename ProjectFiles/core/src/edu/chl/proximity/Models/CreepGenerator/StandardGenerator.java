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
 * A class that adds a creep every 50th frame
 *
 * =======
 * Revised Simon Gislen 16/04: Modified purpose of class to handle creep spawning logic.
 */
public class StandardGenerator {

    private int counter = 0;
    private Map map;


    public StandardGenerator() {
        this.map = GameData.getInstance().getMap();

    }

    public Wave generateWaveForWaveIndex(int waveIndex) {

        //Defaults
        double spawnInterval = 1;
        double cooldown = 4;
        ArrayList<Creep> creeps = new ArrayList<Creep>();

        //First 5 waves are standard, after this spawning follows an algorithm
        if (waveIndex <= 5) {

            for (int i = 0; i < 5; i++) {
                creeps.add(new Circle());
            }

            switch (waveIndex) {
                case 2: {
                    spawnInterval = 0.75;
                }
                break;
                case 3: {
                    for (int i = 0; i < 5; i++) {
                        creeps.add(new Circle());
                    }
                }
                break;
                case 4: {
                    for (int i = 0; i < 10; i++) {
                        creeps.add(new Circle());
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
            for (int i = 0; i < waveIndex*2; i++) {
                creeps.add(new Circle());
            }
            for (int i = 0; i < waveIndex; i++) {
                creeps.add(new Triangle());
            }

            spawnInterval = Math.max(3/waveIndex, 0.1);
        }

        return new Wave(creeps, spawnInterval, cooldown);
    }
}

