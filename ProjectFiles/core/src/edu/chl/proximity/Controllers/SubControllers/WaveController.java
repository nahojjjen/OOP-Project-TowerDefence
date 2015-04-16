package edu.chl.proximity.Controllers.SubControllers;

import edu.chl.proximity.Models.CreepGenerator.StandardGenerator;
import edu.chl.proximity.Models.Creeps.Creep;
import edu.chl.proximity.Models.GameData;
import edu.chl.proximity.Models.Maps.Map;
import edu.chl.proximity.Models.Waves.Wave;

import java.util.List;

/**
 * Created by simongislen on 16/04/15.
 */
public class WaveController {
    private StandardGenerator waveGenerator;
    private Map map;
    private int waveIndex = 1;

    private Wave currentWave;
    private List<Creep> creeps;
    private int creepIndex;

    private int spawnIntervalCounter = 0;
    private int cooldownCounter;


    public WaveController() {
        this.waveGenerator = new StandardGenerator();
        this.map = GameData.getInstance().getMap();
    }

    public void update() {

        if (currentWave == null) {
            currentWave = waveGenerator.generateWaveForWaveIndex(waveIndex);
            creeps = currentWave.getCreeps();
            creepIndex = 0;
        }
        if (creepIndex < creeps.size()) {
            if (spawnIntervalCounter < currentWave.getSpawnInterval() * 60) {
                spawnIntervalCounter++;
                return;
            }
            spawnIntervalCounter = 0;
            Creep creep = creeps.get(creepIndex);
            map.addCreep(creep);
            creepIndex++;
        }
        else {
            if (cooldownCounter < currentWave.getCooldownTimeInterval() * 60) {
                cooldownCounter++;
            }
            else {
                waveIndex++;
                currentWave = null;
            }
        }



    }

}
