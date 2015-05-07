package edu.chl.proximity.Controllers;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.Viewport;
import edu.chl.proximity.Controllers.SubControllers.*;
import edu.chl.proximity.Models.BoardObject;
import edu.chl.proximity.Models.Map.Creeps.Creep;
import edu.chl.proximity.Models.Map.Maps.Map;
import edu.chl.proximity.Models.Map.Projectiles.Projectile;
import edu.chl.proximity.Models.Player.Spells.PersistentObject;
import edu.chl.proximity.Models.Map.Towers.Tower;
import edu.chl.proximity.Proximity;
import edu.chl.proximity.Utilities.PointCalculations;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * @author Johan Swanberg and Linda Evaldsson
 * @date 2015-04-02
 *
 * MainController controls all the SubControllers in the game. It updates the
 * controllers 60 times per second and also handles input that is delegated to
 * the SubControllers.
 *
 * 07/04 Modified by Johan Swanberg. Updated so it doesn't crash the program on run.
 * 21/04 Modified by Simon Gislén. Addded WaveController
 * 21/04 Modified by Hanna Römer.
 * 23/04 Modified by Simon Gislen Added PersistentObjectController
 * 23/04 Modified by Linda Evaldsson. Added unprojection to the mouseMoved-method.
 * 29/04 modified by Linda Evaldsson. Updated how the clicking sends signals to the controllers that handle clicks.
 * 07/05 modofied by Linda Evaldsson. Added key handling
 */
public class MainController implements InputProcessor{


    private CreepController creepController;
    private TowerController towerController;
    private ProjectileController projectileController;
    private BackgroundController backgroundController = new BackgroundController();
    private ControlPanelController controlPanelController;
    private WaveController waveController;
    private MapController mapController;
    private HandController handController;
    private PersistentObjectController persistentObjectController;
    private List<ClickHandler> clickHandlers = new ArrayList<ClickHandler>();
    private Map map;

    private Viewport viewport;
    private int tempCounter=0;
    private Proximity game;



    public MainController(Map map, Viewport v, Proximity game) {
        this.game = game;
        this.map = map;
        creepController = new CreepController(map);
        towerController = new TowerController(map);
        projectileController = new ProjectileController(map);
        controlPanelController = new ControlPanelController(map, game);
        waveController = new WaveController(map);
        mapController = new MapController(map);
        handController = new HandController(map);
        persistentObjectController = new PersistentObjectController(map);


        viewport=v;
        clickHandlers.add(controlPanelController);
        clickHandlers.add(mapController);
        //clickHandlers.add(buttonPanelController);
        clickHandlers.add(handController);
        //clickHandlers.add(propertiesPanelController);
    }

    public List<BoardObject> getControlPanels() {
        return controlPanelController.getControlPanels();
    }
/*
    public void setControlPanel(ControlPanel controlPanel) {
        this.controlPanel = controlPanel;
        controlPanelController.setControlPanel(controlPanel);
    }

    public void setButtonPanel(ButtonPanel buttonPanel){
        controlPanelController.setButtonPanel(buttonPanel);
    }
    public void setProfilePanel(ProfilePanel profilePanel){
        controlPanelController.setProfilePanel(profilePanel);
    }
    public void setSpellPanel(SpellPanel spellPanel){
        controlPanelController.setSpellPanel(spellPanel);
    }*/


    public void updateAllControllers() {
        if(game.getCurrentScreen().equals(Proximity.State.GAME)) {
            waveController.update();
            creepController.update();
            towerController.update();
            projectileController.update();
            persistentObjectController.update();
            backgroundController.update();
            controlPanelController.update();
            clearKillStacks();
            clearAddStacks();
        }

    }

    /**
     * Remove all objects marked for deletion this frame.
     */
    public void clearKillStacks() {
        Set<BoardObject> killStack = map.getRemoveStack();
        Iterator killIterator = killStack.iterator();

        List<Creep> creepList = map.getCreeps();
        List<Projectile> projectileList = map.getProjectiles();
        List<Tower> towerList = map.getTowers();
        List<PersistentObject> persistentObjects = map.getPersistentObjects();

        while (killIterator.hasNext()){
            BoardObject o = (BoardObject)killIterator.next();
            if(o instanceof Creep) {
                Creep creep = (Creep)o;
                if (creep != null) {
                    killIterator.remove();
                    creepList.remove(creep);
                }
            }

            if(o instanceof Projectile) {
                Projectile projectile = (Projectile)o;
                if (projectile != null) {
                    killIterator.remove();
                    projectileList.remove(projectile);
                }
            }
            if(o instanceof Tower) {
                Tower tower = (Tower)o;
                if (tower != null) {
                    killIterator.remove();
                    towerList.remove(tower);
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
     * Add all objects marked for adding this frame.
     */
    public void clearAddStacks() {
        Set<BoardObject> addStack = map.getAddStack();
        Iterator addIterator = addStack.iterator();

        List<Creep> creepList = map.getCreeps();
        List<Projectile> projectileList = map.getProjectiles();
        List<Tower> towerList = map.getTowers();
        List<PersistentObject> persistentObjectList = map.getPersistentObjects();

        while (addIterator.hasNext()){
            BoardObject o = (BoardObject)addIterator.next();
            if(o instanceof Creep) {
                Creep creep = (Creep)o;
                creepList.add(creep);
            }
            if(o instanceof Projectile) {
                Projectile projectile = (Projectile)o;
                projectileList.add(projectile);
            }
            if(o instanceof Tower) {
                Tower tower = (Tower)o;
                towerList.add(tower);
            }
            if(o instanceof PersistentObject) {
                PersistentObject persistentObject = (PersistentObject)o;
                persistentObjectList.add(persistentObject);
            }
            addIterator.remove();
        }

    }

    @Override
    public boolean keyDown (int keycode) {
        controlPanelController.keyDown(keycode);
        return false;
    }

    @Override
    public boolean keyUp (int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped (char character) {
        return false;
    }

    private int counter = 0;
    @Override
    public boolean touchDown (int x, int y, int pointer, int button) {


        //Calculates the real coordinates from the scaled coordinates
        Vector2 clickedPoint = viewport.unproject(new Vector2(x, y));


        //Test code to run spells. Commented out because it messed up tower placing.
        /*
        Spell effect = new WallOfStone();
        if (counter % 4 == 0){
             effect = new WallOfStone();

        }else if(counter % 4 == 1){
             effect = new ChainLightning();
            System.out.println("In MainController: Cycling through spells to create, lightning not working correctly.");
        }else if(counter % 4 == 2){
             effect = new FireField();
        }else if(counter % 4 == 3){
             effect = new FrostField();
        }
        counter++;
        effect.placeObject(clickedPoint);
        */

        //For creating paths during the developing state
        PointCalculations.createPathTool((int) clickedPoint.x, (int) clickedPoint.y);

        //Runs through the clickable controllers and informs them if their models is clicked
        for(ClickHandler controller : clickHandlers) {

            controller.touchDown(clickedPoint, pointer, button);

        }



        return true;
    }

    @Override
    public boolean touchUp (int x, int y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged (int x, int y, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved (int x, int y) {
        Vector2 clickedPoint = viewport.unproject(new Vector2(x, y));
        for(ClickHandler controller : clickHandlers) {
            controller.mouseMoved(clickedPoint);
        }
        return true;
    }

    @Override
    public boolean scrolled (int amount) {
        return false;
    }


}
