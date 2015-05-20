package edu.chl.proximity.Models.Map.Maps;

import edu.chl.proximity.Models.Player.Players.GameData;
import edu.chl.proximity.Models.Player.Spells.Spell;
import edu.chl.proximity.Models.ResourceSystem.Resources;
import edu.chl.proximity.Models.Utils.ProximityBatch;
import edu.chl.proximity.Utilities.ProximityVector;
import edu.chl.proximity.Models.Map.Holdables.Hand;
import edu.chl.proximity.Models.Map.Background;
import edu.chl.proximity.Models.Map.Bases.Base;
import edu.chl.proximity.Models.BoardObject;
import edu.chl.proximity.Models.Map.Creeps.Creep;
import edu.chl.proximity.Models.Map.Particles.ParticleManager;
import edu.chl.proximity.Models.Map.Paths.Path;
import edu.chl.proximity.Models.Map.Projectiles.Projectile;
import edu.chl.proximity.Models.Map.Towers.Tower;
import edu.chl.proximity.Utilities.PointCalculations;

import java.util.*;

/**
 * @author Simon Gislén
 * @author Linda Evaldsson, Johan Swanberg (revised)
 * @date 2015-04-02
 *
 * The map holds all information related to an instance of the game except for the player details
 *
 * ---
 * 08/04 Modified by Johan Swanberg. Switch to Screen from GameState.
 * 08/04 modified by Linda Evaldsson. Made Map-class abstract. It is no longer a singleton.
 * 23/04 Modified by Simon Gislen, added Persistant Object management
 * 25/04 Modified by Johan Swanberg, adds adding Stack
 * 08/05 modified by Linda Evaldsson. Moved functionality to this class; rendering of towers, updating and stack-functionality (clear stacks)
 * 10/5 modified by Johan Swanberg, fixed creepwithinrange method and added somee comments
 * 10/05 modified by Hanna Romer. Added method towersWithinRange that is exactly like creepsWithinRange.
 * 17/05 modified by Hanna Romer. Added method getNew.
 * */
public abstract class Map {

    private String name;
    private Hand hand = new Hand();

    private ArrayList<Creep> creeps = new ArrayList<Creep>();
    private ArrayList<Spell> spells = new ArrayList<Spell>();
    private ArrayList<Tower> towers = new ArrayList<Tower>();
    private ArrayList<Projectile> projectiles = new ArrayList<Projectile>();

    private Resources collectedResources = new Resources(0, 0, 0);
    private int collectedExperience = 0;

    private Path path;
    private Background background;
    private Base base;

    private ParticleManager particleManager;

    /**
     * Create a map with the specified path and image background
     * @param path what path the creeps should follow on this map.
     * @param background What background image should be displayed on this map
     */
    public Map(Path path, Background background, String name){
        this.path = path;
        this.background = background;
        this.name=name;
        particleManager = new ParticleManager(GameData.getInstance().getPlayer().getSettings());
    }


    /**
     * Mark an object for adding to the map, the object will be added for next frame
     * @param object what object to add for next frame
     */
    public void add(BoardObject object) {
        if(object instanceof Creep)
            creeps.add((Creep) object);
        if(object instanceof Tower)
            towers.add((Tower) object);
        if(object instanceof Spell)
            spells.add((Spell) object);
        if(object instanceof Projectile)
            projectiles.add((Projectile) object);
    }

    public int getNumberOfObjectsOnMap() {
        return creeps.size() + towers.size() + spells.size() + projectiles.size();
    }

    /**
     * get the name of the map, example "practiceMap" or "The Snow hill"
     * @return A string of the name of the map
     */
    public String getName(){
        return name;
    }

    /**
     * get what the player is currently holding in his "hand" (cursor)
     * @return
     */
    public Hand getHand() { return hand; }

    public Resources getCollectedResources() {
        return collectedResources;
    }
    public void clearCollectedResources() {
        collectedResources.setResources(0, 0, 0);
    }

    public int getCollectedExperience() {
        return collectedExperience;
    }
    public void clearCollectedExperience() {
        collectedExperience = 0;
    }

    /**
     * get the particleManager in the map
     * @return An object that keeps track of all the particle-effects in play, and that can also create
     * new particle effects.
     */
    public ParticleManager getParticleManager(){
        return particleManager;
    }

    public ArrayList<Creep> getCreeps() {
        return creeps;
    }
    public BoardObject getObjectOnPosition(ProximityVector position) {
        for(Tower t : towers) {
            if(t.containsPoint(position))
                return t;
        }
        for(Creep c : creeps) {
            if(c.containsPoint(position)) {
                return c;
            }
        }
        return null;
    }

    /**
     * Get a list of all creeps within range (radius) of the position
     * @param position What position to search around
     * @param range What range (radius) to search around
     * @return A list of creeps within the range specified
     */
    public List<Creep> getCreepsWithinDistance(ProximityVector position, double range) {
        List<Creep> creepsWithinRange = new ArrayList<Creep>();
        if (range <= 0 || position == null){return creepsWithinRange;}
        for (Creep creep : creeps) {
            if (PointCalculations.distanceBetweenNoSqrt(creep.getCenter(), position) < range * range) {

                creepsWithinRange.add(creep);
            }
        }
        return creepsWithinRange;
    }

    /**
     * Updates all towers, creeps, spells and projectile logic on this map.
     */
    public void update() {

        for (Tower tower : towers) {
            tower.update(creeps);
        }
        for (Creep creep : creeps) {
            creep.rotate();
            creep.move();
        }

        for (Spell spell : spells) {
            base.setLife(base.getLife() + spell.getHealthChange());
            spell.setHealthChange(0);
            spell.setCreeps(creeps);
            spell.setTowers(towers);
            spell.tick();

        }

        for (Projectile projectile:projectiles){
            projectile.reAngle();
            projectile.move();
            for (Creep creep : creeps) {
                if (projectile.collidesWith(creep)) {
                    projectile.collide(creep);

                    double range = projectile.getRange();
                    if(range <= 0) {
                        projectile.attack(creep);
                    }
                    else {
                        for(Creep listCreep: creeps) {
                            if(PointCalculations.distanceBetweenNoSqrt(projectile.getCenter(), listCreep.getCenter()) < range*range){
                                projectile.attack(listCreep);
                            }
                        }
                    }

                }
            }
            if (projectile.isOutsideView()){
                projectile.remove();
            }
        }

    }

    public Path getPath(){return path;}
    public void setPath(Path newPath){
        if(newPath == null) {
            throw new IllegalArgumentException();
        }
        path = newPath;}
    public Background getBackground(){ return  background;}
    public Base getBase(){return base;}
    public void setBase(Base base){ this.base = base;}

    public void setChosenTower(Tower tower){
        System.out.println(towers.contains(tower));
        if(towers.contains(tower)) {
            getHand().setItem(tower);
        }
        else {
            getHand().setItem(null);
        }
    }

    public Tower getChosenTower(){
        if(getHand().getItem() instanceof Tower){
            if(towers.contains(((Tower) getHand().getItem()))) {
                return (Tower) getHand().getItem();
            }
        }
        return null;
    }


    /**
     * Remove all objects that have been marked for deletion from this map
     */
    public void clearRemoveStack() {

        removeFromMapFromList(creeps);
        removeFromMapFromList(towers);
        removeFromMapFromList(projectiles);
        removeFromMapFromList(spells);

    }
    public void removeFromMapFromList(List<? extends BoardObject> list) {
        Iterator mapIterator = list.iterator();
        while(mapIterator.hasNext()) {
            BoardObject object = (BoardObject)mapIterator.next();

            if(object.isRemoved()) {

                if(object instanceof Creep) {
                    Creep creep = (Creep) object;
                    mapIterator.remove();
                    if (creep.reachedLastWayPoint()) {
                        getBase().damage();
                    }
                    else {
                        collectedResources.addResources(creep.getCreepResource());
                        collectedExperience += creep.getCreepExperiencePoints();
                    }
                }
                if(object instanceof Tower) {
                    mapIterator.remove();
                }
                if(object instanceof Projectile) {
                    mapIterator.remove();
                }
                if(object instanceof Spell) {
                    mapIterator.remove();
                }

            }
        }
    }

    public void addToMapFromList(List<? extends BoardObject> list) {

        Set<BoardObject> addSet = new HashSet<BoardObject>();
        Iterator mapIterator = list.iterator();

        while(mapIterator.hasNext()) {

            BoardObject object = (BoardObject)mapIterator.next();
            List<BoardObject> addList = object.getAddList();
            if(object.getAddList().size() > 0) {
                Iterator addIterator = addList.iterator();
                while(addIterator.hasNext()) {
                    BoardObject o = (BoardObject)addIterator.next();
                    addSet.add(o);
                }
            }

            object.clearAddList();
        }
        for(BoardObject o : addSet) {
            if(o instanceof Creep)
                creeps.add((Creep)o);
            if(o instanceof Tower) {
                towers.add((Tower) o);
            }
            if(o instanceof Projectile)
                projectiles.add((Projectile)o);
            if(o instanceof Spell)
                spells.add((Spell)o);

        }
    }

    /**
     * Add all objects that have been marked for addition this frame to the map
     */
    public void clearAddStack() {

        addToMapFromList(creeps);
        addToMapFromList(towers);

    }




    /**
     * Render all projectiles, creeps and towers on the map.
     * Notice that the particles are handeled seperately
     * @param batch What batch should be used to draw the images corresponding to the items on the map
     */
    public void render(ProximityBatch batch) {

        if (towers != null){
            for (Tower tower : towers) {
                tower.render(batch);

            }
        }
        if (projectiles != null){
            for (Projectile projectile : projectiles) {
                projectile.render(batch);
            }
        }
        if (creeps != null){
            for (Creep creep : creeps) {
                creep.render(batch);
            }
        }


    }

    public abstract Map getNew();



}
