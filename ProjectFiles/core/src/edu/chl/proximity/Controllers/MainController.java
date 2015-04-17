package edu.chl.proximity.Controllers;

import com.badlogic.gdx.Game;
import edu.chl.proximity.Controllers.SubControllers.*;
import edu.chl.proximity.Models.ControlPanel;
import edu.chl.proximity.Models.Creeps.Creep;
import edu.chl.proximity.Models.GameData;
import edu.chl.proximity.Models.Projectiles.Projectile;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Created by Johan on 2015-04-02. Group work with Linda
 *
 */
public class MainController {


    private CreepController creepController = new CreepController();
    private TowerController towerController = new TowerController();
    private ProjectileController projectileController = new ProjectileController();
    private BackgroundController backgroundController = new BackgroundController();
    private ControlPanelController controlPanelController = new ControlPanelController();


    public MainController() {}

    public void updateAllControllers(){
        creepController.update();
        towerController.update();
        projectileController.update();
        backgroundController.update();
        controlPanelController.update();
        clearKillStacks();

    }

    public void setControlPanel(ControlPanel controlPanel) {
        controlPanelController.setControlPanel(controlPanel);
    }

    /**
     * Remove all objects marked for deletion this frame.
     */
    public void clearKillStacks(){
        clearProjectileStack();
        clearCreepStack();
    }

    /**
     * clear all creeps that have been marked for deletion this frame
     */
    public void clearCreepStack(){
        Set<Creep> creepKillSet = GameData.getInstance().getMap().getCreepKillStack();
        Iterator creepIterator = creepKillSet.iterator();
        List<Creep> creepList = GameData.getInstance().getMap().getCreeps();

        while (creepIterator.hasNext()){
            Creep creep = (Creep) creepIterator.next();
            if (creep != null){
                creepIterator.remove();
                creepList.remove(creep);
            }
        }
    }

    /**
     * clear all projectiles that have been marked for deletion this frame
     */
    public void clearProjectileStack(){
        Set<Projectile> projectiles = GameData.getInstance().getMap().getProjectileKillStack();
        Iterator projectileIterator = projectiles.iterator();
        List<Projectile> projectileList = GameData.getInstance().getMap().getProjectiles();

        while (projectileIterator.hasNext()){
            Projectile projectile = (Projectile) projectileIterator.next();
            if (projectile != null){
                projectileIterator.remove();
                projectileList.remove(projectile);
            }
        }

    }

}
