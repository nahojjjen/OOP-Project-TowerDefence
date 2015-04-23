package edu.chl.proximity.Controllers;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.Viewport;
import edu.chl.proximity.Controllers.SubControllers.*;
import edu.chl.proximity.Models.BoardObject;
import edu.chl.proximity.Models.ButtonsPanel.ButtonPanel;
import edu.chl.proximity.Models.ControlPanel.ControlPanel;
import edu.chl.proximity.Models.Creeps.Creep;
import edu.chl.proximity.Models.GameData;
import edu.chl.proximity.Models.Projectiles.Projectile;
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
 */
public class MainController implements InputProcessor{


    private CreepController creepController = new CreepController();
    private TowerController towerController = new TowerController();
    private ProjectileController projectileController = new ProjectileController();
    private BackgroundController backgroundController = new BackgroundController();
    private ControlPanelController controlPanelController = new ControlPanelController();
    private WaveController waveController = new WaveController();
    private MapController mapController = new MapController();
    private HandController handController = new HandController();
    private ButtonPanelController buttonPanelController=new ButtonPanelController();
    private PersistentObjectController persistentObjectController = new PersistentObjectController();
    private List<ClickHandler> clickHandlers = new ArrayList<ClickHandler>();

    private Viewport viewport;
    ControlPanel controlPanel;
    private int tempCounter=0;

    public MainController(Viewport v) {
        viewport=v;
        clickHandlers.add(controlPanelController);
        clickHandlers.add(mapController);
        clickHandlers.add(buttonPanelController);
        clickHandlers.add(handController);
    }

    public void setControlPanel(ControlPanel controlPanel) {
        this.controlPanel = controlPanel;
        controlPanelController.setControlPanel(controlPanel);
    }

    public void setButtonPanel(ButtonPanel buttonPanel){
        buttonPanelController.setButtonPanel(buttonPanel);
    }

    public void updateAllControllers() {
        waveController.update();
        creepController.update();
        towerController.update();
        projectileController.update();
        persistentObjectController.update();
        backgroundController.update();
        controlPanelController.update();
        clearKillStacks();

    }

    /*
    public void setControlPanel(ControlPanel controlPanel) {
        controlPanelController.setControlPanel(controlPanel);
    }*/

    /**
     * Remove all objects marked for deletion this frame.
     */

    public void clearKillStacks() {
        Set<BoardObject> killStack = GameData.getInstance().getMap().getRemoveStack();
        Iterator killIterator = killStack.iterator();

        List<Creep> creepList = GameData.getInstance().getMap().getCreeps();
        List<Projectile> projectileList = GameData.getInstance().getMap().getProjectiles();

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
        }

    }



    @Override
    public boolean keyDown (int keycode) {
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

    @Override
    public boolean touchDown (int x, int y, int pointer, int button) {

        //Calculates the real coordinates from the scaled coordinates
        Vector2 clickedPoint = viewport.unproject(new Vector2(x, y));

        //For creating paths during the developing state
        PointCalculations.createPathTool((int) clickedPoint.x, (int) clickedPoint.y);

        //Runs through the clickable controllers and informs them if their models is clicked
        for(ClickHandler controller : clickHandlers) {
            if(controller.getModel().containsPoint(clickedPoint))
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
            if(controller.getModel().containsPoint(clickedPoint))
                controller.mouseMoved(clickedPoint);
        }
        return true;
    }

    @Override
    public boolean scrolled (int amount) {
        return false;
    }


}
