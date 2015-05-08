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
        controlPanelController = new ControlPanelController(map, game,viewport);
        waveController = new WaveController(map);
        mapController = new MapController(map);
        handController = new HandController(map);
        persistentObjectController = new PersistentObjectController(map);

        viewport=v;
        clickHandlers.add(controlPanelController);
        clickHandlers.add(handController);
    }

    public List<BoardObject> getControlPanels() {
        return controlPanelController.getControlPanels();
    }

    public void updateAllControllers() {
        if(game.getCurrentScreen().equals(Proximity.State.GAME)) {
            waveController.update();
            //creepController.update();
            //towerController.update();
            projectileController.update();
            persistentObjectController.update();
            backgroundController.update();
            controlPanelController.update();
            map.clearRemoveStack();
            map.clearAddStack();
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

        //For creating paths during the developing state
        PointCalculations.createPathTool((int) clickedPoint.x, (int) clickedPoint.y);

        //Runs through the clickable controllers and informs them if their models is clicked
        for(ClickHandler controller : clickHandlers) {

            controller.touchDown(clickedPoint, pointer, button);

        }
        if(!controlPanelController.modelsClicked(clickedPoint)) {
            mapController.touchDown(clickedPoint, pointer, button);
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
