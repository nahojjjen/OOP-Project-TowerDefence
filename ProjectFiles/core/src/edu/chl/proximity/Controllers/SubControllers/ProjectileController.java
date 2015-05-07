package edu.chl.proximity.Controllers.SubControllers;

import edu.chl.proximity.Models.Map.Creeps.Creep;
import edu.chl.proximity.Models.Utils.GameData;
import edu.chl.proximity.Models.Map.Maps.Map;
import edu.chl.proximity.Models.Map.Projectiles.Projectile;

import java.util.List;

/**
 * Created by Johan on 2015-04-11. Group work with Linda
 */
public class ProjectileController {

    private List<Projectile> projectiles;
    private List<Creep> creeps;
    private Map map;

    public ProjectileController(Map map){
        this.map = map;
        projectiles = map.getProjectiles();
        creeps = map.getCreeps();
    }

    public void update() {
        for (Projectile projectile:projectiles){
            projectile.reAngle();
            projectile.move();
            /*
            for (Creep creep : creeps){
                if(projectile.collidesWith(creep.getPosition(), 20)) {
                    projectile.collide();
                }
            }*/
            projectile.checkCollision();
            if (projectile.isOutsideView()){
                map.getRemoveStack().add(projectile);
            }
        }
    }
}
