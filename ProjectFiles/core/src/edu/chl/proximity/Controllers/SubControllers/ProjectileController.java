package edu.chl.proximity.Controllers.SubControllers;

import edu.chl.proximity.Models.Creeps.Creep;
import edu.chl.proximity.Models.GameData;
import edu.chl.proximity.Models.Maps.Map;
import edu.chl.proximity.Models.Projectiles.Projectile;

import java.util.Iterator;
import java.util.List;

/**
 * Created by Johan on 2015-04-11. Group work with Linda
 */
public class ProjectileController {

    private List<Projectile> projectiles;
    private List<Creep> creeps;
    private Map map;

    public ProjectileController(){
        map = GameData.getInstance().getMap();
        projectiles = map.getProjectiles();
        creeps = map.getCreeps();
    }

    public void update() {
        for (Projectile projectile:projectiles){
            projectile.reAngle();
            projectile.move();

            for (Creep creep : creeps){
                if(projectile.collidesWith(creep.getPosition(), 20)) {
                    projectile.doCollisionEffect(creep);
                }
            }
            if (projectile.isOutsideView()){
                map.getProjectileKillStack().add(projectile);
            }
        }
    }
}
