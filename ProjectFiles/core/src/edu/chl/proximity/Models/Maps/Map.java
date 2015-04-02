package edu.chl.proximity.Models.Maps;

import edu.chl.proximity.Models.Creeps.AbstractCreep;
import edu.chl.proximity.Models.Paths.Path;
import edu.chl.proximity.Models.Projectiles.AbstractProjectile;
import edu.chl.proximity.Models.Towers.AbstractTower;
import edu.chl.proximity.Models.Waves.Wave;

import java.awt.*;
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
   // private ArrayList<AbstractParticle> particles;
    /**
     * List of Projectiles on map
     */
    private ArrayList<AbstractProjectile> projectiles;

    private Path path;
    private String backgroundImage;
    private int resources;
    //private Faction faction;
    private static Map map;


    /**
     * creates the map instance
     */
    private Map(){}

    public static Map getInstance(){
        if (map == null){
            map = new Map();
        }
        return map;
    }

    //Getters and Setters;
    public ArrayList<Wave> getWaves() {
        return waves;
    }

    public void setWaves(ArrayList<Wave> waves) {
        this.waves = waves;
    }

    public ArrayList<AbstractTower> getTowers() {
        return towers;
    }

    public void setTowers(ArrayList<AbstractTower> towers) {
        this.towers = towers;
    }
/*
    public ArrayList<Particle> getParticles() {        return particles;    }

    public void setParticles(ArrayList<Particle> particles) {        this.particles = particles;    }

*/

    public ArrayList<AbstractProjectile> getProjectiles() {
        return projectiles;
    }

    public void setProjectiles(ArrayList<AbstractProjectile> projectiles) {
        this.projectiles = projectiles;
    }

    public ArrayList<AbstractCreep> getCreeps() {
        return creeps;
    }

    public void setCreeps(ArrayList<AbstractCreep> creeps) {
        this.creeps = creeps;
    }

    public Path getPath(){return path;}

    public void setPath(Path newPath){ path = newPath;}


    /**
     * create a creep
     */
    public void spawnCreep(AbstractCreep creep) {
        creeps.add(creep);
    }

    /**
     * test method to test performance, spawns 1000 basic creeps.
     */
    public void spawnCreepMass(AbstractCreep creep) {
        for (int i = 0; i < 1000; i++) {
            creeps.add(creep);
        }
        System.out.println("amount of creeps = " + creeps.size());
    }
    /**
     * test method to test performance, spawns 1000 basic turrets.
     */
    public  void spawnTurretMass(AbstractTower tower)   {
        for (int i = 0; i < 1000; i++) {
            towers.add(tower);
        }
        System.out.println("amount of creeps = " + creeps.size());
    }
    /**
     * adds some randomly placed turrets for testing purposes
     * @param amount amount of towers that should be placed
     */
    public void addSpreadTurrets(int amount, AbstractTower tower)  {
        for (int i=0; i<amount; i++){
            addTower(tower);
        }
    }
    /**
     * add a projectile to the model
     *
     * @param p the projectile to be added
     */
    public void addProjectile(AbstractProjectile p) {
        projectiles.add(p);
    }

    /**
     * add a tower to the model
     *
     * @param t what tower should be added
     */
    public  void addTower(AbstractTower t) {
        towers.add(t);
    }

    /**
     * add a particle to the model
     *
     * @param par what particle to add
     */
    /*
    public  void addParticle(Particle par) {
        particles.add(par);
    }
    */


}
