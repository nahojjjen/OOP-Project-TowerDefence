package edu.chl.proximity.Models.Maps;

import edu.chl.proximity.Models.Creeps.Creep;
import edu.chl.proximity.Models.Image;
import edu.chl.proximity.Models.Paths.Path;
import edu.chl.proximity.Models.Projectiles.Projectile;
import edu.chl.proximity.Models.Towers.Tower;
import edu.chl.proximity.Models.Waves.Wave;

import java.util.ArrayList;

/**
 * Created by simongislen on 02/04/15.
 */
public abstract class Map {

    private int waveIndex;

    private ArrayList<Creep> creeps = new ArrayList<Creep>();
    private ArrayList<Wave> waves = new ArrayList<Wave>();
    private ArrayList<Tower> towers = new ArrayList<Tower>();
   // private ArrayList<AbstractParticle> particles;
    private ArrayList<Projectile> projectiles = new ArrayList<Projectile>();

    private Path path;
    private Image backgroundImage;
    private int resources;
    //private Faction faction;
    private static Map map;


    /**
     * creates the map instance
     */
    public Map(Path path, Image background){
        this.path = path;
        backgroundImage = background;

    }

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
/*
    public ArrayList<Particle> getParticles() {        return particles;    }

    public void setParticles(ArrayList<Particle> particles) {        this.particles = particles;    }

*/

    public ArrayList<Projectile> getProjectiles() {
        return projectiles;
    }

    public void setProjectiles(ArrayList<Projectile> projectiles) {
        this.projectiles = projectiles;
    }

    public ArrayList<Creep> getCreeps() {
        return creeps;
    }

    public void setCreeps(ArrayList<Creep> creeps) {
        this.creeps = creeps;
    }

    public Path getPath(){return path;}

    public void setPath(Path newPath){ path = newPath;}


    /**
     * create a creep
     */
    public void spawnCreep(Creep creep) {
        creeps.add(creep);
    }

    /**
     * test method to test performance, spawns 1000 basic creeps.
     */
    public void spawnCreepMass(Creep creep) {
        for (int i = 0; i < 1000; i++) {
            creeps.add(creep);
        }
        System.out.println("amount of creeps = " + creeps.size());
    }
    /**
     * test method to test performance, spawns 1000 basic turrets.
     */
    public  void spawnTurretMass(Tower tower)   {
        for (int i = 0; i < 1000; i++) {
            towers.add(tower);
        }
        System.out.println("amount of creeps = " + creeps.size());
    }
    /**
     * adds some randomly placed turrets for testing purposes
     * @param amount amount of towers that should be placed
     */
    public void addSpreadTurrets(int amount, Tower tower)  {
        for (int i=0; i<amount; i++){
            addTower(tower);
        }
    }
    /**
     * add a projectile to the model
     *
     * @param p the projectile to be added
     */
    public void addProjectile(Projectile p) {
        projectiles.add(p);
    }

    /**
     * add a tower to the model
     *
     * @param t what tower should be added
     */
    public  void addTower(Tower t) {
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
