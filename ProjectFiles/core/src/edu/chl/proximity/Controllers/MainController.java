package edu.chl.proximity.Controllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sun.prism.image.ViewPort;
import edu.chl.proximity.Controllers.SubControllers.*;
import edu.chl.proximity.Models.BoardObject;
import edu.chl.proximity.Models.ControlPanel.ControlPanel;
import edu.chl.proximity.Models.ControlPanel.ControlPanelTower;
import edu.chl.proximity.Models.Creeps.Creep;
import edu.chl.proximity.Models.GameData;
import edu.chl.proximity.Models.Projectiles.Projectile;
import edu.chl.proximity.Models.Towers.BulletTower;
import edu.chl.proximity.Models.Towers.MissileTower;
import edu.chl.proximity.Models.Towers.SlowTower;
import edu.chl.proximity.Utilities.PointCalculations;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Created by Johan on 2015-04-02. Group work with Linda
 *
 * ----
 * Revised by Simon Gislen 21/04
 * Revised by Hanna Römer 21/04
 */
public class MainController implements InputProcessor{


    private CreepController creepController = new CreepController();
    private TowerController towerController = new TowerController();
    private ProjectileController projectileController = new ProjectileController();
    private BackgroundController backgroundController = new BackgroundController();
    private ControlPanelController controlPanelController = new ControlPanelController();
    private WaveController waveController = new WaveController();

    private Viewport viewport;
    ControlPanel controlPanel;
    private int tempCounter=0;

    public MainController(Viewport v) {viewport=v;}

    public void setControlPanel(ControlPanel controlPanel) {
        this.controlPanel = controlPanel;
        controlPanelController.setControlPanel(controlPanel);
    }

    public void updateAllControllers() {
        waveController.update();
        creepController.update();
        towerController.update();
        projectileController.update();
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

            System.out.println(o.getClass());
            if(o instanceof Projectile) {
                Projectile projectile = (Projectile)o;
                if (projectile != null) {
                    killIterator.remove();
                    projectileList.remove(projectile);
                }
            }
        }

    }

    /**
     * clear all creeps that have been marked for deletion this frame
     */
    public void clearCreepStack(){
        /*Set<Creep> creepKillSet = GameData.getInstance().getMap().getCreepKillStack();
        Iterator creepIterator = creepKillSet.iterator();
        List<Creep> creepList = GameData.getInstance().getMap().getCreeps();

        while (creepIterator.hasNext()){
            Creep creep = (Creep) creepIterator.next();
            if (creep != null){
                creepIterator.remove();
                creepList.remove(creep);
            }
        }*/
    }

    /**
     * clear all projectiles that have been marked for deletion this frame
     */
    public void clearProjectileStack(){
        /*Set<Projectile> projectiles = GameData.getInstance().getMap().getProjectileKillStack();
        Iterator projectileIterator = projectiles.iterator();
        List<Projectile> projectileList = GameData.getInstance().getMap().getProjectiles();

        while (projectileIterator.hasNext()){
            Projectile projectile = (Projectile) projectileIterator.next();
            if (projectile != null){
                projectileIterator.remove();
                projectileList.remove(projectile);
            }
        }*/

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
        Vector2 tmp = viewport.unproject(new Vector2(x, y));
        //System.out.println("Mouse x: " + (int)tmp.x + " Mouse y: " + (int)tmp.y);
        PointCalculations.createPathTool((int) tmp.x, (int) tmp.y);


        //Todo: Move this handling to separate class
        //Checks if the Control Panel is clicked
        if(tmp.x > Gdx.graphics.getWidth() - controlPanel.getWidth()){
            System.out.println("MainController: ControlPanel is clicked");

            //Checks if a tower on the ControlPanel is clicked
            ControlPanelTower cpTower = controlPanel.getTowerOnPosition(tmp);
            if(cpTower != null) {
                System.out.println("MainController: Clicked on Tower");
                cpTower.getTower();
            }

        }

        tempCounter++;
        if (tempCounter % 3== 0){
            GameData.getInstance().getMap().addTower(new SlowTower(tmp));
        }else if(tempCounter%3 ==1){

            GameData.getInstance().getMap().addTower(new MissileTower(tmp));
        }else{
            GameData.getInstance().getMap().addTower(new BulletTower(tmp));
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
        //ystem.out.println("Test");
        return true;
    }

    @Override
    public boolean scrolled (int amount) {
        return false;
    }


}
