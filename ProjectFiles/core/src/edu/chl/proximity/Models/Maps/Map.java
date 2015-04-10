package edu.chl.proximity.Models.Maps;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool;
import edu.chl.proximity.Models.Creeps.Creep;
import edu.chl.proximity.Models.Image;
import edu.chl.proximity.Models.Paths.Path;
import edu.chl.proximity.Models.Projectiles.Projectile;
import edu.chl.proximity.Models.Towers.Tower;
import edu.chl.proximity.Models.Waves.Wave;
import edu.chl.proximity.Utilities.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by simongislen on 02/04/15. Modified by Linda And Johan
 */
public abstract class Map {

    private int waveIndex;

    private ArrayList<Creep> creeps = new ArrayList<Creep>();
    private ArrayList<Wave> waves = new ArrayList<Wave>();
    private ArrayList<Tower> towers = new ArrayList<Tower>();
    private ArrayList<Projectile> projectiles = new ArrayList<Projectile>();


    private ParticleEffectPool explosionEffectPool;
    List<ParticleEffectPool.PooledEffect> bombEffects = new ArrayList();


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

        initiateParticles();

    }
//////////////////////Particle work

    /**
     * create all the particle pools (currently only explosion)
     */
    private void initiateParticles(){
        FileHandle particleEffectsImagesFolder = new FileHandle(Constants.filePath + "Particles/");

        ParticleEffect explosionEffect = new ParticleEffect();
        FileHandle explosionEffectFile = new FileHandle(Constants.filePath + "Particles/explosion");
        explosionEffect.load(explosionEffectFile, particleEffectsImagesFolder);

        explosionEffectPool  = new ParticleEffectPool(explosionEffect, 1, 100 );
    }

    /**
     *
     * @param x
     * @param y
     */
    public void createExplosion(int x, int y){
        ParticleEffectPool.PooledEffect effect = explosionEffectPool.obtain();
        effect.setPosition(x,y);
        bombEffects.add(effect);
        effect.start();
    }

    public List<ParticleEffectPool.PooledEffect> getExplosions(){
        return bombEffects;
    }

    /////////////////////////////////End particle work



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
