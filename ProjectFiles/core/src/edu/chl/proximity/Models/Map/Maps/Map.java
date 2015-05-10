package edu.chl.proximity.Models.Map.Maps;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
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
 * */
public abstract class Map {

    private String name;

    private int waveIndex;
    private Hand hand = new Hand();
    private Tower choosenTower;

    private PropertiesPanel propertiesPanel;

    private ArrayList<Creep> creeps = new ArrayList<Creep>();
    private ArrayList<PersistentObject> persistentObjects = new ArrayList<PersistentObject>();
    private ArrayList<Wave> waves = new ArrayList<Wave>();
    private ArrayList<Tower> towers = new ArrayList<Tower>();
    private ArrayList<Projectile> projectiles = new ArrayList<Projectile>();

    private Path path;
    private Background background;
    private Base base;

    private ParticleManager particleManager;

    private Set<BoardObject> removeStack = new HashSet<BoardObject>();
    private Set<BoardObject> addStack = new HashSet<BoardObject>();

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
     * Marks an object for deletion, the object will be removed for next frame
     * @param object What boardObject to remove
     */
    public void remove(BoardObject object) {
        removeStack.add(object);
    }

    /**
     * Mark an object for adding to the map, the object will be added for next frame
     * @param object what object to add for next frame
     */
    public void add(BoardObject object) {
        addStack.add(object);
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
        List<Creep> creepsWithinRange = new ArrayList<>();
        if (range <= 0 || position == null){return creepsWithinRange;}
        for (Creep creep : creeps) {
            if (PointCalculations.distanceBetweenNoSqrt(creep.getCenter(), position) < range * range) {
                creepsWithinRange.add(creep);
            }
        }
        return creepsWithinRange;
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
            projectile.checkCollision();
            if (projectile.isOutsideView()){
                remove(projectile);
            }
        }

    }

    public Path getPath(){return path;}
    public void setPath(Path newPath){ path = newPath;}
    public Background getBackground(){ return  background;}
    public Base getBase(){return base;}
    public void setBase(Base base){ this.base = base;}
    public void setChoosenTower(Tower tower){ choosenTower=tower;}
    public Tower getChoosenTower(){ return choosenTower;}

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
        Iterator killIterator = removeStack.iterator();

        while (killIterator.hasNext()){
            BoardObject o = (BoardObject)killIterator.next();
            if(o instanceof Creep) {
                Creep creep = (Creep)o;
                if (creep != null) {
                    killIterator.remove();
                    creeps.remove(creep);
                }
            }

            if(o instanceof Projectile) {
                Projectile projectile = (Projectile)o;
                if (projectile != null) {
                    killIterator.remove();
                    projectiles.remove(projectile);
                }
            }
            if(o instanceof Tower) {
                Tower tower = (Tower)o;
                if (tower != null) {
                    killIterator.remove();
                    towers.remove(tower);
                }
            }
            if(o instanceof PersistentObject) {
                PersistentObject persistentObject = (PersistentObject)o;
                if (persistentObject != null) {
                    killIterator.remove();
                    persistentObjects.remove(persistentObject);
                }
            }
        }

    }

    /**
     * Add all objects that have been marked for addition this frame to the map
     */
    public void clearAddStack() {

        Iterator addIterator = addStack.iterator();

        while (addIterator.hasNext()){
            BoardObject o = (BoardObject)addIterator.next();
            if(o instanceof Creep) {
                Creep creep = (Creep)o;
                creeps.add(creep);
            }
            if(o instanceof Projectile) {
                Projectile projectile = (Projectile)o;
                projectiles.add(projectile);
            }
            if(o instanceof Tower) {
                Tower tower = (Tower)o;
                towers.add(tower);
            }
            if(o instanceof PersistentObject) {
                PersistentObject persistentObject = (PersistentObject)o;
                persistentObjects.add(persistentObject);
            }
            addIterator.remove();
        }

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
