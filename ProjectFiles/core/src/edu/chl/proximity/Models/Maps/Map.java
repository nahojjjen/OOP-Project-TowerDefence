package edu.chl.proximity.Models.Maps;

import edu.chl.proximity.Models.Backgrounds.Background;
import edu.chl.proximity.Models.Creeps.Creep;
import edu.chl.proximity.Models.Particles.ParticleManager;
import edu.chl.proximity.Models.Paths.Path;
import edu.chl.proximity.Models.Projectiles.Projectile;
import edu.chl.proximity.Models.Towers.Tower;
import edu.chl.proximity.Models.Waves.Wave;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by simongislen on 02/04/15. Modified by Linda And Johan
 * The map holds all information related to an instance of the game except for the player details
 * The games current map is accessible from GameData.
 */
public abstract class Map {

    private int waveIndex;

    private ArrayList<Creep> creeps = new ArrayList<Creep>();
    private ArrayList<Wave> waves = new ArrayList<Wave>();
    private ArrayList<Tower> towers = new ArrayList<Tower>();
    private ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
    private ParticleManager particleManager = new ParticleManager();
    private Set<Creep> creepKillStack = new HashSet<Creep>();
    private Set<Projectile> projectileKillStack = new HashSet<Projectile>();

    private Path path;
    private Background background;
    private int resources;
    //private Faction faction;
    private static Map map;


    /**
     * Create a map with the specified path and image background
     * @param path what path the creeps should follow on this map.
     * @param background What background image should be displayed on this map
     */
    public Map(Path path, Background background){
        this.path = path;
        this.background = background;
    }


    /**
     * get the particleManager in the map
     * @return An object that keeps track of all the particle-effects in play, and that can also create
     * new particle effects.
     */
    public ParticleManager getParticleManager(){
        return particleManager;
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
    public Background getBackground(){ return  background;}
    public Set<Creep> getCreepKillStack(){ return creepKillStack;}
    public Set<Projectile> getProjectileKillStack(){ return projectileKillStack;}


    /**
     * Add a creep to the map.
     */
    public void addCreep(Creep creep) {
        creeps.add(creep);
    }

    /**
     * test method to test performance, spawns 1000 of specified creep & prints out how many creeps are currently on the map.
     */
    public void addCreepMass(Creep creep, int amount) {
        if (amount > 1) {
            for (int i = 0; i < amount; i++) {
                creeps.add(creep);
            }
            System.out.println("amount of creeps = " + creeps.size());
        }
    }


    /**
     * test method to test performance, spawns 1000  turrets. & prints out how many towers are on the map
     */
    public  void spawnTurretMass(Tower tower)   {
        for (int i = 0; i < 1000; i++) {
            towers.add(tower);
        }
        System.out.println("amount of towers = " + towers.size());
    }


    /**
     * Add a projectile to the map
     *
     * @param p the projectile to be added
     */
    public void addProjectile(Projectile p) {
        projectiles.add(p);
    }

    /**
     * add a tower to the map
     *
     * @param t what tower should be added
     */
    public  void addTower(Tower t) {
        towers.add(t);
    }



}
