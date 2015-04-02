package edu.chl.proximity.Models.Maps;

import edu.chl.proximity.Models.Creeps.AbstractCreep;
import edu.chl.proximity.Models.Factions.Faction;
import edu.chl.proximity.Models.Paths.Path;
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

    public Path getPath(){return path};

    public void setPath(Path newPath){ path = newPath};


    /**
     * create a creep
     */
    public static void spawnCreep() {
        creeps.add(new Creep());
    }

    /**
     * test method to test performance, spawns 1000 basic creeps.
     */
    public static void spawnCreepMass() {
        for (int i = 0; i < 1000; i++) {
            creeps.add(new Creep());
        }
        System.out.println("amount of creeps = " + creeps.size());
    }
    /**
     * test method to test performance, spawns 1000 basic turrets.
     */
    public static void spawnTurretMass()   {
        for (int i = 0; i < 1000; i++) {
            towers.add(new MissileTower(new Point(500,500)));
        }
        System.out.println("amount of creeps = " + creeps.size());
    }
    /**
     * adds some randomly placed turrets for testing purposes
     * @param amount amount of towers that should be placed
     */
    public static void addSpreadTurrets(int amount)  {
        for (int i=0; i<amount; i++){
            addTower(new MissileTower(new Point((int)(Math.random()*700), (int)(Math.random()*700))));
        }
    }
    /**
     * add a projectile to the model
     *
     * @param p the projectile to be added
     */
    public static void addProjectile(Projectile p) {
        projectiles.add(p);
    }

    /**
     * add a tower to the model
     *
     * @param t what tower should be added
     */
    public static void addTower(MissileTower t) {
        towers.add(t);
    }

    /**
     * add a particle to the model
     *
     * @param par what particle to add
     */
    public static void addParticle(Particle par) {
        particles.add(par);
    }


}
