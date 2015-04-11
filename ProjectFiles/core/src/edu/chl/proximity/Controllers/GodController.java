package edu.chl.proximity.Controllers;

import com.badlogic.gdx.graphics.g2d.ParticleEffectPool;
import com.badlogic.gdx.math.Vector2;
import edu.chl.proximity.Controllers.SubControllers.BackgroundController;
import edu.chl.proximity.Controllers.SubControllers.CreepController;
import edu.chl.proximity.Controllers.SubControllers.ProjectileController;
import edu.chl.proximity.Controllers.SubControllers.TowerController;
import edu.chl.proximity.Models.Creeps.Creep;
import edu.chl.proximity.Models.Maps.Map;
import edu.chl.proximity.Models.Projectiles.Projectile;
import edu.chl.proximity.Models.Towers.Tower;
import edu.chl.proximity.Utilities.PointCalculations;

import java.awt.*;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Johan on 2015-04-02. Group work with Linda
 */
public class GodController {


    private CreepController creepController = new CreepController();
    private TowerController towerController = new TowerController();
    private ProjectileController projectileController = new ProjectileController();
    private BackgroundController backgroundController = new BackgroundController();


    public GodController() {}

    public void updateAllControllers(){
        creepController.update();
        towerController.update();
        projectileController.update();
        backgroundController.update();
    }





}
