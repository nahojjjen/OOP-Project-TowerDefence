package edu.chl.proximity.Models.Map.Maps;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.sun.corba.se.impl.encoding.WrapperInputStream;
import edu.chl.proximity.Utilities.ProximityVector;
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
 * 10/05 modified by Hanna Römer. Added method towersWithinRange that is exactly like creepsWithinRange.
 * */
public abstract class Map {

    private String name;
    private Hand hand = new Hand();

    private PropertiesPanel propertiesPanel;

    private ArrayList<Creep> creeps = new ArrayList<Creep>();
    private ArrayList<PersistentObject> persistentObjects = new ArrayList<PersistentObject>();
    private ArrayList<Tower> towers = new ArrayList<Tower>();
    private ArrayList<Projectile> projectiles = new ArrayList<Projectile>();

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
        particleManager = new ParticleManager();
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
        if(object instanceof PersistentObject)
            persistentObjects.add((PersistentObject) object);
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
            if(c.containsPoint(position))
                return c;
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
     * Get a list of all towers within range(radius) of the position
     * @param pos What position to search around
     * @param range What range (radius) to search around
     * @return A list of towers within the range specified
     */
    public List<Tower> getTowersWithinDistance(ProximityVector pos, double range){
        List<Tower> towersInRange = new ArrayList<Tower>();
        if(range <=0 || pos==null){return towersInRange;}
        for(Tower tower: towers){
            if(PointCalculations.distanceBetweenNoSqrt(tower.getCenter(),pos) < range*range){
                towersInRange.add(tower);
            }
        }
        return towersInRange;
    }

    /**
     * Check if a creep is somewhere on the map this frame
     * @param target what creep to look for
     * @return true if creep is on the map
     */
    public boolean containsCreep(Creep target) {
        return creeps.contains(target);
    }


    /**
     * Updates all towers, creeps, spells and projectile logic on this map.
     */
    public void update() {

        for (Tower tower : towers) {
            tower.update();
        }
        for (Creep creep : creeps) {
            creep.rotate();
            creep.move();
        }

        for (PersistentObject persistentObject : persistentObjects) {
            persistentObject.tick();
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
    public void setPath(Path newPath){ path = newPath;}
    public Background getBackground(){ return  background;}
    public Base getBase(){return base;}
    public void setBase(Base base){ this.base = base;}

    public void setChoosenTower(Tower tower){
        getHand().setItem(tower);
    }

    public Tower getChoosenTower(){
        if(getHand().getItem() instanceof Tower){
            if(((Tower) getHand().getItem()).getIfPlaced()){
                return (Tower) getHand().getItem();
            }
        }
        return null;
    }

    public PropertiesPanel getPropertiesPanel() {
        return propertiesPanel;
    }

    public void setPropertiesPanel(PropertiesPanel propertiesPanel) {
        this.propertiesPanel = propertiesPanel;
    }

    /**
     * Remove all objects that have been marked for deletion from this map
     */
    public void clearRemoveStack() {

        removeFromMapFromList(creeps);
        removeFromMapFromList(towers);
        removeFromMapFromList(projectiles);
        removeFromMapFromList(persistentObjects);

    }
    public void removeFromMapFromList(List<? extends BoardObject> list) {
        Iterator mapIterator = list.iterator();
        while(mapIterator.hasNext()) {
            BoardObject object = (BoardObject)mapIterator.next();

            if(object.isRemoved()) {

                if(object instanceof Creep) {
                    mapIterator.remove();
                    if (((Creep)object).reachedLastWayPoint())
                        getBase().damage();
                }
                if(object instanceof Tower) {
                    mapIterator.remove();
                }
                if(object instanceof Projectile) {
                    mapIterator.remove();
                }
                if(object instanceof PersistentObject) {
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
            if(o instanceof PersistentObject)
                persistentObjects.add((PersistentObject)o);

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
     * render all the tower ranged with a semi transparant circle
     * @param shapeRenderer what shaperenderer should draw the circles on the screen
     */
    public void renderRanges(ShapeRenderer shapeRenderer) {
        for (Tower tower : towers) {
            renderRangeIndicator(shapeRenderer, new Color(0.4f, 0.2f, 0.9f, 0.2f), tower.getCenter(), tower.getRange());
        }
    }
    /**
     * Helper method to draw a circular range
     * @param renderer shape renderer that does the rendering
     * @param color range colour
     * @param position position to draw
     * @param range radius to draw
     */
    private void renderRangeIndicator(ShapeRenderer renderer, Color color, ProximityVector position, double range) {
        Gdx.gl.glEnable(GL20.GL_BLEND); //enables transparency
        renderer.setColor(color);
        renderer.circle(position.x, position.y, (float) range);
    }

    /**
     * Render all projectiles, creeps and towers on the map.
     * Notice that the particles are handeled seperately
     * @param batch What batch should be used to draw the images corresponding to the items on the map
     * @param shapeRenderer What instanc will render the geometrical shapes on the map.
     */
    public void render(SpriteBatch batch, ShapeRenderer shapeRenderer) {

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

        batch.end();
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        if(getChoosenTower() != null)
            renderRangeIndicator(shapeRenderer, new Color(0.4f, 0.2f, 0.9f, 0.2f), getChoosenTower().getCenter(), getChoosenTower().getRange());
        shapeRenderer.end();
        batch.begin();


    }



}
