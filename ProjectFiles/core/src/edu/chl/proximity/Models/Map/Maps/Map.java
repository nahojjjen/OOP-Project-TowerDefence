package edu.chl.proximity.Models.Map.Maps;

import edu.chl.proximity.Models.ControlPanel.PropertiesPanel.PropertiesPanel;
import edu.chl.proximity.Models.Player.Holdables.Hand;
import edu.chl.proximity.Models.Utils.Background;
import edu.chl.proximity.Models.Map.Bases.Base;
import edu.chl.proximity.Models.BoardObject;
import edu.chl.proximity.Models.Map.Creeps.Creep;
import edu.chl.proximity.Models.Map.Particles.ParticleManager;
import edu.chl.proximity.Models.Map.Paths.Path;
import edu.chl.proximity.Models.Map.Projectiles.Projectile;
import edu.chl.proximity.Models.Player.Spells.PersistentObject;
import edu.chl.proximity.Models.Map.Towers.Tower;
import edu.chl.proximity.Models.Map.Waves.Wave;
import edu.chl.proximity.Models.Utils.Settings;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Simon Gislén
 * @author Linda Evaldsson, Johan Swanberg (revised)
 * @date 2015-04-02
 *
 * The map holds all information related to an instance of the game except for the player details
 * The games current map is accessible from GameData.
 *
 * ---
 * 08/04 Modified by Johan Swanberg. Switch to Screen from GameState.
 * 08/04 modified by Linda Evaldsson. Made Map-class abstract. It is no longer a singleton.
 * 23/04 Modified by Simon Gislen, added Persistant Object management
 * 25/04 Modified by Johan Swanberg, adds adding Stack
 * */
public abstract class Map {
    private String name;
    private int waveIndex;
    private Hand hand = new Hand();

    private PropertiesPanel propertiesPanel;


    private ArrayList<Creep> creeps = new ArrayList<Creep>();
    private ArrayList<PersistentObject> persistentObjects = new ArrayList<PersistentObject>();
    private ArrayList<Wave> waves = new ArrayList<Wave>();
    private ArrayList<Tower> towers = new ArrayList<Tower>();
    private ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
    private ParticleManager particleManager;


    private Set<BoardObject> removeStack = new HashSet<BoardObject>();
    private Set<BoardObject> addStack = new HashSet<BoardObject>();

    private Path path;
    private Background background;
    private Base base;


    /**
     * Create a map with the specified path and image background
     * @param path what path the creeps should follow on this map.
     * @param background What background image should be displayed on this map
     */
    public Map(Path path, Background background, String name){
        this.path = path;
        this.background = background;
        this.name=name;
        particleManager = new ParticleManager();
    }



    public Set<BoardObject> getRemoveStack() {
        return removeStack;
    }
    public Set<BoardObject> getAddStack() {
        return addStack;
    }

    public String getName(){
        return name;
    }

    public Hand getHand() { return hand; }

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
    public Base getBase(){return base;}
    public void setBase(Base base){ this.base = base;}
    public ArrayList<PersistentObject> getPersistentObjects() {
        return persistentObjects;
    }
    public void setPersistentObjects(ArrayList<PersistentObject> persistentObjects) {
        this.persistentObjects = persistentObjects;
    }

    /**
     * Add a creep to the map.
     */
    public void addCreep(Creep creep) {
        addStack.add(creep);
    }
    /**
     * Add a projectile to the map
     *
     * @param p the projectile to be added
     */
    public void addProjectile(Projectile p) {
        addStack.add(p);
    }


    /**
     * add a tower to the map
     *
     * @param t what tower should be added
     */
    public  void addTower(Tower t) {
        addStack.add(t);
    }

    public PropertiesPanel getPropertiesPanel() {
        return propertiesPanel;
    }

    public void setPropertiesPanel(PropertiesPanel propertiesPanel) {
        this.propertiesPanel = propertiesPanel;
    }
}
