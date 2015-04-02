package edu.chl.proximity.Models.Maps;

import edu.chl.proximity.Models.Creeps.AbstractCreep;
import edu.chl.proximity.Models.Factions.Faction;
import edu.chl.proximity.Models.Projectiles.AbstractProjectile;
import edu.chl.proximity.Models.Towers.AbstractTower;
import edu.chl.proximity.Models.Waves.Wave;

import java.util.ArrayList;

/**
 * Created by simongislen on 02/04/15.
 */
public class Map {

    private int waveIndex;

    /**
     * Creep list
     */
    private ArrayList<AbstractCreep> creeps;
    /**
     * Creep wave list
     */
    private ArrayList<Wave> waves;
    /**
     * List of Towers placed on map
     */
    private ArrayList<AbstractTower> towers;
    /**
     * List of Particles on map
     */
    private ArrayList<AbstractParticle> particles;
    /**
     * List of Projectiles on map
     */
    private ArrayList<AbstractProjectile> projectiles;

    private Path path;
    private String backgroundImage;
    private int resources;
    private Faction faction;


    //Getters and Setters;
    public ArrayList<Wave> getWaves() {
        return waves;
    }

    public void setWaves(ArrayList<Wave> waves) {
        this.waves = waves;
    }

    public ArrayList<Tower> getTowers() {
        return towers;
    }

    public void setTowers(ArrayList<Tower> towers) {
        this.towers = towers;
    }

    public ArrayList<Particle> getParticles() {
        return particles;
    }

    public void setParticles(ArrayList<Particle> particles) {
        this.particles = particles;
    }

    public ArrayList<Projectile> getProjectiles() {
        return projectiles;
    }

    public void setProjectiles(ArrayList<Projectile> projectiles) {
        this.projectiles = projectiles;
    }

    public ArrayList<AbstractCreep> getCreeps() {
        return creeps;
    }

    public void setCreeps(ArrayList<AbstractCreep> creeps) {
        this.creeps = creeps;
    }
}
