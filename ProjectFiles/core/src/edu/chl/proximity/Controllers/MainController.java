package edu.chl.proximity.Controllers;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.Viewport;
import edu.chl.proximity.Controllers.GameStates.GameScreen;
import edu.chl.proximity.Controllers.SubControllers.*;
import edu.chl.proximity.Models.BoardObject;
import edu.chl.proximity.Models.Map.Maps.Map;
import edu.chl.proximity.Proximity;
import edu.chl.proximity.Utilities.PointCalculations;
import edu.chl.proximity.Utilities.ProximityVector;

import java.util.ArrayList;
import java.util.List;

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
 * 21/04 Modified by Hanna Romer.
 * 23/04 Modified by Simon Gislen Added PersistentObjectController
 * 23/04 Modified by Linda Evaldsson. Added unprojection to the mouseMoved-method.
 * 29/04 modified by Linda Evaldsson. Updated how the clicking sends signals to the controllers that handle clicks.
 * 07/05 modofied by Linda Evaldsson. Added key handling
 */
public class MainController implements InputProcessor{


    private ControlPanelController controlPanelController;
    private WaveController waveController;
    private MapController mapController;
    private HandController handController;
    private List<ClickHandler> clickHandlers = new ArrayList<ClickHandler>();
    private Map map;
    private Viewport viewport; //used for translating scaled click-position to model click position
    private Game game;



    public MainController(Map map, Viewport v, Game game) {
        this.game = game;
        this.map = map;
        controlPanelController = new ControlPanelController(map, game,viewport);
        waveController = new WaveController(map);
        mapController = new MapController(map, game, v);
        handController = new HandController(map);

        viewport=v;
        clickHandlers.add(controlPanelController);
        clickHandlers.add(handController);
    }

    public List<BoardObject> getControlPanels() {
        return controlPanelController.getControlPanels();
    }

    public BoardObject getWavePanel() {
        return waveController.getWavePanel();
    }

    public void updateAllControllers() {
        if(game.getScreen() instanceof GameScreen){            //game.getCurrentScreen().equals(Proximity.State.GAME)) {
            waveController.update();
            mapController.update();
            handController.update();
            controlPanelController.update();

            map.clearAddStack();
            map.clearRemoveStack();
        }
    }
    @Override
    public boolean keyDown (int keycode) {
        controlPanelController.keyDown(keycode);
        return false;
    }
    /**
     * Handles a click event in the game
     * @param x where the window was clicked in x coordinate
     * @param y where the window was clicked in y coordinate
     * @param pointer
     * @param button Which button was pressed
     * @return true, unless program crashes
     */
    @Override
    public boolean touchDown (int x, int y, int pointer, int button) {
        //Calculates the real coordinates from the scaled coordinates
        Vector2 clickedPointVector2 = viewport.unproject(new Vector2(x, y));
        ProximityVector clickedPoint = new ProximityVector(clickedPointVector2.x, clickedPointVector2.y);

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


    /**
     * Tells all the controllers that the mouse has moved, the controllers that handle mouse movement update accordingly
     * @param x where the window was hovered in x coordinate
     * @param y where the window was hovered in y coordinate
     * @return true
     */
    @Override
    public boolean mouseMoved (int x, int y) {
        Vector2 clickedPoint2 = viewport.unproject(new Vector2(x, y));
        ProximityVector draggedPoint = new ProximityVector(clickedPoint2.x, clickedPoint2.y);
        for(ClickHandler controller : clickHandlers) {
            controller.mouseMoved(draggedPoint);
        }
        return true;
    }


    ///////////////////////////////////////////
    ////////   Unused controller input   //////
    ///////////////////////////////////////////

    @Override
    public boolean scrolled (int amount) {
        return false;
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
    public boolean keyUp (int keycode) {
        return false;
    }
    @Override
    public boolean keyTyped (char character) {
        return false;
    }


}
