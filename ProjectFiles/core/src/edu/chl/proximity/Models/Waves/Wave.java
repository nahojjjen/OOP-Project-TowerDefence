package edu.chl.proximity.Models.Waves;

import edu.chl.proximity.Models.Creeps.Creep;

import java.util.List;

/**
 * @author Simon Gislén
 * @date 2015-04-02
 */
public class Wave {
    private List<Creep> creeps;
    private double spawnInterval;
    private double cooldownTimeInterval;


    public Wave(List<Creep> creeps, double spawnInterval, double cooldownTimeInterval) {
        this.creeps = creeps;
        this.spawnInterval = spawnInterval;
        this.cooldownTimeInterval = cooldownTimeInterval;
    }

    //Getters
    public List<Creep> getCreeps() {
        return creeps;
    }
    public double getSpawnInterval() {
        return spawnInterval;
    }
    public double getCooldownTimeInterval() {
        return cooldownTimeInterval;
    }
}
